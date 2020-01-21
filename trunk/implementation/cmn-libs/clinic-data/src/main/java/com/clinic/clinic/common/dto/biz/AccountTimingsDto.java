package com.clinic.clinic.common.dto.biz;

import java.util.ArrayList;
import java.util.List;

import com.clinic.clinic.common.dto.TraceDto;

import net.sf.oval.constraint.NotEmpty;

public class AccountTimingsDto extends TraceDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6828910797926776751L;
	
	@NotEmpty
	private List<TimingsDto> timings = new ArrayList<>();
	
	@Override
	public void toUpercaseFirstChar() {
	}
	
	public List<TimingsDto> getTimings() {
		return timings;
	}

	public void setTimings(List<TimingsDto> timings) {
		this.timings = timings;
	}
}
