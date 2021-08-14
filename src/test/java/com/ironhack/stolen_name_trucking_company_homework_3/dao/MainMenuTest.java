package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

class MainMenuTest {

    Integer counterStatus;
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
    LeadRepository leadRepository;
    AccountRepository accountRepository;
    ContactRepository contactRepository;
    OpportunityRepository opportunityRepository;
    SalesRepRepository salesRepRepository;


    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {
        counterStatus = ClientInformation.getUniqueID();
        ClientInformation.setUniqueID(0);
        lead1 = new Lead("TestOne", "123546", "test1@test.gmail.com", "TestCompany1");
        lead2 = new Lead("TestTwo", "654987", "test1@test.gmail.com", "TestCompany1");
        lead3 = new Lead("TestThree", "7536836", "test1@test.gmail.com", "TestCompany1");

        MainMenu.theLeads.put(lead1.getId(), lead1);
        MainMenu.theLeads.put(lead2.getId(), lead2);
        MainMenu.theLeads.put(lead3.getId(), lead3);
    }

    @AfterEach
    void tearDown() {
        MainMenu.theLeads.remove(lead1.getId(), lead1);
        MainMenu.theLeads.remove(lead2.getId(), lead2);
        MainMenu.theLeads.remove(lead3.getId(), lead3);
        ClientInformation.setUniqueID(counterStatus);
        MainMenu.theContacts.clear();
        MainMenu.theOpportunities.clear();
    }

   /* @Test
    void testNewLeadPositive() {
        String data = "y \n Nathan \n 0028263 \n 122@gmail.com \n Santander \n"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1
            int hashMapSize = MainMenu.theLeads.size();
            MainMenu test = new MainMenu(); // Creates a sales associate to test method

            Lead theNewLead = test.newLead(); // creates new lead
            //Assertions check Object created correctly and added to hashmap
            Assertions.assertEquals(hashMapSize + 1, MainMenu.theLeads.size()); // Checks that new lead is added to array
            Assertions.assertEquals("NATHAN", MainMenu.theLeads.get(theNewLead.getId()).getName());
            Assertions.assertEquals("SANTANDER", MainMenu.theLeads.get(theNewLead.getId()).getCompanyName());
            Assertions.assertEquals("0028263", MainMenu.theLeads.get(theNewLead.getId()).getPhoneNumber());
            Assertions.assertEquals("122@GMAIL.COM", MainMenu.theLeads.get(theNewLead.getId()).getEmail());
        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }*/

    @Test
    void testConvertLeadThrowsNullPointerException(){
        MainMenu test = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository); // Creates a sales associate to test method

        Assertions.assertThrows(NullPointerException.class, () -> test.convertLead("239832487248"));
        Assertions.assertThrows(NullPointerException.class, () -> test.convertLead("Sausage"));
    }



   /* @Test
    void testConvertLeadPositive() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        //Assertions.assertEquals(0, MainMenu.theOpportunities.size());
        //Assertions.assertEquals(0, MainMenu.theContacts.size());
       // Contact testContact = new Contact(lead1.getName(), lead1.getPhoneNumber(), lead1.getEmail(),
        //        lead1.getCompanyName());
        String data = "y \n" +
                        "box \n" +
                        "20 \n" +
                        "\n" +
                        "\n";
                       // "produce \n" +
                       // "54 \n" +
                        //"London \n" +
                       // "united kingdom \n" +
                       // "\n"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1
            //int oppHashMapSize = MainMenu.theOpportunities.size();
            //int conHashMapSize = MainMenu.theContacts.size();

            MainMenu test = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository); // Creates a sales associate to test method
            Opportunity newOpp = test.convertLead(lead1.getId());
            //Assertions check Object created correctly and added to hashmap
            //Assertions.assertEquals(oppHashMapSize + 1, MainMenu.theOpportunities.size()); // Checks it's added to HashMap
            Assertions.assertEquals(Truck.BOX, MainMenu.theOpportunities.get(newOpp.getId()).getProduct());
            Assertions.assertEquals(20, MainMenu.theOpportunities.get(newOpp.getId()).getQuantity());
            //Assertions.assertEquals(conHashMapSize + 1, MainMenu.theContacts.size()); // Checks that new lead is added to array
        } finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }


    @Test
    void TestCreateAccountPositive() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {
        String data = "Produce\n 200 \n Stourbridge \n United Kingdom \n"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in

        Contact testContact = new Contact("TestContact", "1234567", "email@email.com",
                "TestContactCompany");
        Opportunity testOpp = new Opportunity(Truck.HYBRID, 30, testContact);

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1
            int accHashMapSize = MainMenu.theAccounts.size();
            MainMenu test = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository); // Creates a sales associate to test method

            Account testAccount = test.createAccount(testOpp);
            //Assertions check Object created correctly and added to hashmap
            Assertions.assertEquals(accHashMapSize + 1, MainMenu.theAccounts.size()); // Checks it's added to HashMap
            Assertions.assertEquals(Industry.PRODUCE, MainMenu.theAccounts.get(testAccount.getId()).getIndustry());
            Assertions.assertEquals(200, MainMenu.theAccounts.get(testAccount.getId()).getEmployeeCount());
            Assertions.assertEquals("STOURBRIDGE", MainMenu.theAccounts.get(testAccount.getId()).getCity());
            Assertions.assertEquals("UNITED KINGDOM", MainMenu.theAccounts.get(testAccount.getId()).getCountry());
        }finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }


    @Test
    void TestQuitWorksInOS() {
        String data = "\n quit"; // Used to simulate user input
        InputStream stdin = System.in; // Used to store default System.in
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes())); // Sets System.In to test1

            MainMenu test = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository); // Creates a sales associate to test method

            Assertions.assertThrows(RuntimeException.class, test::OS);
        }finally {
            System.setIn(stdin); /// Resets System.in to default state
        }
    }*/

    @Test
    void showLeads() {

        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        MainMenu test = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository);
        test.showLeads();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        //We also noticed it works slightly differently for Windows compared to other operating systems
        if(os.contains("win")){
            expectedOutput  = colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Leads: 3" + colorMain+ " ════════════════╗"  +
                    reset + "\r\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold+"Name                                        " + colorMain +"║" +
                    "\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "1          " + colorMain+ "║ " + colorTable + "TESTONE                                     "+ colorMain + "║" +
                    reset + "\n" + colorMain + "║ " + colorTable + "2          " + colorMain+ "║ " + colorTable + "TESTTWO                                     "+ colorMain + "║" +
                    reset + "\n" + colorMain + "║ " + colorTable + "3          " + colorMain+ "║ " + colorTable + "TESTTHREE                                   "+ colorMain + "║" + reset + "\n";
        } else {
            expectedOutput  = colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Leads: 3" + colorMain+ " ════════════════╗"  +
                    reset + "\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold+"Name                                        " + colorMain +"║" +
                    "\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "1          " + colorMain+ "║ " + colorTable + "TESTONE                                     "+ colorMain + "║" +
                    reset + "\n" + colorMain + "║ " + colorTable + "2          " + colorMain+ "║ " + colorTable + "TESTTWO                                     "+ colorMain + "║" +
                    reset + "\n" + colorMain + "║ " + colorTable + "3          " + colorMain+ "║ " + colorTable + "TESTTHREE                                   "+ colorMain + "║" + reset + "\n";
        }

        Assertions.assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    void showContacts() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        Contact testContact = new Contact("TESTCONTACT", "1234567", "EMAIL@EMAIL.COM", "TESTCOMPANY");
        MainMenu.theContacts.put(testContact.getId(), testContact);
        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        MainMenu test = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository);
        test.showContacts();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        if(os.contains("win")){
            expectedOutput  = colorMain + "\n╔════════════╦════════ " + colorMainBold + "Total Number Of Contacts: 1" + colorMain+ " ════════╦══════════════════════════════════════════╗"  +
                    reset + "\r\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold+"Name                                        " + colorMain + "║ " + colorHeadlineBold+"Company name                             " + colorMain +"║" +
                    "\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╬══════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "4          " + colorMain+ "║ " + colorTable + "TESTCONTACT                                 "+ colorMain+ "║ " + colorTable + "TESTCOMPANY                              " + colorMain + "║" + reset + "\n";
        } else{
            expectedOutput  = colorMain + "\n╔════════════╦════════ " + colorMainBold + "Total Number Of Contacts: 1" + colorMain+ " ════════╦══════════════════════════════════════════╗"  +
                    reset + "\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold+"Name                                        " + colorMain + "║ " + colorHeadlineBold+"Company name                             " + colorMain +"║" +
                    "\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╬══════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "4          " + colorMain+ "║ " + colorTable + "TESTCONTACT                                 "+ colorMain+ "║ " + colorTable + "TESTCOMPANY                              " + colorMain + "║" + reset + "\n";
        }

        Assertions.assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void showOpportunities() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        Contact testContact = new Contact("TESTCONTACT", "1234567", "EMAIL@EMAIL.COM", "TESTCOMPANY");
        Opportunity testOpp = new Opportunity(Truck.HYBRID, 30, testContact);
        MainMenu.theOpportunities.put(testOpp.getId(), testOpp);
        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        MainMenu test = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository);
        test.showOpportunities();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        if(os.contains("win")){
            expectedOutput  = colorMain + "\n╔════════════╦═════ " + colorMainBold + "Total Number Of Opportunities: 1" + colorMain+ " ══════╦══════════════════════════════════════════╗"  +
                    reset + "\r\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold+"Contract status   " + colorMain + "║ " + colorHeadlineBold + "Product    " + colorMain + "║ " + colorHeadlineBold + "Quantity   " + colorMain + "║ " + colorHeadlineBold+"Decision maker                           " + colorMain +"║" +
                    "\n" + colorMain + "╠════════════╬═══════════════════╬════════════╬════════════╬══════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "5          " + colorMain + "║ " + colorTable+"OPEN              "+ colorMain + "║ " + colorTable+"HYBRID     "+ colorMain + "║ " + colorTable+"30         "+colorMain + "║ " + colorTable + "TESTCONTACT                              " + colorMain + "║" + reset + "\n";
        } else {
            expectedOutput  = colorMain + "\n╔════════════╦═════ " + colorMainBold + "Total Number Of Opportunities: 1" + colorMain+ " ══════╦══════════════════════════════════════════╗"  +
                    reset + "\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold+"Contract status   " + colorMain + "║ " + colorHeadlineBold + "Product    " + colorMain + "║ " + colorHeadlineBold + "Quantity   " + colorMain + "║ " + colorHeadlineBold+"Decision maker                           " + colorMain +"║" +
                    "\n" + colorMain + "╠════════════╬═══════════════════╬════════════╬════════════╬══════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "5          " + colorMain + "║ " + colorTable+"OPEN              "+ colorMain + "║ " + colorTable+"HYBRID     "+ colorMain + "║ " + colorTable+"30         "+colorMain + "║ " + colorTable + "TESTCONTACT                              " + colorMain + "║" + reset + "\n";
        }

        Assertions.assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    void showAccounts() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        Contact testContact = new Contact("TESTCONTACT", "1234567", "EMAIL@EMAIL.COM", "TESTCOMPANY");
        Opportunity testOpp = new Opportunity(Truck.HYBRID, 30, testContact);
        Account testAcc = new Account(testContact, testOpp);
        MainMenu.theAccounts.put(testAcc.getId(), testAcc);
        // After this all System.out.println() statements will come to outContent stream.
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        MainMenu test = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository);
        test.showAccounts();

        //Now we have to validate the output. It has to exactly mimic the output we created.
        if(os.contains("win")){
            expectedOutput  = colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Accounts: 1" + colorMain+ " ═════════════╗"  +
                    reset + "\r\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold+"Company name                                " + colorMain +"║" +
                    "\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "6          " + colorMain+ "║ " + colorTable + "TESTCOMPANY                                 "+ colorMain + "║" + reset + "\n";
        } else {
            expectedOutput  = colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Accounts: 1" + colorMain+ " ═════════════╗"  +
                    reset + "\n" + colorMain + "║ " + colorHeadlineBold + "ID         " + colorMain + "║ " + colorHeadlineBold+"Company name                                " + colorMain +"║" +
                    "\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╣" +
                    reset + "\n" + colorMain + "║ " + colorTable + "6          " + colorMain+ "║ " + colorTable + "TESTCOMPANY                                 "+ colorMain + "║" + reset + "\n";
        }

        Assertions.assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    void lookUpLeadId_FindLead() {
        Assertions.assertEquals("TestOne", MainMenu.theLeads.get(lead1.getId()).getName());
    }


    @Test
    void closeLost() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {

        String data = "y";
        InputStream stdin = System.in;

        Contact testContact = new Contact("TestContact", "1234567", "email@email.com",
                "TestContactCompany");
        Opportunity testOpp = new Opportunity(Truck.HYBRID, 30, testContact);
        MainMenu.theOpportunities.put(testOpp.getId(), testOpp);

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            MainMenu test = new MainMenu();
            test.closeLost(testOpp.getId());
            Assertions.assertEquals(Status.CLOSED_LOST, MainMenu.theOpportunities.get(testOpp.getId()).getStatus());

        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void closeWon() throws ExceedsMaxLength, NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException {
        String data = "y";
        InputStream stdin = System.in;
        MainMenu test = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository);
        Contact testContact = new Contact("TestContact", "1234567", "email@email.com",
                "TestContactCompany");
        Opportunity testOpp = new Opportunity(Truck.HYBRID, 30, testContact);
        MainMenu.theOpportunities.put(testOpp.getId(), testOpp);

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            test.closeWon(testOpp.getId());
            Assertions.assertEquals(Status.CLOSED_WON, MainMenu.theOpportunities.get(testOpp.getId()).getStatus());

        } finally {
            System.setIn(stdin);
        }
    }
}

