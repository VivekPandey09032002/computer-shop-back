package com.vivek.shop.cart;

import com.vivek.shop.cart.dto.CartItemDto;
import com.vivek.shop.customer.Customer;
import com.vivek.shop.product.Product;
import org.springframework.data.repository.Repository;
import java.util.Optional;

import java.util.List;

public interface CartItemQueryRepository extends Repository<CartItem, Integer> {
    boolean existsById(int id);

    List<CartItemDto> findAllByCustomerOrderByCreatedAtDesc(Customer customer);

    Optional<CartItem> findByIdAndCustomer(int cartId, Customer customer);

    Optional<CartItem> findByProductAndCustomer(Product productId, Customer customer);
}
