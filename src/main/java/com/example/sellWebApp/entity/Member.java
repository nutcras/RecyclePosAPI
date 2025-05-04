package com.example.sellWebApp.entity;

import com.example.sellWebApp.entity.impl.EntityImpl;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members")
@Getter
@Setter
public class Member extends EntityImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long memberId;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;


}