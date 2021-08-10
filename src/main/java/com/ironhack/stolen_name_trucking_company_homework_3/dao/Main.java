/*package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;


public class Main {


    public static MainMenu menu = new MainMenu(LeadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository);


    public static void main(String[] args) throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength, NoSuchValueException {



        SalesRep salesRep1 = new SalesRep("James");
        SalesRep salesRep2 = new SalesRep("Sara");
        SalesRep salesRep3 = new SalesRep("Michael");
        SalesRep salesRep4 = new SalesRep("Julia");






        Lead lead1 = new Lead("Sebastian Marek Labedz", "123456789", "labedzsebastian@gmail.co", "Wings of Freedom");
        Lead lead2 = new Lead("Lee Dawson", "980651164", "ld@gmail.com", "LeeD");
        Lead lead3 = new Lead("Natalia Shilyaeva", "563782789", "nattyshil@yahoo.com", "Nathy From Wonderland");
        //Login.login();

        Contact contact1 = new Contact("John Doe", "123475357", "alfa@beta.uk", "Kałasznikow");
        Contact contact2 = new Contact("Martha Steward", "123475357", "ms@wp.pl", "My own company");
        Contact contact3 = new Contact("George Truman", "123475357", "thisisverylongemail@gmail.com", "Truman Show");

        Opportunity opportunity1 = new Opportunity(Truck.FLATBED, 10, contact1);
        Opportunity opportunity2 = new Opportunity(Truck.BOX, 1150, contact2);
        Opportunity opportunity3 = new Opportunity(Truck.HYBRID, 1, contact3);

        Account account1 = new Account(contact1, opportunity1);
        Account account2 = new Account(contact2, opportunity2);
        Account account3 = new Account(contact3, opportunity3);



        MainMenu.theLeads.put(lead1.getId(), lead1);
        MainMenu.theLeads.put(lead2.getId(), lead2);
        MainMenu.theLeads.put(lead3.getId(), lead3);

        MainMenu.theContacts.put(contact1.getId(), contact1);
        MainMenu.theContacts.put(contact2.getId(), contact2);
        MainMenu.theContacts.put(contact3.getId(), contact3);

        MainMenu.theOpportunities.put(opportunity1.getId(), opportunity1);
        MainMenu.theOpportunities.put(opportunity2.getId(), opportunity2);
        MainMenu.theOpportunities.put(opportunity3.getId(), opportunity3);

        MainMenu.theAccounts.put(account1.getId(), account1);
        MainMenu.theAccounts.put(account2.getId(), account2);
        MainMenu.theAccounts.put(account3.getId(), account3);

        Login.login2();

    }

}
*/