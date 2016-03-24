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
 * @see {@link TraceDto}
 */
public abstract class NameDesDto extends TraceDto {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3246511215229379342L;
	private String name;
    private String description;
	
    /**
     * <p>Default constructor (no parameter) of this class.</p>
     */
    public NameDesDto() {
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
     * <p>Returns current value of description attribute.</p>
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * <p>Sets value of description attribute.</p>
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /* (non-Javadoc)
     * @see com.clinic.clinic.common.dto.TraceDto#toUpercaseFirstChar()
     */
    @Override
    public void toUpercaseFirstChar() {
        name = StringUtil.toUpercaseFirstChar(name);
        description = StringUtil.toUpercaseFirstChar(description);
    }
}
