package br.com.jlex.bot.test.quartz;

import org.junit.Test;

public class QuartsTest {
	
	@Test
	public void initScheduler() {/*
		
		SchedulerFactory scFactory = new StdSchedulerFactory();
		Scheduler s;
		
		JobDetail job = JobBuilder
				.newJob(SchedulerTest.class)
				.withIdentity("Job1", "JLex")
				.build();
		
		Trigger trigger = (Trigger) TriggerBuilder
				.newTrigger()
				.withIdentity("onUpdateReceived", "JLex")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")) // executa a cada 2s
				.build();
		
		try {
			
			s = scFactory.getScheduler();
			s.start();
			s.scheduleJob(job, trigger);
			Thread.sleep(900000);
			
		} catch (Exception e) {
			e.printStackTrace();
		} */
	}
}
