package alertSystem;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import datanucleus.dao.DAOAccessor;
import datanucleus.dao.ress.Flight;

public class AlertSystem {
	
	private static Logger logger = LogManager.getLogger(AlertSystem.class);
	private Scheduler scheduler;
	
	public AlertSystem(){
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
		} catch (SchedulerException e) {
			logger.fatal("Can't lauch the Quartz Scheduler");
			System.exit(1);
		}
	}
	
	private void scheduleJob(Class <? extends AlertSystemJob> jobClass, Date startDate, String name, String group) {
		if (startDate.before(new Date())){
			startDate = new Date();
		}
		
		Trigger trigger = newTrigger()
			      .withIdentity(name,group)
			      .startNow()//.startAt(startDate)
			      .build();
		
		try {
			if (scheduler.checkExists(new JobKey(name, group))) {
				scheduler.rescheduleJob(new TriggerKey(name, group), trigger);
				return;
			}
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
		}
		
		JobDetail job = newJob(jobClass)
			      .withIdentity(name,group)
			      .build();
		
		try {
			scheduler.scheduleJob(job, trigger);
			System.out.println("AlertSystem - OK");
		} catch (SchedulerException e) {
			logger.error("Can't schedule the job "+name);
		}
		
	}
	public void scheduleFlight(String flightIdentifier){
		Flight flight=DAOAccessor.getFlightDAO().getElement(flightIdentifier);
		if(flight==null){
			logger.warn("Can't execute Job because the Flight doesn't exist in the database.");
			return;
		}
		
		long time=flight.departure_time.getTime();
		
		Date checkCrewDate = new Date(time - 7*24*3600*1000);
		Date ckeckOFPDate = new Date(time - 12*3600*1000);
		
		scheduleJob(CrewCheckJob.class, checkCrewDate, flightIdentifier, "checkCrew");
		scheduleJob(OFPCheckJob.class, ckeckOFPDate, flightIdentifier, "checkOFP");
	}
	
	public void unscheduleFlight(String flightId) {
		try {
			scheduler.unscheduleJob(new TriggerKey(flightId, "checkCrew"));
			scheduler.deleteJob(new JobKey(flightId, "checkCrew"));
			scheduler.unscheduleJob(new TriggerKey(flightId, "checkOFP"));
			scheduler.deleteJob(new JobKey(flightId, "checkOFP"));
		} catch (SchedulerException e1) {
			logger.error("Can't unschedule Flight");
		}
	}
	
}
