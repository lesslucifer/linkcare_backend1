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
 * File name     : SubcategoryServiceImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 7, 2016				Vuong Do				Initial<br>
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
import com.clinic.clinic.api.bizlogic.service.ISubcategoryService;
import com.clinic.clinic.api.persistence.entity.SubcategoryEntity;
import com.clinic.clinic.api.persistence.repository.ISubcategoryRepository;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.api.translator.impl.SubcategoryTranslatorImpl;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.dto.biz.SubcategoryDto;
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
public class SubcategoryServiceImpl extends AbsService implements ISubcategoryService {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubcategoryServiceImpl.class);

    @Autowired
    private ISubcategoryRepository subcategoryRepo;
    
    private ITranslator<SubcategoryDto, SubcategoryEntity> subcategoryTrans = new SubcategoryTranslatorImpl();
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.ISubcategoryService#getAllSubcategory(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<SubcategoryDto> getSubcategoryByCateId(Pageable range, Integer cateId) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Page<SubcategoryDto> pageDto = null;
        try {
            List<SubcategoryEntity> ents = subcategoryRepo.findSubcategoryByCateId(range, cateId).getContent();
            pageDto = new PageImpl<SubcategoryDto>(subcategoryTrans.getDtoList(ents));
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
     * @see com.clinic.clinic.api.bizlogic.service.ISubcategoryService#getSubcategoryByMajorId(org.springframework.data.domain.Pageable, java.lang.Integer)
     */
    @Override
    public Page<SubcategoryDto> getSubcategoryByMajorId(Pageable range,final Integer majorId) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Page<SubcategoryDto> pageDto = null;
        try {
            List<SubcategoryEntity> ents = subcategoryRepo.findSubcategoryByMajor(range, majorId).getContent();
            pageDto = new PageImpl<SubcategoryDto>(subcategoryTrans.getDtoList(ents));
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
	@Override
	public List<SubcategoryDto> getAllSubcategories(Integer majorId) {
		List<SubcategoryEntity> entities = subcategoryRepo.getAllSubcategoryByMajor(majorId);
		return subcategoryTrans.getDtoList(entities);
	}
}
