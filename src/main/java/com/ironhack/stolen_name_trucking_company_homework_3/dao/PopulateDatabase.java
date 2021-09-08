package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public class PopulateDatabase{

    public static void populateDatabase(LeadRepository leadRepository,
                            SalesRepRepository salesRepRepository,
                            ContactRepository contactRepository,
                            OpportunityRepository opportunityRepository,
                            AccountRepository accountRepository)
            throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException, InvalidCountryException, IdContainsLettersException {

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

    public static void clearDatabase(LeadRepository leadRepository,
                              SalesRepRepository salesRepRepository,
                              ContactRepository contactRepository,
                              OpportunityRepository opportunityRepository,
                              AccountRepository accountRepository) {
        leadRepository.deleteAll();
        opportunityRepository.deleteAll();
        contactRepository.deleteAll();
        salesRepRepository.deleteAll();
        accountRepository.deleteAll();
    }
}
