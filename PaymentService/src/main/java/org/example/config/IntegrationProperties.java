package org.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "integration.clients")
public class IntegrationProperties {

    private final RestTemplateProperties paymentsClient;

    public IntegrationProperties(RestTemplateProperties paymentsClient) {
        this.paymentsClient = paymentsClient;
    }

    public RestTemplateProperties getPaymentsClientProperties() {
        return paymentsClient;
    }
}
