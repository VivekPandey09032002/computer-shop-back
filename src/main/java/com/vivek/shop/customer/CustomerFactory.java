package com.vivek.shop.customer;

import com.vivek.shop.customer.dto.CustomerDetailsDto;
import com.vivek.shop.customer.dto.CustomerDto;
import com.vivek.shop.customer.enums.EGender;
import org.springframework.stereotype.Service;

@Service
public class CustomerFactory {
    public CustomerDto toDto(Customer entity) {
        return CustomerDto.create(
          entity.getId(),
          entity.getUsername(),
          entity.getEmail(),
          entity.getDetails() == null ? null : toDto(entity.getDetails())
        );
    }

    CustomerDetailsDto toDto(CustomerDetails entity) {
        return CustomerDetailsDto.create(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getGender().toString(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getPhone(),
                entity.getCity(),
                entity.getCountry()
        );
    }

    CustomerDetails toEntity(CustomerDetailsDto dto) {
        return new CustomerDetails(
                dto.getFirstName(),
                dto.getLastName(),
                EGender.valueOf(dto.getGender()),
                dto.getAddressLine1(),
                dto.getAddressLine2(),
                dto.getPhone(),
                dto.getCity(),
                dto.getCountry()
        );
    }
}
