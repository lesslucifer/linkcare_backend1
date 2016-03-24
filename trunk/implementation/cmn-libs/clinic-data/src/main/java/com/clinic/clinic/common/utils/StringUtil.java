/**==============================================================================
 * MAPER JSC (www.maper.vn) Proprietary.
 * Copyright 2015 MAPER JSC.
 * UNPUBLISHED WORK
 * ALL RIGHTS RESERVED
 *
 * This software is the confidential and proprietary information of 
 * MAPER J.S.C ("Proprietary Information").  Any use, reproduction,
 * distribution or disclosure of the software or Proprietary Information,
 * in whole or in part, must comply with the terms of the license  
 * agreement, nondisclosure agreement or contract entered into with 
 * MAPER providing access to this software.
 *
 * Project name  : maper-data<br>
 * File name     : StringUtil.java<br>
 * <p>
 * Changes History <br>
 *		Date				Person				Reason<br>
 *		Nov 26, 2015				Vuong Do				Initial<br>
 * </p>
 *
 * @author Vuong Do
 *=============================================================================*/
package com.clinic.clinic.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Utility for string.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class StringUtil {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);
    /**
     * <p>
     * Return String with the first character upper case.
     * </p>
     *
     * @param string
     * @return
     * @author Vuong Do
     */
    public static String toUpercaseFirstChar(String string) {
        if (string != null && string.length() > 1) {
            char c[] = string.toCharArray();
            c[0] = Character.toUpperCase(c[0]);
            return new String(c);
        }
        return string;
    }

    /**
     * <p>
     * Returns trimmed of given string when it is not empty or null other case.
     * </p>
     *
     * @param string
     * @return null or not empty string
     * @author Vuong Do
     */
    public static String getNormalizedString(String string) {
        if (string == null) {
            return null;
        }
        return string.trim();
    }
    
    /**
     * <p>
     * Returns true if given string null or empty (blank).
     * </p>
     *
     * @param string
     * @return
     * @author Vuong Do
     */
    public static boolean isEmpty(String string) {
        if (string == null || string.trim().length() == 0) {
            return true;
        }
        return false;
    }
    
    public static String getHashedText(String plainText) {
        String retValue = null;
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plainText.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashText = bigInt.toString(16);

            // zero pad to have the full 32 chars.
            StringBuilder sb = new StringBuilder();
            sb.append(hashText);
            while (sb.length() < 32) {
                sb.insert(0, "0");
            }
            retValue = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Exception", e);
        }
        return retValue;
    }
    
    public static String getSession() {
        String retValue = null;
        UUID uuid = UUID.randomUUID();
        retValue = uuid.toString();
        return retValue;
    }

}
