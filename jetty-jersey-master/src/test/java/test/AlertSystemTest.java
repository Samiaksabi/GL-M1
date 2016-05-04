package test;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertSystemTest {
	
	public static void main(String[]args) throws SchedulerException{
		Scheduler scheduler=null;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JobDetail job = newJob(Job1.class)
			      .withIdentity("myJob", "group1") // name "myJob", group "group1"
			      .build();
		Trigger trigger = newTrigger()
			      .withIdentity("myTrigger", "group1")
			      .startNow()           
			      .build();
		
		scheduler.scheduleJob(job, trigger);
		
		
		scheduler.shutdown();
		
	}
}
