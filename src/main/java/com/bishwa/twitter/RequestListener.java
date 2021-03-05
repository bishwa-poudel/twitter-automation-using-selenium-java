package com.bishwa.twitter;

import com.bishwa.twitter.scheduler.TaskScheduler;
import com.bishwa.twitter.webdriver.ChromeDriverManager;
import com.bishwa.twitter.webdriver.IDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
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
    @Inject
    private TaskScheduler taskScheduler;

    private static final Logger logger = LoggerFactory.getLogger(RequestListener.class);

    // initializing class will initialize static values once, thereby initializes chrome driver.
    private final IDriverManager driverManager = new ChromeDriverManager();

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kathmandu"));
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("SERVER STARTED SUCCESSFULLY");
        logger.info("RUNNING POST DEPLOYMENT HOOKS");

        taskScheduler.initScheduler();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("[CONTEXT-DESTROYED] SERVER STOP SIGNAL RECEIVED");

        taskScheduler.stopScheduler();
        driverManager.tearDown();
    }
}
