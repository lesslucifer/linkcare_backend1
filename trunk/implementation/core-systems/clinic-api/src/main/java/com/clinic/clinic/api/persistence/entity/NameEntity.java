/**==============================================================================
 * CLINIC JSC (www.clinic.vn) Proprietary.
 * Copyright 2015 CLINIC JSC.
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
 * File name     : NameEntity.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Aug 20, 2015		Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * <p>
 * The abstract entity class contains ID, and name attributes.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see {@link IdEntity}
 */
@MappedSuperclass
public abstract class NameEntity extends TraceEntity {
    private static final long serialVersionUID = -7922778916904672693L;
    
    /**
     * The name attribute.
     */
    @Column(name="name", length=450)
    private String name;
    
    /**
     * <p>Default constructor of this class.</p>
     */
    public NameEntity() {
        this(null);
    }
    
    /**
     * <p>Default constructor of this class.</p>
     */
    public NameEntity(String name) {
        super();
        this.name=name;
    }
    
    /**
     * <p>Returns current value of name attribute.</p>
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * <p>Sets value of name attribute.</p>
     *
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }
}
