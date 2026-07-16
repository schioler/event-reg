package dk.schioler.event.web2.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dk.schioler.event.web2.common.WebLogin;

@Controller
public class AuthorisationController extends AbstractController {

   public static final String IDX = "/";

//   public String showLogin( Model model, Map<String, String> params) {
   @RequestMapping(IDX)
   public String showLogin( Model model, Map<String, String> params) {
//      LocalDateTime created 
     logger.debug("showLogin"); 
      WebLogin login = new WebLogin(null, null,false);
     
      model.addAttribute("login", login);
      return "login";
   }

   @RequestMapping("index.jsp")
   
   public String showLoginJsp( Model model, Map<String, String> params) {
//      LocalDateTime created 
//     logger.debug("showLogin"); 
//      WebLogin login = new WebLogin(null, null,false);
//     
//      model.addAttribute("login", login);
      return "login.jsp";

   }
}
