package com.project.memorise.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.memorise.model.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, String>{

	Optional<Users> findByUserName(String userName);

}
