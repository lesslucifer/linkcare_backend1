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
 * Project name  : clinic-data<br>
 * File name     : CommetDto.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 20, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.common.dto.biz;

import com.clinic.clinic.common.dto.NameCodeDto;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class CommetDto extends NameCodeDto {

    /**
     * <p>Description of this field.</p>
     */
    private static final long serialVersionUID = -7681647317961504579L;
    
    private String content;
    private AccountDto patient;
    private AccountDto medicar;
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public AccountDto getPatient() {
        return patient;
    }
    public void setPatient(AccountDto patient) {
        this.patient = patient;
    }
    public AccountDto getMedicar() {
        return medicar;
    }
    public void setMedicar(AccountDto medicar) {
        this.medicar = medicar;
    }
}
