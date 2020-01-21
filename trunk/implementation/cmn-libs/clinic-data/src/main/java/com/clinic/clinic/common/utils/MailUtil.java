package com.clinic.clinic.common.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clinic.clinic.common.consts.IConstants;



/**
 * <p>
 * Describe functionality of this class here.
 * </p>
 *
 * @author Vuong Do<br>
 * @version 1.0<br>
 * @see TODO
 */
public class MailUtil {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MailUtil.class);
    
    public static void sendMail(final String mailServer, final String senderMail, final String senderPassword,
            final String toMail, final String mailSubject, final String mailContent) {
        boolean endNormalFlag = false;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(IConstants.BEGIN_METHOD);
        }
        try {

            // Get the session object
            Properties props = new Properties();
            props.put("mail.smtp.host", mailServer);
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                    return new javax.mail.PasswordAuthentication(senderMail, senderPassword);
                }
            });
            
            // Compose the message

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderMail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

            message.setSubject(mailSubject);
            message.setText(mailContent);

            // send the message
            Transport.send(message);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("a mail has sent from [{}] to [{}] with subject [{}] ", senderMail, toMail, mailSubject);
            }

            endNormalFlag = true;

        } catch (MessagingException e) {
                LOGGER.error("error sending mail [{}] ", e.getMessage());
        } finally {
            if (LOGGER.isDebugEnabled()) {
                if (endNormalFlag) {
                    LOGGER.debug(IConstants.END_METHOD_NORMAL);
                } else {
                    LOGGER.debug(IConstants.END_METHOD_ERROR);
                }
            }
        }

    }
}
