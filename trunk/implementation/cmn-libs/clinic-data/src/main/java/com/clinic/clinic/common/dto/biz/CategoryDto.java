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
 * File name     : CategoryDto.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Mar 2, 2016				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.common.dto.biz;

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
public class CategoryDto extends NameCodeDescDto {

	private static final long serialVersionUID = 1L;

	private Long viewRadiusLimit;
	private String i18nLangKeyPrefix;
	private MajorDto major;

	public Long getViewRadiusLimit() {
		return viewRadiusLimit;
	}

	public void setViewRadiusLimit(Long viewRadiusLimit) {
		this.viewRadiusLimit = viewRadiusLimit;
	}

	public String getI18nLangKeyPrefix() {
		return i18nLangKeyPrefix;
	}

	public void setI18nLangKeyPrefix(String i18nLangKeyPrefix) {
		this.i18nLangKeyPrefix = i18nLangKeyPrefix;
	}

	public MajorDto getMajor() {
		return major;
	}

	public void setMajor(MajorDto major) {
		this.major = major;
	}

	@Override
	public void toUpercaseFirstChar() {
		super.toUpercaseFirstChar();
	}
}