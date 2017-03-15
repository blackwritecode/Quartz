package com.panku.quartz;

import java.util.concurrent.TimeUnit;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bootstrap {

	private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);
	
	public static void main(String[] args) {
		try{
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			
			JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1","group1").build();
			
			SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1").startNow().withSchedule(simpleScheduleBuilder).build();
			scheduler.scheduleJob(job,trigger);
			try{
				TimeUnit.MINUTES.sleep(3);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			
			// 关闭Scheduler
			scheduler.shutdown();
		}catch (SchedulerException e){
			logger.error(e.getMessage(),e);
		}
	}
}
