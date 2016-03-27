package com.clinic.clinic.common.dto.biz;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.clinic.clinic.common.dto.TraceDto;

public class AccountTimingsDto extends TraceDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6828910797926776751L;
	
	private Date beginDate;
	private List<TimingsDto> timings = new ArrayList<>();
	
	@Override
	public void toUpercaseFirstChar() {
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public List<TimingsDto> getTimings() {
		return timings;
	}

	public void setTimings(List<TimingsDto> timings) {
		this.timings = timings;
	}
}
