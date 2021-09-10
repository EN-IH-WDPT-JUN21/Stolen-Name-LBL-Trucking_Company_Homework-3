package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.StolenNameTruckingCompanyHomework3Application;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PopulateDatabaseTest {

    @MockBean
    private StolenNameTruckingCompanyHomework3Application stolenNameTruckingCompanyHomework3Application;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private SalesRepRepository salesRepRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LeadRepository leadRepository;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        PopulateDatabase.populateDatabase( leadRepository, salesRepRepository, contactRepository, opportunityRepository, accountRepository);
    }

    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        accountRepository.deleteAll();
        salesRepRepository.deleteAll();
    }


    @Test
    void populateDatabase_shouldWork() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
       var leadCount = leadRepository.findAllLeads().size();
        assertEquals(3, leadCount);
        var salesRepCount = salesRepRepository.findAllSalesReps().size();
        assertEquals(2, salesRepCount);
        var contactCount = contactRepository.findAllContacts().size();
        assertEquals(3, contactCount);
        var opportunityCount = opportunityRepository.findAllOpportunities().size();
        assertEquals(3, opportunityCount);
        var accountCount = accountRepository.findAllAccounts().size();
        assertEquals(3, accountCount);
    }

    @Test
    void clearDatabase_shouldWork() {
        PopulateDatabase.clearDatabase(leadRepository, salesRepRepository, contactRepository, opportunityRepository, accountRepository);
        var leadCount = leadRepository.findAllLeads().size();
        assertEquals(0, leadCount);
        var salesRepCount = salesRepRepository.findAllSalesReps().size();
        assertEquals(0, salesRepCount);
        var contactCount = contactRepository.findAllContacts().size();
        assertEquals(0, contactCount);
        var opportunityCount = opportunityRepository.findAllOpportunities().size();
        assertEquals(0, opportunityCount);
        var accountCount = accountRepository.findAllAccounts().size();
        assertEquals(0, accountCount);
    }
}