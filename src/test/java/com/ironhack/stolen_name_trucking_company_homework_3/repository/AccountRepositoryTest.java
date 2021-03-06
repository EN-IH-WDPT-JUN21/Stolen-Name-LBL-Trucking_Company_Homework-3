package com.ironhack.stolen_name_trucking_company_homework_3.repository;

import com.ironhack.stolen_name_trucking_company_homework_3.StolenNameTruckingCompanyHomework3Application;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.Account;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.Contact;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.Opportunity;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.SalesRep;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
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
class AccountRepositoryTest {

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

    private List<SalesRep> salesReps;
    private List<Contact> contacts;
    private List<Opportunity> opportunities;
    private List<Account> accounts;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength,
            PhoneNumberContainsLettersException, InvalidCountryException {

        salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep("David Lynch"),
                new SalesRep("Martha Stewart")
        ));

        contacts = contactRepository.saveAll(List.of(
                new Contact("John Doe", "123475357", "alfa@beta.uk", "Ka??asznikow",
                        salesReps.get(0)),
                new Contact("Martha Steward", "123475357", "ms@wp.pl",
                        "My own company", salesReps.get(1)),
                new Contact("George Truman", "123475357", "thisisverylongemail@gmail.com",
                        "Truman Show",salesReps.get(0))

        ));

        opportunities = opportunityRepository.saveAll(List.of(
                new Opportunity(Truck.FLATBED, 10, contacts.get(0), salesReps.get(0)),
                new Opportunity(Truck.BOX, 1150, contacts.get(1), salesReps.get(0)),
                new Opportunity(Truck.HYBRID, 1, contacts.get(2), salesReps.get(1))

        ));

        accounts = accountRepository.saveAll(List.of(
                new Account(Industry.PRODUCE, 50, "London", "UNITED KINGDOM",contacts.get(0),
                        opportunities.get(0))  ,
                new Account(Industry.ECOMMERCE, 500, "Madrid", "SPAIN",contacts.get(1),
                        opportunities.get(1)),
                new Account(Industry.MANUFACTURING, 20, "Paris", "FRANCE",contacts.get(2),
                        opportunities.get(2))
        ));

    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();
        accountRepository.deleteAll();

    }

    @Test
    void findMeanEmployeeCount() {
        var meanEmployeeCount = accountRepository.findMeanEmployeeCount();
        assertEquals(190, meanEmployeeCount.get().doubleValue());
    }

    @Test
    void findMedianEmployeeCountStep1_test(){
        var medianEmployeeCount = accountRepository.findMedianEmployeeCountStep1();
        assertEquals(3, medianEmployeeCount.length);
    }

    @Test
    void findMaxEmployeeCount() {
        var maxEmployeeCount = accountRepository.findMaxEmployeeCount();
        assertEquals(500, maxEmployeeCount.get().intValue());
    }


    @Test
    void findMinEmployeeCount() {
        var minEmployeeCountEmployeeCount = accountRepository.findMinEmployeeCount();
        assertEquals(20, minEmployeeCountEmployeeCount.get().intValue());
    }


    @Test
    void findAllAccounts() {
    }
}