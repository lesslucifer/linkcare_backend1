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
 * File name     : AuthServiceImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 18, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.bizlogic.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.IAuthService;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.SessionLogEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.ISessionLogRepository;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.api.translator.impl.AccountTranslatorImpl;
import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.dto.biz.AccountDto;
import com.clinic.clinic.common.dto.biz.ClinicRightDto;
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
@ApplicationService
public class AuthServiceImpl extends AbsService implements IAuthService {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private IAccountRepository accountRepo;
    @Autowired
    private ISessionLogRepository sessionRepo;
    
    private ITranslator<AccountDto, AccountEntity> accountTrans = new AccountTranslatorImpl();
    
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.IAuthService#checkAuthenticated(java.lang.String, java.lang.String)
     */
    @Override
    public AccountDto checkAuthenticated(final String sessionId,final String loginName) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        AccountDto retValue = null;
        try {
            AccountEntity accountEnt = accountRepo.findFirstEntity(loginName, IDbConstants.FIELD_ACC_ACCOUNT_LOGIN_NAME, false);
            List<SessionLogEntity> sessionEnts = sessionRepo.findByTwoFields(IDbConstants.FIELD_FK_ACCOUNT, accountEnt.getId(), IDbConstants.FIELD_SESSION_LOGOUT_TIME, null, false);
            if (sessionEnts == null || sessionEnts.size() != 1) {
                LOGGER.error("found NO or too MANY active sessions for {}", loginName);
                throwBizlogicException("Session time out", loginName);
            }
            // check session id match
            if (!sessionId.equals(sessionEnts.get(0).getSessionId())) {
                LOGGER.error("found NO MATCH active session id for {}", loginName);
                throwBizlogicException("Session not match", loginName);
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD_NORMAL);
            }
            retValue = accountTrans.getDto(accountEnt);
        } catch (BizlogicException be) {
            LOGGER.error("error", be);
        } catch (Exception e) {
            LOGGER.error("error", e);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retValue;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.IAuthService#hasRight(java.lang.String, java.lang.String)
     */
    @Override
    public ClinicRightDto hasRight(final String sessionId,final String loginName) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        ClinicRightDto retValue = null;
        try {
            
        } catch (BizlogicException be) { 
            LOGGER.error("error", be);
        } catch (Exception e) {
            LOGGER.error("error", e);
        }finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        return retValue;
    }

    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.IAuthService#login(java.lang.String, java.lang.String)
     */
    @Override
    public String login(String loginName, String password) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        String retSession = null;
        try {
            AccountEntity accountEnt = accountRepo.findFirstEntity(IDbConstants.FIELD_ACC_ACCOUNT_LOGIN_NAME, loginName, false);
            if(!accountEnt.getHashedPassword().equals(StringUtil.getHashedText(password))) {
                retSession = "Password not match";
            } else {
                if(LOGGER.isDebugEnabled()) {
                    LOGGER.debug("login success");
                }
                Long logInTime = System.currentTimeMillis();
                retSession = StringUtil.getSession();
                SessionLogEntity sessionEnt = new SessionLogEntity();
                sessionEnt.setAccount(accountEnt);
                sessionEnt.setSessionId(retSession);
                sessionEnt.setLoginTime(logInTime);
                sessionEnt.setLogoutTime(null);
                sessionRepo.save(sessionEnt);
            }
        } catch (BizlogicException be) {
            LOGGER.error("error", be);
        } catch (Exception e) {
            LOGGER.error("error", e);
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            } 
        }
        return retSession;
    }
}
