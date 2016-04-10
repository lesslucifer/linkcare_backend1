/**==============================================================================
 * MAPER JSC (www.maper.vn) Proprietary.
 * Copyright 2015 MAPER JSC.
 * UNPUBLISHED WORK
 * ALL RIGHTS RESERVED
 *
 * This software is the confidential and proprietary information of 
 * MAPER J.S.C ("Proprietary Information").  Any use, reproduction,
 * distribution or disclosure of the software or Proprietary Information,
 * in whole or in part, must comply with the terms of the license  
 * agreement, nondisclosure agreement or contract entered into with 
 * MAPER providing access to this software.
 *
 * Project name  : mapercollect<br>
 * File name     : SessionLogEntity.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Sep 3, 2015				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * <p>
 * The actual entity class of SessionLog table.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see {@link IdEntity}
 */
@Entity
@Table(name="session_log")
@NamedQuery(name="SessionLogEntity.findAll", query="SELECT s FROM SessionLogEntity s")
public class SessionLogEntity extends TraceEntity {
    private static final long serialVersionUID = -2212878037326891620L;
    /**
     * <p>Default constructor </p>
     *
     */
    public SessionLogEntity() {
    }
    @Column(name = "session_id", length = 45)
    private String sessionId;
    @Column(name = "login_time")
    private Long loginTime;
    @Column(name = "logout_time")
    private Long logoutTime;
    @Column(name = "expired_time")
    private Long expiredTime;
    
    //fk: account and session_log table
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id", nullable = false)
    private AccountEntity account;
    
    /**
     * <p>Returns current value of account attribute.</p>
     *
     * @return the account
     */
    public AccountEntity getAccount() {
        return account;
    }
    /**
     * <p>Sets value of account attribute.</p>
     *
     * @param account the account to set
     */
    public void setAccount(AccountEntity account) {
        this.account = account;
    }
    /**
     * <p>Returns current value of sessionId attribute.</p>
     *
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }
    /**
     * <p>Sets value of sessionId attribute.</p>
     *
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    /**
     * <p>Returns current value of loginTime attribute.</p>
     *
     * @return the loginTime
     */
    public Long getLoginTime() {
        return loginTime;
    }
    /**
     * <p>Sets value of loginTime attribute.</p>
     *
     * @param loginTime the loginTime to set
     */
    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }
    /**
     * <p>Returns current value of logoutTime attribute.</p>
     *
     * @return the logoutTime
     */
    public Long getLogoutTime() {
        return logoutTime;
    }
    /**
     * <p>Sets value of logoutTime attribute.</p>
     *
     * @param logoutTime the logoutTime to set
     */
    public void setLogoutTime(Long logoutTime) {
        this.logoutTime = logoutTime;
    }
    
	public Long getExpiredTime() {
		return expiredTime;
	}
	
	public void setExpiredTime(Long expiredTime) {
		this.expiredTime = expiredTime;
	}
}
