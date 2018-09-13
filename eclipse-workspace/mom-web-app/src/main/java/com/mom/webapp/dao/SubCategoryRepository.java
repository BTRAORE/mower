package com.mom.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

	SubCategory findByScName(String subCatFilter);

}
