/**==============================================================================
 * clinic JSC (www.clinic.vn) Proprietary.
 * Copyright 2015 clinic JSC.
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
 * File name     : IdEntity.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Aug 20, 2015		Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * <p>
 * The abstract entity contains ID attribute.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see {@link Serializable}
 */
@MappedSuperclass
public abstract class IdEntity implements Serializable {
    private static final long serialVersionUID = 3953977144749349775L;
    /**
     * The ID attribute.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * <p>Returns current value of id attribute.</p>
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * <p>Sets value of id attribute.</p>
     *
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
