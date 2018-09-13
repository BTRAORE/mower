/**
 * 
 */
package com.mom.webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

import com.mom.webapp.interceptors.MomUserModelInterceptor;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * @author Brehima
 *
 */
@Configuration
//@EnableWebMvc
public class MomWebMvcConf extends  WebMvcConfigurationSupport{
	
//	private static final String MESSAGE_SOURCE = "/WEB-INF/i18n/messages";
//    private static final String VIEWS = "/WEB-INF/views/";
//
    private static final String RESOURCES_LOCATION = "/resources/";
    private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private MomUserModelInterceptor momUserModelInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(momUserModelInterceptor);
    }
//
//    @Override
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
//        requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
//        requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
//        return requestMappingHandlerMapping;
//    }
//
//    @Bean(name = "messageSource")
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename(MESSAGE_SOURCE);
//        messageSource.setCacheSeconds(5);
//        return messageSource;
//    }
//
//    @Bean
//    public ITemplateResolver templateResolver() {
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setPrefix(VIEWS);
//        resolver.setSuffix(".html");
//        resolver.setTemplateMode(TemplateMode.HTML);
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setCacheable(false);
//        return resolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.addTemplateResolver(new UrlTemplateResolver());
//        templateEngine.addTemplateResolver(templateResolver());
////        templateEngine.addDialect(new SpringSecurityDialect());
//        templateEngine.addDialect(new LayoutDialect());
//        templateEngine.addDialect(new Java8TimeDialect());
//        return templateEngine;
//    }

//    @Bean
//    public ViewResolver viewResolver() {
//        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
//        thymeleafViewResolver.setTemplateEngine(templateEngine());
//        thymeleafViewResolver.setCharacterEncoding("UTF-8");
//        return thymeleafViewResolver;
//    }
//
//    @Override
//    public Validator getValidator() {
//        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//        validator.setValidationMessageSource(messageSource());
//        return validator;
//    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/userProfile/userInfos").setViewName("secured/userProfile/userInfos");
        registry.addViewController("/userProfile/userHome").setViewName("secured/userProfile/userInfos");
        registry.addViewController("/userProfile/userMsgs").setViewName("secured/userProfile/userMessages");
        registry.addViewController("/userProfile/userNotifications").setViewName("secured/userProfile/userNotifications");
        registry.addViewController("/userProfile/offerCreationTopics").setViewName("secured/userProfile/offerCreationTopics");
//        registry.addViewController("/userProfile/dealOfferCreation").setViewName("secured/userProfile/dealOfferCreation");
//        registry.addViewController("/userProfile/editDealOffer").setViewName("secured/userProfile/editDealOffer");
//        registry.addViewController("/userProfile/userOffers").setViewName("secured/userProfile/userOffers");
        registry.addViewController("/userProfile/userBookmarkedOffers").setViewName("secured/userProfile/userBookmarkedOffers");
//        registry.addViewController("/deals/offerDetails").setViewName("offerDetails");
        registry.addViewController("/richMarkerTest").setViewName("richMarkerTest");
        registry.addViewController("/").setViewName("welcome");
        registry.addViewController("/topic").setViewName("momHome");
        registry.addViewController("/welcome").setViewName("welcome");
//        registry.addViewController("/momLogin").setViewName("loginForm");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/accessDenied").setViewName("accessDenied");
        registry.addViewController("/filterTab").setViewName("filterTableSample");
        registry.addViewController("/filterList").setViewName("filterList");
        registry.addViewController("/confirmLogout").setViewName("confirmLogout");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
    }
    
//    @Override
//    public void addArgumentResolvers(
//                    List<HandlerMethodArgumentResolver> argumentResolvers) {
//             
//            PageableArgumentResolver resolver = new PageableArgumentResolver();
//            resolver.setFallbackPagable(new PageRequest(1, 10));
//             
//            argumentResolvers.add(new ServletWebArgumentResolverAdapter(resolver));
//    }
    
//
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
//    @Bean
//    public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
//        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
//        Map<String, Object> map = new LinkedHashMap<>();
//        ResourceHttpRequestHandler resourceHttpRequestHandler = new ResourceHttpRequestHandler();
//        List<Resource> locations = new ArrayList<>();
//        locations.add(new ServletContextResource(getServletContext(), "/"));
//        locations.add(new ClassPathResource("META-INF/resources"));
//        locations.add(new ClassPathResource("resources/"));
//        locations.add(new ClassPathResource("static/"));
//        locations.add(new ClassPathResource("public/"));
//        resourceHttpRequestHandler.setLocations(locations);
//        resourceHttpRequestHandler.setApplicationContext(getApplicationContext());
//
//        List<ResourceResolver> resourceResolvers = new ArrayList<>();
//        PathResourceResolver resourceResolver = new PathResourceResolver();
//        resourceResolver.setAllowedLocations(new ServletContextResource(getServletContext(), "/"), new ClassPathResource("META-INF/resources"), new ClassPathResource("resources/"), new ClassPathResource("static/"), new ClassPathResource("public/"));
//        resourceResolvers.add(resourceResolver);
//
//        resourceHttpRequestHandler.setResourceResolvers(resourceResolvers);
//        map.put("/**", resourceHttpRequestHandler);
//        simpleUrlHandlerMapping.setUrlMap(map);
//        ResourceUrlProvider resourceUrlProvider = new ResourceUrlProvider();
//        Map<String, ResourceHttpRequestHandler> handlerMap = new LinkedHashMap<>();
//        handlerMap.put("/**", resourceHttpRequestHandler);
//        resourceUrlProvider.setHandlerMap(handlerMap);
//        ResourceUrlProviderExposingInterceptor interceptor = new ResourceUrlProviderExposingInterceptor(resourceUrlProvider);
//        simpleUrlHandlerMapping.setInterceptors(new Object[]{interceptor});
//        return simpleUrlHandlerMapping;
//    }
    /**
     * Handles favicon.ico requests assuring no <code>404 Not Found</code> error is returned.
     */
    @Controller
    static class FaviconController {
        @RequestMapping("favicon.ico")
        String favicon() {
            return "forward:/resources/static/images/favicon.ico";
        }
    }
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.FRANCE);
        return slr;
    }
//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    @Bean
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(byteArrayHttpMessageConverter());
    }
     
    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
        return arrayHttpMessageConverter;
    }
     
    private List<MediaType> getSupportedMediaTypes() {
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.IMAGE_JPEG);
        list.add(MediaType.IMAGE_PNG);
        list.add(MediaType.IMAGE_GIF);
        return list;
    }
}
