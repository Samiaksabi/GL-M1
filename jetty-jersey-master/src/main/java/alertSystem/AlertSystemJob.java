package alertSystem;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import mailService.EmailSender;

public abstract class AlertSystemJob implements Job {
	
	protected static EmailSender emailSender=new EmailSender("gla2016.flightplanification@gmail.com", "alexgla2016");
	public abstract void execute(JobExecutionContext context) throws JobExecutionException;
}
