package com.clinic.clinic.common.consts;

public interface IRestApiUrlMaps {

    // #### WS RESTFULL current version ####
    static final String REST_API_HOST = "http://localhost:8080/clinic-api/biz/v1";
    
    // #### WS RESTFULL current version ####
    static final String REST_API_CURRENT_VERSION = "v1";

    /**
     * Business group.
     */
    static final String REST_API_BIZ_GROUP = "biz/" + REST_API_CURRENT_VERSION;
    
    static final String REST_API_BIZ_ADDRESSES_ADDRESSID = "/addresses/{addressId}";

    static final String REST_API_BIZ_MAJORS = "/majors";
	static final String REST_API_BIZ_MAJOR_MAJOR_ID = "/major/{majorId}";

	// ### Category
    static final String REST_API_BIZ_CATEGORIES = "/categories";
    static final String REST_API_BIZ_CATEGORIES_MAJOR_MAJOR_ID = "/categories/major/{majorId}";

    // ### Sub-category
    static final String REST_API_BIZ_SUBCATEGORY_CATEGORY_ID = "/subcategories/category/{categoryId}";
    static final String REST_API_BIZ_SUBCATEGORY_MAJOR_ID = "/subcategories/major/{majorId}";
    static final String REST_API_BIZ_SUBCATEGORY_MAJOR_ID_FULL = "/subcategories/major/{majorId}/full";
    
    // ### Account
    static final String REST_API_BIZ_ACCOUNT_SUBCATEGORY_SUBCATEGORYID = "/account/subcategory/{subcategoryId}";
    static final String REST_API_BIZ_ACCOUNT_FILTER = "/account/filter";
    static final String REST_API_BIZ_ACCOUNT_LOGIN = "/account/login";
    static final String REST_API_BIZ_ACCOUNT_LOGOUT = "/account/logout";
    static final String REST_API_BIZ_ACCOUNT_IDCARD_PROFILE = "/account/profile";
    static final String REST_API_BIZ_ACCOUNT_REGISTER = "/account/patient/register";
    
    // ### Timings
    static final String REST_API_BIZ_TIMINGS = "/timings";
    static final String REST_API_BIZ_TIMINGS_SLOT = "/timings/slot/{targetId}";
    static final String REST_API_BIZ_TIMINGS_SLOT_TYPE = "/timings/slot/{targetId}/type/{type}{?date}";
    static final String REST_API_BIZ_TIMINGS_SLOT_TYPE_COUNT = "/timings/slot/{targetId}/date/{date}/count";
    static final String REST_API_BIZ_MEDICAR_TIMINGS_SLOT = "/medicar/timings/slot{?date}";
    static final String REST_API_BIZ_MEDICAR_TIMINGS_SLOT_COUNT = "/medicar/timings/slot/count{?date}";
    
    
    // ### Appointments
    static final String REST_API_BIZ_APPOINTMENTS = "/appointments";
    static final String REST_API_BIZ_APPOINTMENTS_SINGLE = REST_API_BIZ_APPOINTMENTS + "/{appointment_id}";
    static final String REST_API_BIZ_APPOINTMENTS_APPROVE = REST_API_BIZ_APPOINTMENTS_SINGLE + "/approve";
    static final String REST_API_BIZ_APPOINTMENTS_REJECT = REST_API_BIZ_APPOINTMENTS_SINGLE + "/reject";
    static final String REST_API_BIZ_APPOINTMENTS_CANCEL = REST_API_BIZ_APPOINTMENTS_SINGLE + "/cancel";
    static final String REST_API_BIZ_APPOINTMENTS_START = REST_API_BIZ_APPOINTMENTS_SINGLE + "/start";
    
    // ### Medicar's appointments
    static final String REST_API_MEDICAR_APPOINTMENTS = "/medicar/appointments";
    static final String REST_API_MEDICAR_APPOINTMENTS_DATE = REST_API_MEDICAR_APPOINTMENTS + "/{date}";
    static final String REST_API_MEDICAR_APPOINTMENTS_TODAY = REST_API_MEDICAR_APPOINTMENTS + "/today";
    static final String REST_API_MEDICAR_APPOINTMENTS_BY_TYPE = REST_API_MEDICAR_APPOINTMENTS_DATE + "/type/{type}";
    static final String REST_API_COUNT_MEDICAR_APPOINTMENTS_BY_TYPE = REST_API_MEDICAR_APPOINTMENTS_DATE + "/type/{type}/count";
    static final String REST_API_APPOINTMENTS_BY_STATUS = REST_API_MEDICAR_APPOINTMENTS + "/status/{status}";

    static final String REST_API_BIZ_PARTIENT_RATING = "/partient/rating/medicar/{medicarId}";
    
    // ### Prescriptions
    static final String REST_API_BIZ_PRESCRIPTIONS = "/prescriptions";
    static final String REST_API_BIZ_PRESCRIPTIONS_SINGLE = REST_API_BIZ_PRESCRIPTIONS + "/{prescription_id}";
    static final String REST_API_BIZ_PATIENT_PRESCRIPTIONS = "account/{patient_id}/prescriptions";
    
    // ### Block vacations
    static final String REST_API_BIZ_BLOCK_VACATIONS = "/block_vacations";
    static final String REST_API_BIZ_BLOCK_VACATIONS_SINGLE = REST_API_BIZ_BLOCK_VACATIONS + "/{block_vacation_id}";
    static final String REST_API_BIZ_BLOCK_VACATIONS_SINGLE_DELETE = REST_API_BIZ_BLOCK_VACATIONS_SINGLE + "/delete";

    // ### Doctor or Nurse
    static final String REST_API_BIZ_MEDICAR_RATE = "/medicar/{medicarId}/rate";
    
    // ### Medicar profile
    static final String REST_API_MEDICAR_REGISTER_DOCTOR = "/account/medicars/doctors";
    static final String REST_API_MEDICAR_REGISTER_NURSE = "/account/medicars/nurses";
    static final String REST_API_MEDICAR_PROFILE_SINGLE = "/account/{account_id}/medicar_profile";
    static final String REST_API_MEDICAR_PROFILE_EXTEND = "/account/{account_id}/medicar_profile/extend";
    
    // ### Notification
    static final String REST_API_NOTIFICATIONS = "/notifications";
    static final String REST_API_NOTIFICATIONS_SINGLE = "/notifications/{notif_id}";
    static final String REST_API_NOTIFICATIONS_SINGLE_READ = "/notifications/{notif_id}/read";
}
