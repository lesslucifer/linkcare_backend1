package com.clinic.clinic.common.dto.biz;

import java.io.Serializable;

import net.sf.oval.constraint.NotNull;

public class PlaceRegisterDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4077144451479451933L;
	
	@NotNull
	private String address;

	@NotNull
	private Double longitude;

	@NotNull
	private Double latitude;

	@NotNull
	private String name;
	private String description;
	private String phoneNumber;
	private String email;
	private String website;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
}
