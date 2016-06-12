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
 * File name     : IAccountService.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 7, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.bizlogic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.common.dto.biz.AccountCustomDto;
import com.clinic.clinic.common.dto.biz.AccountDto;
import com.clinic.clinic.common.dto.biz.AccountFilterDto;
import com.clinic.clinic.common.dto.biz.UserProfileDto;
import com.clinic.clinic.common.dto.biz.UserRegisterDto;
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
public interface IAccountService {

    Page<AccountDto> getAccountBySubcategory(Pageable range, Integer subcategoryId) throws BizlogicException;

    /**
     * <p>Description of this method.</p>
     *
     * @param accountfilterDto
     * @return
     *
     * @author Vuong Do
     */
    Page<AccountCustomDto> getAccountAndClinic(Pageable range, AccountFilterDto accountfilterDto) throws BizlogicException;

    /**
     * <p>Description of this method.</p>
     *
     * @param accountfilterDto
     * @return
     *
     * @author Vuong Do
     */
    Page<AccountCustomDto> getAccountAndHome(Pageable range, AccountFilterDto accountfilterDto) throws BizlogicException;
    
    AccountDto createAccount(Integer loginId, AccountDto account) throws BizlogicException;
    
    AccountDto login(String loginName, String password) throws BizlogicException;

    /**
     * <p>Service get profile's user</p>
     *
     * @param idCard
     * @return
     *
     * @author Vuong Do
     */
    UserProfileDto getProfile(Integer accountId) throws BizlogicException;

    /**
     * <p>Patient register</p>
     *
     * @param userRegister
     * @return
     *
     * @author Vuong Do
     */
    AccountEntity userRegister(UserRegisterDto userRegister) throws BizlogicException;
}
