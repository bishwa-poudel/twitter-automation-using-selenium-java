package com.bishwa.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.TimeZone;

/**
 * Author: Bishwa
 * Date: 28/02/2021
 * Time: 20:00
 */
@WebListener
public class RequestListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(RequestListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("SERVER STARTED SUCCESSFULLY");
        logger.info("RUNNING POST DEPLOYMENT HOOKS");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("[CONTEXT-DESTROYED] SERVER STOP SIGNAL RECEIVED");
    }
}
