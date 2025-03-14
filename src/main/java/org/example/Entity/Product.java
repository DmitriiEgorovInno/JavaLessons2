package org.example.Entity;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.SpringVersion;

import java.math.BigDecimal;

@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private long id;

    @Column(name = "account")
    private String account;

    @Column(name="balance")
    private BigDecimal balance;


    @Convert(converter = ProductTypeConverter.class)
    @Column(name = "product_type")
    private ProductType productType;

    @Override
    public String toString() {
        return "Product{" +
                "balance=" + balance +
                ", account='" + account + '\'' +
                ", id=" + id +
                ", productType=" + productType +
                '}';
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Product(long id, String account, BigDecimal balance, ProductType productType, User user) {
        this.id = id;
        this.account = account;
        this.balance = balance;
        this.productType = productType;
        this.user = user;
    }

}
