/**
 * 
 */
package com.mom.webapp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.mom.webapp.model.Role;

/**
 * @author Brehima
 *
 */
public class SecuUtils {
	public static List<GrantedAuthority> getAuthorities (Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return authorities;
    }
}
