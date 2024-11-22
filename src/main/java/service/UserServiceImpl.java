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
	@Override
	@POST
	@Path("/add")
	public Response addUser(User u) {
		Response response = new Response();
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.persist(u);
		response.setStatus(true);
		response.setMessage("User Added Succssfully!");
		session.getTransaction().commit();
		return response;
	}
	
	
	//delete 
	@GET
	@Path("delete/{id}")
	@Override
	public Response deleteUser(Long id) {
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
		System.out.println("first");
		List<User> users = new ArrayList<User>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			users = session.createQuery("Select u from User u",User.class).list();
			System.out.println("list all users");
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
	public User getUserById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user = (User) session.getReference(User.class, Long.valueOf(id));
		session.getTransaction();
		return user; 
	}

}
