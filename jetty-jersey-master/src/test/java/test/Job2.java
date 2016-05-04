package test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job2 implements Job{

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Job2");
	}

}
