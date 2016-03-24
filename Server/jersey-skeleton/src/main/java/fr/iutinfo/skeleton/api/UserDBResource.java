package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/userdb")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserDBResource {
	private static UserDao dao = BDDFactory.getDbi().open(UserDao.class);
	final static Logger logger = LoggerFactory.getLogger(UserDBResource.class);

	public UserDBResource() {
		try {
			dao.createUserTable();
			dao.insert(new User(0, "Margaret Thatcher"));
		} catch (Exception e) {
			System.out.println("Table déjà là !");
		}
	}

	@POST
	public User createUser(User user) {
		user.resetPasswordHash();
		int id = dao.insert(user);
		user.setId(id);
		return user;
	}
	

	@PUT
	@Path("/{id}")
	public User updateUserWin(@PathParam("id") int id, User user) {
		dao.incWin(id);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		return user;
	}

	@GET
	@Path("/{name}")
	public User getUser(@PathParam("name") String name) {
		User user = dao.findByName(name);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		return user;
	}

	@GET
	@Path("/auth/login")
	public User getlogin(@QueryParam("name") String name,
			@QueryParam("mdp") String mdp) {
		User user = dao.findByName(name);
		if (user == null) {
			throw new WebApplicationException(403);
		}
		if (!user.isGoodPassword(mdp))
			throw new WebApplicationException(403);
		return user;
	}

	@GET
	public List<User> getAllUsers() {
		return dao.all();
	}

}
