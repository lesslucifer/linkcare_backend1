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
 * File name     : AddressRepository.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		May 3, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.api.persistence.repository.impl;

import org.springframework.stereotype.Repository;

import com.clinic.clinic.api.persistence.entity.AddressEntity;
import com.clinic.clinic.api.persistence.repository.IAddressRepository;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
@Repository
public class AddressRepository extends AbsRepositoryImpl<AddressEntity, Integer> implements IAddressRepository{
    
}
