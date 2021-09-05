package com.ironhack.stolen_name_trucking_company_homework_3;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.Login;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.NoSuchValueException;
import com.ironhack.stolen_name_trucking_company_homework_3.menus.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

import static com.ironhack.stolen_name_trucking_company_homework_3.dao.Login.getIsLoggedIn;

@SpringBootApplication
public class StolenNameTruckingCompanyHomework3Application implements CommandLineRunner{

	@Autowired
    MainMenu menu;

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(StolenNameTruckingCompanyHomework3Application.class, args);
	}
	/*@Bean
	CommandLineRunner commandLineRunner(SalesRepRepository salesRepRepository, AccountRepository accountRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository, ContactRepository contactRepository, Login login) throws NoSuchValueException, AWTException {
		return args -> {

			List<SalesRep> salesReps = salesRepRepository.saveAll(List.of(
					new SalesRep("David Lynch"),
					new SalesRep("Martha Stewart")
			));

			List<Lead> leads = leadRepository.saveAll(List.of(
					new Lead("Jane Doe", "123475227", "sadas@beta.uk", "Galactic", salesReps.get(0)),
					new Lead("Maurice Steward", "123467357", "dsaas@wp.pl", "Tesla", salesReps.get(1)),
					new Lead("Georgia Truman", "292334790", "thisisverylong@gmail.com", "Trumpy",salesReps.get(0))
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

		};
	}*/
		@Override
		public void run (String...args) throws NoSuchValueException, AWTException {
		if (getIsLoggedIn() == 1) {
			menu.OS();
		} else if (getIsLoggedIn() == 2) {
			menu.OSGuest();
		} else {
			throw new Error("<<<Our server is busy! Please run the program again to login!>>>");
		}
	}
}

