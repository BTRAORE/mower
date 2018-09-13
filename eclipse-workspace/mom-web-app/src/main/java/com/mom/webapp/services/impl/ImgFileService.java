/**
 * 
 */
package com.mom.webapp.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mom.webapp.exceptions.InvalidImgFileException;
import com.mom.webapp.utils.ArraysUtil;

/**
 * @author Brehima
 *
 */
@Service
public class ImgFileService {

	@Value("${mom.files.imgs.extensions}")
	  private String validExtensions;
	@Value("${mom.files.offers.imgs}")
	private String momOffersImgsDir;
		
//	  @Autowired
//	  private FileDao fileDao;
		
	  String getFileExtension(String fileName) {
	    int dotIndex = fileName.lastIndexOf(".");
	    if(dotIndex < 0) {
	      return null;
	    }
	    return fileName.substring(dotIndex+1);
	  }
		
	  boolean isValidExtension(String fileName) 
	  throws InvalidImgFileException {
	    String fileExtension = getFileExtension(fileName);
			
	    if (fileExtension == null) {
	      throw new InvalidImgFileException("No File Extension");
	    }
			
	    fileExtension = fileExtension.toLowerCase();
			
	    for (String validExtension : validExtensions.split(",")) {
	      if (fileExtension.equals(validExtension)) {
	        return true;
	      }
	    }
	    return false;
	  }
		
	  private int getOpenParenthesisIndex(String baseFileName) {
	    int openParIndex = baseFileName.lastIndexOf("(");
	    int closeParIndex = baseFileName.lastIndexOf(")");
	    boolean isParenthesis = openParIndex > 0 && 
	                            closeParIndex == baseFileName.length()-1;
			
	    if (
	      isParenthesis && 
	      baseFileName.
	      substring(openParIndex+1, closeParIndex).
	      matches("[1-9][0-9]*")
	    ) {
	      return openParIndex;
	    } else {
	      return -1;
	    }
	  }
		
	  String handleFileName(String fileName, String uploadDirectory) 
	  throws InvalidImgFileException {
			
	    String cleanFileName = fileName.replaceAll("[^A-Za-z0-9.()]", "");		
	    String extension = getFileExtension(cleanFileName);
			
	    if(!isValidExtension(cleanFileName)) {
	      throw new InvalidImgFileException("Invalid File Extension");
	    };
			
	    String base = cleanFileName.substring(
	      0, 
	      cleanFileName.length()-extension.length()-1
	    );
			
	    int openParIndex = getOpenParenthesisIndex(base);
			
	    if (openParIndex > 0) {
	      base = base.substring(0, openParIndex);
	      cleanFileName =  base + "." + extension;
	    }
			
	    if (Files.exists(Paths.get(uploadDirectory, cleanFileName))) {
	      cleanFileName =  base + "(1)." + extension;
	    }
			
	    while (Files.exists(Paths.get(uploadDirectory, cleanFileName))) {
	      String nString = cleanFileName.substring(
	        base.length()+1, 
	        cleanFileName.length()-extension.length()-2
	      );
	      int n = Integer.parseInt(nString) + 1;
	      cleanFileName =  base + "(" + n + ")." + extension;
	    }		
			
	    return cleanFileName;
	  }
		
	  public void uploadFile(MultipartFile file, String uploadDirectory) 
			  throws InvalidImgFileException, IOException {		
	    String fileName = handleFileName(file.getOriginalFilename(), uploadDirectory);
	    Path path = Paths.get(uploadDirectory, fileName);
	    Files.copy(file.getInputStream(), path);
	  }
	  public String buildOfferImgsPath(String imgFolderName) {
		  StringBuffer sb = new StringBuffer(momOffersImgsDir);
		  sb.append(File.separator)
		  	.append(imgFolderName);
		  return sb.toString();
	  }
	  public String buildOfferImgsDirName(Long customerId, Long offerId) {
		  StringBuffer sb = new StringBuffer("user_");
		  sb.append(customerId)
			  .append("_offer_")
			  .append(offerId)
			  .append("_imgs");
		  return sb.toString();
	  }
	  public List<String> listDirFiles(String dirPath) {
		  System.out.println("********* listDirFile dirPath : "+dirPath);
		  File[] files = new File(dirPath).listFiles();
		  if(ArraysUtil.isNullOrEmpty(files)) {
			 return Collections.emptyList();
		  }
		  return Arrays.stream(files)
				  .filter(file->file.isFile())
				  .map(file->file.getName())
				  .collect(Collectors.toList());
	}
//	    String filepath = Paths.get(directory, filename).toString();
//	    
//	    // Save the file locally
//	    BufferedOutputStream stream =
//	        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
//	    stream.write(uploadfile.getBytes());
//	    stream.close();
//	    public Resource loadFileAsResource(String fileName) {
//	        try {
//	            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
//	            Resource resource = new UrlResource(filePath.toUri());
//	            if(resource.exists()) {
//	                return resource;
//	            } else {
//	                throw new MyFileNotFoundException("File not found " + fileName);
//	            }
//	        } catch (MalformedURLException ex) {
//	            throw new MyFileNotFoundException("File not found " + fileName, ex);
//	        }
//	    }
//	    public void saveFile(InputStream is) throws IOException {
//	    	try(OutputStream os = new FileOutputStream(targetLocation.)) {
//	            byte[] buffer = new byte[1024];
//	            int bytesRead;
//	            //read from is to buffer
//	            while((bytesRead = is.read(buffer)) !=-1){
//	            	os.write(buffer, 0, bytesRead);
//	            }
//	            os.flush();
//				}finally {
//					is.close();
//				}
//            
//            
//	    }
	  
//	  if (!file.isEmpty()) {
//          try {
//              fileName = file.getOriginalFilename();
//              byte[] bytes = file.getBytes();
//              BufferedOutputStream buffStream = 
//                      new BufferedOutputStream(new FileOutputStream(new File("F:/cp/" + fileName)));
//              buffStream.write(bytes);
//              buffStream.close();
//              return "You have successfully uploaded " + fileName;
//          } catch (Exception e) {
//              return "You failed to upload " + fileName + ": " + e.getMessage();
//          }
}
