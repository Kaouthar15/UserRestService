package service;


import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import model.Response;
import model.User;
import util.Connection;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserServiceImpl implements UserService{

	private SessionFactory sessionFactory = getSessionFactory();
	
	protected SessionFactory getSessionFactory() {
		try {
			return Connection.getSessionFactory();
		}catch(Exception ex) {
			throw new IllegalStateException("Could not create Session Factory");
		}
	}
	// add User 
	@POST
	@Path("/add")
	@Override
	public Response addUser(User u) {
	    Session session = this.sessionFactory.getCurrentSession();
	    session.beginTransaction();

	    Response response = new Response();
	    try {
	        session.merge(u);

	        session.getTransaction().commit();
	        response.setStatus(true);
	        response.setMessage("User added successfully!");
	    } catch (Exception e) {
	        if (session.getTransaction().isActive()) {
	            session.getTransaction().rollback();
	        }
	        response.setStatus(false);
	        response.setMessage("Error adding user: " + e.getMessage());
	    }
	    return response;
	}

	
	
	//delete 
	@GET
	@Path("delete/{id}")
	@Override
	public Response deleteUser(@PathParam("id") Long id) {
		Response response = new Response();
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user = (User) session.getReference(User.class, Long.valueOf(id));
		if(user != null) {
			session.remove(user); 
			response.setStatus(true);
			response.setMessage("User Deleted Succssfully!");
		}

		session.getTransaction().commit();
		return response;
	}
	
	// get all
	@GET
	@Path("getAll")
	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			users = session.createQuery("Select u from User u",User.class).list();
			session.getTransaction().begin();
		}catch(Exception e) {
			Response response = new Response();
			response.setStatus(false);
			response.setMessage("Error in Get All Users");
		}
		return users;	
	}
	
	// get by id 
	@GET
	@Path("get/{id}")
	@Override
	public User getUserById(@PathParam("id") Long id) {
		System.out.println("getUserById");
		Response response = new Response();
		Session session = this.sessionFactory.getCurrentSession();
		
		User user = (User) session.getReference(User.class, Long.valueOf(id));
		if (user == null) {
			response.setStatus(false);
			response.setMessage("User Doesn's Exist!");
		}
		session.beginTransaction();
		session.getTransaction().commit();
		return user; 
	}

}
