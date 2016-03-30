package com.clinic.clinic.common.dto.biz;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.dto.TraceDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

public class AccountTimingsDto extends TraceDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6828910797926776751L;
	
	@NotNull
	private LocalDate beginDate;
	
	@NotEmpty
	private List<TimingsDto> timings = new ArrayList<>();
	
	@Override
	public void toUpercaseFirstChar() {
	}

	@JsonProperty(required = true)
	public LocalDate getBeginDate() {
		return beginDate;
	}

	@JsonProperty(required = true)
	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public List<TimingsDto> getTimings() {
		return timings;
	}

	public void setTimings(List<TimingsDto> timings) {
		this.timings = timings;
	}
}
