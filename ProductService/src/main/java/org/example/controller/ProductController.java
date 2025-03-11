package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.Entity.Product;
import org.example.Entity.User;
import org.example.Service.ProductService;
import org.example.Service.UserService;
import org.example.dto.ErrorResponse;
import org.example.dto.ProductResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/user")
    public ProductResponse findByUser(@RequestParam("userID") Long userID){
        List<Product> products = productService.findByUser(userService.findById(userID));
        return new ProductResponse(products);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorResponse handleEmptyResultDataAccessException(EmptyResultDataAccessException e){
        ErrorResponse errorResponse=new ErrorResponse("Продукт не найден");
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public  ErrorResponse handleExceptio(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Ошибка Product.Service "+ e.getMessage());
        return errorResponse;
    }
}
