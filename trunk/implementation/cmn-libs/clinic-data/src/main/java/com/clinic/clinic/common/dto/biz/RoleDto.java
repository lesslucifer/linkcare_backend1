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
 * File name     : RoleDto.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 18, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.common.dto.biz;

import java.util.ArrayList;
import java.util.List;

import com.clinic.clinic.common.dto.NameCodeDescDto;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class RoleDto extends NameCodeDescDto {

    /**
     * <p>Description of this field.</p>
     */
    private static final long serialVersionUID = 5889637560946480240L;
    private List<ClinicRightDto> clinicRights = new ArrayList<ClinicRightDto>();
    public List<ClinicRightDto> getClinicRights() {
        return clinicRights;
    }
    public void setClinicRights(List<ClinicRightDto> clinicRights) {
        this.clinicRights = clinicRights;
    }
}
