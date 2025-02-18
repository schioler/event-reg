package dk.schioler.event.base.configuration;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.dao.PasswordDAO;
import dk.schioler.shared.security.dao.UserProfileDAO;
import dk.schioler.shared.security.entity.Login;
import dk.schioler.shared.security.entity.LoginVisitor;
import dk.schioler.shared.security.entity.ROLE;
import dk.schioler.shared.security.entity.RoleUtil;
import dk.schioler.shared.security.entity.UserProfile;
import dk.schioler.shared.security.entity.impl.LoginImpl;
import dk.schioler.shared.security.entity.impl.UserProfileImpl;

@Component
public class TestUserSetupUtil {

   @Autowired
   UserProfileDAO userProfileDAO;

   @Autowired
   LoginDAO loginDAO;

   @Autowired
   PasswordDAO passwordDAO;

   private Login ownerLogin;

   private String randomString = RandomStringUtils.random(5, "ABCDEFGHIJKLM");

   LoginVisitor insertVisitor = new LoginVisitor() {

      @Override
      public boolean visit(Login parent) {

         return false;
      }
   };

   public Login createLoginTree(int count) {
      Login rootLogin = loginDAO.getRootLogin();

      UserProfile up = createUserProfile();

//      String rand = RandomStringUtils.random(5, "ABCDEFGHIJKLM");
//
//      ownerLogin = new LoginImpl();
//      ownerLogin.setToken(randomString + "-" + rand);
//      ownerLogin.setRole(ROLE.OWNER);
//      ownerLogin.setUserProfileId(up.getId());
//      ownerLogin.setParentId(rootLogin.getId());
//      ownerLogin = loginDAO.insert(ownerLogin);

      createChildLogins(rootLogin, ROLE.OWNER, count);

      return ownerLogin;
   }

   public Login createChildLogins(Login parentLogin, ROLE role, int count) {
      int c = count;
      if (ROLE.OWNER.equals(role)) {
         c = 1;
      }
      for (int i = 0; i < c; i++) {
         Login l = new LoginImpl();
         l.setParent(parentLogin);
         l.setRole(role);
         l.setToken(randomString + "-" + RandomStringUtils.random(2, "ABCDEFGHIJKLM"));
         l.setStartTS(LocalDateTime.now());
         l = loginDAO.insert(l);

         ROLE role2 = RoleUtil.getRole(role.order + 1);
         if (role2 != null) {
            createChildLogins(l, role2, count);
         }
      }

      return parentLogin;
   }

   public UserProfile createUserProfile() {
      UserProfile up = new UserProfileImpl();
      up.setFirstName("first-" + randomString);
      up.setLastName("last" + randomString);
      up.setPrimaryEmail(randomString + "@test.dk");
      up = userProfileDAO.insert(up);
      return up;
   }
//   
//   up = userProfileDAO.insert(up);
//   
//   Login login = new LoginImpl();
//   login.setUserProfileId(up.getId());
//   login.setParentId(parent.getId());
//   login.setStartTS(LocalDateTime.now());
}
