/**
 * 
 */
package com.mom.webapp.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mom.webapp.dao.CredentialsRepository;
import com.mom.webapp.model.Credentials;
import com.mom.webapp.model.Role;
import com.mom.webapp.utils.SecuUtils;

/**
 * @author Brehima
 *
 */
@Service
@Transactional
public class MomUserDetailsService implements UserDetailsService {
     
	 @Autowired
	private CredentialsRepository userRepository;
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Credentials userCred = userRepository.findByUsername(username);
        if (userCred == null) {
            throw new UsernameNotFoundException(
              "No user found with username: "+ username);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return  new org.springframework.security.core.userdetails.User
          (userCred.getUsername(), 
          userCred.getPassword(), enabled, accountNonExpired, 
          credentialsNonExpired, accountNonLocked, 
          SecuUtils.getAuthorities(userCred.getRoles()));
	}
}
