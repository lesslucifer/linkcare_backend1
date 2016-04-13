package com.clinic.clinic.common.utils;

import java.time.format.DateTimeFormatter;

import com.clinic.clinic.common.consts.IConstants;

public class DateTimeFormatters {
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(IConstants.DateForMat_DDMMYYYY);
	public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(IConstants.DateForMat_DDMMYYYY_HHMM);
	public static final DateTimeFormatter HOUR_MINUTE_FORMATTER = DateTimeFormatter.ofPattern(IConstants.DateForMat_HHMM);
}
