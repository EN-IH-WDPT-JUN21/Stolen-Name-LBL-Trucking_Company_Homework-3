package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.StolenNameTruckingCompanyHomework3Application;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.LeadRepository;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.SalesRepRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalesRepServiceTest {

    @MockBean
    private StolenNameTruckingCompanyHomework3Application stolenNameTruckingCompanyHomework3Application;

    @Autowired
    private SalesRepRepository salesRepRepository;

    @Autowired
    private LeadRepository leadRepository;

   /* private List<SalesRep> salesReps;
    private List<Lead> leads;*/

    private SalesRepService salesRepService;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        salesRepService = new SalesRepService();
     /*   salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep("David Lynch"),
                new SalesRep("Martha Stewart")
        ));

        leads = leadRepository.saveAll(List.of(
                new Lead("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow"),
                new Lead("Martha Steward", "123475357", "ms@wp.pl", "My own company"),
                new Lead("George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show"))

        );*/

    }

    @AfterEach
    void tearDown() {
        salesRepRepository.deleteAll();
        leadRepository.deleteAll();
    }

   /* @Test
    void addLeadToDb_shouldWork()  {
        assertEquals(0,salesRepRepository.count());
        salesRepService.addLeadToDb(new Lead()); //doesn't work
        assertEquals(1,salesRepRepository.count());
    }*/






}