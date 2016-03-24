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
 * File name     : ConfigurationEntity.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 16, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "configuration")
@NamedQuery(name = "ConfigurationEntity.findAll", query = "Select c From ConfigurationEntity c")
public class ConfigurationEntity extends NameCodeDescEntity {
    /**
     * <p>Description of this field.</p>
     */
    private static final long serialVersionUID = 667694748031665655L;
    
    @Column(name = "value", nullable = true)
    private String value;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "configuration")
    private List<CalendarEntity> calendars = new ArrayList<CalendarEntity>();
    
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public List<CalendarEntity> getCalendars() {
        return calendars;
    }
    public void setCalendars(List<CalendarEntity> calendars) {
        this.calendars = calendars;
    }
}
