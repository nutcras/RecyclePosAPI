package com.example.sellWebApp.repository;

import com.example.sellWebApp.entity.Employee;
import com.example.sellWebApp.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {


    @Transactional
    @Modifying
    @Query("update Member m set m.function = ?1 where m.memberId = ?2")
    int updateFunctionByMemberId(String function, Long memberId);
}