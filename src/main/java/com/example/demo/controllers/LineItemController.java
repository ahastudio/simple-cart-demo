package com.example.demo.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controllers.dtos.AddProductDto;
import com.example.demo.controllers.dtos.CartDto;
import com.example.demo.infrastructure.CartDAO;
import com.example.demo.infrastructure.LineItemDAO;
import com.example.demo.infrastructure.ProductDAO;
import com.example.demo.models.Cart;
import com.example.demo.models.LineItem;
import com.example.demo.models.Product;

@RestController
@RequestMapping("/cart/line-items")
public class LineItemController {
    private final CartDAO cartDAO;
    private final LineItemDAO lineItemDAO;
    private final ProductDAO productDAO;

    public LineItemController(
            CartDAO cartDAO, LineItemDAO lineItemDAO, ProductDAO productDAO) {
        this.cartDAO = cartDAO;
        this.lineItemDAO = lineItemDAO;
        this.productDAO = productDAO;
    }

    @GetMapping
    CartDto list() {
        Cart cart = cartDAO.getCart();
        List<LineItem> lineItems = lineItemDAO.getLineItems();

        return new CartDto(
                lineItems.stream()
                        .map(this::generateLineItemDto)
                        .toList(),
                cart.getTotalPrice()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody AddProductDto addProductDto) {
        Cart cart = cartDAO.getCart();

        Product product = productDAO.getProduct(addProductDto.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        LineItem lineItem = new LineItem(
                cart.getId(),
                addProductDto.productId(),
                product.getPrice(),
                addProductDto.quantity()
        );

        lineItemDAO.addLineItem(lineItem);

        List<LineItem> lineItems = lineItemDAO.getLineItems();

        int totalPrice = lineItems.stream()
                .mapToInt(LineItem::getTotalPrice)
                .sum();

        cart.setTotalPrice(totalPrice);

        cartDAO.updateCart(cart);
    }

    private CartDto.LineItemDto generateLineItemDto(LineItem lineItem) {
        Product product = productDAO.getProduct(lineItem.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new CartDto.LineItemDto(
                lineItem.getId(),
                lineItem.getProductId(),
                product.getName(),
                lineItem.getUnitPrice(),
                lineItem.getQuantity(),
                lineItem.getTotalPrice()
        );
    }
}
