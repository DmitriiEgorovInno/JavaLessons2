package org.example.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Entity.Product;
import org.example.Entity.ProductType;
import org.example.Entity.User;
import org.example.Repository.ProductRepository;
import org.example.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service

public class ProductService implements CommandLineRunner {
    public final ProductRepository productRepository;
    public final UserRepository userRepository;

    public ProductService(ProductRepository productRepository,UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Product save(Product user){
        return productRepository.save(user);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product findById(Long id){
         return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    }


    public List<Product> findByUser(User user){
        return productRepository.findByUser(user);
    }
    @Override
    public void run(String... args) throws Exception {
        Product product = findById(6l);
        log.info("Продукт: {}",product);

        User user = userRepository.findUserByName("user_2");
        findByUser(user).forEach(System.out::println);
    }
}

