package com.example.sellWebApp.repository;

import com.example.sellWebApp.entity.AttemptResell;
import com.example.sellWebApp.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AttemptResellRepository extends JpaRepository<AttemptResell, Long>, JpaSpecificationExecutor<AttemptResell> {
    @Query("SELECT a FROM AttemptResell a ORDER BY a.endDate DESC")
    AttemptResell findLastAttempt();
}