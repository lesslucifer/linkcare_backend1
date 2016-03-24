/**
 * ============================================================================
 * == MAPER JSC (www.maper.vn) Proprietary. Copyright 2015 MAPER JSC.
 * UNPUBLISHED WORK ALL RIGHTS RESERVED This software is the confidential and
 * proprietary information of MAPER J.S.C ("Proprietary Information"). Any use,
 * reproduction, distribution or disclosure of the software or Proprietary
 * Information, in whole or in part, must comply with the terms of the license
 * agreement, nondisclosure agreement or contract entered into with MAPER
 * providing access to this software. Project name : mapercollect<br>
 * File name : SessionLogDto.java<br>
 * <p>
 * Changes History <br>
 * Date Person Reason<br>
 * Sep 14, 2015 Ngoc Anh Initial<br>
 * </p>
 * =============================================================================
 */
package com.clinic.clinic.common.dto.biz;

import java.io.Serializable;
import java.sql.Timestamp;

import com.clinic.clinic.common.dto.TraceDto;
/**
 * <p>
 * The SessionLog Dto.
 * </p>
 */
public class SessionLogDto extends TraceDto  implements Serializable {
    /**
     * serial Version UID.
     */
    private static final long serialVersionUID = -2212878037326891620L;
    /**
     * <p>
     * Default constructor
     * </p>
     */
    public SessionLogDto() {
    }

    private String sessionId;

    private Long loginTime;

    private Long logoutTime;

    private AccountDto account;

    /**
     * <p>
     * Returns current value of account attribute.
     * </p>
     */
    public AccountDto getAccount() {
        return account;
    }
    /**
     * <p>
     * Sets value of account attribute.
     * </p>
     */
    public void setAccount(AccountDto account) {
        this.account = account;
    }
    /**
     * <p>
     * Returns current value of sessionId attribute.
     * </p>
     */
    public String getSessionId() {
        return sessionId;
    }
    /**
     * <p>
     * Sets value of sessionId attribute.
     * </p>
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    /**
     * <p>
     * Returns current value of loginTime attribute.
     * </p>
     */
    public Long getLoginTime() {
        return loginTime;
    }
    /**
     * <p>
     * Sets value of loginTime attribute.
     * </p>
     */
    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }
    /**
     * <p>
     * Returns current value of logoutTime attribute.
     * </p>
     */
    public Long getLogoutTime() {
        return logoutTime;
    }
    /**
     * <p>
     * Sets value of logoutTime attribute.
     * </p>
     */
    public void setLogoutTime(Long logoutTime) {
        this.logoutTime = logoutTime;
    }

    /**
     * <p>
     * Returns current value of lastUpdatedTime attribute as timestamp.
     * </p>
     *
     * @return the loginTime as timestamp
     */
    public Timestamp getLoginTimeTimestamp() {
        if (loginTime != null) {
            return new Timestamp(loginTime);
        }
        return null;
    }
    /**
     * <p>
     * Sets value of loginTime attribute as timestamp.
     * </p>
     *
     * @param loginTime
     *            the loginTime as timestamp to set
     */
    public void setLoginTimeTimestamp(final Timestamp loginTime) {
        if (loginTime != null) {
            this.loginTime = loginTime.getTime();
        } else {
            this.loginTime = null;
        }
    }

    /**
     * <p>
     * Returns current value of lastUpdatedTime attribute as timestamp.
     * </p>
     *
     * @return the logoutTime as timestamp
     */
    public Timestamp getLogoutTimeTimestamp() {
        if (logoutTime != null) {
            return new Timestamp(logoutTime);
        }
        return null;
    }
    /**
     * <p>
     * Sets value of logoutTime attribute as timestamp.
     * </p>
     *
     * @param logoutTime
     *            the logoutTime as timestamp to set
     */
    public void setLogoutTimeTimestamp(final Timestamp logoutTime) {
        if (logoutTime != null) {
            this.logoutTime = logoutTime.getTime();
        } else {
            this.logoutTime = null;
        }
    }
    /* (non-Javadoc)
     * @see com.maper.maper.common.dto.IdDto#toUpercaseFirstChar()
     */
    @Override
    public void toUpercaseFirstChar() {
        /**
         * NOP
         */
        
    }

}
