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
 * File name     : NameDescDto.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Aug 12, 2015		daiql				Initial<br>
 * </p>
 *
 * @author daiql
 *=============================================================================*/
package com.clinic.clinic.common.dto;

import com.clinic.clinic.common.utils.StringUtil;

/**
 * <p>
 * Domain class contains mandatory code and description attributes.
 * </p>
 *
 * @author daiql<br>
 * @version 1.0<br>
 * @see {@link NameCodeDto}
 */
public abstract class NameCodeDescDto extends NameCodeDto {
    private String description;
    
    /**
     * <p>Default constructor of this class.</p>
     */
    public NameCodeDescDto() {
        super();
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
    public void setDescription(final String description) {
        this.description = description;
    }
    /*
     * (non-Javadoc)
     * @see com.directv.wkat.bizlogic.domain.NameDomain#toString()
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", description=").append(description);
        return sb.toString();
    }
   
    /* (non-Javadoc)
     * @see com.clinic.clinic.common.dto.NameCodeDto#toUpercaseFirstChar()
     */
    @Override
    public void toUpercaseFirstChar() {
        super.toUpercaseFirstChar();
        description = StringUtil.toUpercaseFirstChar(description);
    }
}
