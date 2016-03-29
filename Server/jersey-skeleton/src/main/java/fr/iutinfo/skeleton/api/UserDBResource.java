package fr.iutinfo.skeleton.api;

//import mainPackage.Map;

import mainPackage.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import src.utilities.Coord;

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
	Map map = new Map();

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

	// TODO Must add some parameters
	@GET
	@Path("/map")
	public Map getMap() {
		return map;
	}

	@PUT
	@Path("/mapa/putaction")
	public Map putAction( @QueryParam("x") int coordx,
			@QueryParam("y") int coordy, @QueryParam("sens") Integer sens,
			@QueryParam("player") Integer player) {
		Coord coord = new Coord(coordx, coordy);
		if (map.getCaseMap()[coord.x()][coord.y()].getOwner() == player) {

			if (map.getCaseMap()[coord.x()][coord.y()].getPipes() == 2) {
				if (sens == 6)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(3);

			} else if (map.getCaseMap()[coord.x()][coord.y()].getPipes() == 6) {
				if (sens == 8)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(9);
				if (sens == 2)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(3);

			} else if (map.getCaseMap()[coord.x()][coord.y()].getPipes() == 8) {
				if (sens == 6)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(9);
				if (sens == 4)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(7);

			} else if (map.getCaseMap()[coord.x()][coord.y()].getPipes() == 4) {
				if (sens == 8)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(7);
				if (sens == 2)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(1);

			} else {

				map.getCaseMap()[coord.x()][coord.y()].setPipes(sens);
			}
		}
		return map;
	}

	@DELETE
	@Path("/map/delaction")
	public Map delAction(@QueryParam("x") int coordx,
			@QueryParam("y") int coordy,
			@QueryParam("sens") int sens, @QueryParam("player") int player) {
Coord coord = new Coord(coordx,coordy);
		if (map.getCaseMap()[coord.x()][coord.y()].getOwner() == player) {

			if (map.getCaseMap()[coord.x()][coord.y()].getPipes() == 9) {
				if (sens == 8)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(6);
				if (sens == 6)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(8);

			} else if (map.getCaseMap()[coord.x()][coord.y()].getPipes() == 7) {
				if (sens == 8)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(4);
				if (sens == 4)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(8);

			} else if (map.getCaseMap()[coord.x()][coord.y()].getPipes() == 3) {
				if (sens == 2)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(6);
				if (sens == 6)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(2);

			} else if (map.getCaseMap()[coord.x()][coord.y()].getPipes() == 1) {
				if (sens == 4)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(2);
				if (sens == 2)
					map.getCaseMap()[coord.x()][coord.y()].setPipes(4);

			} else {
				map.getCaseMap()[coord.x()][coord.y()].setPipes(0);
			}
		}
		return map;
	}

	@GET
	public List<User> getAllUsers() {
		return dao.all();
	}

}
