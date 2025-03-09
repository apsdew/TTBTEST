package com.assignment.crmservice.service;

import com.assignment.crmservice.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public List<Map<String, Object>> getTopSalesByCustomer(LocalDate startDate, LocalDate endDate) {
        List<Object[]> result = salesRepository.findSalesByCustomerIdAndDateRange(startDate, endDate);
        List<Map<String, Object>> rankedSales = new ArrayList<>();
        int rank = 1;

        for (Object[] row : result) {
            Map<String, Object> map = new HashMap<>();
            map.put("customerId", row[0]);
            map.put("totalSales", row[1]);
            map.put("rank", rank++);
            rankedSales.add(map);
        }

        return rankedSales;
    }
}

