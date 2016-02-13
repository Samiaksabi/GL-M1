package ws.bouchon;

import ress.*;
import java.util.*;
import dao.FlightDAO;
import server.FpsServer;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/fps")
public class FlightRessource implements FlightDAO{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight_list")
	public Collection<Flight> getAll(){
		return FpsServer.db.flights.getDb();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight_list/{id}")
	public Flight getElement(@PathParam("id") int id){
		return FpsServer.db.flights.get(id);
	}
	
	@DELETE
	@Path("/flight_list/{id}")
	public void deleteElement(@PathParam("id") int id){
		FpsServer.db.flights.delete(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{crew_id}/flight_list")
    public Collection<Flight> getAll(@PathParam("crew_id") int crew_id){
		return FpsServer.db.getFlights(crew_id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{crew_id}/flight_list/{id}")
    public Flight getFlight(@PathParam("crew_id") int crew_id,@PathParam("id") int id){
		return FpsServer.db.getFlight(crew_id,id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/flight_list")
	public void addElement(Flight f){
		FpsServer.db.flights.add(f);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/flight_list/{id}/edit")
	public void editElement(@PathParam("id") int id, Flight f){
		FpsServer.db.flights.edit(id, f);
	}

}

