package com.assignment.crmservice.service;

import com.assignment.crmservice.repository.SalesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleServiceTest {

    @Mock
    private SalesRepository salesRepository;

    @InjectMocks
    private SalesService salesService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSalesByCustomerIdAndDateRange() {
        List<Object[]> mockData = Arrays.asList(
                new Object[]{1L, 5000.0, 1},
                new Object[]{2L, 7000.0, 2}
        );
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);

        when(salesRepository.findSalesByCustomerIdAndDateRange(any(), any())).thenReturn(mockData);

        List<Map<String, Object>> sales = salesService.getTopSalesByCustomer(startDate, endDate);
        assertEquals(2, sales.size());
    }
}

