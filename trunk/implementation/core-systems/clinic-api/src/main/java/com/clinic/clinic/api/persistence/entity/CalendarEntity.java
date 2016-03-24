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
 * File name     : CalendarEntity.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 16, 2016				Vuong Do				Initial<br>
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
import javax.persistence.Table;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
@Entity
@Table(name = "calendar")
@NamedQuery(name = "CalendarEntity.findAll", query = "Select c From CalendarEntity c")
public class CalendarEntity extends NameCodeEntity {
    /**
     * <p>Description of this field.</p>
     */
    private static final long serialVersionUID = 3166661606999970494L;
    
    @Column(name = "type")
    private Boolean type;
    @Column(name = "slot")
    private Long slot;
    @Column(name = "date")
    private Long date;
    @Column(name = "available")
    private Boolean available;
    @Column(name = "is_blocked")
    private Boolean isBlocked;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "configuration_id", nullable = false)
    private ConfigurationEntity configuration;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;
    
    public Boolean getType() {
        return type;
    }
    public void setType(Boolean type) {
        this.type = type;
    }
    public Long getSlot() {
        return slot;
    }
    public void setSlot(Long slot) {
        this.slot = slot;
    }
    public Long getDate() {
        return date;
    }
    public void setDate(Long date) {
        this.date = date;
    }
    public Boolean getAvailble() {
        return available;
    }
    public void setAvailble(Boolean availble) {
        this.available = availble;
    }
    public Boolean getIsBlocked() {
        return isBlocked;
    }
    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
    public ConfigurationEntity getConfiguration() {
        return configuration;
    }
    public void setConfiguration(ConfigurationEntity configuration) {
        this.configuration = configuration;
    }
    public AccountEntity getAccount() {
        return account;
    }
    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
