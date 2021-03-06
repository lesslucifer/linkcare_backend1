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
 * File name     : IRateRepository.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 20, 2016				dailq				Initial<br>
 * </p>
 *
 * @author dailq
 *=============================================================================*/
package com.clinic.clinic.api.persistence.repository;

import com.clinic.clinic.api.persistence.entity.RateEntity;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author dailq<br>
 * @version 1.0<br>
 * @see TODO
 */
public interface IRateRepository extends IRepository<RateEntity, Integer> {

    /**
     * <p>Description of this method.</p>
     *
     * @param medicarId
     * @return
     *
     * @author Vuong Do
     */
    RateEntity findRateEntityByMedicarId(Integer medicarId);
}
