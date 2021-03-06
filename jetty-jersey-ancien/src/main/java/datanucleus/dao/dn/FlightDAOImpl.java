package datanucleus.dao.dn;

import java.util.*;

import datanucleus.dao.DAOFactory;
import datanucleus.dao.FlightDAO;
import datanucleus.dao.ress.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("")
public class FlightDAOImpl implements FlightDAO{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public Collection<Flight> getAll(){
		return DAOFactory.getFlightDAO().getAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight/{id}")
	public Flight getElement(@PathParam("id") int id){
		return DAOFactory.getFlightDAO().getElement(id);
	}
	
	@DELETE
	@Path("/flight/{id}")
	public void deleteElement(@PathParam("id") int id){
		DAOFactory.getFlightDAO().deleteElement(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{crew_id}/flight")
    public Collection<Flight> getAll(@PathParam("crew_id") int crew_id){
		return DAOFactory.getFlightDAO().getAll(crew_id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{crew_id}/flight/{id}")
    public Flight getFlight(@PathParam("crew_id") int crew_id,@PathParam("id") int id){
		return DAOFactory.getFlightDAO().getFlight(crew_id, id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public void addElement(Flight f){
		DAOFactory.getFlightDAO().addElement(f);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/flight/{id}/edit")
	public void editElement(@PathParam("id") int id, Flight f){
		DAOFactory.getFlightDAO().editElement(id, f);
	}

}