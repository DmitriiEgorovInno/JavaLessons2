package org.example.Repository;

import org.example.Entity.Product;
import org.example.Entity.ProductType;
import org.example.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long id);

    Optional<Product> findByAccount(String account);

    List<Product> findByUser(User user);

    @Modifying
    @Transactional
    @Query(value = "update products set balance = :balance where product_id = :prodId", nativeQuery = true)
    void updateBalance(@Param("balance") BigDecimal balance,@Param("prodId") Long prodId);
}
