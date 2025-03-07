package org.example.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentRequest {
    private User user;
    private BigDecimal ammount;
}
