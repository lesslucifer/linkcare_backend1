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
 * File name     : RateTraceDto.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Apr 7, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.common.dto.biz;

import com.clinic.clinic.common.dto.TraceDto;

import net.sf.oval.constraint.NotNull;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class RateTraceDto extends TraceDto {
    /**
     * <p>Description of this field.</p>
     */
    private static final long serialVersionUID = -7060891016195309464L;
    
    @NotNull
    private Double mark;
    @NotNull
    private String comment;
    
    public Double getMark() {
        return mark;
    }
    public void setMark(Double mark) {
        this.mark = mark;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
