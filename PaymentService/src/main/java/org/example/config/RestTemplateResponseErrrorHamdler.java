package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exception.PaymentException;
import org.example.model.ErrorResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

@Component
public class RestTemplateResponseErrrorHamdler implements ResponseErrorHandler {


    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError()||response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
       if (response.getStatusCode().is4xxClientError()){
           ObjectMapper objectMapper = new ObjectMapper();
           ErrorResponse errorResponse = objectMapper.readValue(response.getBody(),ErrorResponse.class);
           throw new PaymentException("Ошибка PaymentService"+errorResponse.message(),String.valueOf(response.getStatusCode()));
       }
    }
}
