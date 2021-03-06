package com.bishwa.twitter.scheduler;

import com.bishwa.twitter.automate.core.service.AutomateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.TimerTask;

/**
 * Author: Bishwa
 * Date: 05/03/2021
 * Time: 14:46
 */
public class LikeTimer extends TimerTask {
    private static final Logger logger = LoggerFactory.getLogger(LikeTimer.class);

    @Inject
    private AutomateService automateService;

    @Override
    public void run() {
        try {
            logger.info("[TASK-SCHEDULER] Like action triggered");
            automateService.like();
        } catch (Exception e) {
            logger.error("[TASK-SCHEDULER] Error in Like action : " + e.getMessage());
//            e.printStackTrace();
        }
    }
}
