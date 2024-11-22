package service;

import model.Response;
import model.User;

public interface UserService {
	
	Response addUser(User u);
	Response deleteUser(Long id);
	User getAllUsers();
	User getUserById(Long id);
	
}
