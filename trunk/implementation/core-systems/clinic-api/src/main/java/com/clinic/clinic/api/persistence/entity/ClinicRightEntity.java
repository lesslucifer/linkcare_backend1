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
 * File name     : ClinicRightEntity.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 18, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.entity;

import javax.persistence.Entity;
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
@Table(name = "clinic_right")
@NamedQuery(name = "ClinicRightEntity.findAll", query = "Select c From ClinicRightEntity c")
public class ClinicRightEntity extends NameCodeDescEntity {
    /**
     * <p>Description of this field.</p>
     */
    private static final long serialVersionUID = 1728903689391722238L;
//    @ManyToMany(mappedBy = "clinicRights", targetEntity = RoleEntity.class)
//    private List<RoleEntity> roles = new ArrayList<RoleEntity>();
//    
//    public List<RoleEntity> getRoles() {
//        return roles;
//    }
//    public void setRoles(List<RoleEntity> roles) {
//        this.roles = roles;
//    }
}
