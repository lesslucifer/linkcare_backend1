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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.dto.biz.AccountCustomDto;
import com.clinic.clinic.common.dto.biz.AccountFilterDto;
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
        final long now = System.currentTimeMillis();
        List<AccountCustomDto> resultDto = new ArrayList<AccountCustomDto>();
        Page<AccountCustomDto> retVal = null;
        try {
            final EntityManager entityManager = getEntityManager();
            
            List<String> nameWords = StringUtils.isEmpty(accountFilterDto.getName()) ? Collections.emptyList() : Arrays.asList(accountFilterDto.getName().split("[\\s+\\,]"));
            nameWords = nameWords.stream().map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.toList());
            Query query = null;
            
            final StringBuilder summaryQuerySql = new StringBuilder();
            summaryQuerySql.append("SELECT e.id"
                    + ", e.lastName"
                    + ", e.midleName"
                    + ", e.firstName"
                    + ", medicarProfile.graduatedYear"
                    + ", subCate.name"
                    + ", cate.name"
                    + ", major.name"
                    + ", address.address as address"
                    + ", address.longtitude"
                    + ", address.latitude"
                    + ", plcAddr.address as plcAddr"
                    + ", plcAddr.longtitude"
                    + ", plcAddr.latitude"
                    + ", e.avatar"
                    + ", rate.mark "
                    + ", medicarProfile.clinicPrice "
                    + ", medicarProfile.patientHomePrice ");
            summaryQuerySql.append("FROM " + AccountEntity.class.getName() + " e ");
            summaryQuerySql.append("JOIN e.address address ");
            summaryQuerySql.append("JOIN e.place plc ");
            summaryQuerySql.append("JOIN plc.address plcAddr ");
            summaryQuerySql.append("JOIN e.subcategory subCate ");
            summaryQuerySql.append("JOIN subCate.category cate ");
            summaryQuerySql.append("JOIN cate.major major ");
            summaryQuerySql.append("JOIN e.medicarProfile medicarProfile ");
            summaryQuerySql.append("LEFT JOIN e.rate rate ");
            
            summaryQuerySql.append("WHERE 1 = 1 ");
            if(nameWords.size() > 0) {
            	summaryQuerySql.append("AND (1 != 1 ");
            	for (int i = 0; i < nameWords.size(); ++i) {
            		String whereStm = String.format("OR e.firstName LIKE :nameWord%d OR e.lastName LIKE :nameWord%d ", i, i);
                    summaryQuerySql.append(whereStm);
            	}
            	summaryQuerySql.append(") ");
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

            summaryQuerySql.append("AND NOT( medicarProfile.expiredTime <= :now ");
            summaryQuerySql.append("AND medicarProfile.overloadedAppointments > :maxOverloadAppointments)  ");
            
            query = entityManager.createQuery(summaryQuerySql.toString());
            // set parameter
            if(nameWords.size() > 0) {
            	for (int i = 0; i < nameWords.size(); ++i) {
            		String word = nameWords.get(i);
            		String parName = String.format("nameWord%d", i);
            		query.setParameter(parName, word);
            	}
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
            
            query.setParameter("now", now);
            query.setParameter("maxOverloadAppointments", IConstants.MAX_OVERLOAD_APPOINTMENTS);
            
            query.setFirstResult(range.getOffset());
            query.setMaxResults(range.getPageSize());
            
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (Object[] object : result) {
                AccountCustomDto acct = new AccountCustomDto();
                acct.setId((Integer)object[0]);
                acct.setLastName((String) object[1]);
                acct.setMidleName((String) object[2]);
                acct.setFirstName((String) object[3]);
                int graduatedYear = object[4] == null ? currentYear : ((Number)object[4]).intValue();
                acct.setExperience(Math.max(1.0, currentYear - graduatedYear));
                acct.setSubcategory((String)object[5]);
                acct.setCategory((String)object[6]);
                acct.setMajor((String)object[7]);
                acct.setAddressHome((String)object[8]);
                acct.setHomeLongtitude((Double)object[9]);
                acct.setHomeLatitude((Double)object[10]);
                acct.setAddressClinic((String)object[11]);
                acct.setClinicLongtitude((Double)object[12]);
                acct.setClinicLatitude((Double)object[13]);
                acct.setAvatar((String)object[14]);
                acct.setName(acct.getFirstName(), acct.getMidleName(), acct.getLastName());
                acct.setMark(object[15] == null ? 0 : Double.parseDouble(object[15].toString()));
                acct.setCostClinic(((Number) object[16]).doubleValue());
                acct.setCostHome(((Number) object[17]).doubleValue());
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
        final long now = System.currentTimeMillis();
        List<AccountCustomDto> resultDto = new ArrayList<AccountCustomDto>();
        Page<AccountCustomDto> retVal = null;
        try {
            final EntityManager entityManager = getEntityManager();

            List<String> nameWords = StringUtils.isEmpty(accountFilterDto.getName()) ? Collections.emptyList() : Arrays.asList(accountFilterDto.getName().split("[\\s+\\,]"));
            nameWords = nameWords.stream().map(s -> s.trim()).filter(s -> !s.isEmpty()).collect(Collectors.toList());
            Query query = null;
            
            final StringBuilder summaryQuerySql = new StringBuilder();
            summaryQuerySql.append("SELECT e.id"
                    + ", e.last_name"
                    + ", e.midle_name"
                    + ", e.first_name"
                    + ", mp.graduated_year"
                    + ", subCate.name as subcateName"
                    + ", cate.name as cateName"
                    + ", major.name as majorName"
                    + ", address.address as homeAddr"
                    + ", address.longtitude as homeLongtitude"
                    + ", address.latitude as homeLatitude"
                    + ", plcAddr.address as plcAddr"
                    + ", plcAddr.longtitude as placeLongtitude"
                    + ", plcAddr.latitude as placeLatitude"
                    + ", e.image_url"
                    + ", GeoDistDiff('km', :latitude, :longtitude, address.latitude, address.longtitude) * 1000 as distance "
                    + ", coalesce(rate.mark, 0) as mark_medicar "
                    + ", mp.clinic_price as clinic_price "
                    + ", mp.patient_home_price as patient_home_price "
                    + "FROM account e ");
            summaryQuerySql.append("JOIN address address ON e.address_id = address.id ");
            summaryQuerySql.append("LEFT JOIN place plc ON e.place_id = plc.id  ");
            summaryQuerySql.append("LEFT JOIN address plcAddr ON plc.address_id = plcAddr.id ");
            summaryQuerySql.append("JOIN subcategory subCate ON e.subcategory_id = subCate.id ");
            summaryQuerySql.append("JOIN category cate ON cate.id = subCate.category_id ");
            summaryQuerySql.append("JOIN major ON major.id = cate.major_id ");
            summaryQuerySql.append("JOIN medicar_profile mp ON mp.account_id = e.id ");
            summaryQuerySql.append("LEFT JOIN rate rate ON rate.medicar_id = e.id ");

            
            summaryQuerySql.append("WHERE 1 = 1 ");
            if(nameWords.size() > 0) {
            	summaryQuerySql.append("AND (1 != 1 ");
            	for (int i = 0; i < nameWords.size(); ++i) {
            		String whereStm = String.format("OR e.first_name LIKE :nameWord%d OR e.last_name LIKE :nameWord%d ", i, i);
                    summaryQuerySql.append(whereStm);
            	}
            	summaryQuerySql.append(") ");
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

            summaryQuerySql.append("AND NOT (mp.expired_time <= :now ");
            summaryQuerySql.append("AND mp.overloaded_appointments > :maxOverloadAppointments) ");
            
            query = entityManager.createNativeQuery(summaryQuerySql.toString());
            // set parameter
            if(nameWords.size() > 0) {
            	for (int i = 0; i < nameWords.size(); ++i) {
            		String word = nameWords.get(i);
            		String parName = String.format("nameWord%d", i);
            		query.setParameter(parName, word);
            	}
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
            
            query.setParameter("now", now);
            query.setParameter("maxOverloadAppointments", IConstants.MAX_OVERLOAD_APPOINTMENTS);
            
            query.setFirstResult(range.getOffset());
            query.setMaxResults(range.getPageSize());
            
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (Object[] object : result) {
                AccountCustomDto acct = new AccountCustomDto();
                acct.setId((Integer)object[0]);
                acct.setLastName((String) object[1]);
                acct.setMidleName((String) object[2]);
                acct.setFirstName((String) object[3]);
                Calendar graduatedYear = Calendar.getInstance();
                if (object[4] != null) {
                	graduatedYear.setTime((Date) object[4]);
                }
                acct.setExperience(Math.max(1.0, currentYear - graduatedYear.get(Calendar.YEAR)));
                acct.setSubcategory((String)object[5]);
                acct.setCategory((String)object[6]);
                acct.setMajor((String)object[7]);
                acct.setAddressHome((String)object[8]);
                acct.setHomeLongtitude((Double)object[9]);
                acct.setHomeLatitude((Double)object[10]);
                acct.setAddressClinic((String)object[11]);
                acct.setClinicLongtitude((Double)object[12]);
                acct.setClinicLatitude((Double)object[13]);
                acct.setAvatar((String)object[14]);
                acct.setDistance(Double.parseDouble(object[15].toString()));
                acct.setName(acct.getFirstName(), acct.getMidleName(), acct.getLastName());
                acct.setMark(object[16] == null ? 0 : Double.parseDouble(object[16].toString()));
                acct.setCostClinic(((Number) object[17]).doubleValue());
                acct.setCostHome(((Number) object[18]).doubleValue());
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

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IAccountRepository#findAccountByIdCard(java.lang.String)
     */
    @Override
    public AccountEntity findAccountByIdCard(String idCard) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        AccountEntity ent = null;
        try {
            Specification<AccountEntity> spec = new Specification<AccountEntity>() {
                @Override
                public Predicate toPredicate(Root<AccountEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return cb.equal(root.get(IDbConstants.FIELD_ACC_ID_CARD), idCard);
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

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IAccountRepository#findAccountByEmail(java.lang.String)
     */
    @Override
    public AccountEntity findAccountByEmail(String email) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        AccountEntity ent = null;
        try {
            Specification<AccountEntity> spec = new Specification<AccountEntity>() {
                @Override
                public Predicate toPredicate(Root<AccountEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return cb.equal(root.get(IDbConstants.FIELD_ACC_EMAIL), email);
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