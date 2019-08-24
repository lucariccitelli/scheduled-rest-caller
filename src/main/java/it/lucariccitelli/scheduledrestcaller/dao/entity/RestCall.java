package it.lucariccitelli.scheduledrestcaller.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import it.lucariccitelli.scheduledrestcaller.dao.base.BaseEntity;

/**
 * @author luc created on 24 ago 2019
 *
 */
@Entity
@Table(name = "rest_call")
@Where(clause = "active_status = 'A'")
public class RestCall extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "call_id", updatable = false, nullable = false)
	private long id;

	@Column(name = "http_method", nullable = false)
	private String method;

	@Column(name = "url", nullable = false)
	private String url;

	@Column(name = "param")
	private String param;

	@Column(name = "body")
	private String body;

	@Column(name = "send_status")
	private String sendStatus;

	@Column(name = "feedback")
	private String feedback;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}
