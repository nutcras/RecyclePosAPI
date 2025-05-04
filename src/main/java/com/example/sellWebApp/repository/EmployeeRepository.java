package com.example.sellWebApp.repository;

import com.example.sellWebApp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {


    Employee findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update Employee e set e.function = ?1 where e.username = ?2")
    int updateFunctionByUsername(String function, String username);
}