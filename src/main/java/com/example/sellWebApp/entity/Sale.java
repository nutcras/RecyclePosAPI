package com.example.sellWebApp.entity;

import com.example.sellWebApp.entity.impl.EntityImpl;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
@Getter
@Setter
public class Sale extends EntityImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "sale_id")
    private Long saleId;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(name = "employee_id", nullable = false)
    private Long employee;

    @Column(name = "member_id")
    private Long member;

}