package util;

import java.util.ArrayList;
import java.util.List;

import model.User;
import service.UserServiceImpl;

public class Test {
	public static void main(String[] args) {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		List<User> users = new ArrayList<User>();
		users = userServiceImpl.getAllUsers();
		System.out.println(users);
	}
}
