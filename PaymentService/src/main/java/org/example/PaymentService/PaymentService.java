package org.example.PaymentService;

import org.example.exception.PaymentException;
import org.example.model.OneProductResponse;
import org.example.model.PaymentRequest;
import org.example.model.ProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private final RestTemplate restTemplate;

    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductResponse payForProduct(Long prodId) {
        ProductResponse response = restTemplate.getForObject("/api/v1/"+prodId, ProductResponse.class);
        return response;
    }

    public OneProductResponse getProdByAccount(String account){
        Map<String,String> params= Collections.singletonMap("account",account);
        return restTemplate.getForObject("/api/v1/user?account={account}",OneProductResponse.class,params);
    }

    public OneProductResponse newPayment(PaymentRequest paymentRequest) {
        OneProductResponse product = getProdByAccount(paymentRequest.getAccount());
        if (product.product().getBalance().compareTo(paymentRequest.getAmmount())<0){
            throw new PaymentException("Недостаточно средств для совершения платежа","402 PAYMENT_DECLINED");
        }
        Map<String,String> params = new HashMap<>();
        params.put("prodId",String.valueOf(product.product().getId()));
        params.put("balance",String.valueOf(product.product().getBalance().subtract(paymentRequest.getAmmount())));
        restTemplate.getForObject("/api/v1/product?prodId={prodId}&balance={balance}",OneProductResponse.class,params);
        product = getProdByAccount(paymentRequest.getAccount());
        return product;
    }

}
