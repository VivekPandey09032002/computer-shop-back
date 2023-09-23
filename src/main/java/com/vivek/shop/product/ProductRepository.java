package com.vivek.shop.product;

import org.springframework.data.repository.Repository;

import java.util.Optional;

interface ProductRepository extends Repository<Product, Integer> {

    Optional<Product> findById(int id);

    Product save(Product entity);

    void delete(Product entity);
}
