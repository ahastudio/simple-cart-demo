package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MongoClient mongoClient() {
        String uri = "mongodb://localhost:27017";
        return MongoClients.create(uri);
    }

    @Bean
    MongoDatabase mongoDatabase(MongoClient mongoClient) {
        String database = "demo";
        return mongoClient.getDatabase(database);
    }
}
