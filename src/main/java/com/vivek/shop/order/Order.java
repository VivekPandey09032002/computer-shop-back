package com.vivek.shop.order;

import com.vivek.shop.customer.Customer;
import com.vivek.shop.order.enums.EOrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private BigDecimal totalCost;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderItem> items;
    @ManyToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private EOrderStatus status;
    private String paymentId;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Calendar updatedAt;


    public Order(final BigDecimal totalCost,
                 final Set<OrderItem> items,
                 final Customer customer,
                 final EOrderStatus status
    ) {
        this.totalCost = totalCost;
        this.items = items;
        this.customer = customer;
        this.status = status;
    }

    void cancelOrder() {
        this.status = EOrderStatus.CANCELLED;
    }


}
