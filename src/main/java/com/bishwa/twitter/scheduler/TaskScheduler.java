package com.bishwa.twitter.scheduler;

import com.bishwa.twitter.automate.properties.TwitterProperties;
import com.bishwa.twitter.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;

/**
 * Author: Bishwa
 * Date: 05/03/2021
 * Time: 14:48
 */
public class TaskScheduler {
    @Inject
    private LikeTimer likeTimer;

    public static final Logger logger = LoggerFactory.getLogger(TaskScheduler.class);

    private final Timer timer = new Timer();
    private final Long repeatIntervalMillis = (Objects.equals(TwitterProperties.REPEAT_AFTER_IN_MINUTES.val(), "") ? 1L : Long.parseLong(TwitterProperties.REPEAT_AFTER_IN_MINUTES.val())) * 60 * 1000;
    private final Date scheduledDate = DateUtils.getNextSchedulingTime();


    public void initScheduler() {

        timer.scheduleAtFixedRate(likeTimer, scheduledDate, repeatIntervalMillis);

        logger.info("[TASK-SCHEDULER] CHECK IN TASK SCHEDULED FOR " + scheduledDate);
    }

    public void stopScheduler() {
        likeTimer.cancel();

        timer.cancel(); // Terminates this timer, discarding any currently scheduled tasks.
        timer.purge();  // Removes all cancelled tasks from this timer's task queue.

        logger.info("[TASK-SCHEDULER] TASK SCHEDULER STOPPED SUCCESSFULLY");
    }
}
