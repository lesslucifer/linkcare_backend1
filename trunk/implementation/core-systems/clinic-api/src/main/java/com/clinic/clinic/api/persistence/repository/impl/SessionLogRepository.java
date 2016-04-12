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


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.SessionLogEntity;
import com.clinic.clinic.api.persistence.repository.ISessionLogRepository;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;
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
@Repository
public class SessionLogRepository extends AbsRepositoryImpl<SessionLogEntity, Integer> implements ISessionLogRepository {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionLogRepository.class);

	@Override
	public Integer getAccountIdForSession(String session) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Integer ret = null;
        try {
            Specification<SessionLogEntity> spec = new Specification<SessionLogEntity>() {
                @Override
                public Predicate toPredicate(Root<SessionLogEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return cb.equal(root.get("sessionId"), session);
                }
            };
            SessionLogEntity ent = findOne(spec);
            ret = ent.getAccount().getId();
        } catch (Exception e) {
            LOGGER.error("error", e);
        } finally {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
		return ret;
    }

	public SessionLogEntity findSessionLogByAccountId(final Integer accountId, final Integer sessionId) {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug(IConstants.BEGIN_METHOD);
		}
		SessionLogEntity retEnt = null;
		try {
			Specification<SessionLogEntity> spec = new Specification<SessionLogEntity>() {
				
				@Override
				public Predicate toPredicate(Root<SessionLogEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					final Join<SessionLogEntity, AccountEntity> joinAccount = root.join(IDbConstants.FIELD_FK_ACCOUNT);
					return cb.and(cb.equal(joinAccount.get(IDbConstants.FIELD_FK_ACCOUNT), accountId), cb.equal(joinAccount.get("sessionId"), sessionId));
				}
			};
			retEnt = findOne(spec, false);
		} catch (BizlogicException be) {
			LOGGER.error("Error", be);
		} catch (Exception e) {
			LOGGER.error("Error", e);
		} finally {
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug(IConstants.END_METHOD);
			}
		}
		return retEnt;
	}

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.ISessionLogRepository#findSessionLogByAccountId(java.lang.Integer)
     */
    @Override
    public SessionLogEntity findSessionLogByAccountId(Integer id) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        SessionLogEntity ret = null;
        try {
            Specification<SessionLogEntity> spec = new Specification<SessionLogEntity>() {
                @Override
                public Predicate toPredicate(Root<SessionLogEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return getPredicateParentNotDeleted(root, id, IDbConstants.FIELD_FK_ACCOUNT, IDbConstants.FALSE);
                }
            };
            ret = findOne(spec);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return ret;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.ISessionLogRepository#findAccountEntBySessionId(java.lang.String)
     */
    @Override
    public AccountEntity findAccountEntBySessionId(String sess) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        AccountEntity ret = null;
        try {
            Specification<SessionLogEntity> spec = new Specification<SessionLogEntity>() {
                @Override
                public Predicate toPredicate(Root<SessionLogEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return cb.equal(root.get("sessionId"), sess);
                }
            };
            SessionLogEntity ent = findOne(spec);
            ret = ent.getAccount();
        } catch (Exception e) {
            LOGGER.error("error", e);
        } finally {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return ret;
    }
}
