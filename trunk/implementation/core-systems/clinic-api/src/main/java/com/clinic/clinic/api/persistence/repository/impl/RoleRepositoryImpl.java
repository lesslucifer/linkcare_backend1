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
 * File name     : RoleRepositoryImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 20, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.repository.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.AppointmentBookingEntity;
import com.clinic.clinic.api.persistence.entity.RoleEntity;
import com.clinic.clinic.api.persistence.repository.IRoleRepository;
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
public class RoleRepositoryImpl extends AbsRepositoryImpl<RoleEntity, Integer> implements IRoleRepository {

    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleRepositoryImpl.class);

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IRoleRepository#findRoleByAccountId(java.lang.Integer)
     */
    @Override
    public List<RoleEntity> findRoleByAccountId(final Integer accountId) {
        
        Specification<RoleEntity> spec = new Specification<RoleEntity>() {
            
            @Override
            public Predicate toPredicate(Root<RoleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate pre = getPredicateParentNotDeleted(root, accountId, "accounts", IDbConstants.FALSE);
                return cb.and(pre);
            }
        };
        return findAll(spec);
    }
    
    public boolean isHasRole(Integer userId, String roleCode) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT `role_id` FROM `account_role` AS ar ");
		sb.append("INNER JOIN `role` AS r ON r.id = ar.role_id ");
		sb.append("WHERE ar.account_id = :user_id and r.code = :role_code ");

		Query q = getEntityManager().createNativeQuery(sb.toString());
		q.setParameter("user_id", userId);
		q.setParameter("role_code", roleCode);

		List<Object[]> result = q.getResultList();
		
		return result != null && !result.isEmpty();
    }
}
