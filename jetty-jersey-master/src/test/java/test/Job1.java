package test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class Job1 implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("OK");
		//JobKey jk=context.getJobDetail().getKey();
		//System.out.println(context.getJobDetail().getKey());
	}

}
