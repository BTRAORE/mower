/**
 * 
 */
package com.mom.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mom.webapp.model.Topic;

/**
 * @author Brehima
 *
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {

	Topic findByTopicName(String topicFilterName);

}
