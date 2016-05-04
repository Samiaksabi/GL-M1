package alertSystem;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import datanucleus.dao.DAOAccessor;
import datanucleus.dao.ress.Flight;
import mailService.EmailSender;

public class OFPCheckJob extends AlertSystemJob {
	
	private static Logger logger = LogManager.getLogger(OFPCheckJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String flightIdentifier=context.getJobDetail().getKey().getName();
		Flight flight=DAOAccessor.getFlightDAO().getElement(flightIdentifier);
		if(flight==null){
			logger.info("Can't execute Job because the Flight doesn't exist in the database.");
			return;
		}
		
		if(flight.getOfp_url()!=null){
			return;
		}
		
		String subject ="Missing OFP Alert - Flight "+flightIdentifier;
		String message="The OFP of the flight "+flightIdentifier+" is missing.\nPlease, edit this flight as soon as possible.";
		try {
			emailSender.sendMessage(subject, message,"gla2016.flightplanification@gmail.com");
		} catch (AddressException e) {
			logger.error(e.getMessage());
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
	}

}
