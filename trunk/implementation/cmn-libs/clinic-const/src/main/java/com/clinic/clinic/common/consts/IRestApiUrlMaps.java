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
    
    // ### Account
    static final String REST_API_BIZ_ACCOUNT_SUBCATEGORY_SUBCATEGORYID = "/account/subcategory/{subcategoryId}";
    static final String REST_API_BIZ_ACCOUNT_FILTER = "/account/filter";

	static final String REST_API_BIZ_ACCOUNT_LOGIN = "/account/login";
}
