package com.bishwa.twitter.automate.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Author: Bishwa
 * Date: 02/03/2021
 * Time: 18:59
 */
public enum TwitterProperties {
    USERNAME("USERNAME"),
    PASSWORD("PASSWORD");

    public static final Logger logger = LoggerFactory.getLogger(TwitterProperties.class);
    public static final Properties twitterProperties = new Properties();
    public static final String PROPERTIES_FILE = "twitter.properties";

    static {
        try {
            twitterProperties.load(TwitterProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
        } catch (Exception e) {
            logger.error("Failed to load Twitter Properties file : " + e.getMessage());
        }
    }

    private final String key;

    TwitterProperties(String key) {
        this.key = key;
    }

    public String val() {
        return twitterProperties.getProperty(key, "");
    }
}
