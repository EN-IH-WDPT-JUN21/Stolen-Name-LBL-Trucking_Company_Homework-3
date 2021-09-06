package com.ironhack.stolen_name_trucking_company_homework_3.repository;

import com.ironhack.stolen_name_trucking_company_homework_3.StolenNameTruckingCompanyHomework3Application;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.Lead;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.SalesRep;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeadRepositoryTest {

    @MockBean
    private StolenNameTruckingCompanyHomework3Application stolenNameTruckingCompanyHomework3Application;

    @Autowired
    private SalesRepRepository salesRepRepository;

    @Autowired
    private LeadRepository leadRepository;


    private List<SalesRep> salesReps;
    private List<Lead> leads;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {

        salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep("David Lynch"),
                new SalesRep("Martha Stewart")
                ));

        leads = leadRepository.saveAll(List.of(
                new Lead("Sebastian Marek Labedz", "123456789", "labedzsebastian@gmail.co", "Wings of Freedom", salesReps.get(0)),
                new Lead("Lee Dawson", "980651164", "ld@gmail.com", "LeeD", salesReps.get(0)),
                new Lead("Natalia Shilyaeva", "563782789", "nattyshil@yahoo.com", "Nathy From Wonderland", salesReps.get(1))

        ));


    }

    @AfterEach
    void tearDown() {
       leadRepository.deleteAll();
       salesRepRepository.deleteAll();
    }


    @Test
    public void createLeads_positiveCreation(){
        var leadsy = leadRepository.findAll();
        var salesRepsy = salesRepRepository.findAll();
        assertEquals(3, leadsy.size());
        assertEquals(2, salesRepsy.size());
        //assertEquals("David Lynch", leadRepository.findAll().get(3));
    }


    @Test
    void getCountOfLeadsGroupBySalesRep_Test() {
        var leadByRep = leadRepository.findCountLeadByRepName();

        assertEquals("David Lynch", leadByRep.get(0)[0]);
        assertEquals("Martha Stewart", leadByRep.get(1)[0]);
        assertEquals(1L, leadByRep.get(1)[1]);
        assertEquals(2L,leadByRep.get(0)[1]);
    }


}