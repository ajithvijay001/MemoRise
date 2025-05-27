package com.project.memorise.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.memorise.model.CustomSequences;
import com.project.memorise.model.Decks;
import com.project.memorise.model.Users;
import com.project.memorise.repository.DeckRepository;
import com.project.memorise.repository.UserRepository;
import com.project.memorise.service.SequenceGeneratorService;
import com.project.memorise.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
    private SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DeckRepository deckRepo;
	
	@Override
	public String addUsers(Users user) {
		 user.setUserId(sequenceGeneratorService.getNextSequence("users_seq"));
		user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
		userRepo.save(user);
		return "New User Created Successfully";
	}

	@Override
	public Optional<Users> getUserDetails() {
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Users> userDetails = userRepo.findByUserName(user.getUsername());
		List<Decks> deck = deckRepo.findByUserId(userDetails.get().getUserId());
		userDetails.get().setDecks(deck);
		return userDetails;
	}

}
