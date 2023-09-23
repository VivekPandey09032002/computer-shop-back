package com.vivek.shop.cart;

import com.vivek.shop.customer.Customer;
import org.springframework.data.repository.Repository;
import java.util.Optional;

interface CartItemRepository extends Repository<CartItem, Integer> {
    Optional<CartItem> findById(int id);

    CartItem save(CartItem entity);

    void deleteByIdAndCustomer(int id, Customer customer);

    void deleteAllByCustomer(Customer customer);
}
