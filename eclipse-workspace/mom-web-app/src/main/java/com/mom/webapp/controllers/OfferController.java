/**
 * 
 */
package com.mom.webapp.controllers;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mom.webapp.dao.BrandRepository;
import com.mom.webapp.dao.CategoryRepository;
import com.mom.webapp.dao.ItemStateRepository;
import com.mom.webapp.dao.ItemsCatalogRepository;
import com.mom.webapp.dao.OfferRepository;
import com.mom.webapp.dao.OfferStatusRepository;
import com.mom.webapp.dao.TopicRepository;
import com.mom.webapp.dto.OfferDto;
import com.mom.webapp.dto.OfferProperties;
import com.mom.webapp.dto.SearchCriteria;
import com.mom.webapp.exceptions.InvalidImgFileException;
import com.mom.webapp.geocode.GeocodeUtil;
import com.mom.webapp.geocode.LatLng;
import com.mom.webapp.model.Brand;
import com.mom.webapp.model.Category;
import com.mom.webapp.model.Credentials;
import com.mom.webapp.model.Customer;
import com.mom.webapp.model.ItemState;
import com.mom.webapp.model.ItemsCatalog;
import com.mom.webapp.model.Offer;
import com.mom.webapp.model.OfferStatus;
import com.mom.webapp.model.PagerModel;
import com.mom.webapp.model.Topic;
import com.mom.webapp.services.UserService;
import com.mom.webapp.services.impl.ImgFileService;
import com.mom.webapp.spec.OfferSearchCriteriaBuilder;
import com.mom.webapp.utils.ModelMapperUtil;
import com.mom.webapp.utils.OfferStatusEnum;

/**
 * @author Brehima
 *
 */
@Controller
@RequestMapping("/offers")
public class OfferController {
	
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
		
	    @GetMapping
	    public ModelAndView findAllOffers(@ModelAttribute(OFFER_PROPERTIES)OfferProperties offerProperties,
	    		@ModelAttribute(SEARCH_CRITERIA)SearchCriteria searchCriteria, 
	    		@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page,
	            @RequestParam("sw_lat") Optional<Double> swLat,
	    		@RequestParam("sw_lng") Optional<Double> swLng,
	    		@RequestParam("ne_lat") Optional<Double> neLat, 
	    		@RequestParam("ne_lng") Optional<Double> neLng){
	    	
	    	offerSearchCriteriaBuilder.init();
	    	if(GeocodeUtil.isValidMapBound(neLat.orElse(0.0), neLng.orElse(0.0),
	    			swLat.orElse(0.0), swLng.orElse(0.0) )) {
	    		offerSearchCriteriaBuilder
	    		.inMapBound(new LatLng(neLat.get(), neLng.get()), new LatLng(swLat.get(), swLng.get()));
	    	}
	    	Specification<Offer> searchCriteriaSpec =  offerSearchCriteriaBuilder
												    	.withProperties(offerProperties)
												    	.withSearchCriteria(searchCriteria)
												    	.build();
	        int pageSizeVal = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int pageVal = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<Offer> offerList = offerRepository.findAll(searchCriteriaSpec,
	        								PageRequest.of(pageVal, pageSizeVal, new Sort(Direction.DESC,"offerLastUpdate")));
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
	        modelAndView.addObject(SELECTED_PAGE_SIZE, pageSizeVal);
	        modelAndView.addObject(PAGE_SIZES_KEY, PAGE_SIZES);
	        modelAndView.addObject(PAGER, pager);
	        return modelAndView;
	         
	    }
	    @PostMapping
		public ModelAndView createOffer(@ModelAttribute("momOffer") @Valid OfferDto offerDto, BindingResult result,
				final HttpServletRequest request, Errors errors, Principal principal,
				OfferProperties params, @RequestParam("files") MultipartFile[] files, String mainImg, ModelMap model) {
			
			if (!result.hasErrors()) {
				Offer offer = modelMapperUtil.convertToEntity(offerDto);
				ItemState state = itemStateRepository.findByStateName(offerDto.getState().getStateName());
				ItemsCatalog item = itemsCatalogRepository.findByItemName(params.getItemName());
				OfferStatus offerStatus = offerStatusRepository.findByStatusName(OfferStatusEnum.VALIDATION_IN_PROGRESS.name());
				Category category =  categoryRepository.findByCategoryName(params.getCategory());
				Topic topic =  topicRepository.findByTopicName(params.getTopic());
				Brand brand = brandRepository.findByBrandName(offerDto.getBrand().getBrandName());
				offer.setTopic(topic);
				offer.setCategory(category);
				offer.setItem(item);
				offer.setState(state);
				offer.setBrand(brand);
				offer.setOfferStatus(offerStatus);
				Credentials credentials =  userService.findUserCredentialsByEmail(principal.getName());
				Customer customer = credentials.getCustomer();
				offer.setCustomer(customer);
				Date now = new Date();
				offer.setOfferCreationDate(now);
				offer.setOfferLastUpdate(now);
				offer = offerRepository.save(offer);
				String offerImgsFolder = imgFileService.buildOfferImgsDirName(customer.getCustomerId(), offer.getOfferId());
				offer.setOfferImgsFolder(offerImgsFolder);
				String imgsDirPath = imgFileService.buildOfferImgsPath(offerImgsFolder);
				File imgDir  = new File(imgsDirPath);
				if(!imgDir.exists()) {
					imgDir.mkdirs();
				}
				for(MultipartFile file : files) {
					try {
						imgFileService.uploadFile(file, imgsDirPath);
					} catch (InvalidImgFileException | IOException e) {
						LOGGER.error("erreur lors de l'enregistrement des images : "+e);
					}
				}
						
			}
			if (result.hasErrors()) {
				LOGGER.error("Les erreur de validation sont produitent : " + result.getAllErrors());
				model.addAttribute("momOffer", offerDto);
				model.addAttribute("offerCreationParams", params);
				ModelAndView modelView = new ModelAndView("/secured/userProfile/deals/createDealOffer", model);
				return modelView;
			} else {
				LOGGER.info("un utilisateur a été créé !");
				// eventPublisher.publishEvent(new
				// CompleteRegistrationEvent(customer.getCredentials(), request.getLocale(),
				// getAppUrl(request)));
				return new ModelAndView("redirect:/userProfile/userOffers");
			}
		}	
		@RequestMapping(value = "/{offerId}", method = RequestMethod.GET)
		public ModelAndView offerDetails(@PathVariable("offerId")Long offerId,
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
	    @PutMapping("/{offerId}/to-delete-status")
	    public ResponseEntity<Offer>  toDeleteStatus(@PathVariable("offerId")Long offerId){
	    	Offer offer = offerRepository.findAllByOfferId(offerId);
	    	OfferStatus offerStatus = offerStatusRepository.findByStatusName(OfferStatusEnum.TO_DELETE.name());
	    	offer.setOfferStatus(offerStatus);
	    	return new ResponseEntity<Offer>(HttpStatus.NO_CONTENT);
	    }
	    @PostMapping("/{offerId}/delete")
	    public ResponseEntity<Offer> toDeleteStatus2(@PathVariable("offerId")Long offerId){
	    	offerRepository.deleteByOfferId(offerId);
	    	return new ResponseEntity<Offer>(HttpStatus.NO_CONTENT);
	    }
}
