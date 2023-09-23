package com.vivek.shop.cart;

import com.vivek.shop.customer.Customer;
import com.vivek.shop.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
@Getter
@Setter
class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public CartItem() {
    }

    public CartItem(final Product product,
                    final int quantity,
                    final Customer customer) {
        this.product = product;
        this.quantity = quantity;
        this.customer = customer;
    }

    void decreaseQuantity() {
        quantity--;
    }



}
