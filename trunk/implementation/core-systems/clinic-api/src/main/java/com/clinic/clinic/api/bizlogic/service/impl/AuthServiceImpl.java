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

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.clinic.clinic.api.bizlogic.annotation.ApplicationService;
import com.clinic.clinic.api.bizlogic.service.IAuthService;
import com.clinic.clinic.api.persistence.entity.AccountEntity;
import com.clinic.clinic.api.persistence.entity.SessionLogEntity;
import com.clinic.clinic.api.persistence.repository.IAccountRepository;
import com.clinic.clinic.api.persistence.repository.ISessionLogRepository;
import com.clinic.clinic.common.consts.IBizErrorCode;
import com.clinic.clinic.common.consts.IConstants;
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
@ApplicationService
public class AuthServiceImpl extends AbsService implements IAuthService {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private IAccountRepository accountRepo;
    @Autowired
    private ISessionLogRepository sessionRepo;
    
    /* (non-Javadoc)
     * @see com.clinic.clinic.api.bizlogic.service.IAuthService#login(java.lang.String, java.lang.String)
     */
    @Override
    public String login(String loginName, String password) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        String retValue = null;
        try {
            AccountEntity accountEnt = accountRepo.findAccountByLoginName(loginName);
            if(null != accountEnt) {
                UUID uuid = UUID.randomUUID();
                String sessionId = uuid.toString();
                retValue = sessionId;
                SessionLogEntity sessEnt = sessionRepo.findSessionLogByAccountId(accountEnt.getId());
                if(null == sessEnt) {
                	SessionLogEntity sessionEnt = new SessionLogEntity();
                	sessionEnt.setAccount(accountEnt);
                	sessionEnt.setCreatedBy(accountEnt.getId());
                	sessionEnt.setLoginTime(System.currentTimeMillis());
                	sessionEnt.setCreatedDatetime(System.currentTimeMillis());
                	sessionEnt.setLastUpdated(System.currentTimeMillis());
                	sessionEnt.setLastUpdatedBy(accountEnt.getId());
                	sessionEnt.setExpiredTime(sessionEnt.getLastUpdated() + IConstants.DURABLE_SESSION);
                    sessionEnt.setSessionId(sessionId); 
                    sessionRepo.save(sessionEnt);
                } else {
                    sessEnt.setSessionId(sessionId);
                    sessionRepo.save(sessEnt);
                }
            } else {
            	throwBizlogicException(401, IBizErrorCode.WRONG_USERNAME_OR_PASSWORD, "User or password invalid");
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
        return retValue;
    }

	@Override
	public Integer authSession(String sessionId) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        try {
        	Integer retValue = sessionRepo.getAccountIdForSession(sessionId);
        	if (retValue == null) {
        		throwBizlogicException(401, IBizErrorCode.INVALID_SESSION, "Invalid sessions", sessionId);
        	}
        	return retValue;
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
	}

	@Override
	public boolean authRight(Integer accountId, String right) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        boolean retVal = false;
        try {
        	boolean hasRight = accountRepo.isAccountHasRight(accountId, right);
        	
        	if (!hasRight) {
        		throwBizlogicException(403, IBizErrorCode.INVALID_SESSION, "Permission deined!", right);
        	}

        	retVal = true;
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        
        return retVal;
	}

	@Override
	public boolean authRights(Integer accountId, String... rights) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        boolean retVal = false;
        try {
        	Set<String> matchedRights = accountRepo.checkAccountRights(accountId, rights);
        	
        	if (matchedRights.size() < rights.length) {
        		String[] lackedRights = Arrays.stream(rights).filter((r) -> !matchedRights.contains(r)).toArray(String[]::new);
        		throwBizlogicException(403, IBizErrorCode.MISSING_RIGHT, "Permission Denied", lackedRights);
        	}

        	retVal = true;
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        
        return retVal;
	}

	@Override
	public Set<String> authRights(Integer accountId, String[] requiredRights, String[] optionalRights)
			throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        Set<String> retVal = Collections.emptySet();
        
    	String[] allRights = new String[requiredRights.length + optionalRights.length];
    	System.arraycopy(requiredRights, 0, allRights, 0, requiredRights.length);
    	System.arraycopy(optionalRights, 0, allRights, requiredRights.length, optionalRights.length);
        
        try {
        	Set<String> matchedRights = accountRepo.checkAccountRights(accountId, allRights);
        	String[] lackedRights = Arrays.stream(requiredRights).filter((r) -> !matchedRights.contains(r)).toArray(String[]::new);
        	
        	if (lackedRights.length > 0) {
        		throwBizlogicException(403, IBizErrorCode.MISSING_RIGHT, "Permission Denied", (Object[]) lackedRights);
        	}

    		retVal = matchedRights;
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        
        return retVal;
	}
}
