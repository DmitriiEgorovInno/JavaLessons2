package org.example.controller;

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

    @Autowired
    private RestTemplate restTemplate;

    @Value("${product.service.url}")
    private String productServiceUrl;

    @GetMapping(value = "/products/{prodId}")
    public ResponseEntity<ProductResponse> payForProduct(@PathVariable("prodId") Long prodId){
        String url= productServiceUrl+prodId;
        ResponseEntity<ProductResponse> response = restTemplate.getForEntity(url, ProductResponse.class);
        if (response.getStatusCode()== HttpStatus.OK){
            return response;
        } else if (response.getStatusCode()==HttpStatus.NO_CONTENT) {
            throw new RestClientException("Продукт с ID "+ prodId + " не найден");
        } else {
            throw new RestClientException("Ошибка получения продукта с id "+prodId);
        }
    }

    @PostMapping(value = "/newPayment")
    public ResponseEntity<?> newPayment(@RequestBody PaymentRequest paymentRequest){
        String url = productServiceUrl+"user?userID="+paymentRequest.getUser().getId();
        ResponseEntity<ProductResponse> response = restTemplate.getForEntity(url, ProductResponse.class);
        if (response.getStatusCode()==HttpStatus.OK){
            ProductResponse product = response.getBody();
            if (product.products().getFirst().getBalance().compareTo(paymentRequest.getAmmount())<0){
                throw new RestClientException("Недостаточно средств");
            }else {
                return ResponseEntity.ok("Платеж успешно проведен");
            }
        } else {
            throw new RestClientException("Нет продукта с таким юзером");
        }
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponse> handleRestClientExceptio(RestClientException e){
        ErrorResponse errorResponse = new ErrorResponse("Ошибка ProductService: "+ e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }


}
