package datanucleus.dao.dn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import datanucleus.dao.DAOAccessor;
import datanucleus.dao.FlightDAO;
import datanucleus.dao.ress.Flight;
import server.FpsServer;
import datanucleus.dao.ress.User;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("")
public class FlightWS implements FlightDAO{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public Collection<Flight> getAll(){
		//return new ArrayList<Flight>();
		return DAOAccessor.getFlightDAO().getAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight/{id}")
	public Flight getElement(@PathParam("id") String id){
		return DAOAccessor.getFlightDAO().getElement(id);
	}
	
	@DELETE
	@Path("/flight/{id}")
	public void deleteElement(@PathParam("id") String id){
		DAOAccessor.getFlightDAO().deleteElement(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{crew_id}/flight")
    public Collection<Flight> getAll(@PathParam("crew_id") String crew_id){
		return DAOAccessor.getFlightDAO().getAll(crew_id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{crew_id}/flight/{id}")
    public Flight getFlight(@PathParam("crew_id") String crew_id,@PathParam("id") String id){
		return DAOAccessor.getFlightDAO().getFlight(crew_id, id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public void addElement(Flight f){
		DAOAccessor.getFlightDAO().addElement(f);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/flight/{id}/edit")
	public void editElement(@PathParam("id") String id, Flight f){
		DAOAccessor.getFlightDAO().editElement(id, f);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{crew_name}/flight/{id}")
	public void addCrew(@PathParam("crew_name") String crew_name, @PathParam("id") String id) {
		DAOAccessor.getFlightDAO().addCrew(crew_name, id);
	}

	@POST
	@Path("/flight/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void importExcelFile(@FormDataParam("file") InputStream stream) throws FileNotFoundException, IOException {
		DAOAccessor.getFlightDAO().importExcelFile(stream);
		// TODO Auto-generated method stub
		
	}


	/*public void editElement(String name, Flight elt) {
		// TODO Auto-generated method stub
		
	}*/

}