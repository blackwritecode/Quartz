package com.panku.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.logger.info("定时任务开始：");
		
		
		this.logger.debug(this.getClass().getName() + "trigger...");
	}
}
