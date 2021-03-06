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
 * File name     : ISubcategoryRepository.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 7, 2016				dailq				Initial<br>
 * </p>
 *
 * @author dailq
 *=============================================================================*/
package com.clinic.clinic.api.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinic.clinic.api.persistence.entity.SubcategoryEntity;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author dailq<br>
 * @version 1.0<br>
 * @see TODO
 */
public interface ISubcategoryRepository extends IRepository<SubcategoryEntity, Integer> {

    Page<SubcategoryEntity> findSubcategoryByCateId(final Pageable range,final Integer cateId);
    
    Page<SubcategoryEntity> findSubcategoryByMajor(final Pageable range, final Integer majorId);
    
    List<SubcategoryEntity> getAllSubcategoryByMajor(Integer majorId);
}
