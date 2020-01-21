/**==============================================================================
 * CLINIC JSC (www.clinic.vn) Proprietary.
 * Copyright 2015 CLINIC JSC.
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
 * File name     : NameDto.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Aug 12, 2015		Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.common.dto;

import com.clinic.clinic.common.utils.StringUtil;

/**
 * <p>
 * Domain class contains mandatory and name attributes.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see {@link IdDto}
 */
public abstract class NameCodeDto extends TraceDto {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5126866259805523071L;
	private String name;
    private String code;
	
    /**
     * <p>Default constructor (no parameter) of this class.</p>
     */
    public NameCodeDto() {
        super();
    }
    
    /**
     * <p>Returns current value of name attribute.</p>
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * <p>Sets value of name attribute.</p>
     *
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }
	
    /**
     * <p>Returns current value of code attribute.</p>
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * <p>Sets value of Code attribute.</p>
     * 
     * @param code the Code to set
     */
    public void setCode(final String code) {
        this.code = code;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        return sb.toString();
    }
    
    /* (non-Javadoc)
     * @see com.clinic.clinic.common.dto.TraceDto#toUpercaseFirstChar()
     */
    @Override
    public void toUpercaseFirstChar() {
        code= StringUtil.toUpercaseFirstChar(code);
        name = StringUtil.toUpercaseFirstChar(name);

    }
}
