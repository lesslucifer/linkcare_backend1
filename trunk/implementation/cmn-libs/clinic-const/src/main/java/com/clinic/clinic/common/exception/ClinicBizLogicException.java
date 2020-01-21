package com.clinic.clinic.common.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class ClinicBizLogicException extends RuntimeException {
    private static final long serialVersionUID = -8995305131923457267L;
    private List<String> paramValues;

    /**
     * <p>Constructor.</p>
     *
     * @param msgKey String
     * @param cause {@link Throwable}
     */
    public ClinicBizLogicException(final String msgKey, final Throwable cause) {
        super(msgKey, cause);
    }

    /**
     * <p>Constructor.</p>
     *
     * @param msgKey String
     */
    public ClinicBizLogicException(final String msgKey) {
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
}
