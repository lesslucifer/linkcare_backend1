/**==============================================================================
 * clinic JSC (www.clinic.vn) Proprietary.
 * Copyright 2015 clinic JSC.
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
 * File name     : AbsRepository.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Aug 20, 2015		daiql				Initial<br>
 * </p>
 *
 * @author daiql
 *=============================================================================*/
package com.clinic.clinic.api.persistence.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.clinic.clinic.api.persistence.entity.IdEntity;
import com.clinic.clinic.api.persistence.entity.NameCodeDescEntity;
import com.clinic.clinic.api.persistence.entity.TraceEntity;
import com.clinic.clinic.api.persistence.repository.IRepository;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;

/**
 * <p>
 * The abstract repository which implements common APIs of a repository.
 * </p>
 *
 * @author daiql<br>
 * @version 1.0<br>
 * @see {@link IRepository}
 */
@NoRepositoryBean
public class AbsRepositoryImpl<T extends IdEntity, ID extends Serializable> implements IRepository<T, ID> {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbsRepositoryImpl.class);
    
    /**
     * The {@link EntityManager}.
     */
   
    private EntityManager entityManager;    
    /**
     * <p>Sets value of entityManager attribute.</p>
     *
     * @param entityManager the entityManager to set
     */
    @PersistenceContext()
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    /**
     * <p>Returns current value of entityManager attribute.</p>
     *
     * @return the entityManager
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    /**
     * The internal repository.
     * @see SimpleJpaRepository
     */
    private SimpleJpaRepository<T, ID> repository;
    /**
     * <p>Initialize.</p>
     */
    @SuppressWarnings("unchecked")
    @PostConstruct
    protected final void init() {
        LOGGER.debug(IConstants.BEGIN_METHOD);
        try {
            final ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
            final Class<T> domainClass = (Class<T>) superclass.getActualTypeArguments()[0];
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Initialize repository for domain: " + domainClass);
            }
            //entityManager = entityManager.getEntityManagerFactory().createEntityManager();
            final JpaEntityInformation<T, ID> entityInfo = (JpaEntityInformation<T, ID>)
                    JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager);
            repository = new SimpleJpaRepository<T, ID>(entityInfo, entityManager);
        } finally {
            LOGGER.debug(IConstants.END_METHOD);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
     */
    @Override
    public List<T> findAll() {
        return doFindAll(null, null, true);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll(java.lang.Iterable)
     */
    @Override
    public List<T> findAll(final Iterable<ID> ids) {
        return doFindIds(ids, true);
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#findAll(org.springframework.data.domain.Sort)
     */
    @Override
    public List<T> findAll(final Sort sort) {
        return doFindAll(null, sort, true);
    }    

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(
     * org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<T> findAll(final Pageable pageable) {
        return doFindPageable(null, pageable, true);
    }    

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#findAll(
     * org.springframework.data.jpa.domain.Specification)
     */
    @Override
    public List<T> findAll(final Specification<T> spec) {
        return doFindAll(spec, null, true);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#findAll(.
     * org.springframework.data.jpa.domain.Specification, org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<T> findAll(final Specification<T> spec, final Pageable pageable) {
        return doFindPageable(spec, pageable, true);
    }
    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#findAll(
     * org.springframework.data.jpa.domain.Specification, org.springframework.data.domain.Sort)
     */
    @Override
    public List<T> findAll(final Specification<T> spec, final Sort sort) {
        return doFindAll(spec, sort, true);
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.webapp.cliniccollect.persistence.repository.IRepository#findAll(
     * org.springframework.data.jpa.domain.Specification, org.springframework.data.domain.Sort, boolean)
     */
    @Override
    public List<T> findAll(Specification<T> spec, Sort sort, boolean filterLogicalDeletion) {
        Specification<T> newSpec = null;
        if (Boolean.TRUE == filterLogicalDeletion) {
            newSpec = buildLogicalDeletion(spec);
        } else {
            newSpec = spec;
        }
        return repository.findAll(newSpec, sort);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#getOne(java.io.Serializable)
     */
    @Override
    public T getOne(final ID id) {
        return doFindOne(id, true);
    }
    
    
    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#flush()
     */
    @Override
    public void flush() {
        repository.flush();
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#save(S)
     */
    @Override
    public <S extends T> S save(final S entity) {
        return doSave(entity);
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#save(java.lang.Iterable)
     */
    @Override
    public <S extends T> List<S> save(final Iterable<S> entities) {
        return doSave(entities);
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#saveAndFlush(S)
     */
    @Override
    public <S extends T> S saveAndFlush(final S entity) {
        return doSaveAndFlush(entity);
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#count()
     */
    @Override
    public long count() {
        return doCount(null, true);
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#deleteAllInBatch()
     */
    @Override
    public void deleteAllInBatch() {
        doDeleteAll(false);
    }
    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaRepository#deleteInBatch(java.lang.Iterable)
     */
    @Override
    public void deleteInBatch(final Iterable<T> entities) {
        doDelete(entities, false);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#delete(java.io.Serializable)
     */
    @Override
    public void delete(final ID id) {
        doDelete(id, false);
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
     */
    @Override
    public void delete(final T entity) {
        doDelete(entity, false);      
    }
    
    /* (non-Javadoc)
     * @see com.clinic.clinic.webapp.cliniccollect.persistence.repository.IRepository#permanentDelete(java.io.Serializable)
     */
    @Override
    public void permanentDelete(ID id) {
        doDelete(id, true);
    }
    
    /* (non-Javadoc)
     * @see com.clinic.clinic.webapp.cliniccollect.persistence.repository.IRepository#permanentDelete(java.lang.Object)
     */
    @Override
    public void permanentDelete(T entity) {
        doDelete(entity, true);
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Iterable)
     */
    @Override
    public void delete(Iterable<? extends T> entities) {
        doDelete(entities, false);
    }
    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#deleteAll()
     */
    @Override
    public void deleteAll() {
        doDeleteAll(false);
    }
    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#exists(java.io.Serializable)
     */
    @Override
    public boolean exists(final ID id) {
        return repository.exists(id);
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#findOne(java.io.Serializable)
     */
    @Override
    public T findOne(final ID id) {
        return doFindOne(id, true);
    }
    
    /* (non-Javadoc)
     * @see com.clinic.clinic.webapp.cliniccollect.persistence.repository.IRepository#findOne(java.io.Serializable, boolean)
     */
    @Override
    public T findOne(ID id, boolean filterLogicalDeletion) {
        return doFindOne(id, filterLogicalDeletion);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#count(
     * org.springframework.data.jpa.domain.Specification)
     */
    @Override
    public long count(final Specification<T> spec) {
        return doCount(spec, true);
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.webapp.cliniccollect.persistence.repository.IRepository#findAll(
     * org.springframework.data.jpa.domain.Specification, boolean)
     */
    @Override
    public List<T> findAll(Specification<T> spec, boolean filterLogicalDeletion) {
        Specification<T> newSpec = null;
        if (Boolean.TRUE == filterLogicalDeletion) {
            newSpec = buildLogicalDeletion(spec);
        } else {
            newSpec = spec;
        }
        return repository.findAll(newSpec);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#findOne(
     * org.springframework.data.jpa.domain.Specification)
     */
    @Override
    public T findOne(final Specification<T> spec) {
        return doFindOne(spec, true);
    }
    
    /* (non-Javadoc)
     * @see com.clinic.clinic.webapp.cliniccollect.persistence.repository.IRepository#findOne(
     * org.springframework.data.jpa.domain.Specification, boolean)
     */
    @Override
    public T findOne(Specification<T> spec, boolean filterLogicalDeletion) {
        T t = doFindOne(spec, filterLogicalDeletion);
        return t;
    }
    
//    /**
//     * <p>Find a specific page.</p>
//     *
//     * @param spec {@link Specification}
//     * @param pageSpec {@link SearchCriteria}
//     * @param sort {@link Sort}
//     * @return {@link Page}
//     */
//    protected final Page<T> findByPage(final Specification<T> spec, final SearchCriteria pageSpec, final Sort sort) {
//        boolean endNormalFlag = false;
//        
//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug(IConstants.BEGIN_METHOD);
//        }
//        
//        try {
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug("Page spec: {}", pageSpec);
//            }
//            int pageNumber = pageSpec.getPageNumber();
//            final int pageSize = pageSpec.getPageSize();
//            final long totalElements = pageSpec.getTotalElements();
//            if (0 >= totalElements) {
//                if (0 < pageNumber) {
//                    LOGGER.warn("First request but page more than zero => Set to first page: requestPageNumer[{}]",
//                            pageNumber);
//                    pageNumber = 0;
//                }
//            } else {
//                int totalPages = (int) (totalElements / pageSize);
//                if (totalElements % pageSize != 0) {
//                    totalPages++;
//                }
//                if (pageNumber >= totalPages -1) {
//                    LOGGER.warn("Over number of pagers => Set to last page: requestPageNumer[{}], lastPage[{}]",
//                            pageNumber, totalPages - 1);
//                    pageNumber = totalPages - 1; //last page
//                }                
//            }
//            Pageable pageable;
//            if (null != sort) {
//                pageable = new PageRequest(pageNumber, pageSize, sort);
//            } else {
//                pageable = new PageRequest(pageNumber, pageSize);
//            }            
//            Page<T> page = findAll(spec, pageable);
//            
//            endNormalFlag = true;
//            
//            return page;
//        } finally {
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug(IConstants.END_METHOD);
//            }
//        }
//    }

//    /**
//     * <p>Find a specific page.</p>
//     *
//     * @param extractSqlStr a Java Persistence query string (extracting)
//     * @param countSqlStr a Java Persistence query string (counting)
//     * @param pageSpec {@link SearchCriteria}
//     * @param resultClass the type of the query result
//     *
//     * @return {@link Page}
//     */
//    protected final Page<T> findByPage(final String extractSqlStr, final String countSqlStr,
//            final SearchCriteria pageSpec, final Class<T> resultClass) {
//        boolean endNormalFlag = false;
//        
//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug(IConstants.BEGIN_METHOD);
//        }
//        
//        try {
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug("Page spec: {}", pageSpec);
//            }
//            int pageNumber = pageSpec.getPageNumber();
//            final int pageSize = pageSpec.getPageSize();
//            final long totalElements = pageSpec.getTotalElements();
//            if (0 >= totalElements) {
//                if (0 < pageNumber) {
//                    LOGGER.warn("First request but page more than zero => Set to first page: requestPageNumer[{}]",
//                            pageNumber);
//                    pageNumber = 0;
//                }
//            } else {
//                int totalPages = (int) (totalElements / pageSize);
//                if (totalElements % pageSize != 0) {
//                    totalPages++;
//                }
//                if (pageNumber >= totalPages -1) {
//                    LOGGER.warn("Over number of pagers => Set to last page: requestPageNumer[{}], lastPage[{}]",
//                            pageNumber, totalPages - 1);
//                    pageNumber = totalPages - 1; //last page
//                }                
//            }
//            final Pageable pageable = new PageRequest(pageNumber, pageSize);
//            final TypedQuery<T> query = entityManager.createQuery(extractSqlStr, resultClass);
//            query.setFirstResult(pageable.getOffset());
//            query.setMaxResults(pageable.getPageSize());
//            final TypedQuery<Long> countQuery = entityManager.createQuery(countSqlStr, Long.class);
//            final Long total = countQuery.getSingleResult();
//            
//            PageImpl<T> page = new PageImpl<T>(query.getResultList(), pageable, total);
//            
//            endNormalFlag = true;
//            
//            return page;
//            
//        } finally {
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug(IConstants.END_METHOD);
//            }
//        }
//    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.webapp.cliniccollect.persistence.repository.IRepository#findByName(java.lang.String, boolean)
     */
    @Override
    public T findByName(final String entityName, boolean filterLogicalDeletion) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("entityName[{}]", entityName);
            }
            final Specification<T> spec = new Specification<T>() {
                @Override
                public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, 
                        final CriteriaBuilder cb) {
                    final Expression<String> pathName = root.get(IDbConstants.FIELD_NAME);
                    return cb.equal(cb.lower(pathName), entityName.toLowerCase());
                }
            };
            final List<T> records = findAll(spec, new Sort(Direction.DESC, IDbConstants.FIELD_ID), filterLogicalDeletion);
            
            
            if (null != records && !records.isEmpty()) {
                return records.get(0);
            }
            return null;
        } finally {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
    }
    
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IRepository#findByCode(java.lang.String, boolean)
     */
    @Override
	public T findByCode(final String entityCode, boolean filterLogicalDeletion) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        try {
            final Specification<T> spec = new Specification<T>() {
                @Override
                public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, 
                        final CriteriaBuilder cb) {
                    final Expression<String> pathName = root.get(IDbConstants.FIELD_CODE);
                    return cb.equal(cb.lower(pathName), entityCode.toLowerCase());
                }
            };
            final List<T> records = findAll(spec, new Sort(Direction.DESC, IDbConstants.FIELD_ID), filterLogicalDeletion);
            if (null != records && !records.isEmpty()) {
                return records.get(0);
            }
            return null;
        } finally {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
	}
  
    /* (non-Javadoc)
     * @see com.clinic.clinic.webapp.cliniccollect.persistence.repository.IRepository#isNameExisted(
     * java.lang.Integer, java.lang.String, boolean)
     */
    @Override
    public Boolean isNameExisted(final Integer entityId, final String entityName, final boolean filterLogicalDeletion) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("id[{}], name[{}]", entityId, entityName);
            }
            final Specification<T> spec = new Specification<T>() {
                @Override
                public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, 
                        final CriteriaBuilder cb) {
                    final Expression<String> path = root.get(IDbConstants.FIELD_NAME);
                    final Predicate preName = cb.equal(cb.lower(path), entityName.toLowerCase());
                    // In case of updating entity, the entity name is existed if 'name' is the same but 'id' is different.
                    if (null != entityId && 0 < entityId) {
                        final Predicate preId = cb.notEqual(root.get(IDbConstants.FIELD_ID), entityId);
                        return cb.and(preName, preId);
                    }
                    return preName;
                }
            };
            Specification<T> newSpec = null;
            if (Boolean.TRUE == filterLogicalDeletion) {
                newSpec = buildLogicalDeletion(spec);
            } else {
                newSpec = spec;
            }
            final long num = count(newSpec);
            return (num > 0);
        } finally {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
    }
    

    protected void doDelete(final ID id, boolean permanent) {
        T entity = findOne(id);
        doDelete(entity, permanent);
    }
    protected void doDelete(final T entity, boolean permanent) {
        if (permanent) {
            repository.delete(entity);
        } else {
            ((TraceEntity) entity).setIsDeleted(true);
            save(entity);
        }
    }
    
    protected void doDelete(final Iterable<? extends T> entities, boolean permanent) {
        if (permanent) {
            repository.delete(entities);
        } else {
            for (T entity : entities) {
                ((TraceEntity) entity).setIsDeleted(true);
            }
            save(entities);
        }
    }
    
    protected void doDeleteAll(boolean permanent) {
        final List<T> currentItems = doFindAll(null, null, true);
        doDelete(currentItems, permanent);
    }
    
    protected List<T> doFindAll(final Specification<T> spec, final Sort sort, final boolean filterLogicalDeletion) {
        Specification<T> qSpec;
        if (filterLogicalDeletion) {
            qSpec = buildLogicalDeletion(spec);
        } else {
            qSpec = spec;
        }
        List<T> list;
        if (null != qSpec && null != sort) {
            list = repository.findAll(qSpec, sort);
        } else if (null != sort) {
            list = repository.findAll(sort);
        } else if (null != qSpec) {
            list = repository.findAll(qSpec);
        } else {
            list = repository.findAll();
        }
        return list;
    }
    
    protected List<T> doFindIds(final Iterable<ID> ids, final boolean filterLogicalDeletion) {
        final List<T> tmpList = repository.findAll(ids);
        List<T> list = new ArrayList<T>();
        
        if (filterLogicalDeletion) {
            for (T entity : tmpList) {
                if (((TraceEntity) entity).getIsDeleted().booleanValue() == false) {
                    list.add(entity);
                } else {
                    //Ignore
                }
            }
        } else {
            list = tmpList;
        }
        return list;
    }
    
    protected Page<T> doFindPageable(final Specification<T> spec, final Pageable pageable, final boolean filterLogicalDeletion) {
        Specification<T> qSpec;
        if (filterLogicalDeletion) {
            qSpec = buildLogicalDeletion(spec);
        } else {
            qSpec = spec;
        }
        Page<T> list = null;
        if (null != qSpec && null != pageable) {
            list = repository.findAll(qSpec, pageable);
        } else if (null != pageable) {
            list = repository.findAll(pageable);
        }
        return list;
    }
    
    protected T doFindOne(final ID id, final boolean filterLogicalDeletion) {
        T t = repository.findOne(id);
        if (filterLogicalDeletion && ((TraceEntity) t).getIsDeleted()) {
            return null;
        }
        return t;
    }
    
    protected T doFindOne(final Specification<T> spec, final boolean filterLogicalDeletion) {
        Specification<T> qSpec;
        if (filterLogicalDeletion) {
            qSpec = buildLogicalDeletion(spec);
        } else {
            qSpec = spec;
        }
        return repository.findOne(qSpec);
    }
    
    protected long doCount(final Specification<T> spec, final boolean filterLogicalDeletion) {
        Specification<T> qSpec;
        if (filterLogicalDeletion) {
            qSpec = buildLogicalDeletion(spec);
        } else {
            qSpec = spec;
        }
        if (null != qSpec) {
            return repository.count(qSpec);
        } else {
            return repository.count();
        }
    }
    
    protected <S extends T> S doSave(final S entity) {
        ((TraceEntity) entity).setLastUpdated(System.currentTimeMillis());
		if (!entityManager.contains(entity)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("DEbug save persist in abs repository");
            }
			// persist object - add to entity manager
			entityManager.persist(entity);

			// flush em - save to DB
			entityManager.flush();
		} else {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("DEbug save merge in abs repository");
            }
			entityManager.merge(entity);
			entityManager.flush();
		}
        return entity;
    }
    
    protected <S extends T> S doSaveWithIdEntity(final S entity) {
        if (!entityManager.contains(entity)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("DEbug save persist in abs repository");
            }
            // persist object - add to entity manager
            entityManager.persist(entity);

            // flush em - save to DB
            entityManager.flush();
        } else {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("DEbug save merge in abs repository");
            }
            entityManager.merge(entity);
            entityManager.flush();
        }
        return entity;
    }
    protected <S extends T> List<S> doSave(final Iterable<S> entities) {
        for (IdEntity entity: entities) {
            //Update last changed time-stamp
            ((TraceEntity) entity).setLastUpdated(System.currentTimeMillis());
        }
        return repository.save(entities);
    }
    
    protected <S extends T> S doSaveAndFlush(final S entity) {
        ((TraceEntity) entity).setLastUpdated(System.currentTimeMillis());
        return repository.saveAndFlush(entity);
    }
    
    protected Specification<T> buildLogicalDeletion(final Specification<T> otherSpec) {
        final Specification<T> spec = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                final Predicate notDelete = cb.equal(root.get(IDbConstants.FIELD_IS_DELETED), IDbConstants.FALSE_VALUE);
                if (null != otherSpec) {
                    Predicate otherPredecate = otherSpec.toPredicate(root, query, cb);
                    if (null != otherPredecate) {
                        return cb.and(notDelete, otherPredecate);
                    }
                }
                return notDelete;
            }
        };
        return spec;
    }
    
    /**
     * <p>Get Predicate of Parent Entity.
     *    with is_deleted is false
     * </p>
     *
     * @param parentId
     * @param join
     * @param parentFieldName
     * @param filterLogicalDeletion
     * @return
     *
     * @author Logan Phan
     */
    protected Predicate getPredicateParentNotDeleted(final Root<?> root, 
                    final int parentId, final String parentFieldName, final boolean filterLogicalDeletion) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final Join<?, ?> join = root.join(parentFieldName);
        Predicate pre = cb.and(join.get(IDbConstants.FIELD_ID).in(parentId),
                cb.equal(join.get(IDbConstants.FIELD_IS_DELETED), filterLogicalDeletion));
        return pre;
    }
    
    /**
     * <p>Get Predicate of Parent Entity.
     *    with is_deleted is false.
     *    with parameter is some value.
     * </p>
     *
     * @param root
     * @param parentId
     * @param parentFieldName
     * @param filterLogicalDeletion
     * @return
     *
     * @author Vuong Do Tuan
     */
    protected Predicate getPredicateParentNotDeleted(final Root<?> root, 
            final List<Integer> parentId, final String parentFieldName, final boolean filterLogicalDeletion) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final Join<?, ?> join = root.join(parentFieldName);
        Predicate pre = cb.and(join.get(IDbConstants.FIELD_ID).in(parentId),
                cb.equal(join.get(IDbConstants.FIELD_IS_DELETED), filterLogicalDeletion));
        return pre;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IRepository#findFirstEntity(java.lang.String, java.lang.Object, boolean)
     */
    @Override
    public T findFirstEntity(final String fieldName, final Object fieldValue, boolean filterLogicalDeletion) {
        boolean endNormalFlag = false;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("fieldName[{}], fieldValue[{}]", fieldName, fieldValue);
            }
            final Specification<T> spec = new Specification<T>() {
                @Override
                public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, 
                        final CriteriaBuilder cb) {
                    final Expression<String> pathName = root.get(fieldName);
                    return cb.equal(pathName, fieldValue);
                }
            };
            final List<T> records = findAll(spec, new Sort(Direction.DESC, fieldName), filterLogicalDeletion);
            if (null != records && !records.isEmpty()) {
                endNormalFlag = true;
                return records.get(0);
            }

            endNormalFlag = true;

            return null;
        } finally {
            if (LOGGER.isDebugEnabled()) {
                if (endNormalFlag) {
                    LOGGER.debug(IConstants.END_METHOD_NORMAL);
                } else {
                    LOGGER.debug(IConstants.END_METHOD_ERROR);
                }
            }
        }
    }
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IRepository#getMaxCounter(java.lang.String, boolean)
     */
    @Override
    public String getMaxCounter(String entityCodePrefix, boolean filterLogicalDeletion) {
        boolean endNormalFlag = false;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        String maxCodeCounter = null;
        
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("entityCodePrefix[{}], filterLogicalDeletion[{}]", entityCodePrefix, filterLogicalDeletion);
            }
            
            T maxEntity = findByCodePrefixAndMaxCounter(entityCodePrefix, filterLogicalDeletion);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("current maxEntity of [{}] is [{}]", entityCodePrefix, maxEntity);
            }
            
            if (maxEntity != null) {
                NameCodeDescEntity codedEntity = (NameCodeDescEntity) maxEntity;
                
                String maxCode = codedEntity.getCode();
                
                maxCodeCounter = maxCode.substring(entityCodePrefix.length());
                
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("maxCode = [{}]; entityCodePrefix = [{}]; maxCodeCounter= [{}]", maxCode,
                            entityCodePrefix, maxCodeCounter);
                }
            }
            endNormalFlag = true;
            return maxCodeCounter;
        } finally {
            if (LOGGER.isDebugEnabled()) {
                if (endNormalFlag) {
                    LOGGER.debug(IConstants.END_METHOD_NORMAL);
                } else {
                    LOGGER.debug(IConstants.END_METHOD_ERROR);
                }
            }
        }
    }
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IRepository#findByCodePrefixAndMaxCounter(java.lang.String, boolean)
     */
    @Override
    public T findByCodePrefixAndMaxCounter(String entityCodePrefix, boolean filterLogicalDeletion) {
        boolean endNormalFlag = false;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("entityCodePrefix[{}], filterLogicalDeletion[{}]", entityCodePrefix, filterLogicalDeletion);
            }

            List<T> entityList = findAllByCodePrefix(entityCodePrefix, filterLogicalDeletion);

            T result = null;

            if (entityList != null && entityList.size() > 0) {
                result = entityList.get(0);
            } else {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("result list empty");
                }
            }

            endNormalFlag = true;

            return result;
        } finally {
            if (LOGGER.isDebugEnabled()) {
                if (endNormalFlag) {
                    LOGGER.debug(IConstants.END_METHOD_NORMAL);
                } else {
                    LOGGER.debug(IConstants.END_METHOD_ERROR);
                }
            }
        }
    }   
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IRepository#findAllByCodePrefix(java.lang.String, boolean)
     */
    @Override
    public List<T> findAllByCodePrefix(final String entityCodePrefix, boolean filterLogicalDeletion) {
        boolean endNormalFlag = false;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }

        try {
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug("entityCodePrefix[{}], filterLogicalDeletion[{}]", entityCodePrefix, filterLogicalDeletion);
//            }
            final Specification<T> spec = new Specification<T>() {
                @Override
                public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {
                    final Expression<String> pathName = root.get(IDbConstants.FIELD_CODE);

                    return cb.like(pathName, entityCodePrefix + IDbConstants.LIKE_WC_MANY_CHAR);
                }
            };
            List<T> records = findAll(spec, new Sort(Direction.DESC, IDbConstants.FIELD_CODE), filterLogicalDeletion);

            if (null == records) {
                records = new ArrayList<T>();
            }

            endNormalFlag = true;

            return records;
        } finally {
            if (LOGGER.isDebugEnabled()) {
                if (endNormalFlag) {
                    LOGGER.debug(IConstants.END_METHOD_NORMAL);
                } else {
                    LOGGER.debug(IConstants.END_METHOD_ERROR);
                }
            }
        }
    }
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IRepository#getNewCounter(java.lang.String, int, boolean)
     */
    @Override
    public String getNewCounter(String entityCodePrefix, int codeLength, boolean filterLogicalDeletion) {
        boolean endNormalFlag = false;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }        
        String newCounterStr = null;        
        try {
       
            if (entityCodePrefix == null || entityCodePrefix.trim().length() == 0) {
                entityCodePrefix = "";
            }            
            String curCounter = getMaxCounter(entityCodePrefix, filterLogicalDeletion);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("current counter of [{}] is [{}]", entityCodePrefix, curCounter);
            }           
            if (curCounter != null) {
                Integer newCounter = Integer.parseInt(curCounter);
                newCounter = newCounter + 1;
                newCounterStr = newCounter.toString();
            } else {
                newCounterStr = IDbConstants.CODE_COUNTER_START_ORDER;
                
            }
            
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("new counter of [{}] BEFORE PREPAD is [{}]", entityCodePrefix, newCounterStr);
            }
            
            /**
             * add zero pad at the begin of counter
             */
//            while (newCounterStr.length() < codeLength - entityCodePrefix.length()) {
//                newCounterStr = "0" + newCounterStr; // TODO optimize this
//            }
            
            int i = 0;
            
            while (newCounterStr.length() < codeLength) {
                
                newCounterStr = "0" + newCounterStr; // TODO optimize this
                i++;
            }
            
            
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("new counter of [{}] is [{}]", entityCodePrefix, newCounterStr);
            }
            
            endNormalFlag = true;

            return newCounterStr;
        } finally {
            if (LOGGER.isDebugEnabled()) {
                if (endNormalFlag) {
                    LOGGER.debug(IConstants.END_METHOD_NORMAL);
                } else {
                    LOGGER.debug(IConstants.END_METHOD_ERROR);
                }
            }
        }
    }
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IRepository#findAll(org.springframework.data.jpa.domain.Specification, org.springframework.data.domain.Pageable, boolean)
     */
    @Override
    public Page<T> findAllHasDeleteItem(Specification<T> spec, Pageable pageable, boolean filterLogicalDeletion) {
        return doFindPageable(spec, pageable, filterLogicalDeletion);
    }
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.persistence.repository.IRepository#findByTwoFields(java.lang.String, java.lang.Object, java.lang.String, java.lang.Object, boolean)
     */
    @Override
    public List<T> findByTwoFields(final String fieldOne, final Object valueOne, final String fieldTwo, final Object valueTwo,
            boolean filterLogicalDeletion) {
        boolean endNormalFlag = false;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }

        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("fieldOne[{}], valueOne[{}], fieldTwo[{}], valueTwo[{}]", fieldOne, valueOne, fieldTwo,
                        valueTwo);
            }
            final Specification<T> spec = new Specification<T>() {
                @Override
                public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query,
                        final CriteriaBuilder cb) {
                    final Expression<String> pathName1 = root.get(fieldOne);
                    final Expression<String> pathName2 = root.get(fieldTwo);
                    return cb.and(valueOne != null ? cb.equal(pathName1, valueOne) : cb.isNull(pathName1),
                            valueTwo != null ? cb.equal(pathName2, valueTwo) : cb.isNull(pathName2));
                }
            };
            List<T> records = findAll(spec, new Sort(Direction.DESC, fieldOne), filterLogicalDeletion);

            if (null == records) {
                records = new ArrayList<T>();
            }

            endNormalFlag = true;

            return records;
        } finally {
            if (LOGGER.isDebugEnabled()) {
                if (endNormalFlag) {
                    LOGGER.debug(IConstants.END_METHOD_NORMAL);
                } else {
                    LOGGER.debug(IConstants.END_METHOD_ERROR);
                }
            }
        }
    }
}
