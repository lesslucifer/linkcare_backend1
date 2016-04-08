package com.clinic.clinic.common.consts;

public interface IBizErrorCode {
	// # Common (-1 -> -99)
	static final int UNKNOWN_ERROR = -1;
	static final int OBJECT_NOT_FOUND = -2;
	static final int INVALIDATED_OBJECT = -3;
	static final int NOT_SUPPORTED = -4;
	
	// # Authenticate (-101 -> -199)
	static final int INVALID_SESSION = -101;
	static final int MISSING_RIGHT = -102;
	static final int WRONG_USERNAME_OR_PASSWORD = -103;
	
	// # Reserve (-201 -> -999)
	
	// # Doctor's timings (-1001 -> -1999)
	static final int TIMINGS_EMPTY = -1001;
	static final int TIMINGS_CONFLICT = -1002;
	static final int TIMINGS_NOT_FOUND = -1002;
	
	// # Appointment (-2001 -> -2999)
	static final int APPOINTMENT_MEDICAR_SUB_CATEGORY_MISMATCH = -2001;
	static final int APPOINTMENT_INVALID_TIME = -2002;
	static final int APPOINTMENT_BOOKER_MISMATCH = -2003;
	static final int APPOINTMENT_STATUS_MISMATCH = -2004;
	static final int APPOINTMENT_MEDICAR_MISMATCH = -2005;
	static final int APPOINTMENT_INVALID_APPROVE_TIME = -2006;
	static final int APPOINTMENT_HAVE_UNFINISHED_APPOINTMENT = -2007;
    
	// # rating
	static final int RATING_NOT_FOUNT = -3001;
    static final int APPOINTMENT_NOT_FOUNT = -3002;
    static final int APPOINTMENT_NOT_FINISHED = -3003;
}
