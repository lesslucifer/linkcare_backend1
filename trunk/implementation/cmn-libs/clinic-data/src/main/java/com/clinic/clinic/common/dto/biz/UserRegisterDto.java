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
 * File name     : UserRegisterDto.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		May 3, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.common.dto.biz;

import net.sf.oval.constraint.NotNull;

/**
 * <p>
 * Dto to user(patient) register.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class UserRegisterDto {
	
	@NotNull
	private String loginName;

	@NotNull
    private String firstName;

	@NotNull
    private String lastName;
	
    private String midleName;

	@NotNull
    private Integer gender;

	@NotNull
    private String birthday;

    private String idCard;
	
    private String passport;
    
    private String email;
    private String phoneNumber;

	@NotNull
    private String password;

	@NotNull
    private String address;

	@NotNull
    private Double longtitude;
	
	@NotNull
    private Double latitude;
    
    public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
    public String getMidleName() {
        return midleName;
    }
    public void setMidleName(String midleName) {
        this.midleName = midleName;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLongtitude() {
        return longtitude;
    }
    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}