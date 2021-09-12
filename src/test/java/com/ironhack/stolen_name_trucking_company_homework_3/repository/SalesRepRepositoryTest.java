package com.ironhack.stolen_name_trucking_company_homework_3.repository;

import com.ironhack.stolen_name_trucking_company_homework_3.StolenNameTruckingCompanyHomework3Application;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.SalesRep;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalesRepRepositoryTest {

    @MockBean
    private StolenNameTruckingCompanyHomework3Application stolenNameTruckingCompanyHomework3Application;

    @Autowired
    private SalesRepRepository salesRepRepository;

    private List<SalesRep> salesReps;

    @BeforeEach
    void setUp() {
        salesRepRepository.deleteAll();
        salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep("David Lynch"),
                new SalesRep("Martha Stewart")
        ));
    }

    @AfterEach
    void tearDown() { ;
        salesRepRepository.deleteAll();
        salesReps.clear();
    }

    @Test
    void findAllSalesreps_shouldWork() {
        var salesRepsCount = salesRepRepository.findAllSalesReps().size();
        assertEquals(2, salesRepsCount);
    }


}