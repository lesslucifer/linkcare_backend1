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
 * File name     : CategoryServiceImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 6, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.bizlogic.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.ICategoryService;
import com.clinic.clinic.api.persistence.entity.CategoryEntity;
import com.clinic.clinic.api.persistence.repository.ICategoryRepository;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.api.translator.impl.CategoryTranslatorImpl;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.dto.biz.CategoryDto;
import com.clinic.clinic.common.exception.BizlogicException;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
@ApplicationService
public class CategoryServiceImpl extends AbsService implements ICategoryService {

    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
    
    @Autowired
    private ICategoryRepository categoryRepo;
    private ITranslator<CategoryDto, CategoryEntity> categoryTrans = new CategoryTranslatorImpl();
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.ICategoryService#getAllCategory(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<CategoryDto> getAllCategory(Pageable range) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Page<CategoryDto> pageDto = null;
        try {
            List<CategoryEntity> ents = categoryRepo.findAll(range).getContent();
            pageDto = new PageImpl<CategoryDto>(categoryTrans.getDtoList(ents));
        } catch (BizlogicException be) {
            LOGGER.error("Error", be);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return pageDto;
    }
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.ICategoryService#getCategoriesByMajorId(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<CategoryDto> getCategoriesByMajorId(Pageable range, Integer majorId) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Page<CategoryDto> pageDto = null;
        try {
            List<CategoryEntity> ents = categoryRepo.findCategoriesByMajorId(range, majorId).getContent();
            pageDto = new PageImpl<CategoryDto>(categoryTrans.getDtoList(ents));
        } catch (BizlogicException be) {
            LOGGER.error("Error", be);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return pageDto;
    }
}
