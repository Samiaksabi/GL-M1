package datanucleus.dao.dn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import datanucleus.dao.DAOAccessor;
import datanucleus.dao.ress.Crew;

@Path("")
public class CrewWS {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/crew/{userName}")
	public Crew getElement(@PathParam("userName") String userName){
		//return new ArrayList<Flight>();
		return DAOAccessor.getCrewDAO().getElement(userName);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/crew/{firstName}/{lastName}")
	public Crew getElement(@PathParam("firstName") String firstName,@PathParam("lastName") String lastName){
		//return new ArrayList<Flight>();
		return DAOAccessor.getCrewDAO().getElement(firstName, lastName);
	}
	
}
