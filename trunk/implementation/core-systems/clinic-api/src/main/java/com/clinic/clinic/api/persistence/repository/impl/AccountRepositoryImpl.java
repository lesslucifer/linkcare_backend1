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
 * File name     : AccountRepositoryImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 7, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.ClinicRightEntity;
import com.clinic.clinic.api.persistence.entity.RoleEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.dto.biz.AccountCustomDto;
import com.clinic.clinic.common.dto.biz.AccountFilterDto;
import com.clinic.clinic.common.exception.BizlogicException;
import com.clinic.clinic.common.utils.StringUtil;

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
public class AccountRepositoryImpl extends AbsRepositoryImpl<AccountEntity, Integer> implements IAccountRepository {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRepositoryImpl.class);

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IAccountRepository#findAccountBySubcategoryId(org.springframework.data.domain.Pageable, java.lang.Integer)
     */
    @Override
    public Page<AccountEntity> findAccountBySubcategoryId(final Pageable range,final Integer subcategoryId) {
        Specification<AccountEntity> spec = new Specification<AccountEntity>() {
            @Override
            public Predicate toPredicate(Root<AccountEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                final Predicate parentPre = getPredicateParentNotDeleted(root, subcategoryId, IDbConstants.FIELD_FK_SUBCATEGORY,
                        IDbConstants.FALSE);
                return cb.and(parentPre);
            }
        };
        Page<AccountEntity> entity = findAll(spec, range);
        return entity;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IAccountRepository#findAccountAndClinic(org.springframework.data.domain.Pageable, com.clinic.clinic.common.dto.biz.AccountFilterDto)
     */
    @Override
    public Page<AccountCustomDto> findAccountAndClinic(final Pageable range, final AccountFilterDto accountFilterDto) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        List<AccountCustomDto> resultDto = new ArrayList<AccountCustomDto>();
        Page<AccountCustomDto> retVal = null;
        try {
            final EntityManager entityManager = getEntityManager();
            
            Query query = null;
            
            final StringBuilder summaryQuerySql = new StringBuilder();
            summaryQuerySql.append("SELECT e.id"
                    + ", e.lastName"
                    + ", e.midleName"
                    + ", e.firstName"
                    + ", e.experience"
                    + ", subCate.name"
                    + ", cate.name"
                    + ", major.name"
                    + ", cost.price as homePrice"
                    + ", placeCost.price as placeCost"
                    + ", concat(address.houseNumber,', ',address.street,', ',address.ward,', ',address.district,', ',address.city) as address"
                    + ", address.longtitude"
                    + ", address.latitude"
                    + ", concat(plcAddr.houseNumber,', ',plcAddr.street,', ', plcAddr.ward,', ', plcAddr.district,', ', plcAddr.city) as plcAddr"
                    + ", plcAddr.longtitude"
                    + ", plcAddr.latitude"
                    + ", e.avatar FROM " + AccountEntity.class.getName() + " e ");
            summaryQuerySql.append("JOIN e.address address ");
            summaryQuerySql.append("JOIN e.cost cost ");
            summaryQuerySql.append("JOIN e.place plc ");
            summaryQuerySql.append("JOIN plc.address plcAddr ");
            summaryQuerySql.append("JOIN plc.cost placeCost ");
            summaryQuerySql.append("JOIN e.subcategory subCate ");
            summaryQuerySql.append("JOIN subCate.category cate ");
            summaryQuerySql.append("JOIN cate.major major ");
            
            summaryQuerySql.append("WHERE 1 = 1 ");
            if(!StringUtil.isEmpty(accountFilterDto.getName())) {
                summaryQuerySql.append("AND e.firstName LIKE :firstName ");
            }
            if(accountFilterDto.getSubcategory() != null) {
                summaryQuerySql.append("AND subCate.id=:subCate ");
            }
            if(accountFilterDto.getCategory() != null) {
                summaryQuerySql.append("AND cate.id=:cate ");
            }
            if(accountFilterDto.getMajor() != null) {
                summaryQuerySql.append("AND major.id=:major ");
            }
            query = entityManager.createQuery(summaryQuerySql.toString());
            // set parameter
            if(!StringUtil.isEmpty(accountFilterDto.getName())) {
                query.setParameter("firstName","%" + accountFilterDto.getName() + "%");
            }
            if(accountFilterDto.getSubcategory() != null) {
                query.setParameter("subCate", accountFilterDto.getSubcategory());
            }
            if(accountFilterDto.getCategory() != null) {
                query.setParameter("cate", accountFilterDto.getCategory());
            }
            if(accountFilterDto.getMajor() != null) {
                query.setParameter("major", accountFilterDto.getMajor());
            }
            query.setFirstResult(range.getOffset());
            query.setMaxResults(range.getPageSize());
            
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            
            for (Object[] object : result) {
                AccountCustomDto acct = new AccountCustomDto();
                acct.setId((Integer)object[0]);
                acct.setLastName((String) object[1]);
                acct.setMidleName((String) object[2]);
                acct.setFirstName((String) object[3]);
                acct.setExperience((Double)object[4]);
                acct.setSubcategory((String)object[5]);
                acct.setCategory((String)object[6]);
                acct.setMajor((String)object[7]);
                acct.setCostHome((Double)object[8]);
                acct.setCostClinic((Double)object[9]);
                acct.setAddressHome((String)object[10]);
                acct.setHomeLongtitude((Double)object[11]);
                acct.setHomeLatitude((Double)object[12]);
                acct.setAddressClinic((String)object[13]);
                acct.setClinicLongtitude((Double)object[14]);
                acct.setClinicLatitude((Double)object[15]);
                acct.setAvatar((String)object[16]);
                acct.setName(acct.getFirstName(), acct.getMidleName(), acct.getLastName());
                resultDto.add(acct);
            }
            
            retVal = new PageImpl<AccountCustomDto>(resultDto);
            
        } catch (Exception e) {
            LOGGER.error("error", e);
        } finally {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retVal;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IAccountRepository#findAccountAndHome(org.springframework.data.domain.Pageable, com.clinic.clinic.common.dto.biz.AccountFilterDto)
     */
    @Override
    public Page<AccountCustomDto> findAccountAndHome(final Pageable range,final AccountFilterDto accountFilterDto) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        List<AccountCustomDto> resultDto = new ArrayList<AccountCustomDto>();
        Page<AccountCustomDto> retVal = null;
        try {
            final EntityManager entityManager = getEntityManager();
            
            Query query = null;
            
            final StringBuilder summaryQuerySql = new StringBuilder();
            summaryQuerySql.append("SELECT e.id"
                    + ", e.last_name"
                    + ", e.midle_name"
                    + ", e.first_name"
                    + ", e.experience"
                    + ", subCate.name as subcateName"
                    + ", cate.name as cateName"
                    + ", major.name as majorName"
                    + ", cost.price as homePrice"
                    + ", placeCost.price as placeCost"
                    + ", concat(address.house_number,', ',address.street,', ',address.ward,', ',address.district,', ',address.city) as homeAddr"
                    + ", address.longtitude as homeLongtitude"
                    + ", address.latitude as homeLatitude"
                    + ", concat(plcAddr.house_number,', ',plcAddr.street,', ', plcAddr.ward,', ', plcAddr.district,', ', plcAddr.city) as plcAddr"
                    + ", plcAddr.longtitude as placeLongtitude"
                    + ", plcAddr.latitude as placeLatitude"
                    + ", e.image_url"
                    + ", GeoDistDiff('km', :latitude, :longtitude, address.latitude, address.longtitude) * 1000 as distance "
                    + "FROM account e ");
            summaryQuerySql.append("JOIN address address ON e.address_id = address.id ");
            summaryQuerySql.append("JOIN cost cost ON e.cost_id = cost.id ");
            summaryQuerySql.append("JOIN place plc ON e.place_id = plc.id  ");
            summaryQuerySql.append("JOIN address plcAddr ON plc.address_id = plcAddr.id ");
            summaryQuerySql.append("JOIN cost placeCost  ON plc.cost_id = placeCost.id ");
            summaryQuerySql.append("JOIN subcategory subCate ON e.subcategory_id = subCate.id ");
            summaryQuerySql.append("JOIN category cate ON cate.id = subCate.category_id ");
            summaryQuerySql.append("JOIN major ON major.id = cate.major_id ");
            
            summaryQuerySql.append("WHERE 1 = 1 ");
            if(!StringUtil.isEmpty(accountFilterDto.getName())) {
                summaryQuerySql.append("AND e.first_name LIKE :firstName ");
            }
            if(accountFilterDto.getSubcategory() != null) {
                summaryQuerySql.append("AND subCate.id=:subCate ");
            }
            if(accountFilterDto.getCategory() != null) {
                summaryQuerySql.append("AND cate.id=:cate ");
            }
            if(accountFilterDto.getMajor() != null) {
                summaryQuerySql.append("AND major.id=:major ");
            }
            if(accountFilterDto.getLatitude() != null && accountFilterDto.getLongtitude() != null) {
                summaryQuerySql.append("AND GeoDistDiff('km', :latitude, :longtitude, address.latitude, address.longtitude) <= 100 ");
            }
            query = entityManager.createNativeQuery(summaryQuerySql.toString());
            // set parameter
            if(!StringUtil.isEmpty(accountFilterDto.getName())) {
                query.setParameter("firstName","%" + accountFilterDto.getName() + "%");
            }
            if(accountFilterDto.getSubcategory() != null) {
                query.setParameter("subCate", accountFilterDto.getSubcategory());
            }
            if(accountFilterDto.getCategory() != null) {
                query.setParameter("cate", accountFilterDto.getCategory());
            }
            if(accountFilterDto.getMajor() != null) {
                query.setParameter("major", accountFilterDto.getMajor());
            }
            if(accountFilterDto.getLatitude() != null && accountFilterDto.getLongtitude() != null) {
                query.setParameter("latitude", accountFilterDto.getLatitude());
                query.setParameter("longtitude", accountFilterDto.getLongtitude());
            }
            //query.setFirstResult(range.getOffset());
            //query.setMaxResults(range.getPageSize());
            
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            
            for (Object[] object : result) {
                AccountCustomDto acct = new AccountCustomDto();
                acct.setId((Integer)object[0]);
                acct.setLastName((String) object[1]);
                acct.setMidleName((String) object[2]);
                acct.setFirstName((String) object[3]);
                acct.setExperience((Double)object[4]);
                acct.setSubcategory((String)object[5]);
                acct.setCategory((String)object[6]);
                acct.setMajor((String)object[7]);
                acct.setCostHome((Double)object[8]);
                acct.setCostClinic((Double)object[9]);
                acct.setAddressHome((String)object[10]);
                acct.setHomeLongtitude((Double)object[11]);
                acct.setHomeLatitude((Double)object[12]);
                acct.setAddressClinic((String)object[13]);
                acct.setClinicLongtitude((Double)object[14]);
                acct.setClinicLatitude((Double)object[15]);
                acct.setAvatar((String)object[16]);
                acct.setDistance(Double.parseDouble(object[17].toString()));
                acct.setName(acct.getFirstName(), acct.getMidleName(), acct.getLastName());
                resultDto.add(acct);
            }
            
            retVal = new PageImpl<AccountCustomDto>(resultDto);
            
        } catch (Exception e) {
            LOGGER.error("error", e);
        } finally {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retVal;
    }
    
    public Boolean isAccountHasRight(final Integer accountId, final String right) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        Boolean ret = false;
        try {
  /*          Specification<AccountEntity> spec = new Specification<AccountEntity>() {
                
                @Override
                public Predicate toPredicate(Root<AccountEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    final Join<AccountEntity, RoleEntity> joinRole = root.join("roles");
                    final Join<RoleEntity, ClinicRightEntity> joinRight = joinRole.join("clinicRights");
                    return cb.and(cb.equal(joinRight.get("code"), IDbConstants.RIGHT_RATING),
                            cb.equal(joinRight.get(IDbConstants.FIELD_ID), accountId));
                }
            };
            AccountEntity accEnt = findOne(spec); 
            if(null == accEnt) {
                ret = false;
            } else {
                ret = true;
            }
            */
            
            final EntityManager entityManager = getEntityManager();
            
            Query query = null;
            
            final StringBuilder summaryQuerySql = new StringBuilder();
            summaryQuerySql.append("SELECT 1 "
                    + "FROM `account_role` a ");
            summaryQuerySql.append("JOIN `role` role ON a.role_id = role.id ");
            summaryQuerySql.append("JOIN `role_right` rr ON rr.role_id = role.id ");
            summaryQuerySql.append("JOIN `clinic_right` r ON rr.clinic_right_id = r.id AND r.code = :right ");
            
            summaryQuerySql.append("WHERE a.account_id = :accountId");
            query = entityManager.createNativeQuery(summaryQuerySql.toString());
            
            query.setParameter("accountId", accountId);
            query.setParameter("right", right);
            
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            
            if (result != null && !result.isEmpty()) {
            	return true;
            }
          
        } catch (Exception e) {
            LOGGER.error("error", e);
        } finally {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        
		return ret;
    }
    
    @Override
    public Set<String> checkAccountRights(final Integer accountId, final String[] rights) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        try {
            final EntityManager entityManager = getEntityManager();
            
            Query query = null;
            
            final StringBuilder summaryQuerySql = new StringBuilder();
            summaryQuerySql.append("SELECT r.code "
                    + "FROM `account_role` a ");
            summaryQuerySql.append("JOIN `role` role ON a.role_id = role.id ");
            summaryQuerySql.append("JOIN `role_right` rr ON rr.role_id = role.id ");
            summaryQuerySql.append("JOIN `clinic_right` r ON rr.clinic_right_id = r.id AND r.code IN :rights ");
            
            summaryQuerySql.append("WHERE a.account_id = :accountId");
            query = entityManager.createNativeQuery(summaryQuerySql.toString());
            
            query.setParameter("accountId", accountId);
            query.setParameter("rights", rights);
            
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            
            if (result == null || result.isEmpty()) {
            	return Collections.emptySet();
            }

            return result.stream().map((o) -> ((String) o[0])).collect(Collectors.toSet());
            
        } catch (Exception e) {
            LOGGER.error("error", e);
        } finally {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }

    	return Collections.emptySet();
    }

	@Override
	public AccountEntity findAccountByLoginName(final String loginName) {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug(IConstants.BEGIN_METHOD);
		}
		AccountEntity ent = null;
		try {
			Specification<AccountEntity> spec = new Specification<AccountEntity>() {

				@Override
				public Predicate toPredicate(Root<AccountEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.get(IDbConstants.FIELD_ACC_LOGIN_NAME), loginName);
				}
			};
			ent = findOne(spec);
		} catch (BizlogicException be) {
			LOGGER.error("Error", be);
		} catch (Exception e) {
			LOGGER.error("Error", e);
		} finally {
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug(IConstants.END_METHOD);
			}
		}
		return ent;
	}
}