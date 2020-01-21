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
 * File name     : RateTraceRepositoryImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Apr 7, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.repository.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.RateTraceEntity;
import com.clinic.clinic.api.persistence.repository.IRateTraceRepository;
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
public class RateTraceRepositoryImpl extends AbsRepositoryImpl<RateTraceEntity, Integer> implements IRateTraceRepository {

    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RateTraceRepositoryImpl.class);
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IRateTraceRepository#findRateTraceEntityByAppointmentBookingId(java.lang.Integer)
     */
    @Override
    public List<RateTraceEntity> findRateTraceEntityByAppointmentBookingId(List<Integer> appointmentId) {   
        Specification<RateTraceEntity> spec = new Specification<RateTraceEntity>() {
            
            @Override
            public Predicate toPredicate(Root<RateTraceEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate pre = getPredicateParentNotDeleted(root, appointmentId, "appointmentBooking", IDbConstants.FALSE);
                return cb.and(pre);
            }
        };
        return findAll(spec);
    }
}
