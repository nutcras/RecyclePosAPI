package com.example.sellWebApp.entity;

import com.example.sellWebApp.entity.impl.EntityImpl;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "attempt_resells")
@Getter
@Setter
public class AttemptResell extends EntityImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "attemptResell_id")
    private Long attemptResellId;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private Float amount;

    @Column(nullable = false)
    private Float weight;

    @Column(nullable = false)
    private Float quantity;




}