package com.vivek.shop.cart;

import com.vivek.shop.customer.Customer;
import com.vivek.shop.exception.ApiBadRequestException;
import com.vivek.shop.exception.ApiNotFoundException;
import com.vivek.shop.cart.dto.AddToCartDto;
import com.vivek.shop.cart.dto.CartItemDto;
import com.vivek.shop.product.Product;
import com.vivek.shop.product.ProductFactory;
import com.vivek.shop.product.ProductQueryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartFacade {
    private final CartItemRepository repository;
    private final CartItemQueryRepository queryRepository;
    private final ProductQueryRepository productQueryRepository;
    private final CartFactory cartFactory;
    private final ProductFactory productFactory;

    CartFacade(
            @Qualifier("cartItemRepository") final CartItemRepository repository,
            final CartItemQueryRepository queryRepository,
            final ProductQueryRepository productQueryRepository,
            final CartFactory cartFactory,
            final ProductFactory productFactory
    ) {
        this.repository = repository;
        this.queryRepository = queryRepository;
        this.productQueryRepository = productQueryRepository;
        this.cartFactory = cartFactory;
        this.productFactory = productFactory;
    }

    CartItemDto addToCart(AddToCartDto addToCartDto, Customer customer) {
        var product = productQueryRepository.findById(addToCartDto.getProductId())
                .orElseThrow(() -> new ApiNotFoundException("Product with that id not exists: " + addToCartDto.getProductId()));

        var cartItem = queryRepository.findByProductAndCustomer(product, customer)
                .map(cart -> {
                    int finalQuantity = addToCartDto.getQuantity() + cart.getQuantity();
                    checkIfProductIsAvailable(product, finalQuantity);
                    cart.setQuantity(finalQuantity);
                    return cart;
                })
                .orElseGet(() -> {
                    checkIfProductIsAvailable(product, addToCartDto.getQuantity());
                    return cartFactory.toEntity(addToCartDto, customer, productFactory.toDto(product));
                });

        return cartFactory.toDto(repository.save(cartItem));
    }

    @Transactional
    public void deleteCartItem(int id, Customer customer) {
        if (!queryRepository.existsById(id)) {
            throw new ApiNotFoundException("Cart with that id not exists: " + id);
        }

        queryRepository.findByIdAndCustomer(id, customer)
                .ifPresent(cart -> {
                    if(cart.getQuantity() > 1) {
                        cart.decreaseQuantity();
                    } else {
                        repository.deleteByIdAndCustomer(id, customer);
                    }
                });
    }

    @Transactional
    public void deleteAllCartItems(Customer customer) {
        repository.deleteAllByCustomer(customer);
    }

    private void checkIfProductIsAvailable(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            throw new ApiBadRequestException("You cant add more product than is available!");
        }
    }

}
