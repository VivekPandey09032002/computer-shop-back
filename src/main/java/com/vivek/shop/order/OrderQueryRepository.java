package com.vivek.shop.order;

import com.vivek.shop.customer.Customer;
import com.vivek.shop.order.dto.OrderDto;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface OrderQueryRepository extends Repository<Order, Integer> {
    List<OrderDto> findAllDtoByCustomer(Customer customer);

    Optional<OrderDto> findDtoById(int id);

    Optional<OrderDto> findDtoByIdAndCustomer(int id, Customer customer);

}
