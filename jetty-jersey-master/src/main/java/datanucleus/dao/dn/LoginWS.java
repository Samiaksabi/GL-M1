package datanucleus.dao.dn;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import datanucleus.dao.DAOAccessor;
import datanucleus.dao.ress.User;

@Path("")
public class LoginWS {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces("text/plain")
	@Path("/login/{name}/{password}")
	public User login(@PathParam("name") String name, @PathParam("password") String password) throws Exception{
		if (DAOAccessor.getUserDAO().login(name, password)){
			return DAOAccessor.getUserDAO().getElement(name);
		}
		else
			throw new Exception();
	}
	
	//ESSAI de rajouter le logout pour qu'il fonctionne corectement
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public void logout(){
		DAOAccessor.getUserDAO().logout().removeAttribute("User");
		DAOAccessor.getUserDAO().logout().invalidate();
	}
	
}
