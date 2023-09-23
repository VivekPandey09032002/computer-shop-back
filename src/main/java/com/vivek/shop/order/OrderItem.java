package com.vivek.shop.order;

import com.vivek.shop.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private Product product;
    @NotNull
    private int quantity;
    @NotNull
    private BigDecimal price;


    private LocalDateTime createdAt = LocalDateTime.now();
    private Calendar updatedAt;

    public OrderItem(final int quantity, final Product product, final BigDecimal price) {
        this.quantity = quantity;
        this.product = product;
        this.price = price;
    }

}
