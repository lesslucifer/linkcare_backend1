/**
 * =============================================================================
 * = CLINIC JSC (www.clinic.vn) Proprietary. Copyright 2016 CLINIC JSC.
 * UNPUBLISHED WORK ALL RIGHTS RESERVED This software is the confidential and
 * proprietary information of clinic J.S.C ("Proprietary Information"). Any use,
 * reproduction, distribution or disclosure of the software or Proprietary
 * Information, in whole or in part, must comply with the terms of the license
 * agreement, nondisclosure agreement or contract entered into with clinic
 * providing access to this software. Project name : clinic-api<br>
 * File name : SubcategoryRepositoryImpl.java<br>
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
package com.clinic.clinic.api.persistence.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.CategoryEntity;
import com.clinic.clinic.api.persistence.entity.MajorEntity;
import com.clinic.clinic.api.persistence.entity.SubcategoryEntity;
import com.clinic.clinic.api.persistence.repository.ISubcategoryRepository;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author dailq<br>
 * @version 1.0<br>
 * @see TODO
 */
@Repository
public class SubcategoryRepositoryImpl extends AbsRepositoryImpl<SubcategoryEntity, Integer>
        implements
            ISubcategoryRepository {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubcategoryRepositoryImpl.class);
    /*
     * (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.ISubcategoryRepository#
     * findSubcategoryByCateId(org.springframework.data.domain.Pageable,
     * java.lang.Integer)
     */
    @Override
    public Page<SubcategoryEntity> findSubcategoryByCateId(final Pageable range, final Integer cateId) {
        
        Specification<SubcategoryEntity> spec = new Specification<SubcategoryEntity>() {
            @Override
            public Predicate toPredicate(Root<SubcategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                final Predicate parentPre = getPredicateParentNotDeleted(root, cateId, IDbConstants.FIELD_FK_CATEGORY,
                        IDbConstants.FALSE);
                return cb.and(parentPre);
            }
        };
        Page<SubcategoryEntity> entity = findAll(spec, range);
        return entity;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.ISubcategoryRepository#findSubcategoryByMajor(org.springframework.data.domain.Pageable, java.lang.Integer)
     */
    @Override
    public Page<SubcategoryEntity> findSubcategoryByMajor(final Pageable range, final Integer majorId) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Page<SubcategoryEntity> retVal = null;
        try {
            /*
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<SubcategoryEntity> cq = cb.createQuery(SubcategoryEntity.class);
            Root<SubcategoryEntity> root = cq.from(SubcategoryEntity.class);
            final Join<SubcategoryEntity, CategoryEntity> joinCategory = root.join("category");
            final Join<CategoryEntity, MajorEntity> jointMajor = joinCategory.join("major");
            Predicate pre = cb.and(jointMajor.in(majorId));
            findAll();
            */
            Specification<SubcategoryEntity> spec = new Specification<SubcategoryEntity>() {
                
                @Override
                public Predicate toPredicate(Root<SubcategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    final Join<SubcategoryEntity, CategoryEntity> joinCategory = root.join(IDbConstants.FIELD_FK_CATEGORY);
                    final Join<CategoryEntity, MajorEntity> jointMajor = joinCategory.join(IDbConstants.FIELD_FK_MAJOR);
                    return cb.and(jointMajor.in(majorId));
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
    
    @Override
    public List<SubcategoryEntity> getAllSubcategoryByMajor(Integer major) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT subcate.* FROM `SubcategoryEntity` as subcate ");
		sb.append("WHERE subcate.category.major.id = :majorId");
		
		TypedQuery<SubcategoryEntity> q = getEntityManager().createQuery(sb.toString(), SubcategoryEntity.class);
		q.setParameter("major", major);

		return q.getResultList();
    }
}