/**
 * 
 */
package com.mom.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.Furniture;

/**
 * @author Brehima
 *
 */
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {

}
