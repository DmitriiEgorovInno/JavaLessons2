package org.example.PaymentService;

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

@Service
public class PaymentService {

    private final RestTemplate restTemplate;

    @Value("${product.service.url}")
    private String productServiceUrl;

    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductResponse payForProduct(Long prodId) {
        String url = productServiceUrl + prodId;
        ProductResponse response = restTemplate.getForObject(url, ProductResponse.class);
        return response;
    }
    public String newPayment(PaymentRequest paymentRequest) {
        String url = productServiceUrl + "user?userID=" + paymentRequest.getUser().getId();
        ProductResponse response = null;
        try {
            response = restTemplate.getForObject(url, ProductResponse.class);
        } catch (HttpServerErrorException | HttpClientErrorException httpClientOrServExc) {
            if (HttpStatus.INTERNAL_SERVER_ERROR.equals(httpClientOrServExc.getStatusCode()))
                throw new RestClientException("Нет продукта с таким юзером");
        }
        if (response.products().getFirst().getBalance().compareTo(paymentRequest.getAmmount()) < 0) {
            throw new RestClientException("Недостаточно средств");
        } else {
            return "Платеж успешно проведен";
        }
    }

}
