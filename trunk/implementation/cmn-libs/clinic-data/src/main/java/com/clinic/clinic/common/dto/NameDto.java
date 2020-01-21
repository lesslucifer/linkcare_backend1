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
 *		Nov 17, 2015				Vuong Do				Initial<br>
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
public abstract class NameDto extends TraceDto{
    private String name;
    /**
     * <p>Default constructor.</p>
     *
     */
    public NameDto() {
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
    public void setName(String name) {
        this.name = name;
    }
   
    /* (non-Javadoc)
     * @see com.clinic.clinic.common.dto.TraceDto#toUpercaseFirstChar()
     */
    @Override
    public void toUpercaseFirstChar() {
        name = StringUtil.toUpercaseFirstChar(name);
    }
}
