package com.vivek.shop.customer;

import com.vivek.shop.customer.dto.*;
import com.vivek.shop.exception.ApiNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerFacade facade;
    private final CustomerQueryRepository queryRepository;

    CustomerController(final CustomerFacade facade, final CustomerQueryRepository queryRepository) {
        this.facade = facade;
        this.queryRepository = queryRepository;
    }

    @GetMapping
    ResponseEntity<CustomerDto> readCustomerDetails(
            @AuthenticationPrincipal Customer customer
    ) {
        var result = queryRepository.findDtoByUsername(customer.getUsername())
                .orElseThrow(() -> new ApiNotFoundException("Customer with that username not exists!"));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(params = "details")
    ResponseEntity<CustomerDetailsDto> updateCustomerDetails(
            @AuthenticationPrincipal Customer customer,
            @RequestBody CustomerDetailsDto details
    ) {
        var result = facade.addDetails(details, customer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse response = facade.login(loginRequest);
        logger.info("New customer login: " + loginRequest.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);}

    @PostMapping("/register")
    ResponseEntity<JwtResponse> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
        facade.register(signUpRequest);
        logger.info("New customer registered: " + signUpRequest.getUsername());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
