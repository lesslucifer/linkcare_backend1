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
 * File name     : CategoryRepositoryImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 6, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.repository.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.CategoryEntity;
import com.clinic.clinic.api.persistence.repository.ICategoryRepository;
import com.clinic.clinic.api.ws.biz.AccountRestApi;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
@Repository
public class CategoryRepositoryImpl extends AbsRepositoryImpl<CategoryEntity, Integer> implements ICategoryRepository {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestApi.class);
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.ICategoryRepository#findCategoriesByMajorId(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<CategoryEntity> findCategoriesByMajorId(Pageable range, final Integer majorId) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Page<CategoryEntity> retVal = null;
        try {
            Specification<CategoryEntity> spec = new Specification<CategoryEntity>() {
                @Override
                public Predicate toPredicate(Root<CategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    final Predicate parentPre = getPredicateParentNotDeleted(root, majorId, IDbConstants.FIELD_FK_MAJOR,
                            IDbConstants.FALSE);
                    return cb.and(parentPre);
                }
            };
            retVal = findAll(spec, range);
        } catch (Exception e) {
            LOGGER.error("error", e);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retVal;
    }
    
}