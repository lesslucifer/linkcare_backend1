package com.clinic.clinic.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * <p>
 * International i18n message resource utilities.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 */
public class MessageUtils {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtils.class);
    private static MessageSource messageSource;
    
    /**
     * <p>Initializes message resource.</p>
     *
     * @param messageSource
     */
    public static void initialize(final MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }
    
    /**
     * <p>Get a message.</p>
     *
     * @param key {@link String}
     * @param parameters - Array of objects
     * @return {@link String}
     */
    public static String getMessage(final String key, final Object... parameters) {
        try {
            return messageSource.getMessage(key, parameters, LocaleContextHolder.getLocale());
        } catch (Exception ex) {
            LOGGER.warn("Could not get key[" + key + "]: " + ex.getMessage());
            return key;
        }
    }
}
