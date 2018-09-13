/**
 * 
 */
package com.mom.webapp.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mom.webapp.dao.BrandRepository;
import com.mom.webapp.dao.CategoryRepository;
import com.mom.webapp.dao.CustomerRepository;
import com.mom.webapp.dao.ItemStateRepository;
import com.mom.webapp.dao.ItemsCatalogRepository;
import com.mom.webapp.dao.OfferRepository;
import com.mom.webapp.dao.OfferStatusRepository;
import com.mom.webapp.dao.TopicRepository;
import com.mom.webapp.dto.OfferDto;
import com.mom.webapp.dto.OfferProperties;
import com.mom.webapp.dto.SearchCriteria;
import com.mom.webapp.model.Customer;
import com.mom.webapp.model.Offer;
import com.mom.webapp.model.PagerModel;
import com.mom.webapp.services.UserService;
import com.mom.webapp.services.impl.ImgFileService;
import com.mom.webapp.spec.OfferSearchCriteriaBuilder;
import com.mom.webapp.utils.ModelMapperUtil;

/**
 * @author Brehima
 *
 */
@Controller
@RequestMapping("/users")
public class UserControler {
	private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10};
    public static final String OFFER_PROPERTIES = "offerProperties";
    public static final String SEARCH_CRITERIA = "searchCriteria";
    public static final String OFFER_LIST = "offerList";
    public static final String SELECTED_PAGE_SIZE = "selectedPageSize";
    public static final String PAGE_SIZES_KEY = "pageSizes";
    public static final String PAGER = "pager";
    
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Autowired
    OfferRepository offerRepository;
    @Autowired
	private OfferSearchCriteriaBuilder offerSearchCriteriaBuilder;
    @Autowired
	private ModelMapperUtil modelMapperUtil;
    @Autowired
	private UserService userService;
    @Autowired
    private OfferStatusRepository offerStatusRepository;
    private ItemsCatalogRepository itemsCatalogRepository;
	@Autowired
	private ItemStateRepository itemStateRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ImgFileService imgFileService;
	@Autowired
	private CustomerRepository customerRepo;
	@RequestMapping(value = "/{customerId}/offers", method = RequestMethod.GET)
	public ModelAndView offerDetails(@PathVariable("customerId")Long customerId,
			@ModelAttribute(OFFER_PROPERTIES)OfferProperties offerProperties,
			@ModelAttribute(SEARCH_CRITERIA)SearchCriteria searchCriteria) {
		Customer customer = customerRepo.findByCustomerId(customerId);
		Page<Offer> offerList = offerRepository.findAllByCustomerOrderByOfferLastUpdateDesc(PageRequest.of(INITIAL_PAGE, INITIAL_PAGE_SIZE), customer);
        Page<OfferDto> offerDtoList = offerList.map(
        		(offer) ->{ 
        					OfferDto dto = modelMapperUtil.convertToDto(offer);
        					return dto;
        		    	}
        		);
		
        ModelAndView modelAndView = new ModelAndView("offers");
        PagerModel pager = new PagerModel(offerDtoList.getTotalPages(),offerDtoList.getNumber(),BUTTONS_TO_SHOW);
        modelAndView.addObject(OFFER_PROPERTIES, offerProperties);
        modelAndView.addObject(SEARCH_CRITERIA, searchCriteria);
        modelAndView.addObject(OFFER_LIST,offerDtoList);
        modelAndView.addObject(SELECTED_PAGE_SIZE, INITIAL_PAGE_SIZE);
        modelAndView.addObject(PAGE_SIZES_KEY, PAGE_SIZES);
        modelAndView.addObject(PAGER, pager);
		return modelAndView;
	}
	@RequestMapping(value = "/{username}/favorite/offers", method = RequestMethod.GET)
	public ModelAndView fetchUserFavoriteOffers(@PathVariable("username")String username,
			@ModelAttribute(OFFER_PROPERTIES)OfferProperties offerProperties) {
		ModelAndView modelAndView = new ModelAndView("offers");
		modelAndView.addObject(OFFER_PROPERTIES, offerProperties);
		return modelAndView;
	}
	@RequestMapping(value = "/{username}/favorite/offers/{offerId}", method = RequestMethod.POST)
	public ModelAndView addToUserFavoriteOffers(@PathVariable("username")String username,
			@PathVariable("offerId")Long offerId,
			@ModelAttribute(OFFER_PROPERTIES)OfferProperties offerProperties) {
		ModelAndView modelView = new ModelAndView("offerDetails");
		Optional<Offer> offer = offerRepository.findById(offerId);
		if(offer.isPresent()) {
			OfferDto offerDto = modelMapperUtil.convertToDto(offer.get());
			modelView.addObject("offer", offerDto);
		}else{
			modelView.setViewName("offers");
			modelView.addObject("warningMsg", "Cette offre est introuvable !");
		}
		modelView.addObject(OFFER_PROPERTIES, offerProperties);
		return modelView;
	}
	@RequestMapping(value = "/{username}/favorite/offers/{offerId}", method = RequestMethod.DELETE)
	public ModelAndView deleteOfferFromUserFavorite(@PathVariable("username")String username,
			@PathVariable("offerId")Long offerId,
			@ModelAttribute(OFFER_PROPERTIES)OfferProperties offerProperties) {
		ModelAndView modelView = new ModelAndView("offerDetails");
		return modelView;
	}
}
