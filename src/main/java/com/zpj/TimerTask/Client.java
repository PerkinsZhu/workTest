package com.zpj.TimerTask;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.Timer;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;


public class Client {

    public static void main(String[] args) {
    /*	 Timer timer = new Timer("------------------");
	        timer.scheduleAtFixedRate(new TimerTaskTest(), 0, 3 * 1000L);*/
        testTrigger();
    }

    private static void testTrigger() {
        JobDetail cleanTuWenAnswerMap = JobBuilder.newJob(HelloQuartz.class).withIdentity("cleanTuWenAnswerMapJob",
                                                                                          "group_1").build();
        Trigger triggerForCleanTuWenAnswer = TriggerBuilder.newTrigger().withIdentity("triggerForCleanTuWenAnswer",
                                                                                      "group1").startNow().withSchedule(
                simpleSchedule().withIntervalInSeconds(1) .repeatForever()).build();
        //                .withSchedule(cronSchedule("10 * * * * ?")).build();
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = null;
        try {
            sched = sf.getScheduler();
            sched.scheduleJob(cleanTuWenAnswerMap, triggerForCleanTuWenAnswer);
            sched.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}



