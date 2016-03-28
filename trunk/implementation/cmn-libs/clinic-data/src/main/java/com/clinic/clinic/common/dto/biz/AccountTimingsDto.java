package com.clinic.clinic.common.dto.biz;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.clinic.clinic.common.consts.IConstants;
import com.clinic.clinic.common.consts.IDbConstants;
import com.clinic.clinic.common.dto.TraceDto;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountTimingsDto extends TraceDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6828910797926776751L;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern=IConstants.DateForMat_DDMMYYYY)
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
