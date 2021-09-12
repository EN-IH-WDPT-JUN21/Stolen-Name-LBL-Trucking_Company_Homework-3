package com.ironhack.stolen_name_trucking_company_homework_3;

import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
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

	@Override
	public void run (String...args) throws NoSuchValueException, AWTException, NameContainsNumbersException, EmptyStringException, InvalidCountryException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException {
		if (getIsLoggedIn() == 1) {
			menu.OS();
		} else if (getIsLoggedIn() == 2) {
			menu.OSGuest();
		} else {
			throw new Error("Our server is busy! Please run the program again to login!");
		}
	}
}

