package com.clinic.clinic.common.dto.biz;

import java.time.LocalDateTime;

import com.clinic.clinic.common.dto.IdDto;

import net.sf.oval.constraint.NotNull;

public class AccountBlockTimeDto extends IdDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7062806960931261125L;

	@NotNull
	LocalDateTime begin;
	
	@NotNull
	Integer length;

	public LocalDateTime getBegin() {
		return begin;
	}

	public void setBegin(LocalDateTime begin) {
		this.begin = begin;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
}