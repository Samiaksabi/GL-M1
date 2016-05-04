package alertSystem;

import java.util.Collection;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import datanucleus.dao.DAOAccessor;
import datanucleus.dao.FlightDAOImpl;
import datanucleus.dao.ress.Crew;
import datanucleus.dao.ress.CrewStatus;
import datanucleus.dao.ress.Flight;
import mailService.EmailSender;

public class CrewCheckJob extends AlertSystemJob{

	private static Logger logger = LogManager.getLogger(CrewCheckJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String flightIdentifier=context.getJobDetail().getKey().getName();
		Flight flight=DAOAccessor.getFlightDAO().getElement(flightIdentifier);
		if(flight==null){
			logger.info("Can't execute Job because the Flight doesn't exist in the database.");
			return;
		}
		System.out.println("CrewCheckJob - OK");
		Collection<String> collection=flight.getCrew_members();
		if(collection.size()>3){
			for(String s:collection){
				Crew crew=DAOAccessor.getCrewDAO().getElement(s);
				if(crew.getCrewStatus()==CrewStatus.PILOT){
					return;
				}
			}
		}
		
		String subject ="Missing Crew Alert - Flight "+flightIdentifier;
		String message="The Crew of the flight "+flightIdentifier+" is missing.\nPlease, edit this flight as soon as possible.";
		try {
			emailSender.sendMessage(subject, message,"gla2016.flightplanification@gmail.com");
		} catch (AddressException e) {
			logger.error(e.getMessage());
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
	}

}
