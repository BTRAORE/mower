package com.mom.webapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@SpringBootApplication
//@Import(MomWebMvcConf.class)
public class MomWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomWebAppApplication.class, args);
	}
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
	    }
	 @Bean
	    public SpringSecurityDialect springSecurityDialect(){
	        return new SpringSecurityDialect();
	    }
	 @Bean
	 public ModelMapper modelMapper() {
	     return new ModelMapper();
	 }
	 @Bean
	 public com.mom.webapp.utils.ModelMapperUtil ModelMapperUtil() {
		 return new com.mom.webapp.utils.ModelMapperUtil();
	 }
//	 @Bean
//	   public MultipartResolver multipartResolver() {
//	      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//	      multipartResolver.setMaxUploadSize(104857600); // 100MB
//	      multipartResolver.setMaxUploadSizePerFile(10485760); // 10MB
//	      return multipartResolver;
//	   }

}
