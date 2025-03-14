package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.Entity.Product;
import org.example.Entity.User;
import org.example.Service.ProductService;
import org.example.Service.UserService;
import org.example.dto.ErrorResponse;
import org.example.dto.OneProductResponse;
import org.example.dto.ProductResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }


    @GetMapping(value = "/{Id}")
    public ProductResponse getProductById(@PathVariable("Id") Long Id){
        Product product=productService.findById(Id);
        if (product==null){
            throw new EmptyResultDataAccessException(1);
        }
        return new ProductResponse(Collections.singletonList(product));

    }
    @GetMapping("/users")
    public ProductResponse findByUser(@RequestParam("userID") Long userID){
        List<Product> products = productService.findByUser(userService.findById(userID));
        return new ProductResponse(products);
    }

    @GetMapping("/user")
    public OneProductResponse getByAccount(@RequestParam("account") String account){
        return productService.findByUserAndAccount(account);
    }
    @GetMapping("/product")
    public void updateBalance(@RequestParam("prodId") Long prodId, @RequestParam("balance") BigDecimal balance){
        productService.updateBalance(prodId,balance);
    }
}
