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
 * File name     : CalendarTranslatorImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 16, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.CalendarEntity;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.common.dto.biz.CalendarDto;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class CalendarTranslatorImpl extends AbstractTranslatorImpl<CalendarDto, CalendarEntity> implements ITranslator<CalendarDto, CalendarEntity> {

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.translator.ITranslator#getDto(com.clinic.clinic.api.persistence.entity.IdEntity)
     */
    @Override
    public CalendarDto getDto(CalendarEntity ent) {
        CalendarDto dto = new CalendarDto();
        this.entityToDto(ent, dto);
        return dto;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.translator.ITranslator#getEntity(com.clinic.clinic.common.dto.IdDto)
     */
    @Override
    public CalendarEntity getEntity(CalendarDto dto) {
        CalendarEntity ent = new CalendarEntity();
        this.dtoToEntity(dto, ent);
        return ent;
    }
}
