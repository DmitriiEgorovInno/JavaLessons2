package org.example.controller;

import org.example.PaymentService.PaymentService;
import org.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pay")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService=paymentService;
    }

    @GetMapping(value = "/products/{prodId}")
    public ProductResponse payForProduct(@PathVariable("prodId") Long prodId){
        ProductResponse response = paymentService.payForProduct(prodId);
        return response;
    }
    @PostMapping(value = "/payment")
    public OneProductResponse newPayment(@RequestBody PaymentRequest paymentRequest){
        OneProductResponse result = paymentService.newPayment(paymentRequest);
        return result;
    }
}
