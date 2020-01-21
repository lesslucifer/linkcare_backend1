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
 * File name     : ApplicationService.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Aug 20, 2015		daiql				Initial<br>
 * </p>
 *
 * @author daiql
 *=============================================================================*/
package com.clinic.clinic.api.bizlogic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * A customization of meta annotation for services.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Service
@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public @interface ApplicationService {
    
}