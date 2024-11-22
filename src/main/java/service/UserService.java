package service;

import java.util.List;

import model.Response;
import model.User;

public interface UserService {
	
	Response addUser(User u);
	Response deleteUser(Long id);
	List<User> getAllUsers();
	User getUserById(Long id);
	
}
