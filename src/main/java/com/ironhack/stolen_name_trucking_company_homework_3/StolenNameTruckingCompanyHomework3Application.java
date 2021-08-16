package com.ironhack.stolen_name_trucking_company_homework_3;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.*;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class StolenNameTruckingCompanyHomework3Application implements CommandLineRunner{

	@Autowired
	MainMenu menu;

	//public static MainMenu menu = new MainMenu(leadRepository, accountRepository, contactRepository, opportunityRepository, salesRepRepository);

	public static void main(String[] args) {
		SpringApplication.run(StolenNameTruckingCompanyHomework3Application.class, args);

	}
	@Bean
	CommandLineRunner commandLineRunner(SalesRepRepository salesRepRepository, AccountRepository accountRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository, ContactRepository contactRepository) {
		return args -> {

			SalesRep salesRep1 = new SalesRep("James");
			SalesRep salesRep2 = new SalesRep("Sara");
			SalesRep salesRep3 = new SalesRep("Michael");
			SalesRep salesRep4 = new SalesRep("Julia");
			salesRepRepository.save(salesRep1);
			salesRepRepository.save(salesRep2);
			salesRepRepository.save(salesRep3);
			salesRepRepository.save(salesRep4);

			Lead lead1 = new Lead("Sebastian Marek Labedz", "123456789", "labedzsebastian@gmail.co", "Wings of Freedom", salesRep1);
			Lead lead2 = new Lead("Lee Dawson", "980651164", "ld@gmail.com", "LeeD", salesRep2);
			Lead lead3 = new Lead("Natalia Shilyaeva", "563782789", "nattyshil@yahoo.com", "Naty From Wonderland", salesRep3);
			leadRepository.save(lead1);
			leadRepository.save(lead2);
			leadRepository.save(lead3);

			Contact contact1 = new Contact("Ludwig van Beethoven", "123456789", "beet@gmail.co", "Classics Forever", salesRep1);
			Contact contact2 = new Contact("Marie Antoinette", "980651164", "cakes@gmail.com", "Best Cakes", salesRep2);
			Contact contact3 = new Contact("Pythagoras", "563782789", "numbers@yahoo.com", "Triangles", salesRep3);
			contactRepository.save(contact1);
			contactRepository.save(contact2);
			contactRepository.save(contact3);

			Opportunity opportunity1 = new Opportunity(Truck.FLATBED, 10, contact1, salesRep1);
			Opportunity opportunity2 = new Opportunity(Truck.BOX, 1150, contact2, salesRep2);
			Opportunity opportunity3 = new Opportunity(Truck.HYBRID, 1, contact3, salesRep3);
			opportunityRepository.save(opportunity1);
			opportunityRepository.save(opportunity2);
			opportunityRepository.save(opportunity3);

			Account account1 = new Account(Industry.PRODUCE, 25, "VIENNA", "AUSTRIA", contact1, opportunity1);
			Account account2 = new Account(Industry.MANUFACTURING, 190, "VERSAILLES", "FRANCE", contact2, opportunity2);
			Account account3 = new Account(Industry.MEDICAL, 109, "ATHENS", "GREECE", contact3, opportunity3);
			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);
			opportunity1.setAccount(account1);
			opportunity2.setAccount(account2);
			opportunity3.setAccount(account3);
			opportunityRepository.save(opportunity1);
			opportunityRepository.save(opportunity2);
			opportunityRepository.save(opportunity3);


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

			MainMenu.theSalesReps.put(salesRep1.getId().toString(),salesRep1);
			MainMenu.theSalesReps.put(salesRep2.getId().toString(),salesRep2);
			MainMenu.theSalesReps.put(salesRep3.getId().toString(),salesRep3);
			MainMenu.theSalesReps.put(salesRep4.getId().toString(),salesRep4);

			menu.OS();

		};


	}
	@Override
	public void run(String...args){
	}

}
