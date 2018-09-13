/**
 * 
 */
package com.mom.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mom.webapp.dto.CustomerDto;

/**
 * @author Brehima
 *
 */
@Controller
@ControllerAdvice
public class MomUserControlerAdvice {

	@ModelAttribute("momUser")
    public CustomerDto addAttributes() {
        return new CustomerDto();
    }
}
