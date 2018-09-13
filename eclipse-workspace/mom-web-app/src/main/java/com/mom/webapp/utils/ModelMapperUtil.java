/**
 * 
 */
package com.mom.webapp.utils;

import java.util.List;

import org.hsqldb.lib.StringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.mom.webapp.dto.CustomerDto;
import com.mom.webapp.dto.OfferDto;
import com.mom.webapp.model.Customer;
import com.mom.webapp.model.Offer;
import com.mom.webapp.services.impl.ImgFileService;

/**
 * @author Brehima
 *
 */
@Component
public class ModelMapperUtil {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ImgFileService imgFileService;
	
	public OfferDto convertToDto(Offer offer) {
		OfferDto offerDto = modelMapper.map(offer, OfferDto.class);
		String offerImgDir = "";
		if(offerDto.getOfferImgsFolder() != null) {
			offerImgDir = offerDto.getOfferImgsFolder();
		}else {
			offerImgDir = imgFileService.buildOfferImgsDirName(offerDto.customer.getCustomerId(), offer.getOfferId());
		}
		List<String>imgs = imgFileService.listDirFiles(imgFileService.buildOfferImgsPath(offerImgDir));
		offerDto.setOfferImgs(imgs);
		if(StringUtil.isEmpty(offerDto.getMainImg()) && !CollectionUtils.isEmpty(imgs)) {
			offerDto.setMainImg(imgs.get(0));
		}
	    return offerDto;
	}
	public CustomerDto convertToDto(Customer customer) {
		CustomerDto offerDto = modelMapper.map(customer, CustomerDto.class);
		return offerDto;
	}
	public Customer convertToEntity(CustomerDto customerDto){
		Customer customer = modelMapper.map(customerDto, Customer.class);
	    return customer;
	}
	public Offer convertToEntity(OfferDto offerDto){
		Offer offer = modelMapper.map(offerDto, Offer.class);
		return offer;
	}
}
