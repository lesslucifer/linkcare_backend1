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
 * File name     : AccountCustomDto.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 8, 2016				dailq				Initial<br>
 * </p>
 *
 * @author dailq
 *=============================================================================*/
package com.clinic.clinic.common.dto.biz;

import com.clinic.clinic.common.dto.IdDto;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author dailq<br>
 * @version 1.0<br>
 * @see TODO
 */
/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class AccountCustomDto extends IdDto {

    /**
     * <p>Description of this field.</p>
     */
    private static final long serialVersionUID = 189140697240669321L;
    
    private String avatar;
    
    private String name;
    
    private String lastName;
    private String midleName;
    private String firstName;
    
    private String major;
    
    private String category;
    
    private String subcategory;
    
    private Double experience;
    
    private Double costHome;
    
    private Double costClinic;
    
    private String addressHome;
    
    private String addressClinic;
    
    private Double homeLongtitude;
    
    private Double homeLatitude;
    
    private Double clinicLongtitude;
    
    private Double clinicLatitude;
    
    private Double distance;
    
    private Double mark;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName, String midleName, String lastName) {
        if(firstName != null && midleName != null && lastName != null) {
            this.name = firstName + " " + midleName + " " + lastName;
        } else {
            if(midleName == null) {
                this.name = firstName + " " + lastName;
            }
        }
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Double getCostHome() {
        return costHome;
    }

    public void setCostHome(Double costHome) {
        this.costHome = costHome;
    }

    public Double getCostClinic() {
        return costClinic;
    }

    public void setCostClinic(Double costClinic) {
        this.costClinic = costClinic;
    }

    public String getAddressHome() {
        return addressHome;
    }

    public void setAddressHome(String addressHome) {
        this.addressHome = addressHome;
    }

    public String getAddressClinic() {
        return addressClinic;
    }

    public void setAddressClinic(String addressClinic) {
        this.addressClinic = addressClinic;
    }

    public Double getHomeLongtitude() {
        return homeLongtitude;
    }

    public void setHomeLongtitude(Double homeLongtitude) {
        this.homeLongtitude = homeLongtitude;
    }

    public Double getHomeLatitude() {
        return homeLatitude;
    }

    public void setHomeLatitude(Double homeLatitude) {
        this.homeLatitude = homeLatitude;
    }

    public Double getClinicLongtitude() {
        return clinicLongtitude;
    }

    public void setClinicLongtitude(Double clinicLongtitude) {
        this.clinicLongtitude = clinicLongtitude;
    }

    public Double getClinicLatitude() {
        return clinicLatitude;
    }

    public void setClinicLatitude(Double clinicLatitude) {
        this.clinicLatitude = clinicLatitude;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
