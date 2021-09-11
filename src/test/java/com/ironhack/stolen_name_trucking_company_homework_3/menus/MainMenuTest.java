package com.ironhack.stolen_name_trucking_company_homework_3.menus;

import com.ironhack.stolen_name_trucking_company_homework_3.StolenNameTruckingCompanyHomework3Application;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.*;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
class MainMenuTest {

    @MockBean
    private StolenNameTruckingCompanyHomework3Application application;

    @Autowired
    private MainMenu test;

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

    String colorMain = "\033[0;33m";
    String colorMainBold = "\033[1;37m";
    String colorHeadline = "\033[0;34m";
    String colorHeadlineBold = "\033[1;34m";
    String colorTable = "\033[1;32m";
    String reset = "\u001B[0m";
    String os = System.getProperty("os.name").toLowerCase();
    String expectedOutput;
    List<SalesRep> salesReps;
    List<Lead> leads;
    List<Contact> contacts;
    List<Opportunity> opportunities;
    List<Account> accounts;


    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength, InvalidCountryException {

        salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep("David Lynch"),
                new SalesRep("Martha Stewart")
        ));

       leads = leadRepository.saveAll(List.of(
                new Lead("Sebastian Marek Labedz", "123456789", "labedzsebastian@gmail.co", "Wings of Freedom", salesReps.get(0)),
                new Lead("Lee Dawson", "980651164", "ld@gmail.com", "LeeD", salesReps.get(0)),
                new Lead("Natalia Shilyaeva", "563782789", "nattyshil@yahoo.com", "Nathy From Wonderland", salesReps.get(1))
        ));

        contacts = contactRepository.saveAll(List.of(
                new Contact("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow", salesReps.get(0)),
                new Contact("Martha Steward", "123475357", "ms@wp.pl", "My own company", salesReps.get(1)),
                new Contact("George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show", salesReps.get(0))

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
    void testNewLeadPositive() {
        String data = "y \n Nathan \n 0028263 \n 122@gmail.com \n Santander \n " + salesReps.get(0).getId() + "\n"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test
            Lead theNewLead = test.newLead(); // creates new lead
            //check Object created correctly and added to repository
            assertEquals("NATHAN", theNewLead.getName());
            assertEquals("SANTANDER", theNewLead.getCompanyName());
            assertEquals("0028263", theNewLead.getPhoneNumber());
            assertEquals("122@GMAIL.COM", theNewLead.getEmail());
        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }

    @Test
    void testConvertLeadThrowsNullPointerException() {
        assertThrows(NoSuchElementException.class, () -> test.convertLead("239832487248"));
        assertThrows(NumberFormatException.class, () -> test.convertLead("Sausage"));
    }


    @Test
    void testConvertLeadPositive() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {

        String data = "y \n box \n 20 \n \n \n y \n \n \n \n";
        InputStream stdin = System.in; // Used to store default System.in

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1

            var oppRepoSizeBefore = opportunityRepository.findAllOpportunities().size();

            Opportunity newOpp = test.convertLead(leads.get(0).getId().toString());

            var oppRepoSizeAfter = opportunityRepository.findAllOpportunities().size();
            assertEquals(Truck.BOX, newOpp.getProduct());
            assertEquals(20, newOpp.getQuantity());
            assertEquals(oppRepoSizeAfter, oppRepoSizeBefore + 1);

        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }


    @Test
    void createAccount_PositiveTest() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {
        String data = "y \n Produce\n 200 \n Stourbridge \n SPAIN\n \n \n \n"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1

            Account createdAccount = test.createAccount(opportunities.get(0));

            assertEquals("Kałasznikow", createdAccount.getCompanyName());
        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }

    @Test
    void createAccount_AddToExistingAccountTest() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {
        String data = "n \n" + accounts.get(0).getId() +"\n"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in
        SalesRep testRep = new SalesRep("Test Rep");
        salesRepRepository.save(testRep);
        Contact testContact = new Contact("Test Contact", "98765432", "test@contact.co.uk", "Test Company", testRep);
        contactRepository.save(testContact);
        Opportunity testOpp = new Opportunity(Truck.HYBRID, 50, contactRepository.findById(testContact.getId()).get(),
                salesRepRepository.findById(testRep.getId()).get());
        opportunityRepository.save(testOpp);

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1
            var oppSizeBefore = accountRepository.findById(accounts.get(0).getId()).get().getOpportunityList().size();

            Account existingAccount = test.createAccount(testOpp);

            var oppSizeAfter = accountRepository.findById(accounts.get(0).getId()).get().getOpportunityList().size();

            assertEquals(existingAccount, testOpp.getAccount());
            assertEquals(oppSizeAfter, oppSizeBefore + 1);
        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }



    @Test
    void showSalesReps(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.showSalesReps();

        assertTrue(outContent.toString().contains("DAVID LYNCH"));
        assertTrue(outContent.toString().contains("Total Number Of Sales Representatives:"));
    }


    @Test
    void showLeads() {

        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.showLeads();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        assertTrue(outContent.toString().contains("LEE DAWSON"));
        assertTrue(outContent.toString().contains("Total Number Of Leads"));
    }


    @Test
    void showContacts() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.showContacts();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        assertTrue(outContent.toString().contains("JOHN DOE"));
        assertTrue(outContent.toString().contains("Total Number Of Contacts"));
    }

    //
    @Test
    void showOpportunities() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        leadRepository.deleteAll();
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();
        accountRepository.deleteAll();

        Contact contact = new Contact("TESTCONTACT", "1234567", "EMAIL@EMAIL.COM", "TESTCOMPANY");
        contactRepository.save(contact);
        Opportunity newOpp = new Opportunity(Truck.HYBRID, 30, contact);
        opportunityRepository.save(newOpp);

        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        test.showOpportunities();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        assertTrue(outContent.toString().contains("HYBRID"));
        assertTrue(outContent.toString().contains("Total Number Of Opportunities"));
    }

    @Test
    void showAccounts() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        // Clears the repositories for easier testing
        leadRepository.deleteAll();
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();

        // This Block looks messy but sets up the Repositories for testing
        Contact testContact = new Contact("TESTCONTACT", "1234567", "EMAIL@EMAIL.COM", "TESTCOMPANY");
        contactRepository.save(testContact);
        Opportunity testOpp = new Opportunity(Truck.HYBRID, 30, testContact);
        opportunityRepository.save(testOpp);
        Account testAcc = new Account(testContact, testOpp);
        accountRepository.save(testAcc);
        testOpp.getDecisionMaker().setAccount(testAcc);
        contactRepository.save(testOpp.getDecisionMaker());
        accountRepository.save(testAcc);


        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.showAccounts();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        assertTrue(outContent.toString().contains("TESTCOMPANY"));
        assertTrue(outContent.toString().contains("Total Number Of Accounts"));
    }

    @Test
    void lookUpLeadId_FindLead() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        SalesRep testRep = new SalesRep("Test Rep");
        salesRepRepository.save(testRep);
        Lead testLead = new Lead("Test Lead","123456789", "test@testlead.com", "Test Company", testRep);
        leadRepository.save(testLead);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.lookUpLeadId(testLead.getId());

        assertTrue(outContent.toString().contains("TEST LEAD"));
        assertTrue(outContent.toString().contains("Lead details"));
    }

    @Test
    void lookUpOppId_FindOpp() throws ExceedsMaxLength {
        Contact testContact = new Contact("TESTCONTACT", "1234567", "EMAIL@EMAIL.COM", "TESTCOMPANY");
        contactRepository.save(testContact);
        Opportunity testOpp = new Opportunity(Truck.HYBRID, 30, testContact);
        opportunityRepository.save(testOpp);
        Account testAcc = new Account(testContact, testOpp);
        accountRepository.save(testAcc);
        testOpp.getDecisionMaker().setAccount(testAcc);
        contactRepository.save(testOpp.getDecisionMaker());
        accountRepository.save(testAcc);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.lookUpOppId(String.valueOf(testOpp.getId()));

        assertTrue(outContent.toString().contains("HYBRID"));
        assertTrue(outContent.toString().contains("Contract details"));
        assertTrue(outContent.toString().contains("Decision maker details"));
    }


    @Test
    void closeLost() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {

        String data = "y";
        InputStream stdin = System.in;

        try {
            assertEquals(Status.OPEN, opportunityRepository.findById(opportunities.get(0).getId()).get().getStatus());
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            test.closeLost(opportunities.get(0).getId().toString());
            assertEquals(Status.CLOSED_LOST, opportunityRepository.findById(opportunities.get(0).getId()).get().getStatus());
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void closeWon() throws ExceedsMaxLength, NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException {
        String data = "y";
        InputStream stdin = System.in;
        try {
            assertEquals(Status.OPEN, opportunityRepository.findById(opportunities.get(1).getId()).get().getStatus());
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            test.closeWon(opportunities.get(1).getId().toString());
            assertEquals(Status.CLOSED_WON, opportunityRepository.findById(opportunities.get(1).getId()).get().getStatus());

        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void TestCreateSalesrepPositive() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {
        String data = "y\n Diego Maradona \n"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1

            SalesRep createdSalesrep = test.newSalesRep();

            assertEquals("DIEGO MARADONA", createdSalesrep.getRepName());
        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }

}
