package com.example.demo.infrastructure;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public abstract class BaseDAO {
    private final MongoDatabase mongoDatabase;
    private final String collectionName;

    public BaseDAO(MongoDatabase mongoDatabase, String collectionName) {
        this.mongoDatabase = mongoDatabase;
        this.collectionName = collectionName;
    }

    protected MongoCollection<Document> collection() {
        return mongoDatabase.getCollection(collectionName);
    }
}
