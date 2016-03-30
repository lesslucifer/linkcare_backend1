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
 * File name     : IAccountRepository.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 7, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.common.dto.biz.AccountCustomDto;
import com.clinic.clinic.common.dto.biz.AccountDto;
import com.clinic.clinic.common.dto.biz.AccountFilterDto;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public interface IAccountRepository extends IRepository<AccountEntity, Integer> {

    /**
     * <p>Description of this method.</p>
     *
     * @param range
     * @param subcategoryId
     * @return
     *
     * @author Vuong Do
     */
    Page<AccountEntity> findAccountBySubcategoryId(final Pageable range,final Integer subcategoryId);

    /**
     * <p>Description of this method.</p>
     *
     * @param range
     * @param accountfilterDto
     * @return
     *
     * @author Vuong Do
     */
    Page<AccountCustomDto> findAccountAndClinic(final Pageable range, final AccountFilterDto accountfilterDto);

    /**
     * <p>Description of this method.</p>
     *
     * @param range
     * @param accountfilterDto
     * @return
     *
     * @author Vuong Do
     */
    Page<AccountCustomDto> findAccountAndHome(final Pageable range,final AccountFilterDto accountfilterDto);

	AccountEntity findAccountByLoginName(final String loginName);

}
