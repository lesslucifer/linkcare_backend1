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
 * File name     : SessionLogRepository.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 19, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.repository.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.SessionLogEntity;
import com.clinic.clinic.api.persistence.repository.ISessionLogRepository;
import com.clinic.clinic.common.consts.IConstants;

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
public class SessionLogRepository extends AbsRepositoryImpl<SessionLogEntity, Integer> implements ISessionLogRepository {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionLogRepository.class);

	@Override
	public Integer getAccountIdForSession(String session) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        try {
            final EntityManager entityManager = getEntityManager();
            
            Query query = null;
            
            final StringBuilder summaryQuerySql = new StringBuilder();
            summaryQuerySql.append("SELECT account_id, expired_time ");
            summaryQuerySql.append("FROM `session_log` sl ");
            summaryQuerySql.append("WHERE sl.session_id = :session_id ");
            summaryQuerySql.append("ORDER BY login_time DESC ");
            summaryQuerySql.append("LIMIT 1");
            query = entityManager.createNativeQuery(summaryQuerySql.toString());
            
            query.setParameter("session_id", session);
            
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            
            if (result == null || result.isEmpty()) {
            	return null;
            }
            
            final Object[] row = result.get(0);
            final int uid = (Integer) row[0];
            final long expired_time = ((BigInteger) row[1]).longValue();
            
            long now = System.currentTimeMillis();
            if (now >= expired_time) {
                LOGGER.debug("Session " + session + " is expired!");
                return null;
            }
            
            return uid;
        } catch (Exception e) {
            LOGGER.error("error", e);
        } finally {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        
		return null;
	}
}
