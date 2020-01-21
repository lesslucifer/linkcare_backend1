package com.clinic.clinic.common.consts;

/**
 * <p>
 * Define public constants of API RESTful web-services.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 */
public interface IRestApiConstants {
    
    /**
     * Paging navigation constants.
     */
    static final String PAGING_PARAM_NAME_PAGE = "page";
    static final String PAGING_PARAM_NAME_LIMIT = "limit";
    static final String PAGING_RESPONSE_HEADER_CONTENT_RANGE_FORMAT = "%d-%d/%d";
    static final String PAGING_RESPONSE_HEADER_ACCEPT_RANGE_FORMAT = "%s %d";
    static final String PAGING_RESPONSE_HEADER_CONTENT_RANGE_KEY = "Content-Range";
    static final String PAGING_RESPONSE_HEADER_ACCEPT_RANGE_KEY = "Accept-Range";
    static final String PAGING_RESPONSE_HEADER_NAVIGATION_LINK_KEY = "Navigation-Link";
    
    /**
     * Sorting constants.
     */
    static final String SORTING_PARAM_NAME_SORT = "sort";
    static final String SORTING_SEPARATOR = "\\|";
    static final String SORTING_DESC_MARK = "-";
    
    static final String AA_LOGIN_NAME = "login_name";
    static final String AA_SESSION_ID = "session_id";
}
