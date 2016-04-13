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
	static final int WRONG_USERNAME = -103;
	static final int WRONG_PASSWORD = -104;
	
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
	static final int APPOINTMENT_INVALID_TYPE = -2008;
	static final int APPOINTMENT_INVALID_MEDICAR_PROFILE = -2009;
	static final int APPOINTMENT_PATIENT_ALREADY_BOOKED_RECENT_APPOINTMENT = -2010;

	// # Prescription (-3001 -> -3999)
	static final int PRESCRIPTION_APPOINTMENT_STATUS_MISMATCH = -3001;
	static final int PRESCRIPTION_MEDICAR_MISMATCH = -3002;
	static final int PRESCRIPTION_PATIENT_MISMATCH = -3003;
    
	// # rating (-4001 -> -4999)
	static final int RATING_NOT_FOUNT = -4001;
    static final int APPOINTMENT_NOT_FOUNT = -4002;
    static final int APPOINTMENT_NOT_FINISHED = -4003;
    
    // # Block Vacation (-5001 -> 5999)
    static final int BLOCK_VACATION_CONFLICT_TIME = -5001;
    static final int BLOCK_VACATION_TIME_IN_PAST = -5002;
    static final int BLOCK_VACATION_HAS_PROCESSING_APPOINTMENT = -5003;
    static final int BLOCK_VACATION_INVALID_OWNER = -5004;
}
