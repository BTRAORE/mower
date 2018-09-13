/**
 * 
 */
package com.mom.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.Role;

/**
 * @author Brehima
 *
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByAuthority(String string);

}
