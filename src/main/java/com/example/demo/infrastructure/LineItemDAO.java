package com.example.demo.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.models.LineItem;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@Component
public class LineItemDAO extends BaseDAO {
    public LineItemDAO(MongoDatabase mongoDatabase) {
        super(mongoDatabase, "line_items");
    }

    public List<LineItem> getLineItems() {
        List<LineItem> lineItems = new ArrayList<>();
        return collection().find().map(this::mapToLineItem).into(lineItems);
    }

    private LineItem mapToLineItem(Document document) {
        return new LineItem(
                document.getObjectId("_id").toString(),
                document.getString("cart_id"),
                document.getString("product_id"),
                document.getInteger("unit_price"),
                document.getInteger("quantity"),
                document.getInteger("total_price")
        );
    }

    public void addLineItem(LineItem lineItem) {
        collection().insertOne(new Document()
                .append("cart_id", lineItem.getCartId())
                .append("product_id", lineItem.getProductId())
                .append("unit_price", lineItem.getUnitPrice())
                .append("quantity", lineItem.getQuantity())
                .append("total_price", lineItem.getTotalPrice())
        );
    }
}
