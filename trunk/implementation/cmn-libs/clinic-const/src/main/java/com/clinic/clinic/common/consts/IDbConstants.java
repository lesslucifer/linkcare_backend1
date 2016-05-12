package com.clinic.clinic.common.consts;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public interface IDbConstants {

	/**
	 * The code indicates FALSE.
	 */
	static final byte FALSE_VALUE = 0;
	/**
	 * The code indicates TRUE.
	 */
	static final byte TRUE_VALUE = 1;

	/**
	 * The code indicates FALSE.
	 */
	static final boolean FALSE = false;
	/**
	 * The code indicates TRUE.
	 */
	static final boolean TRUE = true;

	/**
	 * Wild card for 1 character in Like expression
	 */
	static final String LIKE_WC_ONE_CHAR = "_";

	/**
	 * Wild card for many characters in Like expression
	 */
	static final String LIKE_WC_MANY_CHAR = "%";

	/**
	 * start order of the counter part of code. Use for zone, team, location.
	 */
	static final String CODE_COUNTER_START_ORDER = "1";

	/**
	 * start character of code in temporary of pending place.
	 */
	static final String CODE_PLACE_TEMP_PREFIX = "T";

	static final String CODE_PROMOTION_PREFIX = "KM";

	/**
	 * default value of length of place code (including prefix).
	 */
	static final int CODE_DEFAULT_LENGTH = 15;

	/**
	 * default value of length of coupon code.
	 * </p>
	 */
	static final int CODE_COUNPON_DEFAULT_LENGTH = 6;

	static final String FILE_IMAGE_LOGO_NAME = "/logo/logo.png";

	static final String FILE_IMAGE_AVATAR_NAME = "/avatar/avatar.png";
	/**
	 * <p>
	 * API key for access googlemap service
	 * </p>
	 */
	static final String GOOGLE_MAP_API_KEY = "AIzaSyDrxKqAnQLndFcOdcW1eKsRPkfAnveLD6I";

	static final String FOLDER_IMAGES_PLACE = "/places";

	static final String FOLDER_IMAGES_MENU = "/menus";

	static final String FOLDER_IMAGES_ACCOUNT = "/account";

	static final String FOLDER_IMAGES_ACCOUNT_AVATAR = "/avatar";

	static final String FOLDER_IMAGES_PROMOTION = "/promotions";

	static final String FOLDER_IMAGES_COMMENTS = "/comments";

	static final String LOGO_PLACE_IMAGES_NAME = "logo_";

	static final String AVATAR_IMAGES_NAME = "avatar.png";

	static final Long BEGIN_ACTIVE_TIME_BO_PACKAGE_PROMOTION = 1454284800000L;

	static final Long END_ACTIVE_TIME_BO_PACKAGE_PROMOTION = 1461974400000L;

	/**
	 * common field names
	 */
	static final String FIELD_ID = "id";
	static final String FIELD_CODE = "code";
	static final String FIELD_NAME = "name";
	static final String FIELD_DESC = "description";
	static final String FIELD_IS_DELETED = "isDeleted";
	static final String FIELD_LAST_UPDATED = "lastUpdated";
	static final String FIELD_LAST_UPDATED_BY = "lastUpdatedBy";
	static final String FIELD_CREATED_BY = "createdBy";
	static final String FIELD_BEGIN_ACTIVE_TIME = "beginActiveTime";
	static final String FIELD_END_ACTIVE_TIME = "endActiveTime";
	static final String FIELD_REGISTER_TIME = "registerTime";
	static final String FIELD_IMAGE_URL = "imageUrl";
	static final String FIELD_ACTIVE_FLAG = "activeFlag";

	/**
	 * account field names
	 */
	static final String FIELD_ACC_LOGIN_NAME = "loginName";
	static final String FIELD_ACC_FACEBOOK_ID = "facebookId";
	static final String FIELD_ACC_PHONE_NUMBER = "phoneNumber";
	static final String FIELD_ACC_EMAIL = "email";
	static final String FIELD_ACC_GOOGLE_PLUS_ID = "googlePlusId";
	static final String FIELD_ACC_HASHED_PASSWORD = "hashedPassword";
	static final String FIELD_ACC_ACTIVE_FLAG = "activeFlag";
	static final String FIELD_ACC_BEGIN_ACTIVE_TIME = "beginActiveTime";
	static final String FIELD_ACC_END_ACTIVE_TIME = "endActiveTime";
	static final String FIELD_ACC_NEED_CHANGE_PWD = "needChangePwd";
	static final Integer FIELD_ACC_ACCOUNT_ID = 2;

	/**
	 * address field names
	 */
	static final String FIELD_ADDRESS_HOUSENUMBER = "houseNumber";
	static final String FIELD_ADDRESS_STREET = "street";
	static final String FIELD_ADDRESS_HAMLET = "hamlet";
	static final String FIELD_ADDRESS_WARD = "ward";
	static final String FIELD_ADDRESS_DISTRICT = "district";
	static final String FIELD_ADDRESS_CITY = "city";

	/**
	 * foreign key field names
	 */
	static final String FIELD_FK_ACCOUNT = "account";
	static final String FIELD_FK_ACCOUNTS = "accounts";
	static final String FIELD_FK_ACCOUNT_TEAM_LINK_TYPE = "accountTeamLinkType";
	static final String FIELD_FK_ADDITIONAL_DESCRIPTION = "additionalDescription";
	static final String FIELD_FK_ADDITIONAL_DESCRIPTION_TYPE = "additionalDescriptionType";
	static final String FIELD_FK_CATEGORY = "category";
	static final String FIELD_FK_SUBCATEGORY = "subcategory";
	static final String FIELD_FK_MAJOR = "major";
	/*
	 * static final String FIELD_FK_CONTACT = "contact"; static final String
	 * FIELD_FK_CONTACT_PRIORITY_TYPE = "contactPriorityType"; static final
	 * String FIELD_FK_CONTACT_TYPE = "contactType";
	 */

	static final String FIELD_FK_CITY = "city";
	static final String FIELD_FK_DATA_TYPE = "dataType";
	static final String FIELD_FK_DATA_UNIT_FORMAT = "dataUnitFormat";
	static final String FIELD_FK_FAMILY_STATUS = "familyStatus";
	static final String FIELD_FK_FORMAT_TYPE = "formatType";
	static final String FIELD_FK_PLACE_BACKUP = "placeBackup";
	static final String FIELD_FK_PLACE = "place";
	static final String FIELD_FK_PLACES = "places";
	static final String FIELD_FK_PLACE_STATUS = "placeStatus";
	static final String FIELD_FK_ACCOUNT_PLACE_ACTION_TYPE = "accountPlaceActionType";
	static final String FIELD_FK_PLACE_SUB_CATEGORY_LINK_TYPE = "placeSubCategoryLinkType";
	static final String FIELD_FK_MAPER_RIGHT = "maperRight";
	static final String FIELD_FK_ROLE = "role";
	static final String FIELD_FK_TEAM = "team";
	static final String FIELD_FK_UNIT_TYPE = "unitType";
	static final String FIELD_FK_UTILITY_TYPE = "utilityType";
	static final String FIELD_FK_ZONE = "zone";
	static final String FIELD_FK_FAVORITE = "favorite";
	static final String FIELD_FK_PROMOTION = "promotion";
	static final String FIELD_FK_PROMOTIONS = "promotions";
	static final String FIELD_FK_PROMOTION_COMMENT = "promotionComment";
	static final String FIELD_FK_PLACE_COMMENT = "placeComment";
	static final String FIELD_FK_PLACE_COMMENTS = "placeComments";
	static final String FIELD_FK_TRACE_COIN = "traceCoin";
	static final String FIELD_FK_FEED_CATEGORY = "feedCategory";
	static final String FIELD_FK_ARTICLE_CATEGORY = "articleCategory";
	static final String FIELD_FK_GIFT = "gift";
	static final String FIELD_FK_COMMENT = "comment";
	static final String FIELD_FK_UTILITY_TYPES = "utilityTypes";
	static final String FIELD_FK_LIKE = "liked";
	static final String FIELD_FK_BANNER_CATEGORY = "bannerCategory";
	static final String FIELD_FK_FAVORITE_PLACES = "favoritePlaces";
	static final String FIELD_FK_ADDRESS = "address";
	/**
	 * SessionLog
	 */
	static final String FIELD_SESSION_ID = "sessionId";
	static final String FIELD_LOGIN_TIME = "loginTime";
	static final String FIELD_LOGOUT_TIME = "logoutTime";

	/**
	 * static final code, type, status. Changing of these values requires
	 * rebuild of the system.
	 */
	/*
	 * 
	 * // DataType static final String DATA_INTEGER = "DATA_INTEGER"; static
	 * final String DATA_DOUBLE = "DATA_DOUBLE"; static final String DATA_STRING
	 * = "DATA_STRING"; static final String DATA_DATE = "DATA_DATE"; static
	 * final String DATA_DATETIME = "DATA_DATETIME"; static final String
	 * DATA_TIMESTAMP = "DATA_TIMESTAMP"; static final String DATA_TIME =
	 * "DATA_TIME";
	 * 
	 * // UnitType static final String UNIT_DISTANCE_M = "UNIT_DISTANCE_M";
	 * static final String UNIT_DISTANCE_KM = "UNIT_DISTANCE_KM"; static final
	 * String UNIT_TIME_S = "UNIT_TIME_S"; static final String UNIT_TIME_MI =
	 * "UNIT_TIME_MI"; static final String UNIT_TIME_H = "UNIT_TIME_H"; static
	 * final String UNIT_TIME_D = "UNIT_TIME_D"; static final String
	 * UNIT_TIME_MO = "UNIT_TIME_MO";
	 * 
	 * // FormatType static final String FORMAT_DOUBLE_DOT =
	 * "FORMAT_DOUBLE_DOT"; static final String FORMAT_DATE_yyyyMMdd =
	 * "FORMAT_DATE_yyyyMMdd"; static final String
	 * FORMAT_DATETIME_yyyyMMddHHmmss = "FORMAT_DATETIME_yyyyMMddHHmmss";
	 * 
	 * // DataUnitFormat static final String DATA_INFO_METRE =
	 * "DATA_INFO_METRE";
	 * 
	 * // Configuration static final String CONFIG_ENTER_LOC_RADIUS =
	 * "CONFIG_ENTER_LOC_RADIUS"; static final String CONFIG_MAIL_SERVER_HOST =
	 * "CONFIG_MAIL_SERVER_HOST"; static final String CONFIG_MAIL_SENDER =
	 * "CONFIG_MAIL_SENDER"; static final String CONFIG_MAIL_SENDER_PWD =
	 * "CONFIG_MAIL_SENDER_PWD";
	 * 
	 * // ContactType static final String CONTACT_NORMAL = "CONTACT_NORMAL";
	 * static final String CONTACT_LEAD = "CONTACT_LEAD"; static final String
	 * CONTACT_MONITOR = "CONTACT_MONITOR"; static final String CONTACT_CUSTOMER
	 * = "CONTACT_CUSTOMER";
	 * 
	 * // ContactPriorityType static final String CONTACT_PRIORITY_NONE =
	 * "CONTACT_PRIORITY_NONE"; static final String CONTACT_PRIORITY_LOW =
	 * "CONTACT_PRIORITY_LOW"; static final String CONTACT_PRIORITY_MEDIUM =
	 * "CONTACT_PRIORITY_MEDIUM"; static final String CONTACT_PRIORITY_HIGH =
	 * "CONTACT_PRIORITY_HIGH";
	 * 
	 * // AccountTeamLinkType static final String ACC_TEAM_LINK_COLLECTER =
	 * "ACC_TEAM_LINK_COLLECTER"; static final String ACC_TEAM_LINK_ADMIN =
	 * "ACC_TEAM_LINK_ADMIN"; static final String ACC_TEAM_LINK_SUPER_ADMIN =
	 * "ACC_TEAM_LINK_SUPER_ADMIN";
	 * 
	 * // AdditionalDescriptionType static final String ADD_DESC_LOC_IMG_URL =
	 * "ADD_DESC_LOC_IMG_URL"; static final String ADD_DESC_LOC_MENU_IMG_URL =
	 * "ADD_DESC_LOC_MENU_IMG_URL";
	 */
	// AccountRole
	static final String ROLE_NEWCOMER = "ROLE_NEWCOMER";
	static final String ROLE_COLLABORATOR = "ROLE_COLLABORATOR";
	static final String ROLE_ADMIN = "ROLE_ADMIN";
	static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
	static final String ROLE_SYSTEM_ADMIN = "ROLE_SYSTEM_ADMIN";

	/**
	 * logic error code
	 */
	static final String ERR_CODE_DUPLCATED = "err_code_duplicated";

	/**
	 * Right code: the right code
	 */
	static final String RIGHT_LOG_IN = "RIGHT_LOG_IN";
	static final String RIGHT_LOG_OUT = "RIGHT_LOG_OUT";
	static final String RIGHT_CHANGE_OWN_PWD = "RIGHT_CHANGE_OWN_PWD";
	static final String RIGHT_FORGOT_PWD = "RIGHT_FORGOT_PWD";
	static final String RIGHT_RESET_PWD = "RIGHT_RESET_PWD";
	static final String RIGHT_ACCOUNT_CREATE = "RIGHT_ACCOUNT_CREATE";
	static final String RIGHT_ACCOUNT_DELETE = "RIGHT_ACCOUNT_DELETE";
	static final String RIGHT_ACCOUNT_READ = "RIGHT_ACCOUNT_READ";
	static final String RIGHT_ACCOUNT_UPDATE = "RIGHT_ACCOUNT_UPDATE";
	static final String RIGHT_ACCOUNT_ROLE_CREATE = "RIGHT_ACCOUNT_ROLE_CREATE";
	static final String RIGHT_ACCOUNT_ROLE_DELETE = "RIGHT_ACCOUNT_ROLE_DELETE";
	static final String RIGHT_ACCOUNT_SETTING_CREATE = "RIGHT_ACCOUNT_SETTING_CREATE";
	static final String RIGHT_ACCOUNT_SETTING_DELETE = "RIGHT_ACCOUNT_SETTING_DELETE";
	static final String RIGHT_ACCOUNT_SETTING_READ = "RIGHT_ACCOUNT_SETTING_READ";
	static final String RIGHT_ACCOUNT_SETTING_UPDATE = "RIGHT_ACCOUNT_SETTING_UPDATE";
	static final String RIGHT_ACCOUNT_TEAM_ADMIN_CREATE = "RIGHT_ACCOUNT_TEAM_ADMIN_CREATE";
	static final String RIGHT_ACCOUNT_TEAM_ADMIN_DELETE = "RIGHT_ACCOUNT_TEAM_ADMIN_DELETE";
	static final String RIGHT_ACCOUNT_TEAM_COLLAB_CREATE = "RIGHT_ACCOUNT_TEAM_COLLAB_CREATE";
	static final String RIGHT_ACCOUNT_TEAM_COLLAB_DELETE = "RIGHT_ACCOUNT_TEAM_COLLAB_DELETE";
	static final String RIGHT_CONTACT_CREATE = "RIGHT_CONTACT_CREATE";
	static final String RIGHT_CONTACT_DELETE = "RIGHT_CONTACT_DELETE";
	static final String RIGHT_CONTACT_READ = "RIGHT_CONTACT_READ";
	static final String RIGHT_CONTACT_UPDATE = "RIGHT_CONTACT_UPDATE";
	static final String RIGHT_LOCATION_CREATE = "RIGHT_LOCATION_CREATE";
	static final String RIGHT_LOCATION_DELETE = "RIGHT_LOCATION_DELETE";
	static final String RIGHT_LOCATION_READ = "RIGHT_LOCATION_READ";
	static final String RIGHT_LOCATION_UPDATE = "RIGHT_LOCATION_UPDATE";
	static final String RIGHT_LOCATION_APPROVE_PENDING = "RIGHT_LOCATION_APPROVE_PENDING";
	static final String RIGHT_LOCATION_REJECT_PENDING = "RIGHT_LOCATION_REJECT_PENDING";
	static final String RIGHT_LOCATION_APPROVE_REVIEWED = "RIGHT_LOCATION_APPROVE_REVIEWED";
	static final String RIGHT_LOCATION_REJECT_REVIEWED = "RIGHT_LOCATION_REJECT_REVIEWED";
	static final String RIGHT_LOCATION_IMPORT = "RIGHT_LOCATION_IMPORT";
	static final String RIGHT_LOCATION_IMPORTED_POS_INIT = "RIGHT_LOCATION_IMPORTED_POS_INIT";
	static final String RIGHT_LOCATION_IMPORTED_POS_VALID = "RIGHT_LOCATION_IMPORTED_POS_VALID";
	static final String RIGHT_TEAM_CREATE = "RIGHT_TEAM_CREATE";
	static final String RIGHT_TEAM_DELETE = "RIGHT_TEAM_DELETE";
	static final String RIGHT_TEAM_READ = "RIGHT_TEAM_READ";
	static final String RIGHT_TEAM_UPDATE = "RIGHT_TEAM_UPDATE";
	static final String RIGHT_ZONE_CREATE = "RIGHT_ZONE_CREATE";
	static final String RIGHT_ZONE_DELETE = "RIGHT_ZONE_DELETE";
	static final String RIGHT_ZONE_READ = "RIGHT_ZONE_READ";
	static final String RIGHT_ZONE_UPDATE = "RIGHT_ZONE_UPDATE";
	static final String RIGHT_REPORT_READ = "RIGHT_REPORT_READ";
	static final String RIGHT_REPORT_CREATE = "RIGHT_REPORT_CREATE";
	static final String RIGHT_UPDATE_TIMINGS = "RIGHT_UPDATE_TIMINGS";
	static final String RIGHT_BOOK_APPOINTMENT = "RIGHT_BOOK_APPOINTMENT";
	static final String RIGHT_APPROVE_APPOINTMENT = "RIGHT_APPROVE_APPOINTMENT";
	static final String RIGHT_REJECT_APPOINTMENT = "RIGHT_REJECT_APPOINTMENT";
	static final String RIGHT_CANCEL_APPOINTMENT = "RIGHT_CANCEL_APPOINTMENT";
	static final String RIGHT_START_APPOINTMENT = "RIGHT_START_APPOINTMENT";
	static final String RIGHT_GET_MEDICAR_APPOINTMENT = "RIGHT_GET_MEDICAR_APPOINTMENT";
	static final String RIGHT_SUBMIT_PRESCRIPTION = "RIGHT_SUBMIT_PRESCRIPTION";
	static final String RIGHT_GET_PRESCRIPTION_HISTORY = "RIGHT_GET_PRESCRIPTION_HISTORY";
	static final String RIGHT_BLOCK_VACATION = "RIGHT_BLOCK_VACATION";
	static final String RIGHT_HAS_MEDICAR_PROFILE = "RIGHT_HAS_MEDICAR_PROFILE";
	static final String RIGHT_UPDATE_MEDICAR_PROFILE = "RIGHT_UPDATE_MEDICAR_PROFILE";

	static final String FIELD_VALUE = "value";
	static final String FIELD_PRICE = "price";
	static final String FIELD_TYPE = "type";
	static final String FIELD_CREATE_DATE_TIME = "createdDatetime";

	static final String CONFIG_COIN_COMMENT_AND_ADD_PICTURE_CHECK_IN = "COMMENT_AND_ADD_PICTURE_CHECK_IN";
	static final String COMMENT_AND_ADD_PICTURE_USER_COUPON = "COMMENT_AND_ADD_PICTURE_USER_COUPON";
	static final String COUPON_FIELD_ACTIVE_FLAG = "activeFlag";
	static final String CONFIG_COIN_LIKE_OR_UNLIKE = "UNLIKE";

	static final String VALUE_CODE_IMAGES_ACCOUNT = "ACCOUNT";
	static final String VALUE_CODE_IMAGES_PLACE = "PLACE";
	static final String VALUE_CODE_IMAGES_PROMTION = "PROMOTION";
	static final String VALUE_CODE_IMAGES_COMMENT_PROMTION = "COMMENT_PROMOTION";
	static final String VALUE_CODE_IMAGES_COMMENT_PLACE = "COMMENT_PLACE";
	static final String VALUE_CODE_IMAGES_MENU = "MENU";
	static final String VALUE_CODE_IMAGES_BANNER = "BANNER";
	static final Integer VALUE_MAX_SIZE_IMAGE_PLACE = 12;
	static final Integer VALUE_DEFAULT_MAX_RECORD = 30;

	static final String FIELD_ACC_ACCOUNT_LOGIN_NAME = "loginName";

	static final String FIELD_SESSION_LOGOUT_TIME = "logoutTime";
	static final String RIGHT_RATING = "RIGHT_RATING";
	
    static final String ACCOUNT_PATIENT_ROLE = "PATIENT_ROLE";
    static final String FIELD_ACC_ID_CARD = "idCard";
}
