/**
 * 
 */
package com.mom.webapp.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.mom.webapp.dto.SearchCriteria;
import com.mom.webapp.dto.ItemsCatalogDto;
import com.mom.webapp.dto.OfferProperties;
import com.mom.webapp.dto.OfferDto;
import com.mom.webapp.events.CompleteRegistrationEvent;
import com.mom.webapp.exceptions.InvalidImgFileException;
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

/**
 * @author Brehima
 *
 */
@Controller
@RequestMapping("/dealOffers")
public class DealsOfferController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10};
	@Autowired
	private UserService userService;
	@Autowired
	private OfferRepository offerRepository;
	@Autowired
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
	private OfferStatusRepository offerStatusRepository;
	@Autowired
	private ModelMapperUtil modelMapperUtil;
	@Autowired
	private MessageSource messages;
	@Autowired
	private ImgFileService imgFileService;
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private Environment env;
	@Autowired
	private OfferSearchCriteriaBuilder searchFilter;

	@RequestMapping(value = "/createOffer", method = RequestMethod.POST)
	public ModelAndView createOffer(@ModelAttribute("momOffer") @Valid OfferDto offerDto, BindingResult result,
			final HttpServletRequest request, Errors errors, Principal principal,
			OfferProperties params, @RequestParam("files") MultipartFile[] files, String mainImg, ModelMap model) {
		
		LOGGER.error("Les erreur de validation sont produitent : *********************" +offerDto);
		
		if (!result.hasErrors()) {
			Offer offer = modelMapperUtil.convertToEntity(offerDto);
			ItemState state = itemStateRepository.findByStateName(offerDto.getState().getStateName());
			ItemsCatalog item = itemsCatalogRepository.findByItemName(params.getItemName());
			OfferStatus offerStatus = offerStatusRepository.findByStatusName("VALIDATION_IN_PROGRESS");
			Category category =  categoryRepository.findByCategoryName(params.getCategory());
			Topic topic =  topicRepository.findByTopicName(params.getTopic());
			Brand brand = brandRepository.findByBrandName(offerDto.getBrand().getBrandName());
			LOGGER.error("La valeur mainImg is : *********************" +offer.getMainImg());
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
			LOGGER.debug("********** file name "+files.length);
			for(MultipartFile file : files) {
				LOGGER.debug("********** file name "+file.getOriginalFilename());
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
			ModelAndView modelView = new ModelAndView(
					"/secured/userProfile/deals/createDealOffer",
					model);
			LOGGER.error("erreur lors de la création de l'offre");
			return modelView;
		} else {
			LOGGER.info("un utilisateur a été créé !");
			// eventPublisher.publishEvent(new
			// CompleteRegistrationEvent(customer.getCredentials(), request.getLocale(),
			// getAppUrl(request)));
			return new ModelAndView("redirect:/userProfile/userOffers");
		}
	}	
	@RequestMapping(value = "/offerDetails/{offerId}", method = RequestMethod.GET)
	public ModelAndView offerDetails(@PathVariable("offerId")Long offerId) {
		ModelAndView modelView = new ModelAndView("offerDetails");
		Optional<Offer> offer = offerRepository.findById(offerId);
		if(offer.isPresent()) {
			OfferDto offerDto = modelMapperUtil.convertToDto(offer.get());
			modelView.addObject("offer", offerDto);
		}else{
			modelView.setViewName("dealsHome");;
		}
		return modelView;
	}
}
