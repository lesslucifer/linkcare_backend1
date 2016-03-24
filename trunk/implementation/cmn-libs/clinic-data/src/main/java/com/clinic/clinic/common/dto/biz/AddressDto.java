/**==============================================================================
 * clinic JSC (www.clinic.vn) Proprietary.
 * Copyright 2015 clinic JSC.
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
 * File name     : AddressDto.java<br>
 * <p>
 * Changes History <br>
 *      Date                Person              Reason<br>
 *      Nov 19, 2015        Vuong Do               Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/

package com.clinic.clinic.common.dto.biz;

import java.io.Serializable;

import com.clinic.clinic.common.dto.TraceDto;
import com.clinic.clinic.common.utils.StringUtil;

/**
 * <p>
 * Data transfer object of Address.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see {@link TraceDto}
 */
public class AddressDto extends TraceDto implements Serializable {
    private static final long serialVersionUID = -4978585765784056197L;
    /**
     * <p>
     * Default constructor.
     * </p>
     */
    public AddressDto() {
        super();
    }
    private String houseNumber;

    private String street;

    private String hamlet;

    private String ward;

    private String district;

    private String city;

    private Double longtitude;
    
    private Double latitude;
    
    private AccountDto account;
    
    /**
     * <p>
     * Returns current value of house_number attribute.
     * </p>
     */
    public String getHouseNumber() {
        return houseNumber;
    }
    /**
     * <p>
     * Sets value of house_number attribute.
     * </p>
     */
    public void setHouseNumber(final String houseNumber) {
        this.houseNumber = houseNumber;
    }
    /**
     * <p>
     * Returns current value of street attribute.
     * </p>
     */
    public String getStreet() {
        return street;
    }
    /**
     * <p>
     * Sets value of street attribute.
     * </p>
     */
    public void setStreet(final String street) {
        this.street = street;
    }
    /**
     * <p>
     * Returns current value of hamlet attribute.
     * </p>
     */
    public String getHamlet() {
        return hamlet;
    }
    /**
     * <p>
     * Sets value of hamlet attribute.
     * </p>
     */
    public void setHamlet(final String hamlet) {
        this.hamlet = hamlet;
    }
    /**
     * <p>
     * Returns current value of ward attribute.
     * </p>
     */
    public String getWard() {
        return ward;
    }
    /**
     * <p>
     * Sets value of ward attribute.
     * </p>
     */
    public void setWard(final String ward) {
        this.ward = ward;
    }
    /**
     * <p>
     * Returns current value of district attribute.
     * </p>
     */
    public String getDistrict() {
        return district;
    }
    /**
     * <p>
     * Sets value of district attribute.
     * </p>
     */
    public void setDistrict(final String district) {
        this.district = district;
    }
    /**
     * <p>
     * Returns current value of city attribute.
     * </p>
     */
    public String getCity() {
        return city;
    }
    /**
     * <p>
     * Sets value of city attribute.
     * </p>
     */
    public void setCity(final String city) {
        this.city = city;
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
    
    public AccountDto getAccount() {
        return account;
    }
    public void setAccount(AccountDto account) {
        this.account = account;
    }
    /* (non-Javadoc)
     * @see com.clinic.clinic.common.dto.TraceDto#toUpercaseFirstChar()
     */
    @Override
    public void toUpercaseFirstChar() {
        houseNumber = StringUtil.toUpercaseFirstChar(houseNumber);
        street = StringUtil.toUpercaseFirstChar(street);
        hamlet = StringUtil.toUpercaseFirstChar(hamlet);
        ward = StringUtil.toUpercaseFirstChar(ward);
        district = StringUtil.toUpercaseFirstChar(district);
        city = StringUtil.toUpercaseFirstChar(city);
        
    }

}
