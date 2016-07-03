package com.clinic.clinic.api.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "device")
@NamedQuery(name = "DeviceEntity.findAll", query = "Select d From DeviceEntity d")
public class DeviceEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222996499285711077L;

	@Id
	@Column(name = "token")
	private String deviceToken;
	
	@Column(name = "type")
	private String type;

	@Column(name = "app")
	private String app;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner")
	private AccountEntity owner;

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public AccountEntity getOwner() {
		return owner;
	}

	public void setOwner(AccountEntity owner) {
		this.owner = owner;
	}
}
