package com.clinic.clinic.common.dto.biz;

import com.clinic.clinic.common.dto.IdDto;

public final class TimingsDto extends IdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -920324334695393803L;

	private int beginTime;
	private int length;
	
	public int getBegin() {
		return beginTime;
	}
	
	public void setBegin(int begin) {
		this.beginTime = begin;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public int getEnd() {
		return beginTime + length;
	}
}
