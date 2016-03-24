/**
 * =============================================================================
 * = CLINIC JSC (www.clinic.vn) Proprietary. Copyright 2016 CLINIC JSC.
 * UNPUBLISHED WORK ALL RIGHTS RESERVED This software is the confidential and
 * proprietary information of clinic J.S.C ("Proprietary Information"). Any use,
 * reproduction, distribution or disclosure of the software or Proprietary
 * Information, in whole or in part, must comply with the terms of the license
 * agreement, nondisclosure agreement or contract entered into with clinic
 * providing access to this software. Project name : clinic-api<br>
 * File name : SubcategoryTranslatorImpl.java<br>
 * <p>
 * Changes History <br>
 * Date Person Reason<br>
 * Mar 7, 2016 dailq Initial<br>
 * </p>
 *
 * @author dailq
 *         =====================================================================
 *         ========
 */
package com.clinic.clinic.api.translator.impl;

import com.clinic.clinic.api.persistence.entity.SubcategoryEntity;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.common.dto.biz.SubcategoryDto;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author dailq<br>
 * @version 1.0<br>
 * @see TODO
 */
public class SubcategoryTranslatorImpl extends AbstractTranslatorImpl<SubcategoryDto, SubcategoryEntity>
        implements
            ITranslator<SubcategoryDto, SubcategoryEntity> {

    /*
     * (non-Javadoc)
     * @see
     * com.clinic.clinic.api.translator.ITranslator#getDto(com.clinic.clinic.api
     * .persistence.entity.IdEntity)
     */
    @Override
    public SubcategoryDto getDto(SubcategoryEntity ent) {
        SubcategoryDto dto = new SubcategoryDto();
        this.entityToDto(ent, dto);
        return dto;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.clinic.clinic.api.translator.ITranslator#getEntity(com.clinic.clinic.
     * common.dto.IdDto)
     */
    @Override
    public SubcategoryEntity getEntity(SubcategoryDto dto) {
        SubcategoryEntity ent = new SubcategoryEntity();
        this.dtoToEntity(dto, ent);
        return ent;
    }
}
