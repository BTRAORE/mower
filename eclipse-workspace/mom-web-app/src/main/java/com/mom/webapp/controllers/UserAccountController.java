/**
 * 
 */
package com.mom.webapp.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mom.webapp.dto.CustomerDto;
import com.mom.webapp.events.CompleteRegistrationEvent;
import com.mom.webapp.exceptions.EmailAlreadyExistsException;
import com.mom.webapp.model.Credentials;
import com.mom.webapp.model.Customer;
import com.mom.webapp.model.EmailVerifToken;
import com.mom.webapp.services.UserService;
import com.mom.webapp.utils.SecuUtils;

/**
 * @author Brehima
 *
 */
@Controller
@RequestMapping("/account")
public class UserAccountController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserService userService;
	
//	@Autowired
//    private ISecurityUserService securityUserService;
//
    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private Environment env;

    @Autowired
    private AuthenticationManager authenticationManager;
    
	@RequestMapping(value = "/momLogin", method = RequestMethod.GET)
	public String showLoginForm(WebRequest request, Model model) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setGender("Madame");
	    model.addAttribute("momUser", customerDto);
	    return "loginForm";
	}
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setGender("Madame");
	    model.addAttribute("momUser", customerDto);
	    model.addAttribute("regForm", true);
	    return "loginForm";
	}
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(@ModelAttribute("momUser") @Valid CustomerDto customerDto, 
											BindingResult result, 
											final HttpServletRequest request, 
											Errors errors) {
	     
	    Customer customer = new Customer();
	    if (!result.hasErrors()) {
	    	LOGGER.debug("Les données de l'utilisateur sont valides"+customer);
	        customer = createMomUserAccount(customerDto, result);
	        LOGGER.debug("Utilisateur crée "+customer);
	    }
	    if (customer == null) {
	        result.rejectValue("email", "message.user.emailAlreadyExists");
	        LOGGER.debug("Utilisateur n'est pas crée");
	    }
	    if (result.hasErrors()) {
	    	LOGGER.error("Les erreur de validation sont produitent : "+result.getAllErrors());
	        ModelAndView modelView = new ModelAndView("loginForm", "momUser", customerDto);
	        modelView.getModel().put("regForm", true);
	        LOGGER.error("erreur lors de la création de l'utilisateur");
	        return modelView;
	    } 
	    else {
	    	LOGGER.info("un utilisateur a été créé !");
	    	eventPublisher.publishEvent(new CompleteRegistrationEvent(customer.getCredentials(), request.getLocale(), getAppUrl(request)));
	        return new ModelAndView("momHome", "momUser", customerDto);
	    }
	}
	private Customer createMomUserAccount(CustomerDto accountDto, BindingResult result) {
	    Customer registeredCustomer = null;
	    try {
	    	registeredCustomer = userService.registerNewUser(accountDto);
	    } catch (EmailAlreadyExistsException e) {
	        return null;
	    }
	    return registeredCustomer;
	}
	@RequestMapping(value = "/confirmRegistration", method = RequestMethod.GET)
    public String confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
        Locale locale = request.getLocale();
        final String result = userService.validateEmailVerifToken(token);
        if (result.equals("valid")) {
            final Credentials userCredentials = userService.getUserCredentials(token);
            authWithoutPassword(userCredentials);
            model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
            return "redirect:/userProfile/userHome";
        }
        model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
        model.addAttribute("expired", "expired".equals(result));
        model.addAttribute("token", token);
        return "redirect:/badUser.html?lang=" + locale.getLanguage();
    }

//    // user activation - verification

    @RequestMapping(value = "/user/resendRegistrationToken", method = RequestMethod.GET)
    public String resendRegistrationToken(final HttpServletRequest request, @RequestParam("token") final String existingToken, Model model) {
        final EmailVerifToken newToken = userService.generateNewEmailVerifToken(existingToken);
        final Credentials user = userService.getUserCredentials(newToken.getToken());
        mailSender.send(constructResendVerificationTokenEmail(getAppUrl(request), request.getLocale(), newToken, user));
        String message = messages.getMessage("message.resendToken", null, request.getLocale());
        model.addAttribute("resendTokenMsg", message);
        return "tokenMailSentInfos";
    }

//    // Reset password
    @RequestMapping(value = "/acount/resetPassword", method = RequestMethod.POST)
    public String resetPassword(final HttpServletRequest request, final Model model, @RequestParam("email") final String userEmail) {
        final Credentials user = userService.findUserCredentialsByEmail(userEmail);
        String viewName = "";
        if (user != null) {
            final String token = UUID.randomUUID().toString();
            userService.createUserPasswordResetToken(user, token);
            mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
            String message = messages.getMessage("message.resendToken", null, request.getLocale());
            model.addAttribute("resendTokenMsg", message);
        }else {
        	
        }
        return "tokenMailSentInfos";
    }

//    @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
//    public String showChangePasswordPage(final Locale locale, final Model model, @RequestParam("id") final long id, @RequestParam("token") final String token) {
//        final String result = securityUserService.validatePasswordResetToken(id, token);
//        if (result != null) {
//            model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
//            return "redirect:/login?lang=" + locale.getLanguage();
//        }
//        return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
//    }
//
//    @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
//    @ResponseBody
//    public GenericResponse savePassword(final Locale locale, @Valid PasswordDto passwordDto) {
//        final User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        userService.changeUserPassword(user, passwordDto.getNewPassword());
//        return new GenericResponse(messages.getMessage("message.resetPasswordSuc", null, locale));
//    }
//
//    // change user password
//    @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
//    @ResponseBody
//    public GenericResponse changeUserPassword(final Locale locale, @Valid PasswordDto passwordDto) {
//        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
//        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
//            throw new InvalidOldPasswordException();
//        }
//        userService.changeUserPassword(user, passwordDto.getNewPassword());
//        return new GenericResponse(messages.getMessage("message.updatePasswordSuc", null, locale));
//    }
//
//    @RequestMapping(value = "/user/update/2fa", method = RequestMethod.POST)
//    @ResponseBody
//    public GenericResponse modifyUser2FA(@RequestParam("use2FA") final boolean use2FA) throws UnsupportedEncodingException {
//        final User user = userService.updateUser2FA(use2FA);
//        if (use2FA) {
//            return new GenericResponse(userService.generateQRUrl(user));
//        }
//        return null;
//    }
//
//    // ============== NON-API ============

    private SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final EmailVerifToken newToken, final Credentials credentials) {
        final String confirmationUrl = contextPath + "/registrationConfirm.html?token=" + newToken.getToken();
        final String message = messages.getMessage("message.resendToken", null, locale);
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, credentials);
    }

    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final Credentials user) {
        final String url = contextPath + "/user/changePassword?id=" + user.getUsername() + "&token=" + token;
        final String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, Credentials credentials) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(credentials.getUsername());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            LOGGER.error("Error while login ", e);
        }
    }

    public void authWithAuthManager(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }

    public void authWithoutPassword(Credentials userCred) {
        List<GrantedAuthority> authorities = SecuUtils.getAuthorities(userCred.getRoles());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCred, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
