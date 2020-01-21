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
 * File name     : RateRepositoryImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 20, 2016				Vuong Do				Initial<br>
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.RateEntity;
import com.clinic.clinic.api.persistence.repository.IRateRepository;
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
public class RateRepositoryImpl extends AbsRepositoryImpl<RateEntity, Integer> implements IRateRepository {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RateRepositoryImpl.class);

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IRateRepository#findRateEntityByMedicarId(java.lang.Integer)
     */
    @Override
    public RateEntity findRateEntityByMedicarId(Integer medicarId) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        RateEntity retEnt = null;
        try {
            Specification<RateEntity> spec = new Specification<RateEntity>() {
                @Override
                public Predicate toPredicate(Root<RateEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return getPredicateParentNotDeleted(root, medicarId, "medicar", IDbConstants.FALSE);
                }
            };
            retEnt = findOne(spec);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retEnt;
    }
}
