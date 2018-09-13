/**
 * 
 */
package com.mom.webapp.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.validation.ReportAsSingleViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mom.webapp.services.impl.ImgFileService;

/**
 * @author Brehima
 *
 */
@Controller
@RequestMapping("/images")
public class FileController {
	@Autowired
	private ImgFileService imgFileService;

	@RequestMapping(value = "/customer/{customerId}/offer/{offerId}/{imgFileName:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getImageAsResource(@PathVariable("customerId") Long customerId, @PathVariable("offerId") Long offerId, @PathVariable("imgFileName") String imgFileName) {
		HttpHeaders headers = new HttpHeaders();
		try {
			String offerImgDir = imgFileService.buildOfferImgsPath(imgFileService.buildOfferImgsDirName(customerId, offerId));
			Path tmpPath = Paths.get(offerImgDir);
			System.out.println("*************** file name as received : "+imgFileName);
			Path filePath = tmpPath.resolve(imgFileName); //add fileName to path
			byte[] data = Files.readAllBytes(filePath);
			return new ResponseEntity<>(data, headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	    
	}
	@RequestMapping(value = "/offer/image/upload", method = RequestMethod.POST)
	@ResponseBody
	public String uploadOfferImages(@PathVariable("customerId") Long customerId, @RequestParam("files") MultipartFile[] files) {
		HttpHeaders headers = new HttpHeaders();
//		try {
			String offerImgDir = imgFileService.buildOfferImgsPath(imgFileService.buildOfferImgsDirName(customerId, new Date().getTime()));
			Path tmpPath = Paths.get(offerImgDir);
//			return "{}";
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return "{}";
	    
	}
	
}
