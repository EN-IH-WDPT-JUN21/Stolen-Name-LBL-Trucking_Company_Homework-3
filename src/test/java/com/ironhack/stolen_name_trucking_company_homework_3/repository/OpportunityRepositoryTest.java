package com.ironhack.stolen_name_trucking_company_homework_3.repository;


import com.ironhack.stolen_name_trucking_company_homework_3.dao.Account;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.Contact;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.Opportunity;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.SalesRep;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
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

    @Autowired
    private AccountRepository accountRepository;

    private List<SalesRep> salesReps;
    private List<Contact> contacts;
    private List<Opportunity> opportunities;
    private List<Account> accounts;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException, InvalidCountryException {

        salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep("David Lynch"),
                new SalesRep("Martha Stewart")
        ));

        contacts = contactRepository.saveAll(List.of(
                new Contact("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow", salesReps.get(0)),
                new Contact("Martha Steward", "123475357", "ms@wp.pl", "My own company", salesReps.get(1)),
                new Contact("George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show",salesReps.get(0))

        ));

        opportunities = opportunityRepository.saveAll(List.of(
                new Opportunity(Truck.FLATBED, 10, contacts.get(0), salesReps.get(0)),
                new Opportunity(Truck.BOX, 1150, contacts.get(1), salesReps.get(0)),
                new Opportunity(Truck.HYBRID, 1, contacts.get(2), salesReps.get(1))

        ));

        accounts = accountRepository.saveAll(List.of(
                new Account(Industry.PRODUCE, 50, "London", "UNITED KINGDOM",contacts.get(0), opportunities.get(0))  ,
                new Account(Industry.ECOMMERCE, 500, "Madrid", "SPAIN",contacts.get(1), opportunities.get(1)),
                new Account(Industry.MANUFACTURING, 20, "Paris", "FRANCE",contacts.get(2), opportunities.get(2))
        ));





        opportunities.get(0).setAccount(accounts.get(0));
        opportunityRepository.save(opportunities.get(0));
        opportunities.get(1).setAccount(accounts.get(1));
        opportunityRepository.save(opportunities.get(1));
        opportunities.get(2).setAccount(accounts.get(2));
        opportunityRepository.save(opportunities.get(2));






    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        accountRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();

    }

    @Test
    void findCountOpportunitySalesRepIdGroup_Test() {
        var leadByRep = opportunityRepository.findCountOpportunityByRepName();

        assertEquals("David Lynch", leadByRep.get(0)[0]);
        assertEquals("Martha Stewart", leadByRep.get(1)[0]);
        assertEquals(1, leadByRep.get(1)[1]);
        assertEquals(2,leadByRep.get(0)[1]);
    }

    @Test
    void findCountOppForProduct_Test(){
        var oppByProd = opportunityRepository.findCountOppForProduct();
        assertEquals(Truck.BOX, oppByProd.get(0)[0]);
        assertEquals(1, oppByProd.get(0)[1]);
        assertEquals(Truck.FLATBED, oppByProd.get(1)[0]);
        assertEquals(1, oppByProd.get(1)[1]);
        assertEquals(Truck.HYBRID, oppByProd.get(2)[0]);
        assertEquals(1, oppByProd.get(2)[1]);
    }

    @Test
    void findCountOpportunityByRepNameForStatus_TestOpen(){
        var oppByRepOpen = opportunityRepository.findCountOpportunityByRepNameForStatus(Status.OPEN.toString());
        assertEquals("David Lynch", oppByRepOpen.get(0)[0]);
        assertEquals("Martha Stewart", oppByRepOpen.get(1)[0]);
        assertEquals(1, oppByRepOpen.get(1)[1]);
        assertEquals(2,oppByRepOpen.get(0)[1]);
    }

    @Test
    void findCountOpportunityByRepNameForStatus_TestCloseWon(){

        opportunities.get(0).setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunities.get(0));
        var oppByRepCloseWon = opportunityRepository.findCountOpportunityByRepNameForStatus(Status.CLOSED_WON.toString());
        assertEquals("David Lynch", oppByRepCloseWon.get(0)[0]);
        assertEquals(1,oppByRepCloseWon.get(0)[1]);
    }

    @Test
    void findCountOpportunityByRepNameForStatus_TestCloseLost(){
        opportunities.get(0).setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunities.get(0));
        var oppByRepCloseLost = opportunityRepository.findCountOpportunityByRepNameForStatus(Status.CLOSED_LOST.toString());
        assertEquals("David Lynch", oppByRepCloseLost.get(0)[0]);
        assertEquals(1,oppByRepCloseLost.get(0)[1]);
    }

    @Test
    void findCountOpportunityByProductForStatus_OPEN(){
        var oppByProdOpen = opportunityRepository.findCountOpportunityByProductForStatus(Status.OPEN);
        assertEquals(Truck.BOX, oppByProdOpen.get(0)[0]);
        assertEquals(1, oppByProdOpen.get(0)[1]);
        assertEquals(Truck.FLATBED, oppByProdOpen.get(1)[0]);
        assertEquals(1, oppByProdOpen.get(1)[1]);
        assertEquals(Truck.HYBRID, oppByProdOpen.get(2)[0]);
        assertEquals(1, oppByProdOpen.get(2)[1]);
    }

    @Test
    void findCountOpportunityByProductForStatus_CLOSED_WON(){
        opportunities.get(0).setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunities.get(0));
        var oppByProdCloseWon = opportunityRepository.findCountOpportunityByProductForStatus(Status.CLOSED_WON);
        assertEquals(Truck.FLATBED, oppByProdCloseWon.get(0)[0]);
        assertEquals(1, oppByProdCloseWon.get(0)[1]);

    }

    @Test
    void findCountOpportunityByProductForStatus_CLOSED_LOST(){
        opportunities.get(0).setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunities.get(0));
        var oppByProdCloseWon = opportunityRepository.findCountOpportunityByProductForStatus(Status.CLOSED_LOST);
        assertEquals(Truck.FLATBED, oppByProdCloseWon.get(0)[0]);
        assertEquals(1, oppByProdCloseWon.get(0)[1]);

    }

    @Test
    void findCountOppForCountry_Test() {
        var oppByCountry = opportunityRepository.findCountOppForCountry();
        assertEquals("FRANCE", oppByCountry.get(0)[0]);
        assertEquals("SPAIN", oppByCountry.get(1)[0]);
        assertEquals("UNITED KINGDOM", oppByCountry.get(2)[0]);
        assertEquals(1, oppByCountry.get(0)[1]);
        assertEquals(1,oppByCountry.get(1)[1]);
        assertEquals(1,oppByCountry.get(2)[1]);
    }

    @Test
    void findCountOpportunityByCountryForStatus_Open_Test(){
        var oppByCountryOpen = opportunityRepository.findCountOpportunityByCountryForStatus(Status.OPEN.toString());
        assertEquals("FRANCE", oppByCountryOpen.get(0)[0]);
        assertEquals("SPAIN", oppByCountryOpen.get(1)[0]);
        assertEquals("UNITED KINGDOM", oppByCountryOpen.get(2)[0]);
        assertEquals(1, oppByCountryOpen.get(0)[1]);
        assertEquals(1,oppByCountryOpen.get(1)[1]);
        assertEquals(1,oppByCountryOpen.get(2)[1]);
    }

    @Test
    void findCountOpportunityByCountryForStatus_Close_Won_Test(){
        opportunities.get(0).setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunities.get(0));
        var oppByCountryCloseWon = opportunityRepository.findCountOpportunityByCountryForStatus(Status.CLOSED_WON.toString());
        assertEquals("UNITED KINGDOM", oppByCountryCloseWon.get(0)[0]);
        assertEquals(1, oppByCountryCloseWon.get(0)[1]);
    }

    @Test
    void findCountOpportunityByCountryForStatus_Close_Lost_Test(){
        opportunities.get(0).setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunities.get(0));
        var oppByCountryCloseLost = opportunityRepository.findCountOpportunityByCountryForStatus(Status.CLOSED_LOST.toString());
        assertEquals("UNITED KINGDOM", oppByCountryCloseLost.get(0)[0]);
        assertEquals(1, oppByCountryCloseLost.get(0)[1]);

    }

    @Test
    void findCountOppForCity_Test() {
        var oppByCity = opportunityRepository.findCountOppForCity();
        assertEquals("London", oppByCity.get(0)[0]);
        assertEquals("Madrid", oppByCity.get(1)[0]);
        assertEquals("Paris", oppByCity.get(2)[0]);
        assertEquals(1, oppByCity.get(0)[1]);
        assertEquals(1, oppByCity.get(1)[1]);
        assertEquals(1, oppByCity.get(2)[1]);
    }

    @Test
    void findCountOpportunityByCityForStatus_Open_Test(){
        var oppByCityOpen = opportunityRepository.findCountOpportunityByCityForStatus(Status.OPEN.toString());
        assertEquals("London", oppByCityOpen.get(0)[0]);
        assertEquals("Madrid", oppByCityOpen.get(1)[0]);
        assertEquals("Paris", oppByCityOpen.get(2)[0]);
        assertEquals(1, oppByCityOpen.get(0)[1]);
        assertEquals(1, oppByCityOpen.get(1)[1]);
        assertEquals(1, oppByCityOpen.get(2)[1]);
    }

    @Test
    void findCountOpportunityByCityForStatus_Close_Won_Test(){
        opportunities.get(0).setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunities.get(0));
        var oppByCityCloseWon = opportunityRepository.findCountOpportunityByCityForStatus(Status.CLOSED_WON.toString());
        assertEquals("London", oppByCityCloseWon.get(0)[0]);
        assertEquals(1, oppByCityCloseWon.get(0)[1]);
    }

    @Test
    void findCountOpportunityByCityForStatus_Close_Lost_Test(){
        opportunities.get(0).setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunities.get(0));
        var oppByCityCloseLost = opportunityRepository.findCountOpportunityByCityForStatus(Status.CLOSED_LOST.toString());
        assertEquals("London", oppByCityCloseLost.get(0)[0]);
        assertEquals(1, oppByCityCloseLost.get(0)[1]);

    }

    @Test
    void findCountOppForIndustry_Test() {
        var oppByIndustry = opportunityRepository.findCountOppForIndustry();
        assertEquals(Industry.ECOMMERCE, oppByIndustry.get(0)[0]);
        assertEquals(Industry.MANUFACTURING, oppByIndustry.get(1)[0]);
        assertEquals(Industry.PRODUCE, oppByIndustry.get(2)[0]);
        assertEquals(1, oppByIndustry.get(0)[1]);
        assertEquals(1, oppByIndustry.get(1)[1]);
        assertEquals(1, oppByIndustry.get(2)[1]);
    }

    @Test
    void findCountOpportunityByIndustryForStatus_Open_Test(){
        var oppByIndustryOpen = opportunityRepository.findCountOpportunityByIndustryForStatus(Status.OPEN.toString());
        assertEquals(Industry.ECOMMERCE, oppByIndustryOpen.get(0)[0]);
        assertEquals(Industry.MANUFACTURING, oppByIndustryOpen.get(1)[0]);
        assertEquals(Industry.PRODUCE, oppByIndustryOpen.get(2)[0]);
        assertEquals(1, oppByIndustryOpen.get(0)[1]);
        assertEquals(1, oppByIndustryOpen.get(1)[1]);
        assertEquals(1, oppByIndustryOpen.get(2)[1]);
    }

    @Test
    void findCountOpportunityByIndustryForStatus_Close_Won_Test(){
        opportunities.get(0).setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunities.get(0));
        var oppByIndustryCloseWon = opportunityRepository.findCountOpportunityByIndustryForStatus(Status.CLOSED_WON.toString());
        assertEquals(Industry.PRODUCE, oppByIndustryCloseWon.get(0)[0]);
        assertEquals(1, oppByIndustryCloseWon.get(0)[1]);
    }

    @Test
    void findCountOpportunityByIndustryForStatus_Close_Lost_Test(){
        opportunities.get(0).setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunities.get(0));
        var oppByIndustryCloseLost = opportunityRepository.findCountOpportunityByIndustryForStatus(Status.CLOSED_LOST.toString());
        assertEquals(Industry.PRODUCE, oppByIndustryCloseLost.get(0)[0]);
        assertEquals(1, oppByIndustryCloseLost.get(0)[1]);

    }

    @Test
    void findMeanProductQuantity() {
        var meanProductQuantity = opportunityRepository.findMeanProductQuantity();
        assertEquals(387, meanProductQuantity.get().doubleValue());
    }

    @Test
    void findMaxProductQuantity() {
        var maxProductQuantity = opportunityRepository.findMaxProductQuantity();
        assertEquals(1150, maxProductQuantity.get().intValue());
    }

    @Test
    void findMedianQuantityStep1_test(){
        var medianQuantity = opportunityRepository.findMedianQuantityStep1();
        assertEquals(3, medianQuantity.length);
    }


    @Test
    void findMinProductQuantity() {
        var minProductQuantity = opportunityRepository.findMinProductQuantity();
        assertEquals(1, minProductQuantity.get().intValue());
    }

    @Test
    void findMeanOpportunitiesPerAccount() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
        var testOpp1 = new Opportunity(Truck.FLATBED, 10, contacts.get(0), salesReps.get(0));
        var testOpp2 = new Opportunity(Truck.BOX, 1150, contacts.get(1), salesReps.get(0));
        var testOpp3 = new Opportunity(Truck.HYBRID, 1, contacts.get(2), salesReps.get(1));
        var testAccount1 = new Account(Industry.PRODUCE, 50, "London", "UNITED KINGDOM",contacts.get(0), opportunities.get(0));
        testAccount1.addOpportunity(testOpp1);
        testAccount1.addOpportunity(testOpp2);
        testAccount1.addOpportunity(testOpp3);
        accountRepository.save(testAccount1);
        testOpp1.setAccount(testAccount1);
        testOpp2.setAccount(testAccount1);
        testOpp3.setAccount(testAccount1);
        opportunityRepository.save(testOpp1);
        opportunityRepository.save(testOpp2);
        opportunityRepository.save(testOpp3);
        var meanOppsPerAccount = opportunityRepository.findMeanOpportunitiesPerAccount();
        assertEquals(1.5, meanOppsPerAccount.get().doubleValue());
    }

    @Test
    void findMedianOppsPerAccountStep1_Test(){
        var medianAccountsFirstStep = opportunityRepository.findMedianOppsPerAccountStep1();
        assertEquals(3, medianAccountsFirstStep.length);
    }

    @Test
    void findMaxOpportunitiesPerAccount() throws NameContainsNumbersException, EmptyStringException, InvalidCountryException, ExceedsMaxLength {
        var testOpp1 = new Opportunity(Truck.FLATBED, 10, contacts.get(0), salesReps.get(0));
        var testOpp2 = new Opportunity(Truck.BOX, 1150, contacts.get(1), salesReps.get(0));
        var testOpp3 = new Opportunity(Truck.HYBRID, 1, contacts.get(2), salesReps.get(1));
        var testAccount1 = new Account(Industry.PRODUCE, 50, "London", "UNITED KINGDOM",contacts.get(0), opportunities.get(0));
        testAccount1.addOpportunity(testOpp1);
        testAccount1.addOpportunity(testOpp2);
        testAccount1.addOpportunity(testOpp3);
        accountRepository.save(testAccount1);
        testOpp1.setAccount(testAccount1);
        testOpp2.setAccount(testAccount1);
        testOpp3.setAccount(testAccount1);
        opportunityRepository.save(testOpp1);
        opportunityRepository.save(testOpp2);
        opportunityRepository.save(testOpp3);

        var maxOppsPerAccount = opportunityRepository.findMaxOpportunitiesPerAccount();
        assertEquals(3, maxOppsPerAccount.get().doubleValue());
    }

    @Test
    void findMinOpportunitiesPerAccount() throws ExceedsMaxLength, NameContainsNumbersException, EmptyStringException, InvalidCountryException {
        var testOpp1 = new Opportunity(Truck.FLATBED, 10, contacts.get(0), salesReps.get(0));
        var testOpp2 = new Opportunity(Truck.BOX, 1150, contacts.get(1), salesReps.get(0));
        var testOpp3 = new Opportunity(Truck.HYBRID, 1, contacts.get(2), salesReps.get(1));
        var testAccount1 = new Account(Industry.PRODUCE, 50, "London", "UNITED KINGDOM",contacts.get(0), opportunities.get(0));
        testAccount1.addOpportunity(testOpp1);
        testAccount1.addOpportunity(testOpp2);
        testAccount1.addOpportunity(testOpp3);
        accountRepository.save(testAccount1);
        testOpp1.setAccount(testAccount1);
        testOpp2.setAccount(testAccount1);
        testOpp3.setAccount(testAccount1);
        opportunityRepository.save(testOpp1);
        opportunityRepository.save(testOpp2);
        opportunityRepository.save(testOpp3);
        var minOppsPerAccount = opportunityRepository.findMinOpportunitiesPerAccount();
        assertEquals(1, minOppsPerAccount.get().doubleValue());
    }



}