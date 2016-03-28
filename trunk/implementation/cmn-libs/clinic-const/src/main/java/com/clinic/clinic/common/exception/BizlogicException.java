package com.clinic.clinic.common.exception;

import java.util.ArrayList;
import java.util.List;

import com.clinic.clinic.common.consts.IBizErrorCode;

/**
 * <p>
 * The data structure presents an error at biz-logic processing.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class BizlogicException extends RuntimeException {
    /**
     * <p>
     * Description of this field.
     * </p>
     */
    private static final long serialVersionUID = 1701713174897230481L;
    
    private int httpCode = 0;
    private int bizCode = IBizErrorCode.UNKNOWN_ERROR;
    private List<String> paramValues;

    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param msgKey
     *            String
     * @param cause
     *            {@link Throwable}
     */
    public BizlogicException(final String msgKey, final Throwable cause) {
        super(msgKey, cause);
    }

    /**
     * <p>
     * Constructor.
     * </p>
     *
     * @param msgKey
     *            String
     */
    public BizlogicException(final String msgKey) {
        this(msgKey, null);
    }

    /**
     * <p>
     * Add actual value of message's parameter.
     * </p>
     *
     * @param value
     *            actual value of message's parameter which will be displayed in
     *            presentation layer (localized)
     */
    public void addParamValue(final String value) {
        if (null == paramValues) {
            paramValues = new ArrayList<String>();
        }
        paramValues.add(value);
    }

    /**
     * <p>
     * Returns current list of message's parameter value.
     * </p>
     *
     * @return list of {@link String} objects
     */
    public List<String> getParamValues() {
        return paramValues;
    }

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public int getBizCode() {
		return bizCode;
	}

	public void setBizCode(int bizCode) {
		this.bizCode = bizCode;
	}
}
