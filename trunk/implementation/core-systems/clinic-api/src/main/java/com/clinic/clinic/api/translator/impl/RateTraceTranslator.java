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
 * Project name  : clinic-api<br>
 * File name     : RateTraceTranslator.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Apr 7, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.RateTraceEntity;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.common.dto.biz.RateTraceDto;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class RateTraceTranslator extends AbstractTranslatorImpl<RateTraceDto, RateTraceEntity> implements ITranslator<RateTraceDto, RateTraceEntity> {
    
    public static final RateTraceTranslator INSTANCE = new RateTraceTranslator();
    /**
     * <p>Default constructor (no parameter) of this class.</p>
     *
     * @param dtoClass
     * @param entClass
     */
    
    private RateTraceTranslator() {
        super(RateTraceDto.class, RateTraceEntity.class);
    }
    protected RateTraceTranslator(Class<RateTraceDto> dtoClass, Class<RateTraceEntity> entClass) {
        super(dtoClass, entClass);
    }
}