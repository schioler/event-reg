package dk.schioler.event.main.test.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dk.schioler.configuration.EventBaseConfiguration;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.AbstractEntityName;
import dk.schioler.event.base.entity.AbstractEntityParentChild;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.xml.EventTypeXMLHelper;
import dk.schioler.event.base.xml.EventXMLException;
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
import dk.schioler.shared.security.search.LoginSearchCriteria;
import dk.schioler.shared.security.search.PasswordSearchCriteria;

public class EstablishBasisEventTypesAndTmplsMain {

   static {
      System.getProperties().setProperty("event.env", "dev1");
   }

   private static Logger logger = LoggerFactory.getLogger(EstablishBasisEventTypesAndTmplsMain.class);

   @Autowired
   private static EventTypeDAO eventTypeDAO;

   @Autowired
   private static EventTemplateDAO eventTemplateDAO;

   @Autowired
   private static UserProfileDAO userProfileDAO;

   @Autowired
   private static LoginDAO loginDAO;

   @Autowired
   private static Encrypter encrypter;

   @Autowired
   private static PasswordDAO passwordDAO;

   static AnnotationConfigApplicationContext ctx;

   static String executionRun = "TestRun1";

//	static String[] cfgFiles = new String[] { "src/main/resources/ApplicationContext.xml" };

   public EstablishBasisEventTypesAndTmplsMain() {

   }

   private static String eventFile = "src/main/data/event-base-data.xml";

   public static void main(String[] args) {
      try {

         ctx = new AnnotationConfigApplicationContext(EventBaseConfiguration.class);

         EstablishBasisEventTypesAndTmplsMain self = new EstablishBasisEventTypesAndTmplsMain();  

         String loginToken = "U1@b";

         loginDAO = ctx.getBean(LoginDAO.class);
         passwordDAO = ctx.getBean(PasswordDAO.class);
         encrypter = ctx.getBean(Encrypter.class);

         eventTypeDAO = ctx.getBean(EventTypeDAO.class);

         eventTemplateDAO = ctx.getBean(EventTemplateDAO.class);

         //
         LoginSearchCriteria lsc = new LoginSearchCriteria();
         lsc.setToken(loginToken);
         List<Login> list = loginDAO.retrieve(lsc, 0);
         Login loginLogin = list.get(0);

         if (loginLogin == null) {
            ctx.close();
            return;
         }

         PasswordSearchCriteria psc = new PasswordSearchCriteria();
         psc.addToLoginIdList(loginLogin.getId());
         List<Password> pwds = passwordDAO.retrieve(psc, 0);
         Password password = pwds.get(0);
         String enc = encrypter.encrypt(loginToken);
         if (!enc.equals(password.getPwd())) {
            logger.error("passwds did not match");
            ctx.close();
            return;
         }
         
         Login ownerLogin = null;
         List<Login> loginTreeTopDown = loginDAO.getLoginTreeTopDown(loginLogin.getId());
         for (Login login2 : loginTreeTopDown) {
            if (ROLE.OWNER.equals(login2.getRole())) {
               ownerLogin = login2;
               break;
            }
         }
         logger.debug("ownwer="+ ownerLogin);

         List<AbstractEntityParentChild> eventTypesAndTemplates = self.readEventTypesAndTemplates(eventFile);

         List<AbstractEntityParentChild> persistedEventTypes = self.persistEventTypes(eventTypesAndTemplates, ownerLogin);

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
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
//			System.exit(1);
      }
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

   public Login establishLogin(Integer parentId, String executionRun, ROLE role, String token, String password) {

      UserProfile up = new UserProfileImpl();
      up.setFirstName(executionRun + "-firstname");
      up.setLastName(executionRun + "-Lastname");
      up.setPrimaryEmail(role.toString() + "@" + executionRun.toLowerCase() + ".dk");
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

         List<AbstractEntityParentChild> children = eventType.getChildren();

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
