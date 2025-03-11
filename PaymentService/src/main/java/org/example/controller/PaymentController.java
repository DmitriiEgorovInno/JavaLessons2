package org.example.controller;

import org.example.PaymentService.PaymentService;
import org.example.model.ErrorResponse;
import org.example.model.PaymentRequest;
import org.example.model.Product;
import org.example.model.ProductResponse;
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


    @Value("${product.service.url}")
    private String productServiceUrl;

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping(value = "/products/{prodId}")
    public ProductResponse payForProduct(@PathVariable("prodId") Long prodId){
        ProductResponse response = paymentService.payForProduct(prodId);
        return response;
    }
    @PostMapping(value = "/newPayment")
    public String newPayment(@RequestBody PaymentRequest paymentRequest){
        String result = paymentService.newPayment(paymentRequest);
        return result;
    }

    @ExceptionHandler(RestClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRestClientExceptio(RestClientException e){
        ErrorResponse errorResponse = new ErrorResponse("Ошибка PaymentService: "+ e.getMessage());
        return errorResponse;

    }


}
