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
 * File name     : ConfigurationTranslatorImpl.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 16, 2016				dailq				Initial<br>
 * </p>
 *
 * @author dailq
 *=============================================================================*/
package com.clinic.clinic.api.translator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clinic.clinic.api.persistence.entity.AddressEntity;
import com.clinic.clinic.api.persistence.entity.ConfigurationEntity;
import com.clinic.clinic.api.translator.ITranslator;
import com.clinic.clinic.common.dto.biz.AddressDto;
import com.clinic.clinic.common.dto.biz.ConfigurationDto;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author dailq<br>
 * @version 1.0<br>
 * @see TODO
 */
public class ConfigurationTranslatorImpl extends AbstractTranslatorImpl<ConfigurationDto, ConfigurationEntity>
        implements
            ITranslator<ConfigurationDto, ConfigurationEntity> {

	
	public ConfigurationTranslatorImpl() {
		super(ConfigurationDto.class, ConfigurationEntity.class);
	}
}
