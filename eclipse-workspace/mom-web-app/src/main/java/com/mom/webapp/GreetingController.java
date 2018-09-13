package com.mom.webapp;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mom.webapp.dao.OfferRepository;


public class GreetingController {
	public OfferRepository offerRepo;
	@RequestMapping(value= "/deals", params= {"page", "size"}, method=RequestMethod.GET )
	public String list(ModelMap model, @SortDefault("offerLastUpdate") Pageable pageable){
		model.addAttribute("dealsPage", offerRepo.findAll(pageable));
		
		return "dealsHome";
	}
//	@Bean
//    public SpringDataDialect springDataDialect() {
//        return new SpringDataDialect();
//    }
//	templateEngine.addDialect(new SpringDataDialect());
	@RequestMapping("/welcome")
    public String hello(Model model) {
        model.addAttribute("name", "traore");
        return "momHome";
    }
	@RequestMapping("/momLogin")
	public String login(Model model) {
		return "loginForm";
	}
	@RequestMapping(value="/userProfile/userHome")
	public String userHome(Model model) {
		return "secured/authUsers/userInfos";
	}
	@RequestMapping("/greetings")
	public String greetings(Model model) {
		return "greetings";
	}
	@RequestMapping("/access-denied")
	public String accessDenied(Model model) {
		return "accessDenied";
	}
}
