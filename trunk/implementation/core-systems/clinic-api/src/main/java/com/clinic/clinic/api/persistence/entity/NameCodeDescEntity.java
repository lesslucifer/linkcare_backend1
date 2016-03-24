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
 * Project name  : maper-api<br>
 * File name     : NameCodeDescEntity.java<br>
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
 * The abstract entity class contains ID, name, description, code  attributes.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see {@link NameDescEntity}
 */
@MappedSuperclass
public abstract class NameCodeDescEntity extends NameDescEntity {
    private static final long serialVersionUID = 4375995012768542055L;
    
    /**
     * The code attribute.
     */
    @Column(name="code", unique = true, length=45)
    private String code;
    
    /**
     * <p>Default constructor.</p>
     */
    public NameCodeDescEntity() {
        this(null, null, null);
    }
    
    /**
     * <p>Default constructor.</p>
     */
    public NameCodeDescEntity(String name, String description, String code) {
        super(name, description);
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
