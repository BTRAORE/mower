/**
 * 
 */
package com.mom.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.Meal;

/**
 * @author Brehima
 *
 */
public interface MealRepository extends JpaRepository<Meal, Integer> {

}