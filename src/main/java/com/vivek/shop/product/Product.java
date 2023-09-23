package com.vivek.shop.product;

import com.vivek.shop.product.enums.ECategory;
import com.vivek.shop.product.enums.ECondition;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Name of product can not be blank!")
    private String name;
    @NotBlank(message = "Description can not be blank!")
    private String description;
    @NotBlank(message = "Please add product code!")
    private String code;
    @NotNull(message = "Please choose category!")
    @Enumerated(EnumType.STRING)
    private ECategory category;
    @NotNull(message = "Please type price of product!")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ECondition condition = ECondition.NEW;
    private int quantity;
    // TODO: in future - upload file
    private String imgLogoUrl;


    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public Product() {
    }

    public Product(final int id,
                   final String name,
                   final String description,
                   final String code,
                   final ECategory category,
                   final BigDecimal price,
                   final ECondition condition,
                   final int quantity,
                   final String imgLogoUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.category = category;
        this.price = price;
        this.condition = condition;
        this.quantity = quantity;
        this.imgLogoUrl = imgLogoUrl;
    }

    public void increaseStock(int q) {
        for (int i = 0; i < q; i++) {
            quantity++;
        }
    }

    public void decreaseStock(int q) {
        for (int i = 0; i < q; i++) {
            if (quantity > 0) {
                quantity--;
            }
        }
    }

    public void update(final String name, final String description, final String code, final ECategory category, final BigDecimal price, final ECondition condition, final int quantity, final String imgLogoUrl) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.category = category;
        this.price = price;
        this.condition = condition;
        this.quantity = quantity;
        this.imgLogoUrl = imgLogoUrl;
    }
}
