package com.project.memorise.service;


import java.util.Optional;


import com.project.memorise.model.Users;

public interface UserService {
	
	public String addUsers(Users user);

	public Optional<Users> getUserDetails();

}
