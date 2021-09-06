package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.StolenNameTruckingCompanyHomework3Application;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import com.ironhack.stolen_name_trucking_company_homework_3.menus.MainMenu;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class MainMenuTest {

    @Autowired
    private MainMenu test;

    @MockBean
    private StolenNameTruckingCompanyHomework3Application application;

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

    List<Lead> leads;
    List<SalesRep> salesReps;

    Lead lead1;
    Lead lead2;
    Lead lead3;
    String colorMain = "\033[0;33m";
    String colorMainBold = "\033[1;37m";
    String colorHeadline = "\033[0;34m";
    String colorHeadlineBold = "\033[1;34m";
    String colorTable = "\033[1;32m";
    String reset = "\u001B[0m";
    String os = System.getProperty("os.name").toLowerCase();
    String expectedOutput;


    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength, InvalidCountryException {

        List<SalesRep> salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep("David Lynch"),
                new SalesRep("Martha Stewart")
        ));

        leadRepository.saveAll(List.of(
                new Lead("Sebastian Marek Labedz", "123456789", "labedzsebastian@gmail.co", "Wings of Freedom", salesReps.get(0)),
                new Lead("Lee Dawson", "980651164", "ld@gmail.com", "LeeD", salesReps.get(0)),
                new Lead("Natalia Shilyaeva", "563782789", "nattyshil@yahoo.com", "Nathy From Wonderland", salesReps.get(1))
        ));

        List<Contact> contacts = contactRepository.saveAll(List.of(
                new Contact("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow", salesReps.get(0)),
                new Contact("Martha Steward", "123475357", "ms@wp.pl", "My own company", salesReps.get(1)),
                new Contact("George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show", salesReps.get(0))

        ));

        List<Opportunity> opportunities = opportunityRepository.saveAll(List.of(
                new Opportunity(Truck.FLATBED, 10, contacts.get(0), salesReps.get(0)),
                new Opportunity(Truck.BOX, 1150, contacts.get(1), salesReps.get(0)),
                new Opportunity(Truck.HYBRID, 1, contacts.get(2), salesReps.get(1))

        ));

        List<Account> accounts = accountRepository.saveAll(List.of(
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
    }

    @Test
    void testNewLeadPositive() {
        String data = "y \n Nathan \n 0028263 \n 122@gmail.com \n Santander"; // Used to simulate user input
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
        assertThrows(NullPointerException.class, () -> test.convertLead("239832487248"));
        assertThrows(NullPointerException.class, () -> test.convertLead("Sausage"));
    }


    @Test
    void testConvertLeadPositive() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {

        String data = "y \n" +
                "box \n" +
                "20 \n" +
                "\n" +
                "\n";
        InputStream stdin = System.in; // Used to store default System.in

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1

            var oppRepoSizeBefore = opportunityRepository.findAllOpportunities().size();

            Opportunity newOpp = test.convertLead("1");

            var oppRepoSizeAfter = opportunityRepository.findAllOpportunities().size();
            assertEquals(Truck.BOX, newOpp.getProduct());
            assertEquals(20, newOpp.getQuantity());
            assertEquals(oppRepoSizeAfter, oppRepoSizeBefore + 1);

        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }


    @Test
    void TestCreateAccountPositive() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {
        String data = "Produce\n 200 \n Stourbridge \n SPAIN\n \n \n \n"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1

            Account createdAccount = test.createAccount(opportunityRepository.findById(1L).get());

            assertEquals("Kałasznikow", createdAccount.getCompanyName());
        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }

    //
//
//    @Test
//    void TestQuitWorksInOS() {
//        String data = "\n quit"; // Used to simulate user input
//        InputStream stdin = System.in; // Used to store default System.in
//        try {
//            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1
//
//            assertThrows(RuntimeException.class, test::OS);
//        }finally {
//            System.setIn(stdin); /// Resets System.in to default state
//        }
//    }
//
    @Test
    void showLeads() {

        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.showLeads();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        //We also noticed it works slightly differently for Windows compared to other operating systems
        if (os.contains("win")) {
            expectedOutput = colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Leads: 3" + colorMain + " ════════════════╗" +
                    reset + "\r\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold + "Name                                        " + colorMain + "║" +
                    "\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "1          " + colorMain + "║ " + colorTable + "SEBASTIAN MAREK LABEDZ                      " + colorMain + "║" +
                    reset + "\n" + colorMain + "║ " + colorTable + "2          " + colorMain + "║ " + colorTable + "LEE DAWSON                                  " + colorMain + "║" +
                    reset + "\n" + colorMain + "║ " + colorTable + "3          " + colorMain + "║ " + colorTable + "NATALIA SHILYAEVA                           " + colorMain + "║" + reset + "\n";
        } else {
            expectedOutput = colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Leads: 3" + colorMain + " ════════════════╗" +
                    reset + "\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold + "Name                                        " + colorMain + "║" +
                    "\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "1          " + colorMain + "║ " + colorTable + "SEBASTIAN MAREK LABEDZ                      " + colorMain + "║" +
                    reset + "\n" + colorMain + "║ " + colorTable + "2          " + colorMain + "║ " + colorTable + "LEE DAWSON                                  " + colorMain + "║" +
                    reset + "\n" + colorMain + "║ " + colorTable + "3          " + colorMain + "║ " + colorTable + "NATALIA SHILYAEVA                           " + colorMain + "║" + reset + "\n";
        }

        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    void showContacts() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        test.showContacts();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        if (os.contains("win")) {
            expectedOutput = colorMain + "\n╔════════════╦════════ " + colorMainBold + "Total Number Of Contacts: 3" + colorMain + " ════════╦══════════════════════════════════════════╗" +
                    reset + "\r\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold + "Name                                        " + colorMain + "║ " + colorHeadlineBold + "Company name                             " + colorMain + "║" +
                    "\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╬══════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "1          " + colorMain + "║ " + colorTable + "JOHN DOE                                    " + colorMain + "║ " + colorTable + "KAŁASZNIKOW                              " + colorMain + "║" +
                    reset + "\n" + colorMain + "║ " + colorTable + "2          " + colorMain + "║ " + colorTable + "MARTHA STEWARD                              " + colorMain + "║ " + colorTable + "MY OWN COMPANY                           " + colorMain + "║" +
                    reset + "\n" + colorMain + "║ " + colorTable + "3          " + colorMain + "║ " + colorTable + "GEORGE TRUMAN                               " + colorMain + "║ " + colorTable + "TRUMAN SHOW                              " + colorMain + "║" + reset + "\n";
        } else {
            expectedOutput = colorMain + "\n╔════════════╦════════ " + colorMainBold + "Total Number Of Contacts: 3" + colorMain + " ════════╦══════════════════════════════════════════╗" +
                    reset + "\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold + "Name                                        " + colorMain + "║ " + colorHeadlineBold + "Company name                             " + colorMain + "║" +
                    "\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╬══════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "1          " + colorMain + "║ " + colorTable + "JOHN DOE                                    " + colorMain + "║ " + colorTable + "KAŁASZNIKOW                              " + colorMain + "║" +
                    reset + "\n" + colorMain + "║ " + colorTable + "2          " + colorMain + "║ " + colorTable + "MARTHA STEWARD                              " + colorMain + "║ " + colorTable + "MY OWN COMPANY                           " + colorMain + "║" +
                    reset + "\n" + colorMain + "║ " + colorTable + "3          " + colorMain + "║ " + colorTable + "GEORGE TRUMAN                               " + colorMain + "║ " + colorTable + "TRUMAN SHOW                              " + colorMain + "║" + reset + "\n";
        }

        assertEquals(expectedOutput, outContent.toString());
    }

    //
    @Test
    void showOpportunities() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        leadRepository.deleteAll();
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();

        contactRepository.save(new Contact("TESTCONTACT", "1234567", "EMAIL@EMAIL.COM", "TESTCOMPANY"));
        Optional<Contact> contact = contactRepository.findById(4L);
        opportunityRepository.save(new Opportunity(Truck.HYBRID, 30, contact.get()));

        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        test.showOpportunities();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        if (os.contains("win")) {
            expectedOutput = colorMain + "\n╔════════════╦═════ " + colorMainBold + "Total Number Of Opportunities: 1" + colorMain + " ══════╦══════════════════════════════════════════╗" +
                    reset + "\r\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold + "Contract status   " + colorMain + "║ " + colorHeadlineBold + "Product    " + colorMain + "║ " + colorHeadlineBold + "Quantity   " + colorMain + "║ " + colorHeadlineBold + "Decision maker                           " + colorMain + "║" +
                    "\n" + colorMain + "╠════════════╬═══════════════════╬════════════╬════════════╬══════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "4          " + colorMain + "║ " + colorTable + "OPEN              " + colorMain + "║ " + colorTable + "HYBRID     " + colorMain + "║ " + colorTable + "30         " + colorMain + "║ " + colorTable + "TESTCONTACT                              " + colorMain + "║" + reset + "\n";
        } else {
            expectedOutput = colorMain + "\n╔════════════╦═════ " + colorMainBold + "Total Number Of Opportunities: 1" + colorMain + " ══════╦══════════════════════════════════════════╗" +
                    reset + "\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold + "Contract status   " + colorMain + "║ " + colorHeadlineBold + "Product    " + colorMain + "║ " + colorHeadlineBold + "Quantity   " + colorMain + "║ " + colorHeadlineBold + "Decision maker                           " + colorMain + "║" +
                    "\n" + colorMain + "╠════════════╬═══════════════════╬════════════╬════════════╬══════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "4          " + colorMain + "║ " + colorTable + "OPEN              " + colorMain + "║ " + colorTable + "HYBRID     " + colorMain + "║ " + colorTable + "30         " + colorMain + "║ " + colorTable + "TESTCONTACT                              " + colorMain + "║" + reset + "\n";
        }

        assertEquals(expectedOutput, outContent.toString());

    }

//    @Test
//    void showAccounts() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
//        Contact testContact = new Contact("TESTCONTACT", "1234567", "EMAIL@EMAIL.COM", "TESTCOMPANY");
//        Opportunity testOpp = new Opportunity(Truck.HYBRID, 30, testContact);
//        Account testAcc = new Account(testContact, testOpp);
//        MainMenu.theAccounts.put(testAcc.getId(), testAcc);
//        // After this all System.out.println() statements will come to outContent stream.
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        MainMenu test = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository);
//        test.showAccounts();
//
//        //Now we have to validate the output. It has to exactly mimic the output we created.
//        if(os.contains("win")){
//            expectedOutput  = colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Accounts: 1" + colorMain+ " ═════════════╗"  +
//                    reset + "\r\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold+"Company name                                " + colorMain +"║" +
//                    "\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╣" +
//                    reset + "\n" + colorMain + "║ " + colorTable + "6          " + colorMain+ "║ " + colorTable + "TESTCOMPANY                                 "+ colorMain + "║" + reset + "\n";
//        } else {
//            expectedOutput  = colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Accounts: 1" + colorMain+ " ═════════════╗"  +
//                    reset + "\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold+"Company name                                " + colorMain +"║" +
//                    "\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╣" +
//                    reset + "\n" + colorMain + "║ " + colorTable + "6          " + colorMain+ "║ " + colorTable + "TESTCOMPANY                                 "+ colorMain + "║" + reset + "\n";
//        }
//
//        assertEquals(expectedOutput, outContent.toString());
//    }
//
//
//    @Test
//    void lookUpLeadId_FindLead() {
//        assertEquals("TestOne", MainMenu.theLeads.get(lead1.getId()).getName());
//    }
//
//
//    @Test
//    void closeLost() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {
//
//        String data = "y";
//        InputStream stdin = System.in;
//
//        Contact testContact = new Contact("TestContact", "1234567", "email@email.com",
//                "TestContactCompany");
//        Opportunity testOpp = new Opportunity(Truck.HYBRID, 30, testContact);
//        MainMenu.theOpportunities.put(testOpp.getId(), testOpp);
//
//        try {
//            System.setIn(new ByteArrayInputStream(data.getBytes()));
//            MainMenu test = new MainMenu();
//            test.closeLost(testOpp.getId());
//            assertEquals(Status.CLOSED_LOST, MainMenu.theOpportunities.get(testOpp.getId()).getStatus());
//
//        } finally {
//            System.setIn(stdin);
//        }
//    }
//
//    @Test
//    void closeWon() throws ExceedsMaxLength, NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException {
//        String data = "y";
//        InputStream stdin = System.in;
//        MainMenu test = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository);
//        Contact testContact = new Contact("TestContact", "1234567", "email@email.com",
//                "TestContactCompany");
//        Opportunity testOpp = new Opportunity(Truck.HYBRID, 30, testContact);
//        MainMenu.theOpportunities.put(testOpp.getId(), testOpp);
//
//        try {
//            System.setIn(new ByteArrayInputStream(data.getBytes()));
//            test.closeWon(testOpp.getId());
//            assertEquals(Status.CLOSED_WON, MainMenu.theOpportunities.get(testOpp.getId()).getStatus());
//
//        } finally {
//            System.setIn(stdin);
//        }
//    }
}
