package com.example.vkMessege.dao;

import com.example.vkMessege.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserMongoRepository extends MongoRepository<User, String> {
    @Query("{ 'name' : ?0 }")
    List<User> findByName(String name);

}
