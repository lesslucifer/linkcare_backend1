/**==============================================================================
 * CLINIC JSC (www.clinic.vn) Proprietary.
 * Copyright 2015 Clinic JSC.
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
 * File name     : IdDto.java<br>
 * <p>
 * Changes History <br>
 *      Date                Person              Reason<br>
 *      Aug 20, 2015        Vuong Do               Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.common.dto;

import java.io.Serializable;

/**
 * <p>
 * Domain class contains only ID.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 */
public abstract class IdDto implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2841988854359454463L;
	private Integer id;
    
    /**
     * <p>Default constructor of this class.</p>
     */
    public IdDto() {
    }
    
    /**
     * <p>Returns current value of id attribute.</p>
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * <p>Sets value of id attribute.</p>
     *
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        return sb.toString();
    }
}
