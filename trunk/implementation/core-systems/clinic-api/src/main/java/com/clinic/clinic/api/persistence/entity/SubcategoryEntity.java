/**
 * =============================================================================
 * = CLINIC JSC (www.clinic.vn) Proprietary. Copyright 2016 CLINIC JSC.
 * UNPUBLISHED WORK ALL RIGHTS RESERVED This software is the confidential and
 * proprietary information of clinic J.S.C ("Proprietary Information"). Any use,
 * reproduction, distribution or disclosure of the software or Proprietary
 * Information, in whole or in part, must comply with the terms of the license
 * agreement, nondisclosure agreement or contract entered into with clinic
 * providing access to this software. Project name : clinic-api<br>
 * File name : SubcategoryEntity.java<br>
 * <p>
 * Changes History <br>
 * Date Person Reason<br>
 * Mar 6, 2016 Vuong Do Initial<br>
 * </p>
 *
 * @author Vuong Do
 *         =====================================================================
 *         ========
 */
package com.clinic.clinic.api.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "subcategory")
@NamedQuery(name = "SubcategoryEntity.findAll", query = "Select s From SubcategoryEntity s")
public class SubcategoryEntity extends NameCodeDescEntity {
    /**
     * <p>
     * Description of this field.
     * </p>
     */
    private static final long serialVersionUID = 5295985330516462105L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    
    @Column(name = "clinic_dur")
    private int clinicDur;
    
    @Column(name = "patient_home_dur")
    private int patientHomeDur;

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

	public int getClinicDur() {
		return clinicDur;
	}

	public void setClinicDur(int clinicDur) {
		this.clinicDur = clinicDur;
	}

	public int getPatientHomeDur() {
		return patientHomeDur;
	}

	public void setPatientHomeDur(int patientHomeDur) {
		this.patientHomeDur = patientHomeDur;
	}
}
