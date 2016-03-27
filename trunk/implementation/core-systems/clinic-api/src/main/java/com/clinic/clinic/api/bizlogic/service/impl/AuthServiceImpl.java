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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                sessionEnt.setExpiredTime(logInTime + IConstants.SESSION_EXPIRES);
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

	@Override
	public Integer authSession(String sessionId) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        try {
        	Integer retValue = sessionRepo.getAccountIdForSession(sessionId);
        	
        	if (retValue == null) {
        		throwBizlogicException("Invalid sessions", sessionId);
        	}
        	
        	return retValue;
        } catch (BizlogicException be) {
        	throw be;
        } catch (Exception e) {
        	throwBizlogicException("Unkown error!", e.toString());
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        
        return null;
	}

	@Override
	public boolean authRight(Integer accountId, String right) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        try {
        	boolean hasRight = accountRepo.isAccountHasRight(accountId, right);
        	
        	if (hasRight) {
        		return true;
        	}
        } catch (BizlogicException be) {
        	throw be;
        } catch (Exception e) {
        	throwBizlogicException("Unkown error!", e.toString());
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        
        throwBizlogicException("Permission Denied", right);
        return false;
	}

	@Override
	public boolean authRights(Integer accountId, String... rights) throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
        try {
        	Set<String> matchedRights = accountRepo.checkAccountRights(accountId, rights);
        	
        	if (matchedRights.size() == rights.length) {
        		return true;
        	}
        	else {
        		String[] lackedRights = Arrays.stream(rights).filter((r) -> !matchedRights.contains(r)).toArray(String[]::new);
        		throwBizlogicException("Permission Denied", lackedRights);
        		return false;
        	}
        	
        } catch (BizlogicException be) {
        	throw be;
        } catch (Exception e) {
        	throwBizlogicException("Unkown error!", e.toString());
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        
        throwBizlogicException("Permission Denied", rights);
        return false;
	}

	@Override
	public Set<String> authRights(Integer accountId, String[] requiredRights, String[] optionalRights)
			throws BizlogicException {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        
    	String[] allRights = new String[requiredRights.length + optionalRights.length];
    	System.arraycopy(requiredRights, 0, allRights, 0, requiredRights.length);
    	System.arraycopy(optionalRights, 0, allRights, requiredRights.length, optionalRights.length);
        
        try {
        	Set<String> matchedRights = accountRepo.checkAccountRights(accountId, allRights);
        	String[] lackedRights = Arrays.stream(requiredRights).filter((r) -> !matchedRights.contains(r)).toArray(String[]::new);
        	
        	if (lackedRights.length > 0) {
        		throwBizlogicException("Permission Denied", lackedRights);
        	}

    		return matchedRights;
        } catch (BizlogicException be) {
        	throw be;
        } catch (Exception e) {
        	throwBizlogicException("Unkown error!", e.toString());
        } finally {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(IConstants.END_METHOD);
            }
        }
        
        throwBizlogicException("Permission Denied", allRights);
        return Collections.emptySet();
	}
}
