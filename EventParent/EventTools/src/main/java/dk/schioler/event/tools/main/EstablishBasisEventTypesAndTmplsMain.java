package dk.schioler.event.tools.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import dk.schioler.event.base.EventBaseException;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.AbstractEntityName;
import dk.schioler.event.base.entity.AbstractEntityParentChild;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.xml.EventTypeXMLHelper;
import dk.schioler.event.base.xml.EventXMLException;
import dk.schioler.event.tools.configuration.EventToolsConfiguration;
import dk.schioler.shared.security.criteria.LoginSearchCriteria;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.dao.PasswordDAO;
import dk.schioler.shared.security.dao.UserProfileDAO;
import dk.schioler.shared.security.encrypt.Encrypter;
import dk.schioler.shared.security.entity.Login;
import dk.schioler.shared.security.entity.Password;
import dk.schioler.shared.security.entity.ROLE;
import dk.schioler.shared.security.entity.UserProfile;
import dk.schioler.shared.security.entity.impl.LoginImpl;
import dk.schioler.shared.security.entity.impl.PasswordImpl;
import dk.schioler.shared.security.entity.impl.UserProfileImpl;

@Component
public class EstablishBasisEventTypesAndTmplsMain {

   static {
      System.getProperties().setProperty("event.env", "dev");
      
      // CONFIGURE RUN
      EVENT_FILE = "src/main/data/event-base-data.xml";
      IS_DRYRUN = false ;
      USE_EXISTING_LOGIN = true;
      LOGIN_TOKEN = "1";
      PASSWORD = "b";
      DOMAIN = "mydomain";

   }

   private static Logger logger = LoggerFactory.getLogger(EstablishBasisEventTypesAndTmplsMain.class);

   @Autowired
   private EventTypeDAO eventTypeDAO;

   @Autowired
   private EventTemplateDAO eventTemplateDAO;

   @Autowired
   private UserProfileDAO userProfileDAO;

   @Autowired
   private LoginDAO loginDAO;

   @Autowired
   private Encrypter encrypter;

   @Autowired
   private PasswordDAO passwordDAO;

   @Autowired
   private JdbcTransactionManager txMgr;

   static AnnotationConfigApplicationContext ctx;

   static String executionRun = "TestRun1";

   public EstablishBasisEventTypesAndTmplsMain() {
      logger.debug("Constructor called");
   }

//   static {
//      try {
//         loginDAO.setDataSource(txMgr.getDataSource());
//         passwordDAO.setDataSource(txMgr.getDataSource());
//         userProfileDAO.setDataSource(txMgr.getDataSource());
//         eventTemplateDAO.setDataSource(txMgr.getDataSource());
//         eventTypeDAO.setDataSource(txMgr.getDataSource());
//      } catch (Exception e) {
//         System.err.println(e.getMessage());
//      }
//   }

   private static String EVENT_FILE;
   private static boolean IS_DRYRUN;
   private static boolean USE_EXISTING_LOGIN;
   private static String LOGIN_TOKEN;
   private static String PASSWORD;
   private static String DOMAIN;

   public static void main(String[] args) {

      
      try {
         ctx = new AnnotationConfigApplicationContext(EventToolsConfiguration.class);

         EstablishBasisEventTypesAndTmplsMain self = ctx.getBean(EstablishBasisEventTypesAndTmplsMain.class);
         self.doRun();
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
      }
   }

   public void doRun() {
      // Read inputFile first, to make sure there's something to work with
      List<AbstractEntityParentChild> eventTypesAndTemplates = readEventTypesAndTemplates(EVENT_FILE);
      logger.debug("found in file=");
      if (eventTypesAndTemplates != null) {
         for (AbstractEntityParentChild abstractEntityParentChild : eventTypesAndTemplates) {
            logger.debug("type/template=" + abstractEntityParentChild);
         }
      }
      
            
      DefaultTransactionDefinition trDef = new DefaultTransactionDefinition();
      trDef.setName("UCExecution");
      TransactionStatus transaction = txMgr.getTransaction(trDef);

      try {

         Login ownerLogin = null;
         if (USE_EXISTING_LOGIN) {
            ownerLogin = establishExistingOwnerLogin(LOGIN_TOKEN);
         } else {
            ownerLogin = establishNewOwnerLogin(DOMAIN, LOGIN_TOKEN, PASSWORD);
         }

          
         logger.debug("will persist eventTypes:");
         List<AbstractEntityParentChild> persistedEventTypes = persistEventTypes(eventTypesAndTemplates, ownerLogin);

         logger.debug("will persist templates");
         for (AbstractEntityName absE : persistedEventTypes) {
            EventType eType = (EventType) absE;
            logger.debug("etype=" + eType);
            List<AbstractEntityParentChild> children = eType.getChildren();
            logger.debug("children=" + children.size());
            for (AbstractEntityParentChild abstractEntity : children) {
               EventTemplate tmpl = (EventTemplate) abstractEntity;
               tmpl.setLoginId(ownerLogin.getId());
               logger.debug("CHILD=" + tmpl);
               eventTemplateDAO.insert(tmpl);
            }
         }
         if (IS_DRYRUN) {
            txMgr.rollback(transaction);
         } else {
            txMgr.commit(transaction);
         }
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
      }

   }

   public Login establishNewOwnerLogin(String domain, String loginToken, String password) {
      Login rootLogin = lookupRootLogin();
      Login ownerLogin = establishNewLogin(rootLogin.getId(), domain, ROLE.OWNER, loginToken, password);
      return ownerLogin;
   }

   public Login establishExistingOwnerLogin(String loginToken) {
      LoginSearchCriteria lsc = new LoginSearchCriteria();
      lsc.setToken(loginToken);
      lsc.setEndTS(null);

      List<Login> list = loginDAO.retrieve(lsc, 0);
      if (list == null) {
         logger.error("On loginToken=" + loginToken + ": received list==null");
         throw new EventBaseException("On loginToken=" + loginToken + ": received list==null");

      } else {
         if (list.size() == 0) {
            logger.error("On loginToken=" + loginToken + ": found count logins == 0.  Bad state");
            throw new EventBaseException("On loginToken=" + loginToken + ": found count logins == 0.  Bad state");

         } else if (list.size() > 1) {
            logger.error("On loginToken=" + loginToken + ": found count logins > 1.  Bad state");
            throw new EventBaseException("On loginToken=" + loginToken + ": found count logins > 1.  Bad state");
         }
      }

      Login login = list.get(0);

//      if (login == null) {
//         ctx.close();
//         return;
//      }
      Login ownerLogin = null;
      List<Login> loginTreeTopDown = loginDAO.getLoginTreeTopDown(login.getId());
      for (Login login2 : loginTreeTopDown) {
         if (ROLE.OWNER.equals(login2.getRole())) {
            ownerLogin = login2;
            break;
         }
      }
      logger.debug("owner=" + ownerLogin);
      return ownerLogin;
   }

   public Login lookupRootLogin() {
      LoginSearchCriteria lsc = new LoginSearchCriteria();
      lsc.addToRoleList(ROLE.ROOT);

      List<Login> list = loginDAO.retrieve(lsc, 0);
      logger.debug("looked up;" + list);

      Login retVal = null;
      if (list != null && list.size() == 1) {
         retVal = list.get(0);
      }

      return retVal;

   }

   public Login establishNewLogin(Integer parentId, String domain, ROLE role, String token, String password) {

      UserProfile up = new UserProfileImpl();
      up.setFirstName("firstname@" + domain);
      up.setLastName("Lastname@" + domain);
      up.setPrimaryEmail(role.toString() + "@" + domain.toLowerCase() + ".dk");
      up.setStartTS(LocalDateTime.now());
      up = userProfileDAO.insert(up);

      Login loginInst = new LoginImpl(null, LocalDateTime.now(), role, token);
      loginInst.setUserProfile(up);
      loginInst.setParentId(parentId);
      loginInst = loginDAO.insert(loginInst);

      Password pwd = new PasswordImpl();
      pwd.setStartTS(LocalDateTime.now());
      pwd.setLoginId(loginInst.getId());
      pwd.setPwd(encrypter.encrypt(password));
      pwd = passwordDAO.insert(pwd);
      loginInst.addPassword(pwd);

      return loginInst;
   }

   public List<AbstractEntityParentChild> persistEventTypes(List<AbstractEntityParentChild> types, Login login) {
      List<AbstractEntityParentChild> list = new ArrayList<AbstractEntityParentChild>();
      for (AbstractEntityParentChild eventType : types) {
         EventType et = (EventType) eventType;
         et.setLoginId(login.getId());
         EventType typeInserted = eventTypeDAO.insert(et);
         list.add(typeInserted);

//         List<AbstractEntityParentChild> children = eventType.getChildren();

      }
      return list;
   }

//   public void persistEventTypesAndTemplates(List<AbstractEntity> types, Login login) {
//      for (AbstractEntity abstrEventType : types) {
//         EventType eventType = (EventType) abstrEventType;
//         eventType.setLoginId(login.getId());
//         EventType typeInserted = eventTypeDAO.insert(eventType);
//         List<AbstractEntity> templates = eventType.getChildren();
//         for (AbstractEntity e : templates) {
//            EventTemplate tmpl = (EventTemplate) e;
//            e.setParentId(typeInserted.getId());
//            e.setLoginId(login.getId());
//            eventTemplateDAO.insert(tmpl);
//         }
//      }
//   }

   public List<AbstractEntityParentChild> readEventTypesAndTemplates(String fileName) {
      try {
         FileInputStream fis = new FileInputStream(new File(fileName));
         EventTypeXMLHelper xmlHelper = new EventTypeXMLHelper();
         List<AbstractEntityParentChild> eventTypes = xmlHelper.buildEventTypesFromXML(fis);
         return eventTypes;
      } catch (IOException e) {
         throw new EventXMLException(e.getMessage(), e);
      }
   }
}
