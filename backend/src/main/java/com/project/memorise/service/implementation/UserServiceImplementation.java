package com.project.memorise.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.memorise.model.Users;
import com.project.memorise.repository.UserRepository;
import com.project.memorise.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public String addUsers(Users user) {
		userRepo.save(user);
		return "New User Created";
	}

}
