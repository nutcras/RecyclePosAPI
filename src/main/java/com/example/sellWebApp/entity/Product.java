package com.example.sellWebApp.entity;

import com.example.sellWebApp.entity.impl.EntityImpl;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends EntityImpl {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float amount;

    @Column(name = "amount_member", nullable = false)
    private Float amountMember;

    @Column(nullable = false)
    private String unit;


}