package com.vivek.shop.product;

import com.vivek.shop.product.dto.ProductDto;
import com.vivek.shop.product.enums.ECategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductQueryRepository extends Repository<Product, Integer> {
    boolean existsProductById(int productId);

    boolean existsProductByCode(String code);

    List<ProductDto> findAllByCode(String code);

    List<ProductDto> findAllDtoBy(Pageable pageable);

    List<ProductDto> findAllDtoByCategory(ECategory category);

    List<ProductDto> findAllDtoByNameContaining(String name);

    Optional<ProductDto> findDtoByCode(String code);

    Optional<ProductDto> findDtoById(int id);

    Optional<Product> findById(int id);

    int count();
}
