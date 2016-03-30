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
 * File name     : IAuthService.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 18, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.bizlogic.service;

import com.clinic.clinic.common.dto.biz.AccountDto;
import com.clinic.clinic.common.dto.biz.ClinicRightDto;
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
public interface IAuthService {

    /**
     * <p>Description of this method.</p>
     *
     * @param sessionId
     * @param loginName
     * @return
     *
     * @author Vuong Do
     */
    AccountDto checkAuthenticated(Integer sessionId, String loginName) throws BizlogicException;

    /**
     * <p>Description of this method.</p>
     *
     * @param sessionId
     * @param loginName
     * @return
     *
     * @author Vuong Do
     */
    ClinicRightDto hasRight(String sessionId, String loginName) throws BizlogicException;
    
    String login(final String loginName, final String password) throws BizlogicException;

}
