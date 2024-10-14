package com.example.demo.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AddProductDto(
        @NotBlank
        String productId,

        @Positive
        int quantity
) {
}
