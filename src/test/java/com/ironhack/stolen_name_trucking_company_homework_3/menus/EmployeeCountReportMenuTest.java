package com.ironhack.stolen_name_trucking_company_homework_3.menus;

import com.ironhack.stolen_name_trucking_company_homework_3.StolenNameTruckingCompanyHomework3Application;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.*;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeCountReportMenuTest {

    @MockBean
    private StolenNameTruckingCompanyHomework3Application stolenNameTruckingCompanyHomework3Application;

    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeCountReportMenu test;

    List<SalesRep> salesReps;
    List<Lead> leads;
    List<Contact> contacts;
    List<Opportunity> opportunities;
    List<Account> accounts;
    String expectedOutput;

    @BeforeEach
    public void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException, InvalidCountryException {
        salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep("David Brown"),
                new SalesRep("Marta Stewart")
        ));

        leads = leadRepository.saveAll(List.of(
                new Lead("Roger Federer", "123456789", "feds@gmail.co", "Wings of Freedom", salesReps.get(0)),
                new Lead("Axel Rose", "980651164", "ax@gmail.com", "Roses", salesReps.get(0)),
                new Lead("Lionel Messi", "563782789", "messi@yahoo.com", "Still Not Retired", salesReps.get(1))
        ));

        contacts = contactRepository.saveAll(List.of(
                new Contact("John Dowe", "123475357", "alfa@beta.uk", "Kałasznikow", salesReps.get(0)),
                new Contact("Marta Steward", "123475357", "ms@wp.pl", "My own company", salesReps.get(1)),
                new Contact("George Trumane", "123475357", "thisisverylongemail@gmail.com", "Truman Show", salesReps.get(0))

        ));

        opportunities = opportunityRepository.saveAll(List.of(
                new Opportunity(Truck.FLATBED, 10, contacts.get(0), salesReps.get(0)),
                new Opportunity(Truck.BOX, 1150, contacts.get(1), salesReps.get(0)),
                new Opportunity(Truck.HYBRID, 1, contacts.get(2), salesReps.get(1))

        ));

        accounts = accountRepository.saveAll(List.of(
                new Account(Industry.PRODUCE, 50, "London", "UNITED KINGDOM", contacts.get(0), opportunities.get(0)),
                new Account(Industry.ECOMMERCE, 500, "Madrid", "SPAIN", contacts.get(1), opportunities.get(1)),
                new Account(Industry.MANUFACTURING, 20, "Paris", "FRANCE", contacts.get(2), opportunities.get(2))
        ));

        contacts.get(0).setAccount(accounts.get(0));
        contactRepository.save(contacts.get(0));
        contacts.get(1).setAccount(accounts.get(1));
        contactRepository.save(contacts.get(1));
        contacts.get(2).setAccount(accounts.get(2));
        contactRepository.save(contacts.get(2));

        opportunities.get(0).setAccount(accounts.get(0));
        opportunityRepository.save(opportunities.get(0));
        opportunities.get(1).setAccount(accounts.get(1));
        opportunityRepository.save(opportunities.get(1));
        opportunities.get(2).setAccount(accounts.get(2));
        opportunityRepository.save(opportunities.get(2));
    }

    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void employeeCountReportMenu_GetMean() throws NoSuchValueException, AWTException {
        String data = "mean employeecount \n\n";
        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            test.employeeCountReportMenu();
            assertTrue(outContent.toString().contains("Average number of employees is:"));
            assertTrue(outContent.toString().contains("Report"));

        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void employeeCountReportMenu_GetMedian() throws NoSuchValueException, AWTException {
        String data = "median employeecount \n\n";
        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            test.employeeCountReportMenu();
            assertTrue(outContent.toString().contains("Median number of employees is:"));
            assertTrue(outContent.toString().contains("Report"));

        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void employeeCountReportMenu_GetMax() throws NoSuchValueException, AWTException {
        String data = "max employeecount \n\n";
        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            test.employeeCountReportMenu();
            assertTrue(outContent.toString().contains("Maximum number of employees is:"));
            assertTrue(outContent.toString().contains("Report"));

        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void employeeCountReportMenu_GetMin() throws NoSuchValueException, AWTException {
        String data = "min employeecount \n\n";
        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            test.employeeCountReportMenu();
            assertTrue(outContent.toString().contains("Minimum number of employees is:"));
            assertTrue(outContent.toString().contains("Report"));

        } finally {
            System.setIn(stdin);
        }
    }

}