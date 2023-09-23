package com.vivek.shop.cart.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record CartDto(List<CartItemDto> carts, BigDecimal totalCost) {

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDto cartDto = (CartDto) o;
        return Objects.equals(carts, cartDto.carts) && Objects.equals(totalCost, cartDto.totalCost);
    }
    @Override
    public int hashCode() {
        return Objects.hash(carts, totalCost);
    }

}
