package org.example.PaymentService;

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

    public OneProductResponse newPayment(PaymentRequest paymentRequest) {

        OneProductResponse response = null;
        try {
            response = restTemplate.getForObject("/api/v1/user?userID="+paymentRequest.getUser().getId()+"&account="+paymentRequest.getAccount(), OneProductResponse.class);

        } catch (HttpServerErrorException | HttpClientErrorException httpClientOrServExc) {
            if (HttpStatus.INTERNAL_SERVER_ERROR.equals(httpClientOrServExc.getStatusCode()))
                throw new RestClientException("Нет продукта с таким юзером");
        }
        if (response.product().getBalance().compareTo(paymentRequest.getAmmount()) < 0) {
            throw new RestClientException("Недостаточно средств");
        } else {
            restTemplate.getForObject("/api/v1/product?prodId="+response.product().getId()+"&balance="+response.product().getBalance().subtract(paymentRequest.getAmmount()),int.class);
            response = restTemplate.getForObject("/api/v1/user?userID="+paymentRequest.getUser().getId()+"&account="+paymentRequest.getAccount(), OneProductResponse.class);
            return response;

        }
    }

}
