package com.vivek.shop.order;

import com.vivek.shop.customer.CustomerFactory;
import com.vivek.shop.order.dto.OrderDto;
import com.vivek.shop.order.dto.OrderItemDto;
import com.vivek.shop.product.ProductFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
class OrderFactory {
    private final CustomerFactory customerFactory;
    private final ProductFactory productFactory;

    OrderFactory(final CustomerFactory customerFactory, final ProductFactory productFactory) {
        this.customerFactory = customerFactory;
        this.productFactory = productFactory;
    }

    OrderItem toNewEntity(OrderItemDto dto) {
        return new OrderItem(
                dto.getQuantity(),
                productFactory.toEntity(dto.getProduct()),
                dto.getProduct().getPrice()
        );
    }

    OrderDto toDto(Order entity) {
        return OrderDto.create(
                entity.getId(),
                entity.getTotalCost(),
                entity.getItems().stream().map(this::toDto).collect(Collectors.toSet()),
                customerFactory.toDto(entity.getCustomer()),
                entity.getStatus().toString()
        );
    }

    private OrderItemDto toDto(OrderItem entity) {
        return OrderItemDto.create(
                entity.getId(),
                entity.getQuantity(),
                entity.getPrice(),
                productFactory.toDto(entity.getProduct())
        );
    }
}
