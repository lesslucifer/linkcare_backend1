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
 * File name     : NameDescEntity.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Aug 20, 2016		Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * <p>
 * The abstract entity class contains ID, name, description attributes.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see {@link NameEntity}
 */
@MappedSuperclass
public abstract class NameDescEntity extends NameEntity {
    private static final long serialVersionUID = 4375995012768542055L;
    /**
     * The description attribute.
     */
    @Column(name="description", length=1000)
    private String description;
    
    /**
     * <p>Default constructor.</p>
     */
    public NameDescEntity() {
        this(null, null);
    }
    
    /**
     * <p>Default constructor of this class.</p>
     *
     * @param name
     * @param description
     */
    public NameDescEntity(String name, String description) {
        super(name);
        this.description = description;
    }

    /**
     * <p>Returns current value of description attribute.</p>
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * <p>Sets value of description attribute.</p>
     *
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }
}
