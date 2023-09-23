package com.vivek.shop.cart;

import com.vivek.shop.customer.Customer;
import com.vivek.shop.cart.dto.AddToCartDto;
import com.vivek.shop.cart.dto.CartDto;
import com.vivek.shop.cart.dto.CartItemDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cart")
class CartController {
    private final CartItemQueryRepository queryRepository;
    private final CartFacade facade;


    CartController(final CartItemQueryRepository queryRepository, final CartFacade facade) {
        this.queryRepository = queryRepository;
        this.facade = facade;
    }

    @GetMapping
    ResponseEntity<CartDto> readAllCart(@AuthenticationPrincipal Customer customer) {
        List<CartItemDto> cartList = queryRepository.findAllByCustomerOrderByCreatedAtDesc(customer);

        var result = new CartDto(cartList, cartList.stream().map(cart -> {
            BigDecimal price = cart.getProduct().getPrice();
            BigDecimal quantity = BigDecimal.valueOf(cart.getQuantity());
            return price.multiply(quantity);
        }).reduce(BigDecimal.ZERO, BigDecimal::add));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<CartItemDto> createItemCart(
            @Valid @RequestBody AddToCartDto addCartDto,
            @AuthenticationPrincipal Customer customer
    ) {
        var result = facade.addToCart(addCartDto, customer);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteItemCart(
            @AuthenticationPrincipal Customer customer,
            @PathVariable int id
    ) {
        facade.deleteCartItem(id, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    ResponseEntity<Void> deleteAllCarts(
            @AuthenticationPrincipal Customer customer
    ) {
        facade.deleteAllCartItems(customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
