package com.example.vkMessege.dao;

import com.example.vkMessege.model.Coub;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoubMongoRepository extends MongoRepository<Coub, Long> {
}
