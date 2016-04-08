/**
 * =============================================================================
 * = CLINIC JSC (www.clinic.vn) Proprietary. Copyright 2016 CLINIC JSC.
 * UNPUBLISHED WORK ALL RIGHTS RESERVED This software is the confidential and
 * proprietary information of clinic J.S.C ("Proprietary Information"). Any use,
 * reproduction, distribution or disclosure of the software or Proprietary
 * Information, in whole or in part, must comply with the terms of the license
 * agreement, nondisclosure agreement or contract entered into with clinic
 * providing access to this software. Project name : clinic-api<br>
 * File name : RoleEntity.java<br>
 * <p>
 * Changes History <br>
 * Date Person Reason<br>
 * Mar 18, 2016 Vuong Do Initial<br>
 * </p>
 *
 * @author Vuong Do
 *         =====================================================================
 *         ========
 */
package com.clinic.clinic.api.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "role")
@NamedQuery(name = "RoleEntity.findAll", query = "Select r From RoleEntity r")
public class RoleEntity extends NameCodeDescEntity {

    /**
     * <p>
     * Description of this field.
     * </p>
     */
    private static final long serialVersionUID = -2840256603887150592L;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity=ClinicRightEntity.class)
    @JoinTable(name = "role_right", catalog = "cliniccore_db",
    joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false)}, 
    inverseJoinColumns = {@JoinColumn(name = "clinic_right_id", nullable = false, updatable = false)})
    @JsonIgnore
    private List<ClinicRightEntity> clinicRights = new ArrayList<ClinicRightEntity>();

    public List<ClinicRightEntity> getClinicRights() {
        return clinicRights;
    }

    public void setClinicRights(List<ClinicRightEntity> clinicRights) {
        this.clinicRights = clinicRights;
    }
    
}
