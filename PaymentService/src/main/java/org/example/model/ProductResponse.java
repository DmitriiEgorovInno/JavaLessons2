package org.example.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;


public record ProductResponse(List<Product> products) {
}
