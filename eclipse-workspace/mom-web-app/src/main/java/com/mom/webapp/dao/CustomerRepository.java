/**
 * 
 */
package com.mom.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.Customer;

/**
 * @author Brehima
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByEmail(String email);

	Customer findByCustomerId(Long customerId);

}
