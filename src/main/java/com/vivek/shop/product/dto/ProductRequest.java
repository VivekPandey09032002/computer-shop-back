package com.vivek.shop.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;


import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Name of product can not be blank!")
    private String name;
    @NotBlank(message = "Description can not be blank!")
    private String description;
    private String code;
    @NotNull(message = "Please choose category!")
    private String category;
    @NotNull(message = "Please type price of product!")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal price;
    private String condition;
    private int quantity;
    private String imgLogoUrl;

    public ProductRequest() {
    }

    public ProductRequest(
            @NotBlank final String name,
            @NotBlank final String description,
            final String code,
            @NotBlank final String category,
            final BigDecimal price,
            final String condition,
            final int quantity,
            final String imgLogoUrl
    ) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.category = category;
        this.price = price;
        this.condition = condition;
        this.quantity = quantity;
        this.imgLogoUrl = imgLogoUrl;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRequest that = (ProductRequest) o;
        return quantity == that.quantity && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(code, that.code) && Objects.equals(category, that.category) && Objects.equals(price, that.price) && Objects.equals(condition, that.condition) && Objects.equals(imgLogoUrl, that.imgLogoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, code, category, price, condition, quantity, imgLogoUrl);
    }
}
