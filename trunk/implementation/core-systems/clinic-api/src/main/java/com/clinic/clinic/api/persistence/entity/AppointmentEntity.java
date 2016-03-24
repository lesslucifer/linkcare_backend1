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
 * File name     : AppointmentEntity.java<br>
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
import javax.persistence.OneToOne;
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
@Table(name = "appointment")
@NamedQuery(name = "AppointmentEntity.findAll", query = "Select a From AppointmentEntity a")
public class AppointmentEntity extends NameCodeEntity {

    /**
     * <p>Description of this field.</p>
     */
    private static final long serialVersionUID = -2649489893370451304L;
    @Column(name = "type", nullable = true)
    private Boolean type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient", nullable = true)
    private AccountEntity patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicar", nullable = true)
    private AccountEntity medicar;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = true)
    private AddressEntity address;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = true)
    private StatusEntity status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", nullable = true)
    private CalendarEntity calendar;

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public AccountEntity getPatient() {
        return patient;
    }

    public void setPatient(AccountEntity patient) {
        this.patient = patient;
    }

    public AccountEntity getMedicar() {
        return medicar;
    }

    public void setMedicar(AccountEntity medicar) {
        this.medicar = medicar;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public CalendarEntity getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarEntity calendar) {
        this.calendar = calendar;
    }
    
    
}
