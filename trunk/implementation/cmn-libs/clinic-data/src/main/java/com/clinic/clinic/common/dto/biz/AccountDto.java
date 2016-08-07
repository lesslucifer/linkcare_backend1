/**==============================================================================
 * CLINIC JSC (www.clinic.vn) Proprietary.
 * Copyright 2016 CLINIC JSC.
 * UNPUBLISHED WORK
 * ALL RIGHTS RESERVED
 *
 * This software is the confidential and proprietary information of 
 * clinic J.S.C ("Proprietary Information").  Any use, reproduction,
 * distribution or disclosure of the software or Proprietary Information,
 * in whole or in part, must comply with the terms of the license  
 * agreement, nondisclosure agreement or contract entered into with 
 * clinic providing access to this software.
 *
 * Project name  : clinic-data<br>
 * File name     : AccountDto.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 2, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.common.dto.biz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.clinic.clinic.common.dto.TraceDto;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class AccountDto extends TraceDto implements Serializable {

	private static final long serialVersionUID = 142715070092657866L;

	private String loginName;
	
	private String code;
	
	private String phoneNumber;

	private String idCard;

	private String passport;

	private String firstName;

	private String lastName;

	private String middleName;

	private Integer gender;

	private Integer birthday;
	
	private String avatar;

	private String email;

	private String facebook;

	private String facebookId;

	private String googleId;

	private String website;

	private Integer activeFlag;

	private Long beginActiveTime;

	private Long endActiveTime;

	private AddressDto address;

	private SubcategoryDto subcategory;
	
	private PlaceDto place;
	
	private List<AccountDto> patients = new ArrayList<AccountDto>();
	
	private List<AccountDto> medicars = new ArrayList<AccountDto>();

	public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getBirthday() {
		return birthday;
	}

	public void setBirthday(Integer birthday) {
		this.birthday = birthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Long getBeginActiveTime() {
		return beginActiveTime;
	}

	public void setBeginActiveTime(Long beginActiveTime) {
		this.beginActiveTime = beginActiveTime;
	}

	public Long getEndActiveTime() {
		return endActiveTime;
	}

	public void setEndActiveTime(Long endActiveTime) {
		this.endActiveTime = endActiveTime;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
	
    public SubcategoryDto getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubcategoryDto subcategory) {
        this.subcategory = subcategory;
    }

    public PlaceDto getPlace() {
        return place;
    }

    public void setPlace(PlaceDto place) {
        this.place = place;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<AccountDto> getPatients() {
        return patients;
    }

    public void setPatients(List<AccountDto> patients) {
        this.patients = patients;
    }

    public List<AccountDto> getMedicars() {
        return medicars;
    }

    public void setMedicars(List<AccountDto> medicars) {
        this.medicars = medicars;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.clinic.clinic.common.dto.TraceDto#toUpercaseFirstChar()
	 */
	@Override
	public void toUpercaseFirstChar() {

	}
}
