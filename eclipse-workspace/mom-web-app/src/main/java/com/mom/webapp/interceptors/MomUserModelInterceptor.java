package com.mom.webapp.interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mom.webapp.dto.AddressDto;
import com.mom.webapp.dto.CustomerDto;
import com.mom.webapp.model.Credentials;
import com.mom.webapp.services.UserService;
import com.mom.webapp.utils.ModelMapperUtil;

/**
 * 
 */

/**
 * @author Brehima
 *
 */
@Component
public class MomUserModelInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private ModelMapperUtil modelMapperUtil;
	@Autowired
	private UserService userService;
	@Override
    public void postHandle(HttpServletRequest request, 
                           HttpServletResponse response, 
                           Object handler, 
                           ModelAndView modelAndView) throws Exception {
		if(modelAndView != null) {
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			 if(auth != null && auth.isAuthenticated()) {
//				 UserDetails userDetails = (UserDetails)auth.getCredentials();
				 Object principal = auth.getPrincipal();
				 String username = "";
				 if (principal instanceof UserDetails) {
					 username = ((UserDetails)principal).getUsername();
					 } else {
					 username = principal.toString();
					 }
				 Credentials cred = userService.findUserCredentialsByEmail(username);
				 if(cred != null) {
					 CustomerDto customer = modelMapperUtil.convertToDto(cred.getCustomer());
					 if(customer.getAddress() == null){
						customer.setAddress(new AddressDto()); 
					 }
					 modelAndView.addObject("momUser", customer);
				 }
			 }else {
				 CustomerDto customer = new CustomerDto();
				 customer.setAddress(new AddressDto());
				 modelAndView.addObject("momUser", customer);
			 }
		}
    }
	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
