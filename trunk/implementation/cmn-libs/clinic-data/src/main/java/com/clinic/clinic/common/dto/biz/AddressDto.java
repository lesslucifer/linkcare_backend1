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
    
    private String address;

    private Double longtitude;
    
    private Double latitude;
    
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
