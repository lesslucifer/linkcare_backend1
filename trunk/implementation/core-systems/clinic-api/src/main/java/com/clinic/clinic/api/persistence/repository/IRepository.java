/**==============================================================================
 * CLINIC JSC (www.clinic.vn) Proprietary.
 * Copyright 2015 CLINIC JSC.
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
 * File name     : IRepository.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Aug 20, 2015		daiql				Initial<br>
 * </p>
 *
 * @author daiql
 *=============================================================================*/
package com.clinic.clinic.api.persistence.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * <p>
 * Defines main supported methods of general repository.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see {@link JpaRepository}
 */
@NoRepositoryBean
public interface IRepository <T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    
    
    /**
     * <p>
     * Returns all entities matching the given {@link Specification} and are sorting with {@link Sort}.
     * When filterLogicalDeletion is true, only entities that has deleted flag OFF are returned
     * </p>
     *
     * @param spec
     * @param pageable
     * @param filterLogicalDeletion
     * @return
     *
     * @author Vuong Do
     */
    Page<T> findAllHasDeleteItem(final Specification<T> spec, final Pageable pageable, final boolean filterLogicalDeletion ); 
    /**
     * <p>Returns all entities matching the given {@link Specification} and are sorting with {@link Sort}.
     * When filterLogicalDeletion is true, only entities that has deleted flag OFF are returned.</p>
     * 
     * @param spec
     * @param sort
     * @param filterLogicalDeletion
     * 
     * @return
     */
    List<T> findAll(Specification<T> spec, Sort sort, boolean filterLogicalDeletion);
    
    /**
     * Returns all entities matching the given {@link Specification}.
     * 
     * @param spec
     * @param filterLogicalDeletion
     * @return
     */
    List<T> findAll(Specification<T> spec, boolean filterLogicalDeletion);
    /**
     * Returns a single entity matching the given {@link Specification}.
     * 
     * @param spec
     * @param filterLogicalDeletion
     * 
     * @return
     */
    T findOne(Specification<T> spec, boolean filterLogicalDeletion);
    /**
     * Retrieves an entity by its id.
     * 
     * @param id must not be {@literal null}.
     * @param filterLogicalDeletion
     * 
     * @return the entity with the given id or <code>null</code> if not found
     */
    T findOne(ID id, boolean filterLogicalDeletion);
    
    /**
     * <p>Retrieves an entity by its name.</p>
     *
     * @param name
     * @param filterLogicalDeletion
     * 
     * @return the entity with the given name or <code>null</code> if not found
     */
    T findByName(String name, boolean filterLogicalDeletion);
    
    /**
     * @param entityCode
     * @param filterLogicalDeletion
     * @return the entity with the given code or <code>null</code> if not found
     * 
     * TODO move to entity have field code
     */
    T findByCode(final String entityCode, boolean filterLogicalDeletion);

    /**
     * <p>Checks given entity name is existed in storage or not.</p>
     *
     * @param entityId
     * @param entityName
     * @param filterLogicalDeletion
     * 
     * @return <code>true</code> is existed in storage, <code>false</code> otherwise
     */
    Boolean isNameExisted(Integer entityId, String entityName, boolean filterLogicalDeletion);
    
    /**
     * <p>Deletes record that has given ID permanently in storage.</p>
     *
     * @param id
     */
    void permanentDelete(final ID id);
    
    /**
     * <p>Deletes record of given entity permanently in storage.</p>
     *
     * @param entity
     */
    void permanentDelete(final T entity);
    
    /**
     * <p>Find the first entity found that has value in the fieldName equal fieldValue.
     * This method offer utility to find entity in unique column</p>
     *
     * @param fieldName
     * @param fieldValue
     * @param filterLogicalDeletion
     * @return
     *
     * @author Vuong Do
     */
    T findFirstEntity(final String fieldName, final Object fieldValue, boolean filterLogicalDeletion);
    
    List<T> findByTwoFields(final String fieldOne, final Object valueOne, final String fieldTwo, final Object valueTwo, boolean filterLogicalDeletion);
    
    /**
     * <p>
     * Get max counter of code that has given code prefix. Apply for entities
     * that have prefix to categorize, for example place code, Zone code,
     * Team code.
     * </p>
     *
     * @param entityCodePrefix code category
     * @param filterLogicalDeletion
     * @return
     * @author Vuong Do
     */
    String getMaxCounter(final String entityCodePrefix, boolean filterLogicalDeletion);
    
    /**
     * <p>
     * Find the entity that has given code prefix and its code is max of
     * counter part . Apply for entities that have prefix to categorize, for
     * example Location code, Zone code, Team code.
     * </p>
     *
     * @param entityCodePrefix code category
     * @param filterLogicalDeletion
     * @return
     * @author Vuong Do
     */
    T findByCodePrefixAndMaxCounter(final String entityCodePrefix, boolean filterLogicalDeletion);
    
    /**
     * <p>
     * Find all entities by code prefix. Apply for entities that have prefix to
     * categorize, for example Location code, Zone code, Team code.
     * </p>
     *
     * @param entityCodePrefix code category
     * @param filterLogicalDeletion
     * @return list of result, sort descendant by code
     * @author Vuong Do
     */
    List<T> findAllByCodePrefix(final String entityCodePrefix, boolean filterLogicalDeletion);
    
    /**
     * <p>
     * Get new counter of code that has given code prefix. Apply for entities
     * that have prefix to categorize, for example Location code, Zone code,
     * Team code.
     * </p>
     *
     * @param entityCodePrefix code category
     * @param codeLength  length of generated code, including prefix
     * @param filterLogicalDeletion
     * @return
     * @author Vuong Do
     */
    String getNewCounter(final String entityCodePrefix, int codeLength, boolean filterLogicalDeletion);
    
    T getReference(Class<T> clazz, ID id);
}
