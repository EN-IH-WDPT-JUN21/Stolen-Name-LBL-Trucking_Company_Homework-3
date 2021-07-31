package com.ironhack.stolen_name_trucking_company_homework_3.repository;


import com.ironhack.stolen_name_trucking_company_homework_3.dao.Contact;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.Opportunity;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.SalesRep;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpportunityRepositoryTest {

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private SalesRepRepository salesRepRepository;

    @Autowired
    private ContactRepository contactRepository;

    private List<SalesRep> salesReps;
    private List<Contact> contacts;
    private List<Opportunity> opportunities;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {

        salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep("David Lynch"),
                new SalesRep("Martha Stewart")
        ));

        contacts = contactRepository.saveAll(List.of(
                new Contact("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow"),
                new Contact("Martha Steward", "123475357", "ms@wp.pl", "My own company"),
                new Contact("George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show")

        ));

        opportunities = opportunityRepository.saveAll(List.of(
        new Opportunity(Truck.FLATBED, 10, contacts.get(0), salesReps.get(0)),
        new Opportunity(Truck.BOX, 1150, contacts.get(1), salesReps.get(0)),
        new Opportunity(Truck.HYBRID, 1, contacts.get(2), salesReps.get(1))

        ));


    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();

    }

    @Test
    void findCountOpportunitySalesRepIdGroup_Test() {
        var leadByRep = opportunityRepository.findCountOpportunitySalesRepIdGroup();

        assertEquals("David Lynch", leadByRep.get(0)[0]);
        assertEquals("Martha Stewart", leadByRep.get(1)[0]);
        assertEquals(1L, leadByRep.get(1)[1]);
        assertEquals(2L,leadByRep.get(0)[1]);
    }

    @Test
    void findCountOppForProduct_Test(){
        var oppByProd = opportunityRepository.findCountOppForProduct();
        assertEquals(Truck.HYBRID, oppByProd.get(0)[0]);
        assertEquals(1L, oppByProd.get(0)[1]);
        assertEquals(Truck.FLATBED, oppByProd.get(1)[0]);
        assertEquals(1L, oppByProd.get(1)[1]);
        assertEquals(Truck.BOX, oppByProd.get(2)[0]);
        assertEquals(1L, oppByProd.get(2)[1]);
    }
}