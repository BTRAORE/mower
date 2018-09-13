/**
 * 
 */
package com.mom.webapp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mom.webapp.services.impl.MomUserDetailsService;
import com.mom.webapp.utils.MomUsers;

/**
 * @author Brehima
 *
 */
@Configuration
@EnableWebSecurity
public class MomSecurityConf extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

//	@Autowired
//	private DataSource dataSource;
	
//	@Value("${spring.queries.users-query}")
//	private String usersQuery;
//	
//	@Value("${spring.queries.roles-query}")
//	private String rolesQuery;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)
//			throws Exception {
//		auth.
//			jdbcAuthentication()
//				.usersByUsernameQuery(usersQuery)
//				.authoritiesByUsernameQuery(rolesQuery)
//				.dataSource(dataSource)
//				.passwordEncoder(bCryptPasswordEncoder);
//	}
	@Autowired
	 DataSource dataSource;
	
	@Autowired
	private MomUserDetailsService userDetailsService;
	
	 @Autowired
	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	   auth.userDetailsService(userDetailsService);
	 }
	 @Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	    .csrf()
	        .csrfTokenRepository(new HttpSessionCsrfTokenRepository());
		http.
			authorizeRequests()
				.antMatchers(HttpMethod.GET, "/*", "/welcome", "/offers", "/offers/{\\d+}", "/users/{.*}/offers",
						"/offers/offerDetails/{\\d+}", "/account/registration", "/account/confirmRegistration",
						"/offer/filterOffers", "/dealOffers/createOffer", "/confirmLogout", "/error").permitAll()
				.antMatchers(HttpMethod.PUT, "/offers/{\\d+}").permitAll()
				.antMatchers(HttpMethod.POST, "/offers").permitAll()
				.antMatchers(HttpMethod.PUT, "/offers/{\\d+}/to-delete-status").authenticated()
				.antMatchers(HttpMethod.POST, "/offers/{\\d+}/delete").hasAnyRole(MomUsers.ADMIN.name(), MomUsers.SUPERVISOR.name())
//				.antMatchers("/account/momLogin").permitAll()
//				.antMatchers("/error").permitAll()
				.antMatchers(HttpMethod.POST, "/account/registration").permitAll()
				.antMatchers("/userProfile/**").authenticated()
				.antMatchers("/gpcgr/**").hasAuthority(MomUsers.ADMIN.name()).anyRequest()
				.authenticated()
				.and()
					.formLogin()
					.loginPage("/account/momLogin")
					.loginProcessingUrl("/login") 
					.failureUrl("/account/momLogin?error=true")
		            .permitAll()
				.and()
					.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/offers")
					.deleteCookies("JSESSIONID")
		            .permitAll()
				.and()
					.exceptionHandling()
					.accessDeniedPage("/accessDenied");
		
		
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//	    web.debug(true);
	}

}
