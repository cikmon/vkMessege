package com.example.vkMessege.config;

import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import com.mongodb.MongoClient;

import java.util.Collections;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    public String getDatabaseName() {
        return "vkMessegeCoub";
    }

//    @Override
//    @Bean
//    public MongoClient mongoClient() {
//        return new MongoClient("127.0.0.1", 27017);
//    }

    @Override
    @Bean
    public MongoClient mongoClient() {

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://cikmon:cikmon@cluster0-pzk2y.mongodb.net/admin?retryWrites=true");
        MongoClient mongoClient = new MongoClient(uri);

        return mongoClient;
    }


}
