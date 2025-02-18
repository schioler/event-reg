package dk.schioler.event.main.test.data;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dk.schioler.configuration.EventBaseConfiguration;
import dk.schioler.event.base.dao.impl.SQLConstructs;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.dao.PasswordDAO;
import dk.schioler.shared.security.encrypt.Encrypter;
import dk.schioler.shared.security.entity.Login;
import dk.schioler.shared.security.entity.Password;
import dk.schioler.shared.security.entity.ROLE;
import dk.schioler.shared.security.entity.impl.PasswordImpl;
import dk.schioler.shared.security.search.AbstractSearchCriteria.DateTimeCriteria;
import dk.schioler.shared.security.search.AbstractSearchCriteria.OPERATOR;
import dk.schioler.shared.security.search.LoginSearchCriteria;
import dk.schioler.shared.security.search.PasswordSearchCriteria;

public class ChangePwdMain implements SQLConstructs {

   static {
      System.getProperties().setProperty("event.env", "dev1");
   }

   private static Logger logger = LoggerFactory.getLogger(ChangePwdMain.class);

   protected static ApplicationContext ctx;

   public static void main(String[] args) {
      try {
         ctx = new AnnotationConfigApplicationContext(EventBaseConfiguration.class);

         loginDAO = ctx.getBean(LoginDAO.class);

         passwordDAO = ctx.getBean(PasswordDAO.class);

         encrypter = ctx.getBean(Encrypter.class);

         ChangePwdMain self = new ChangePwdMain();

         String token = "b";

         LoginSearchCriteria lsc = new LoginSearchCriteria();
//         lsc.setToken(token);
//         lsc.addToRoleList(ROLE.USER);
         lsc.addToIdList(7);
         List<Login> list = loginDAO.retrieve(lsc, 0);
         if (list.size() == 1) {
            Login usr1login = list.get(0);
            PasswordSearchCriteria psc = new PasswordSearchCriteria();
            psc.addToLoginIdList(usr1login.getId());
            DateTimeCriteria dtc = new DateTimeCriteria();
            dtc.setOperator(OPERATOR.IS_NULL);
            psc.setEndTS(dtc);
            List<Password> list2 = passwordDAO.retrieve(psc, 0);
            for (Password password : list2) {
               if (password.getEndTS() == null) {
                  password.setEndTS(LocalDateTime.now());
                  passwordDAO.update(password);
               }
            }
            Password pw = new PasswordImpl();
            pw.setStartTS(LocalDateTime.now());
            pw.setLoginId(usr1login.getId());
            pw.setPwd(encrypter.encrypt(token));
            Password insert = passwordDAO.insert(pw);

         }

      } catch (Exception e) {
         logger.error(e.getMessage(), e);
      }
   }

   /*********************************************/

   static private LoginDAO loginDAO;

   static private PasswordDAO passwordDAO;

   static private Encrypter encrypter;

}
