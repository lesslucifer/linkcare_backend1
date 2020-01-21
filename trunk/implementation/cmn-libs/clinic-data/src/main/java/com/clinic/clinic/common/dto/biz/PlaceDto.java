package com.clinic.clinic.common.dto.biz;

import java.util.ArrayList;
import java.util.List;

import com.clinic.clinic.common.dto.NameCodeDescDto;

public class PlaceDto extends NameCodeDescDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3878263230877109441L;

	private String phoneNumber;

	private String email;

	private String website;

	private String fax;

	private Long dailyOpenTime;

	private Long dailyCloseTime;

	private String avatar;

	private Boolean status;

	private List<AccountDto> accounts = new ArrayList<AccountDto>();

	private AddressDto address;

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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Long getDailyOpenTime() {
		return dailyOpenTime;
	}

	public void setDailyOpenTime(Long dailyOpenTime) {
		this.dailyOpenTime = dailyOpenTime;
	}

	public Long getDailyCloseTime() {
		return dailyCloseTime;
	}

	public void setDailyCloseTime(Long dailyCloseTime) {
		this.dailyCloseTime = dailyCloseTime;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<AccountDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDto> accounts) {
		this.accounts = accounts;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

}
