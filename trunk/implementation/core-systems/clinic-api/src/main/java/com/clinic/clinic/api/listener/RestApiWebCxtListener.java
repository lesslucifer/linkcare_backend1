package com.clinic.clinic.api.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.clinic.clinic.api.conf.RestApiConf;
import com.clinic.clinic.api.util.MessageUtils;
import com.clinic.clinic.common.consts.IConstants;


/**
 * <p>
 * Injection class on web context initializing or destroying events.
 * </p>
 *
 * @author daiql<br>
 * @version 1.0<br>
 * @see {@link ContextLoaderListener}
 */
public class RestApiWebCxtListener extends ContextLoaderListener {
    /** Logging property. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiWebCxtListener.class);
   
    /*
     * (non-Javadoc)
     * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(final ServletContextEvent event) {
        LOGGER.info(IConstants.BEGIN_METHOD);
        try {
            super.contextInitialized(event);
            final ServletContext servletContext = event.getServletContext();
            final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);

            /**
             * Message resources loading.
             */
            final MessageSource ms = context.getBean("messageSource", MessageSource.class);
            MessageUtils.initialize(ms);
            
            /**
             * REST API settings initialization.
             */
            final RestApiConf systemConf = context.getBean(RestApiConf.class);
            LOGGER.info("REST API settings: {}", systemConf);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        } finally {
            LOGGER.info(IConstants.END_METHOD);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.web.context.ContextLoaderListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(final ServletContextEvent event) {
        LOGGER.info(IConstants.BEGIN_METHOD);
        try {
            super.contextDestroyed(event);
        } finally {
            LOGGER.info(IConstants.END_METHOD);
        }
    }
}
