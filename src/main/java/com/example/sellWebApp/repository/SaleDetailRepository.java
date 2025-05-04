package com.example.sellWebApp.repository;

import com.example.sellWebApp.entity.Sale;
import com.example.sellWebApp.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long>, JpaSpecificationExecutor<SaleDetail> {

//    @Query("SELECT SUM(sd.weight) FROM SaleDetail sd WHERE sd.sale.date BETWEEN :startDate AND :endDate AND sd.product.unit = :unit")
//    Float sumWeightByDateRangeAndUnit(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("unit") String unit);

    @Query(value = "select sum(sd.amount) FROM sales s JOIN sale_details sd on sd.sale_id=s.sale_id JOIN products p on p.product_id=sd.product_id WHERE s.date between :startDate and :endDate and p.unit=:unit", nativeQuery = true)
    Float sumAmount(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("unit") String unit);

    @Query(value = "select sum(sd.weight) FROM sales s JOIN sale_details sd on sd.sale_id=s.sale_id JOIN products p on p.product_id=sd.product_id WHERE s.date between :startDate and :endDate and p.unit=:unit", nativeQuery = true)
    Float sumWeight(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("unit") String unit);
}