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
 * File name     : CommentEntity.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 20, 2016				Vuong Do				Initial<br>
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
@Table(name = "comment")
@NamedQuery(name = "CommentEntity.findAll", query = "Select c From CommentEntity c")
public class CommentEntity extends NameCodeEntity {

    /**
     * <p>Description of this field.</p>
     */
    private static final long serialVersionUID = -245364151369775450L;
    
    @Column(name = "content", nullable = true, length = 1000)
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = true)
    private AccountEntity patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicar_id", nullable = true)
    private AccountEntity medicar;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AccountEntity getPatient() {
        return patient;
    }

    public void setPatient(AccountEntity patient) {
        this.patient = patient;
    }

    public AccountEntity getMedicar() {
        return medicar;
    }

    public void setMedicar(AccountEntity medicar) {
        this.medicar = medicar;
    }
}
