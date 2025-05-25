package com.project.memorise.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.memorise.model.CustomSequences;
import com.project.memorise.model.Users;
import com.project.memorise.repository.UserRepository;
import com.project.memorise.service.SequenceGeneratorService;
import com.project.memorise.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
    private SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public String addUsers(Users user) {
		 user.setUserId(sequenceGeneratorService.getNextSequence("users_seq"));
		user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
		userRepo.save(user);
		return "New User Created Successfully";
	}

}
