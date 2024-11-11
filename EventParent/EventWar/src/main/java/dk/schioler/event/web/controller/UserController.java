package dk.schioler.event.web.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.encrypt.Encrypter;
import dk.schioler.event.web.WebLogin;
import dk.schioler.secure.dao.LoginDAO;
import dk.schioler.secure.dao.PasswordDAO;
import dk.schioler.secure.entity.Login;
import dk.schioler.secure.entity.Password;
import dk.schioler.secure.entity.impl.PasswordImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController extends AbstractController {

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private PasswordDAO passwordDAO;

	@Autowired
	private Encrypter encrypter;

	Logger logger = LoggerFactory.getLogger(getClass());

	public UserController() {
		super();

	}

	@RequestMapping(value = "public/user-authenticate.do", method = RequestMethod.POST)
	public String userAuthenticate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug("user-authenticate.dk: params=" + params);

		String retVal = "redirect:public/login.jsp";

		HttpSession session = request.getSession();

		String username = (String) params.get(LOGIN);
		if (StringUtils.isNotBlank(username)) {
			Login login = loginDAO.getLogin(username);
			logger.debug("Found login=" + login);
			if (login != null) {

				Password lookedUpPwd = null;
				String password = (String) params.get(PASSWORD);
				if (StringUtils.isNotBlank(password)) {
					logger.debug("looking up pwd, login.id=" + login.getId());
					List<Password> passwordList = passwordDAO.getPassword(login.getId(), false);
					logger.debug("pwdList=" + passwordList);
					if (passwordList != null) {
						if (passwordList.size() == 1) {
							lookedUpPwd = passwordList.get(0);
							logger.debug("passwd found=" + lookedUpPwd.getPwd());
							String encrypted = encrypter.encrypt(password);
							logger.debug("received pwd=" + encrypted);
							if (encrypted.equals(lookedUpPwd.getPwd())) {
								retVal = FAVORITES_SHOW; 
//										"redirect:../event-show.do";
								WebLogin wl = new WebLogin(login, LocalDateTime.now(), true);
								session.setAttribute(SES_AUTHENTICATED_USER, wl);
							}
						} else {
							retVal = "redirect:./login.jsp";
						}
					} else {
						retVal = "redirect:./login.jsp";
					}
				} else {
					retVal = "redirect:./login.jsp";
				}
			} else {
				retVal = "redirect:./login.jsp";
			}
		} else {
			retVal = "redirect:./login.jsp";

		}

		logger.debug("returns:" + retVal);
		return retVal;
	}

	@RequestMapping(value = "public/set-new-password.do", method = RequestMethod.POST)
	public String userSetNewPassword(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug("set-new-password.dk: params=" + params);

		String retVal = "redirect:login.jsp";
		String loginId = (String) params.get(LOGIN);
		String password1 = (String) params.get(PASSWORD + 1);
		String password2 = (String) params.get(PASSWORD + 2);

		if (StringUtils.isNotBlank(loginId)) {
			Login login = loginDAO.getLogin(loginId);
			logger.debug("login" + login);
			if (login != null) {
				logger.debug("login found=" + login);
				if (password1 != null && password2 != null) {
					logger.debug("provided pwds: 1=" + password1 + ", 2=" + password2);
					if (password1.equals(password2)) {
						logger.debug("1+2 are equal");
						Password lookedUpPwd = null;

						logger.debug("will cancel current pwd");

						List<Password> passwordList = passwordDAO.getPassword(login.getId(), false);
						logger.debug("pwdLIst=" + passwordList);
						if (passwordList != null) {

							if (passwordList.size() == 1) {
								logger.debug("will update current pwd");
								lookedUpPwd = passwordList.get(0);
								lookedUpPwd.setEndTS(LocalDateTime.now());
								passwordDAO.update(lookedUpPwd);
							}
						}

						String encrypt = encrypter.encrypt(password1);
						PasswordImpl newPwd = new PasswordImpl();
						newPwd.setLoginId(login.getId());
						newPwd.setPwd(encrypt);
						Password inserted = passwordDAO.insert(newPwd);
						logger.debug("Login, password, inserted=" + inserted);
						retVal = "redirect:login.jsp";
					}
				}
			}
		}
		logger.debug("returns:" + retVal);
		return retVal;
	}

	@RequestMapping(value = "public/show-forgot-password.do", method = RequestMethod.GET)
	public String userShowForgotPassword(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug("public/show-forgot-password.dk: params=" + params);

		String retVal = "redirect:forgot-password.jsp";
		logger.debug("returns:" + retVal);
		return retVal;
	}

	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	public String userLogout(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		String retval = "redirect:public/login.jsp";

		request.getSession().invalidate();

		return retval;
	}

}