package com.vivek.shop.product;

import com.vivek.shop.product.dto.ProductDto;
import com.vivek.shop.product.enums.ECategory;
import com.vivek.shop.product.enums.ECondition;
import com.vivek.shop.product.dto.ProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductFactory {
    public Product toEntity(ProductDto dto) {
        return new Product(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getCode(),
                ECategory.valueOf(dto.getCategory()),
                dto.getPrice(),
                ECondition.valueOf(dto.getCondition()),
                dto.getQuantity(),
                dto.getImgLogoUrl()
        );
    }

    public Product toEntity(ProductRequest request) {
        return new Product(
                0,
                request.getName(),
                request.getDescription(),
                request.getCode(),
                ECategory.valueOf(request.getCategory()),
                request.getPrice(),
                ECondition.valueOf(request.getCondition()),
                request.getQuantity() == 0 ? 1 : request.getQuantity(),
                request.getImgLogoUrl()
        );
    }

    public ProductDto toDto(Product project) {
        return ProductDto.create(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getCode(),
                project.getCategory().toString(),
                project.getPrice(),
                project.getCondition().toString(),
                project.getQuantity(),
                project.getImgLogoUrl()
        );
    }
}
