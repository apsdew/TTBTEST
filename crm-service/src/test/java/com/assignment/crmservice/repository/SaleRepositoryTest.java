package com.assignment.crmservice.repository;


import com.assignment.crmservice.entity.Sale;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SaleRepositoryTest {

    @Autowired
    private SalesRepository salesRepository;

//    @Test
//    void testFindSalesByCustomerIdAndDateRange() {
//
//    }
}

