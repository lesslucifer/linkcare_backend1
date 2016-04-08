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
 * File name     : RateTraceEntity.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Apr 7, 2016				Vuong Do				Initial<br>
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
@Table(name = "rate_trace")
@NamedQuery(name = "RateTraceEntity.findAll", query = "Select r From RateTraceEntity r")
public class RateTraceEntity extends TraceEntity {
    /**
     * <p>Description of this field.</p>
     */
    private static final long serialVersionUID = 5855848231776242322L;
    
    @Column(name = "mark", nullable = false)
    private Double mark;
    @Column(name = "comment", nullable = true)
    private String comment;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_booking_id", nullable = false)
    private AppointmentBookingEntity appointmentBooking;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partient_id", nullable = false)
    private AccountEntity partient;
    public Double getMark() {
        return mark;
    }
    public void setMark(Double mark) {
        this.mark = mark;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public AppointmentBookingEntity getAppointmentBooking() {
        return appointmentBooking;
    }
    public void setAppointmentBooking(AppointmentBookingEntity appointmentBooking) {
        this.appointmentBooking = appointmentBooking;
    }
    public AccountEntity getPartient() {
        return partient;
    }
    public void setPartient(AccountEntity partient) {
        this.partient = partient;
    }
}
