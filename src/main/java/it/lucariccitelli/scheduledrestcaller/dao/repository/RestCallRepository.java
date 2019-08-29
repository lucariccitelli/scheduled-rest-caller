package it.lucariccitelli.scheduledrestcaller.dao.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lucariccitelli.scheduledrestcaller.dao.entity.RestCall;

/**
 * @author luc created on 24 ago 2019
 *
 */
public interface RestCallRepository extends JpaRepository<RestCall, Long> {

	public List<RestCall> findBySendStatusAndCreatedDateAfterOrderByCreatedDateAsc(String status, Date createdDate);
	
	void deleteByCreatedDateBefore(Date date);

	default void softDelete(RestCall entity) {
		entity.setActiveStatus("D");
		save(entity);
	}
}
