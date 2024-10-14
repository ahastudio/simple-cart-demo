package com.example.demo.infrastructure;

import org.springframework.stereotype.Component;

import com.example.demo.models.Cart;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

@Component
public class CartDAO extends BaseDAO {
    public CartDAO(MongoDatabase mongoDatabase) {
        super(mongoDatabase, "carts");
    }

    public Cart getCart() {
        Document document = collection().find().first();

        if (document == null) {
            return createCart();
        }

        return mapToCart(document);
    }

    public void updateCart(Cart cart) {
        collection().updateOne(
                Filters.eq("_id", new ObjectId(cart.getId())),
                Updates.set("total_price", cart.getTotalPrice())
        );
    }

    private Cart mapToCart(Document document) {
        return new Cart(
                document.getObjectId("_id").toString(),
                document.getString("user_id"),
                document.getInteger("total_price")
        );
    }

    private Cart createCart() {
        Document document = new Document()
                .append("user_id", "user-1")
                .append("total_price", 0);

        collection().insertOne(document);

        return mapToCart(document);
    }
}
