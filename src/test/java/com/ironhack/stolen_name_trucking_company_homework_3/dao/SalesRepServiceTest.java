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

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalesRepServiceTest {

    @MockBean
    private StolenNameTruckingCompanyHomework3Application stolenNameTruckingCompanyHomework3Application;

    @Autowired
    private SalesRepRepository salesRepRepository;

    @Autowired
    private LeadRepository leadRepository;

    private List<SalesRep> salesReps;
    private List<Lead> leads;

    private SalesRepService srs;
    private Lead lead;
    private SalesRep salesRep;


    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {

        srs = new SalesRepService();
        salesRep = new SalesRep("Jhon Bro");
        lead = new Lead("TestOne", "123546", "test1@test.gmail.com", "TestCompany1", salesRep);


        salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep("David Lynch"),
                new SalesRep("Martha Stewart")
        ));

        leads = leadRepository.saveAll(List.of(
                new Lead("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow"),
                new Lead("Martha Steward", "123475357", "ms@wp.pl", "My own company"),
                new Lead("George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show"))
        );
    }

    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();
        salesRepRepository.deleteAll();
    }

    @Test
    void addLeadToDb_shouldWork() {
        assertEquals(3,leadRepository.findAllLeads().size());
        salesRepRepository.save(salesRep);
        srs.addLeadToDb(lead);
        assertEquals(4,leadRepository.findAllLeads().size());
    }

    @Test
    void removeLeadFromDb_shouldWork() {
        assertEquals(3,leadRepository.findAllLeads().size());
        srs.removeLeadFromDb(leads.get(0));
        assertEquals(2,leadRepository.findAllLeads().size());
    }

    @Test
    void addSalesRep_shouldWork() {
        assertEquals(2,salesRepRepository.findAllSalesReps().size());
        srs.addSalesRep(salesRep);
        assertEquals(3,salesRepRepository.findAllSalesReps().size());
    }


}