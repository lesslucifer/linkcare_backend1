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
    static byte FALSE_VALUE = 0;
    /**
     * The code indicates TRUE.
     */
    static byte TRUE_VALUE = 1;
    
    /**
     * The code indicates FALSE.
     */
    static boolean FALSE = false;
    /**
     * The code indicates TRUE.
     */
    static boolean TRUE = true;
    
    /**
     * Wild card for 1 character in Like expression
     */
    static String LIKE_WC_ONE_CHAR = "_";
    
    /**
     * Wild card for many characters in Like expression
     */
    static String LIKE_WC_MANY_CHAR = "%";
    
    /**
     * start order of the counter part of code. Use for zone, team, location.
     */
    static String CODE_COUNTER_START_ORDER = "1";
    
    
    /**
     * start character of code in temporary of pending place.
     */
    static String CODE_PLACE_TEMP_PREFIX = "T";
    
    static String CODE_PROMOTION_PREFIX = "KM";
    
    /**
     * default value of length of place code (including prefix).
     */
    static int CODE_DEFAULT_LENGTH = 15;
    
    /**
     * default value of length of coupon code.</p>
     */
    static int CODE_COUNPON_DEFAULT_LENGTH = 6;
    
    static String FILE_IMAGE_LOGO_NAME = "/logo/logo.png";
    
    static String FILE_IMAGE_AVATAR_NAME = "/avatar/avatar.png";
    /**
     * <p> API key for access googlemap service</p>
     */
    static String GOOGLE_MAP_API_KEY = "AIzaSyDrxKqAnQLndFcOdcW1eKsRPkfAnveLD6I";

    static String FOLDER_IMAGES_PLACE = "/places";
    
    static String FOLDER_IMAGES_MENU = "/menus";
    
    static String FOLDER_IMAGES_ACCOUNT = "/account";
    
    static String FOLDER_IMAGES_ACCOUNT_AVATAR = "/avatar";
    
    static String FOLDER_IMAGES_PROMOTION = "/promotions";
    
    static String FOLDER_IMAGES_COMMENTS = "/comments";
    
    static String LOGO_PLACE_IMAGES_NAME = "logo_";
    
    static String AVATAR_IMAGES_NAME = "avatar.png";
    
    static Long BEGIN_ACTIVE_TIME_BO_PACKAGE_PROMOTION = 1454284800000L;
    
    static Long END_ACTIVE_TIME_BO_PACKAGE_PROMOTION = 1461974400000L;
    
    /** 
     * common field names
     */
    static String FIELD_ID = "id";
    static String FIELD_CODE = "code";
    static String FIELD_NAME = "name";
    static String FIELD_DESC = "description";
    static String FIELD_IS_DELETED = "isDeleted";
    static String FIELD_LAST_UPDATED = "lastUpdated";
    static String FIELD_LAST_UPDATED_BY = "lastUpdatedBy";
    static String FIELD_CREATED_BY = "createdBy";
    static String FIELD_BEGIN_ACTIVE_TIME ="beginActiveTime";
    static String FIELD_END_ACTIVE_TIME ="endActiveTime";
    static String FIELD_REGISTER_TIME ="registerTime";
    static String FIELD_IMAGE_URL ="imageUrl";
    static String FIELD_ACTIVE_FLAG ="activeFlag";
    
    /**
     * account field names
     */
    static String FIELD_ACC_LOGIN_NAME ="loginName";
    static String FIELD_ACC_FACEBOOK_ID ="facebookId";
    static String FIELD_ACC_PHONE_NUMBER ="phoneNumber";
    static String FIELD_ACC_EMAIL ="email";
    static String FIELD_ACC_GOOGLE_PLUS_ID ="googlePlusId";
    static String FIELD_ACC_HASHED_PASSWORD ="hashedPassword";
    static String FIELD_ACC_ACTIVE_FLAG ="activeFlag";
    static String FIELD_ACC_BEGIN_ACTIVE_TIME ="beginActiveTime";
    static String FIELD_ACC_END_ACTIVE_TIME ="endActiveTime";
    static String FIELD_ACC_NEED_CHANGE_PWD ="needChangePwd";
    static Integer FIELD_ACC_ACCOUNT_ID = 2;

    /**
     * address field names
     */
    static String FIELD_ADDRESS_HOUSENUMBER = "houseNumber";
    static String FIELD_ADDRESS_STREET = "street";
    static String FIELD_ADDRESS_HAMLET = "hamlet";
    static String FIELD_ADDRESS_WARD = "ward";
    static String FIELD_ADDRESS_DISTRICT = "district";
    static String FIELD_ADDRESS_CITY = "city";
    
    /**
     * foreign key field names
     */
    static String FIELD_FK_ACCOUNT = "account";
    static String FIELD_FK_ACCOUNTS = "accounts";
    static String FIELD_FK_ACCOUNT_TEAM_LINK_TYPE = "accountTeamLinkType";
    static String FIELD_FK_ADDITIONAL_DESCRIPTION = "additionalDescription";
    static String FIELD_FK_ADDITIONAL_DESCRIPTION_TYPE = "additionalDescriptionType";
    static String FIELD_FK_CATEGORY = "category";
    static String FIELD_FK_SUBCATEGORY = "subcategory";
    static String FIELD_FK_MAJOR = "major";
/*    static String FIELD_FK_CONTACT = "contact";
    static String FIELD_FK_CONTACT_PRIORITY_TYPE = "contactPriorityType";
    static String FIELD_FK_CONTACT_TYPE = "contactType";*/
    
    static String FIELD_FK_CITY = "city";
    static String FIELD_FK_DATA_TYPE = "dataType";
    static String FIELD_FK_DATA_UNIT_FORMAT = "dataUnitFormat";
    static String FIELD_FK_FAMILY_STATUS = "familyStatus";
    static String FIELD_FK_FORMAT_TYPE = "formatType";
    static String FIELD_FK_PLACE_BACKUP = "placeBackup";
    static String FIELD_FK_PLACE = "place";
    static String FIELD_FK_PLACES = "places";
    static String FIELD_FK_PLACE_STATUS = "placeStatus";
    static String FIELD_FK_ACCOUNT_PLACE_ACTION_TYPE = "accountPlaceActionType";
    static String FIELD_FK_PLACE_SUB_CATEGORY_LINK_TYPE = "placeSubCategoryLinkType";
    static String FIELD_FK_MAPER_RIGHT = "maperRight";
    static String FIELD_FK_ROLE = "role";
    static String FIELD_FK_TEAM = "team";
    static String FIELD_FK_UNIT_TYPE = "unitType";
    static String FIELD_FK_UTILITY_TYPE = "utilityType";
    static String FIELD_FK_ZONE = "zone";
    static String FIELD_FK_FAVORITE = "favorite";
    static String FIELD_FK_PROMOTION = "promotion";
    static String FIELD_FK_PROMOTIONS = "promotions";
    static String FIELD_FK_PROMOTION_COMMENT = "promotionComment";
    static String FIELD_FK_PLACE_COMMENT = "placeComment";
    static String FIELD_FK_PLACE_COMMENTS = "placeComments";
    static String FIELD_FK_TRACE_COIN = "traceCoin";
    static String FIELD_FK_CONFIGURATION_COIN= "configurationCoin";
    static String FIELD_FK_FEED_CATEGORY = "feedCategory";
    static String FIELD_FK_ARTICLE_CATEGORY = "articleCategory";
    static String FIELD_FK_GIFT = "gift";
    static String FIELD_FK_COMMENT = "comment";
    static String FIELD_FK_UTILITY_TYPES = "utilityTypes";
    static String FIELD_FK_LIKE = "liked";
    static String FIELD_FK_BANNER_CATEGORY = "bannerCategory";
    static String FIELD_FK_FAVORITE_PLACES = "favoritePlaces";
    static String FIELD_FK_ADDRESS = "address";
    /**
     * SessionLog
     */
    static String FIELD_SESSION_ID = "sessionId";
    static String FIELD_LOGIN_TIME="loginTime";
    static String FIELD_LOGOUT_TIME="logoutTime";
    
    /**
     * Static code, type, status. Changing of these values requires rebuild of the system.
     */
    

    // DataType
    static String DATA_INTEGER = "DATA_INTEGER";
    static String DATA_DOUBLE = "DATA_DOUBLE";
    static String DATA_STRING = "DATA_STRING";
    static String DATA_DATE = "DATA_DATE";
    static String DATA_DATETIME = "DATA_DATETIME";
    static String DATA_TIMESTAMP = "DATA_TIMESTAMP";
    static String DATA_TIME = "DATA_TIME";

    // UnitType
    static String UNIT_DISTANCE_M = "UNIT_DISTANCE_M";
    static String UNIT_DISTANCE_KM = "UNIT_DISTANCE_KM";
    static String UNIT_TIME_S = "UNIT_TIME_S";
    static String UNIT_TIME_MI = "UNIT_TIME_MI";
    static String UNIT_TIME_H = "UNIT_TIME_H";
    static String UNIT_TIME_D = "UNIT_TIME_D";
    static String UNIT_TIME_MO = "UNIT_TIME_MO";

    // FormatType
    static String FORMAT_DOUBLE_DOT = "FORMAT_DOUBLE_DOT";
    static String FORMAT_DATE_yyyyMMdd = "FORMAT_DATE_yyyyMMdd";
    static String FORMAT_DATETIME_yyyyMMddHHmmss = "FORMAT_DATETIME_yyyyMMddHHmmss";

    // DataUnitFormat
    static String DATA_INFO_METRE = "DATA_INFO_METRE";

    // Configuration
    static String CONFIG_ENTER_LOC_RADIUS = "CONFIG_ENTER_LOC_RADIUS";
    static String CONFIG_MAIL_SERVER_HOST = "CONFIG_MAIL_SERVER_HOST";
    static String CONFIG_MAIL_SENDER = "CONFIG_MAIL_SENDER";
    static String CONFIG_MAIL_SENDER_PWD = "CONFIG_MAIL_SENDER_PWD";

    // ContactType
    static String CONTACT_NORMAL = "CONTACT_NORMAL";
    static String CONTACT_LEAD = "CONTACT_LEAD";
    static String CONTACT_MONITOR = "CONTACT_MONITOR";
    static String CONTACT_CUSTOMER = "CONTACT_CUSTOMER";

    // ContactPriorityType
    static String CONTACT_PRIORITY_NONE = "CONTACT_PRIORITY_NONE";
    static String CONTACT_PRIORITY_LOW = "CONTACT_PRIORITY_LOW";
    static String CONTACT_PRIORITY_MEDIUM = "CONTACT_PRIORITY_MEDIUM";
    static String CONTACT_PRIORITY_HIGH = "CONTACT_PRIORITY_HIGH";

    // AccountTeamLinkType
    static String ACC_TEAM_LINK_COLLECTER = "ACC_TEAM_LINK_COLLECTER";
    static String ACC_TEAM_LINK_ADMIN = "ACC_TEAM_LINK_ADMIN";
    static String ACC_TEAM_LINK_SUPER_ADMIN = "ACC_TEAM_LINK_SUPER_ADMIN";

    // AdditionalDescriptionType
    static String ADD_DESC_LOC_IMG_URL = "ADD_DESC_LOC_IMG_URL";
    static String ADD_DESC_LOC_MENU_IMG_URL = "ADD_DESC_LOC_MENU_IMG_URL";
    //AccountRole
    static String ROLE_NEWCOMER = "ROLE_NEWCOMER";  
    static String ROLE_COLLABORATOR = "ROLE_COLLABORATOR";
    static String ROLE_ADMIN = "ROLE_ADMIN";
    static String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
    static String ROLE_SYSTEM_ADMIN = "ROLE_SYSTEM_ADMIN";
    /**
     * logic error code
     */
    static String ERR_CODE_DUPLCATED = "err_code_duplicated";
    
    
    /**
     * Right code: the right code
     */
    static String RIGHT_LOG_IN = "RIGHT_LOG_IN";
    static String RIGHT_LOG_OUT = "RIGHT_LOG_OUT";
    static String RIGHT_CHANGE_OWN_PWD = "RIGHT_CHANGE_OWN_PWD";
    static String RIGHT_FORGOT_PWD = "RIGHT_FORGOT_PWD";
    static String RIGHT_RESET_PWD = "RIGHT_RESET_PWD";
    static String RIGHT_ACCOUNT_CREATE = "RIGHT_ACCOUNT_CREATE";
    static String RIGHT_ACCOUNT_DELETE = "RIGHT_ACCOUNT_DELETE";
    static String RIGHT_ACCOUNT_READ = "RIGHT_ACCOUNT_READ";
    static String RIGHT_ACCOUNT_UPDATE = "RIGHT_ACCOUNT_UPDATE";
    static String RIGHT_ACCOUNT_ROLE_CREATE = "RIGHT_ACCOUNT_ROLE_CREATE";
    static String RIGHT_ACCOUNT_ROLE_DELETE = "RIGHT_ACCOUNT_ROLE_DELETE";
    static String RIGHT_ACCOUNT_SETTING_CREATE = "RIGHT_ACCOUNT_SETTING_CREATE";
    static String RIGHT_ACCOUNT_SETTING_DELETE = "RIGHT_ACCOUNT_SETTING_DELETE";
    static String RIGHT_ACCOUNT_SETTING_READ = "RIGHT_ACCOUNT_SETTING_READ";
    static String RIGHT_ACCOUNT_SETTING_UPDATE = "RIGHT_ACCOUNT_SETTING_UPDATE";
    static String RIGHT_ACCOUNT_TEAM_ADMIN_CREATE = "RIGHT_ACCOUNT_TEAM_ADMIN_CREATE";
    static String RIGHT_ACCOUNT_TEAM_ADMIN_DELETE = "RIGHT_ACCOUNT_TEAM_ADMIN_DELETE";
    static String RIGHT_ACCOUNT_TEAM_COLLAB_CREATE = "RIGHT_ACCOUNT_TEAM_COLLAB_CREATE";
    static String RIGHT_ACCOUNT_TEAM_COLLAB_DELETE = "RIGHT_ACCOUNT_TEAM_COLLAB_DELETE";
    static String RIGHT_CONTACT_CREATE = "RIGHT_CONTACT_CREATE";
    static String RIGHT_CONTACT_DELETE = "RIGHT_CONTACT_DELETE";
    static String RIGHT_CONTACT_READ = "RIGHT_CONTACT_READ";
    static String RIGHT_CONTACT_UPDATE = "RIGHT_CONTACT_UPDATE";
    static String RIGHT_LOCATION_CREATE = "RIGHT_LOCATION_CREATE";
    static String RIGHT_LOCATION_DELETE = "RIGHT_LOCATION_DELETE";
    static String RIGHT_LOCATION_READ = "RIGHT_LOCATION_READ";
    static String RIGHT_LOCATION_UPDATE = "RIGHT_LOCATION_UPDATE";
    static String RIGHT_LOCATION_APPROVE_PENDING = "RIGHT_LOCATION_APPROVE_PENDING";
    static String RIGHT_LOCATION_REJECT_PENDING = "RIGHT_LOCATION_REJECT_PENDING";
    static String RIGHT_LOCATION_APPROVE_REVIEWED = "RIGHT_LOCATION_APPROVE_REVIEWED";
    static String RIGHT_LOCATION_REJECT_REVIEWED = "RIGHT_LOCATION_REJECT_REVIEWED";
    static String RIGHT_LOCATION_IMPORT = "RIGHT_LOCATION_IMPORT";
    static String RIGHT_LOCATION_IMPORTED_POS_INIT = "RIGHT_LOCATION_IMPORTED_POS_INIT";
    static String RIGHT_LOCATION_IMPORTED_POS_VALID = "RIGHT_LOCATION_IMPORTED_POS_VALID";
    static String RIGHT_TEAM_CREATE = "RIGHT_TEAM_CREATE";
    static String RIGHT_TEAM_DELETE = "RIGHT_TEAM_DELETE";
    static String RIGHT_TEAM_READ = "RIGHT_TEAM_READ";
    static String RIGHT_TEAM_UPDATE = "RIGHT_TEAM_UPDATE";
    static String RIGHT_ZONE_CREATE = "RIGHT_ZONE_CREATE";
    static String RIGHT_ZONE_DELETE = "RIGHT_ZONE_DELETE";
    static String RIGHT_ZONE_READ = "RIGHT_ZONE_READ";
    static String RIGHT_ZONE_UPDATE = "RIGHT_ZONE_UPDATE";
    static String RIGHT_REPORT_READ = "RIGHT_REPORT_READ";
    static String RIGHT_REPORT_CREATE = "RIGHT_REPORT_CREATE";

    static String FIELD_VALUE = "value";
    static String FIELD_PRICE = "price";
    static String FIELD_TYPE = "type";
    static String FIELD_CREATE_DATE_TIME = "createdDatetime";

    static String CONFIG_COIN_COMMENT_AND_ADD_PICTURE_CHECK_IN = "COMMENT_AND_ADD_PICTURE_CHECK_IN";
    static String COMMENT_AND_ADD_PICTURE_USER_COUPON = "COMMENT_AND_ADD_PICTURE_USER_COUPON";
    static String COUPON_FIELD_ACTIVE_FLAG = "activeFlag";
    static String CONFIG_COIN_LIKE_OR_UNLIKE = "UNLIKE";
    
    
    static String VALUE_CODE_IMAGES_ACCOUNT = "ACCOUNT";
    static String VALUE_CODE_IMAGES_PLACE = "PLACE";
    static String VALUE_CODE_IMAGES_PROMTION = "PROMOTION";
    static String VALUE_CODE_IMAGES_COMMENT_PROMTION = "COMMENT_PROMOTION";
    static String VALUE_CODE_IMAGES_COMMENT_PLACE = "COMMENT_PLACE";
    static String VALUE_CODE_IMAGES_MENU = "MENU";
    static String VALUE_CODE_IMAGES_BANNER = "BANNER";
    static Integer VALUE_MAX_SIZE_IMAGE_PLACE = 12;
    static Integer VALUE_DEFAULT_MAX_RECORD = 30;
    
    static String FIELD_ACC_ACCOUNT_LOGIN_NAME = "loginName";
    
    static String FIELD_SESSION_LOGOUT_TIME = "logoutTime";

}
