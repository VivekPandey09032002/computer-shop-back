package com.vivek.shop.cart;

import com.vivek.shop.customer.CustomerFactory;
import com.vivek.shop.cart.dto.AddToCartDto;
import com.vivek.shop.cart.dto.CartItemDto;
import com.vivek.shop.customer.Customer;
import com.vivek.shop.product.ProductFactory;
import com.vivek.shop.product.dto.ProductDto;
import org.springframework.stereotype.Service;

@Service
class CartFactory {
    private final CustomerFactory customerFactory;
    private final ProductFactory productFactory;

    CartFactory(final CustomerFactory customerFactory, final ProductFactory productFactory) {
        this.customerFactory = customerFactory;
        this.productFactory = productFactory;
    }

    CartItemDto toDto(CartItem entity) {
        return CartItemDto.create(
                entity.getId(),
                customerFactory.toDto(entity.getCustomer()),
                productFactory.toDto(entity.getProduct()),
                entity.getQuantity()
        );
    }

    CartItem toEntity(AddToCartDto addToCartDto, Customer customer, ProductDto productDto) {
        return new CartItem(productFactory.toEntity(productDto), addToCartDto.getQuantity(), customer);
    }
}
