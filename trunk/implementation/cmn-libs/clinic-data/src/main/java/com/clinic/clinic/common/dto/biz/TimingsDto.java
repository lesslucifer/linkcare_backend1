package com.clinic.clinic.common.dto.biz;

import com.clinic.clinic.common.dto.IdDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import net.sf.oval.constraint.NotNull;

public final class TimingsDto extends IdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -920324334695393803L;

	@NotNull
	private Integer beginTime;
	
	@NotNull
	private Integer length;
	
	@NotNull
	private Integer type;
	
	public int getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(int begin) {
		this.beginTime = begin;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@JsonIgnore
	public int getEnd() {
		return beginTime + length;
	}
}
