/**
 * 
 */
package com.mom.webapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.ItemState;

/**
 * @author Brehima
 *
 */
public interface ItemStateRepository extends JpaRepository<ItemState, Long> {

	ItemState findByStateName(String string);

	List<ItemState> findByStateNameIn(List<String> statesList);

}
