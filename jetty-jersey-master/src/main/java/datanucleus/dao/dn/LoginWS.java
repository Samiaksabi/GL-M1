package datanucleus.dao.dn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import datanucleus.dao.DAOFactory;

@Path("")
public class LoginWS {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces("text/plain")
	@Path("/login/{name}/{password}")
	public String login(@PathParam("name") String name, @PathParam("password") String password) throws Exception{
		if (DAOFactory.getUserDAO().login(name, password)){
			return null;
		}
		else
			throw new Exception();
	}
	
}
