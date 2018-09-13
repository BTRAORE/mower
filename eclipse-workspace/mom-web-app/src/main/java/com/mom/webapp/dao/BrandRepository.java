/**
 * 
 */
package com.mom.webapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.Brand;

/**
 * @author Brehima
 *
 */
public interface BrandRepository extends JpaRepository<Brand, Long> {
	
	    public List<Brand> findByCategoryCategoryNameEquals(String category);

		public List<Brand> findByBrandNameIn(List<String> brandList);

		public Brand findByBrandName(String brandName);

}
