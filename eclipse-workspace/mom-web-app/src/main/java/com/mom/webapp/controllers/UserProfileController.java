/**
 * 
 */
package com.mom.webapp.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mom.webapp.dao.OfferRepository;
import com.mom.webapp.dto.OfferProperties;
import com.mom.webapp.dto.OfferDto;
import com.mom.webapp.model.Customer;
import com.mom.webapp.model.Offer;
import com.mom.webapp.model.PagerModel;
import com.mom.webapp.services.UserService;
import com.mom.webapp.utils.ModelMapperUtil;

/**
 * @author Brehima
 *
 */
@Controller
@RequestMapping("/userProfile")
public class UserProfileController {
	private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 10;
    private static final int[] PAGE_SIZES = { 5, 10, 20};
    private static final String OFFER_PROPERTIES = "offerProperties";
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapperUtil modelMapperUtil;
	@GetMapping("/userOffers")
	public ModelAndView userOffers(Model model, OfferProperties params) {
		model.addAttribute(OFFER_PROPERTIES, params);
		Customer customer = userService.getCurrentAuthenticatedUser();
		Page<Offer> offerList = offerRepository.findAllByCustomerOrderByOfferLastUpdateDesc(PageRequest.of(INITIAL_PAGE, INITIAL_PAGE_SIZE), customer);
        Page<OfferDto> offerDtoList = offerList.map(
        		(offer) ->{ 
        					OfferDto dto = modelMapperUtil.convertToDto(offer);
        					return dto;
        		    	}
        		);
        PagerModel pager = new PagerModel(offerDtoList.getTotalPages(),offerDtoList.getNumber(),BUTTONS_TO_SHOW);
        ModelAndView modelAndView = new ModelAndView("/secured/userProfile/userOffers");
        // add clientmodel
        modelAndView.addObject("offerList",offerDtoList);
        // evaluate page size
//        modelAndView.addObject("selectedPageSize", evalPageSize);
        // add page sizes
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        // add pager
        modelAndView.addObject("pager", pager);
		return modelAndView;
	}
	@RequestMapping("/dealOfferCreationCategories")
	public String dealsOfferCreationCategories(Model model, OfferProperties params) {
		model.addAttribute(OFFER_PROPERTIES, params);
		return "/secured/userProfile/deals/dealOfferCreationCategories";
	}
	@RequestMapping("/dealOfferCreationSubCategories")
	public String dealsOfferCreationSubCategories(Model model, OfferProperties params) {
		model.addAttribute(OFFER_PROPERTIES, params);
		return "/secured/userProfile/deals/dealOfferCreationSubCategories";
	}
	@RequestMapping("/dealOfferCreationForm")
	public String createDealOffer(Model model, OfferProperties params) {
		model.addAttribute(OFFER_PROPERTIES, params);
		OfferDto offerDto = new OfferDto();
	    model.addAttribute("momOffer", offerDto);
		return "/secured/userProfile/deals/createDealOffer";
	}
	@RequestMapping("/rentOfferCreationCategories")
	public String rentOfferCreationCategories(Model model, OfferProperties params) {
		model.addAttribute(OFFER_PROPERTIES, params);
		return "/secured/userProfile/rents/rentOfferCreationCategories";
	}
	@RequestMapping("/rentOfferCreationSubCategories")
	public String rentOfferCreationSubCategories(Model model, OfferProperties params) {
		model.addAttribute(OFFER_PROPERTIES, params);
		return "/secured/userProfile/rents/rentOfferCreationSubCategories";
	}
	@RequestMapping("/rentOfferCreationForm")
	public String createRentOffer(Model model, OfferProperties params) {
		model.addAttribute(OFFER_PROPERTIES, params);
		OfferDto offerDto = new OfferDto();
	    model.addAttribute("momOffer", offerDto);
		return "/secured/userProfile/rents/createRentOffer";
	}
	@RequestMapping("/offerCreationCategories")
	public String offerCreationCategories(@PathParam("topic") String topic) {
		
		return "";
	}
}
