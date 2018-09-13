/**
 * 
 */
package com.mom.webapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.ItemsCatalog;

/**
 * @author Brehima
 *
 */
public interface ItemsCatalogRepository extends JpaRepository<ItemsCatalog, Long> {

	ItemsCatalog findByItemName(String itemName);

	ItemsCatalog findByItemNameAndSubCategory(String itemNameFilter, Long subCategoryId);

	List<ItemsCatalog> findBySubCategoryScName(String string);

}
