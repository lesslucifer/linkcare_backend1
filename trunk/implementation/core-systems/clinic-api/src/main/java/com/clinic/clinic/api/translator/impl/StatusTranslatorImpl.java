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
 * File name     : StatusTranslatorImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 16, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.StatusEntity;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.common.dto.biz.StatusDto;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class StatusTranslatorImpl extends AbstractTranslatorImpl<StatusDto, StatusEntity>
        implements
            ITranslator<StatusDto, StatusEntity> {

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.translator.ITranslator#getDto(com.clinic.clinic.api.persistence.entity.IdEntity)
     */
    @Override
    public StatusDto getDto(StatusEntity ent) {
        StatusDto dto = new StatusDto();
        this.entityToDto(ent, dto);
        return dto;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.translator.ITranslator#getEntity(com.clinic.clinic.common.dto.IdDto)
     */
    @Override
    public StatusEntity getEntity(StatusDto dto) {
        StatusEntity ent = new StatusEntity();
        this.dtoToEntity(dto, ent);
        return ent;
    }
}
