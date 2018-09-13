/**
 * 
 */
package com.mom.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.Category;

/**
 * @author Brehima
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByCategoryName(String catFilterName);

}
