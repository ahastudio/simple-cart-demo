package com.example.demo.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.demo.models.Product;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

@Component
public class ProductDAO extends BaseDAO {
    public ProductDAO(MongoDatabase mongoDatabase) {
        super(mongoDatabase, "products");
    }

    public void crateProduct(String name, int price) {
        Document document = new Document()
                .append("name", name)
                .append("price", price);

        collection().insertOne(document);
    }

    public Optional<Product> getProduct(String id) {
        Document document = collection().find(
                Filters.eq("_id", new ObjectId(id))
        ).first();

        if (document == null) {
            return Optional.empty();
        }

        return Optional.of(mapToProduct(document));
    }

    private Product mapToProduct(Document document) {
        return new Product(
                document.getObjectId("_id").toString(),
                document.getString("name"),
                document.getInteger("price")
        );
    }
}
