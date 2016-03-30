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
public interface IConstants {

    /**
     * The string presents beginning of a method in logging.
     */
    static final String BEGIN_METHOD = "---begin---";
    /**
     * The string presents ending of a method in logging.
     */
    static final String END_METHOD = "----end----";

    /**
     * The string presents ending of a method in logging.
     */
    static final String END_METHOD_NORMAL = "----end normal----";
    /**
     * The string presents ending of a method in logging.
     */
    static final String END_METHOD_ERROR = "----end ERROR----";
    /**
     * Error code.
     */
    static final int INTERNAL_SYSTEM_ERROR_CODE = 1000;
    static final int BIZLOGIC_ERROR_CODE = 2000;
    
    static final long DURABLE_SESSION = 1200000;

    /**
     * default value of page size (number of row in a page).
     */
    static int PAGE_SIZE_DEFAULT = 20;

    static final String SLASH = "/";

    static final String DateForMat_DDMMYYYY = "dd-MM-yyyy";
    static final String DateForMat_DDMMYYYY_HHMM = "dd-MM-yyyy HH:mm aa";

    static final String ROOT_IMAGE_URL = "E:\\\\";
    static final String PUB_RES = ROOT_IMAGE_URL + "var\\www\\html\\pub-res\\images";
}
