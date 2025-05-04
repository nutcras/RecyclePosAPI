package com.example.sellWebApp.entity;

import com.example.sellWebApp.entity.impl.EntityImpl;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sale_details")
@Getter
@Setter
public class SaleDetail extends EntityImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "saleDetail_id")
    private Long saleDetailId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "sale_id", nullable = false)
    private Long sale;

    @Column(nullable = false)
    private Float amount;

    @Column(nullable = false)
    private Float weight;

    @Column(name = "product_amount", nullable = false)
    private Float productAmount;


    @Transient
    private String unit;


}