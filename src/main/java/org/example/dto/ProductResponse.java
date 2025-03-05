package org.example.dto;

import org.example.Entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public record ProductResponse(List<Product> products) {
}
