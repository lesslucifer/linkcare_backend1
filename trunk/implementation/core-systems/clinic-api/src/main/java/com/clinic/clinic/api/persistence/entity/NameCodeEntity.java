/**==============================================================================
 * CLINIC JSC (www.clinic.vn) Proprietary.
 * Copyright 2015 Clinic JSC.
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
 * File name     : NameCodeEntity.java<br>
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
 * The abstract entity class contains ID, code and name attributes.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see {@link NameEntity}
 */
@MappedSuperclass
public abstract class NameCodeEntity extends NameEntity {
    private static final long serialVersionUID = -7922778916904672693L;
    
    /**
     * The name attribute.
     */
    @Column(name="code", unique = true, nullable = false, length=45)
    private String code;
    
    /**
     * <p>Default constructor of this class.</p>
     */
    public NameCodeEntity() {
        this(null, null);
    }
    
    /**
     * <p>Default constructor of this class.</p>
     */
    public NameCodeEntity(String name, String code) {
        super(name);
        this.code = code;
    }

    /**
     * <p>Returns current value of code attribute.</p>
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * <p>Sets value of code attribute.</p>
     *
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
}
