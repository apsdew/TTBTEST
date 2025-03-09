package com.assignment.crmservice.repository;

import com.assignment.crmservice.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s.customerId, SUM(s.saleAmount) AS totalSales FROM Sale s " +
            "WHERE s.saleDate >= :startDate AND s.saleDate < :endDate " +
            "GROUP BY s.customerId ORDER BY totalSales DESC")
    List<Object[]> findSalesByCustomerIdAndDateRange(LocalDate startDate, LocalDate endDate);
}

