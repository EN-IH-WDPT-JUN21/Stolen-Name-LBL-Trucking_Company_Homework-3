package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.ReportCommands;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

@Component
public class MainMenu {

    LeadRepository leadRepository;
    AccountRepository accountRepository;
    ContactRepository contactRepository;
    OpportunityRepository opportunityRepository;
    SalesRepRepository salesRepRepository;

    Scanner scanner = new Scanner(System.in);

    public MainMenu() {
    }

    enum consoleTextColor {
        ANSI_RED("\033[0;31m"),
        ANSI_GREEN("\033[0;32m"),
        ANSI_YELLOW("\033[0;33m"),
        ANSI_BLUE("\033[0;34m"),
        ANSI_RESET("\u001B[0m"),
        GREEN_BOLD("\033[1;32m"),
        BLUE_BOLD("\033[1;34m"),
        CYAN_BOLD("\033[1;36m"),
        WHITE_BOLD("\033[1;37m");

        private final String color;

        consoleTextColor(String color) {
            this.color = color;
        }
    }

    private static final String colorMain = consoleTextColor.ANSI_YELLOW.color;
    private static final String colorMainBold = consoleTextColor.WHITE_BOLD.color;
    private static final String colorInput = consoleTextColor.CYAN_BOLD.color;
    private static final String colorHeadline = consoleTextColor.ANSI_BLUE.color;
    private static final String colorHeadlineBold = consoleTextColor.BLUE_BOLD.color;
    private static final String colorTable = consoleTextColor.GREEN_BOLD.color;
    private static final String colorError = consoleTextColor.ANSI_RED.color;
    private static final String colorLogo = consoleTextColor.ANSI_GREEN.color;
    private static final String reset = consoleTextColor.ANSI_RESET.color;
    private static boolean wasRun = false;
    private static boolean valid;

    @Autowired
    public MainMenu(LeadRepository leadRepository, AccountRepository accountRepository, ContactRepository contactRepository, OpportunityRepository opportunityRepository, SalesRepRepository salesRepRepository) {
        this.leadRepository = leadRepository;
        this.accountRepository = accountRepository;
        this.contactRepository = contactRepository;
        this.opportunityRepository = opportunityRepository;
        this.salesRepRepository = salesRepRepository;
    }

    public void OS() throws RuntimeException, AWTException, NoSuchValueException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + colorHeadline + colorLogo
                + "                                                                                                \n" +
                "                                         *#### #####        ###################*   *####*         \n" +
                "                         #################### #####        ######################  #####          \n" +
                "                    ,######              ### #####        #####            ###### #####           \n" +
                "                  ####                  ### #####        #####    ############## #####            \n" +
                "                ####                   ### #####        #####      ###########  #####             \n" +
                "              ########################### #####        #####            ###### #####              \n" +
                "             ####################.###### ############ ###################### ############         \n" +
                "             ################ ####### # ############ #####################  ############          \n" + reset +
                colorHeadline + colorMain + "╔═══════════════════════════════════════════════════════════════════════════════════════════════════╗\n"
                + "║                                " + colorTable + "WELCOME TO LBL CRM SYSTEM" + colorMain + "                                          ║\n"
                + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                + "║     " + colorTable + "WHAT WOULD YOU LIKE TO DO " + /*Login.getUsername().toUpperCase() +*/ "?" + colorMain + insertLine() + "║\n"
                + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                + "║ 1.  To create new Lead " + colorHeadline + "- type: 'new lead'" + colorMain + "                                                         ║\n"
                + "║ 2.  To create new Sales Representative " + colorHeadline + "- type: 'new salesrep'" + colorMain + "                                     ║\n"
                + "║ 3.  To check Leads list " + colorHeadline + "- type: 'show leads'" + colorMain + "                                                      ║\n"
                + "║ 4.  To check individual Lead's details " + colorHeadline + "- type: 'lookup lead ' + Lead Id" + colorMain + "                           ║\n"
                + "║ 5.  To convert Lead into Opportunity " + colorHeadline + "- type: - 'convert ' + Lead Id" + colorMain + "                               ║\n"
                + "║ 6.  To check Opportunity list " + colorHeadline + "- type: 'show opportunities'" + colorMain + "                                        ║\n"
                + "║ 7.  To check individual Opportunity's details " + colorHeadline + "- type: 'lookup opportunity ' + Opportunity Id" + colorMain + "      ║\n"
                + "║ 8.  To change Opportunity status to WON" + colorHeadline + "- type: 'close-won' + Opportunity Id" + colorMain + "                       ║\n"
                + "║ 9.  To change Opportunity status to LOST" + colorHeadline + "- type: 'close-lost' + Opportunity Id" + colorMain + "                     ║\n"
                + "║ 10. To check Contact list " + colorHeadline + "- type: 'show contacts'" + colorMain + "                                                 ║\n"
                + "║ 11. To check Account list " + colorHeadline + "- type: 'show accounts'" + colorMain + "                                                 ║\n"
                + "║ 12. To check Sales Representatives list " + colorHeadline + "- type: 'show salesreps'" + colorMain + "                                  ║\n"
                + "║ 13. To check available Reports " + colorHeadline + "- type: 'view reports'" + colorMain + "                                             ║\n"
                + "║ 14. To populate Database " + colorHeadline + "- type: 'populate'" + colorMain + "                                                       ║\n"
                + "║ 15. To clear Database " + colorHeadline + "- type: 'clear'" + colorMain + "                                                             ║\n"
                + "║ 16. To quit " + colorHeadline + "- type: 'quit'" + colorMain + "                                                                        ║\n"
                + "╚═══════════════════════════════════════════════════════════════════════════════════════════════════╝\n" + reset);

        consoleFocusRunOnce();

        try {

            // Creates String array from scanner input
            String[] input = scanner.nextLine().trim().toLowerCase().split("\\s+");

            if (input[0].equals("quit")) {
                System.out.println(colorMainBold + "\nThank you for using our LBL CRM SYSTEM!" + reset);
                System.out.println(colorError + "Exiting the program" + reset);
                System.exit(0);
            } else if (input[0].equals("populate")) {
                PopulateDatabase.populateDatabase(leadRepository, salesRepRepository, contactRepository, opportunityRepository, accountRepository);
            } else if (input[0].equals("clear")){
                PopulateDatabase.clearDatabase(leadRepository, salesRepRepository, contactRepository, opportunityRepository, accountRepository);

            } else if (input.length < 2) {
                throw new IllegalArgumentException();
            }
            else if (input[0].equals("lookup") && input[1].equals("lead") && input.length>2) {
                if(!leadRepository.existsById(Long.parseLong(input[2]))){
                    throw new NoSuchValueException("There is no Lead that matches that id.");
                }
                System.out.println(lookUpLeadId(input[2]));
            } else if (input[0].equals("lookup") && input[1].equals("opportunity") && input.length>2) {
                if(!opportunityRepository.existsById(Long.parseLong(input[2]))){
                    throw new NoSuchValueException("There is no Opportunity that matches that id.");
                }
                System.out.println(lookUpOppId(input[2]));
            } else if (input[0].equals("convert")) { // throws null point exception if number not in array
                if(!leadRepository.existsById(Long.parseLong(input[1]))){
                    throw new NoSuchValueException("There is no Lead that matches that id.");
                }
                createAccount(convertLead(input[1]));
            } else if (input[0].equals("close-lost")) {
                if(!opportunityRepository.existsById(Long.parseLong(input[1]))){
                    throw new NoSuchValueException("There is no Opportunity that matches that id.");
                }
                closeLost(input[1]);
            } else if (input[0].equals("close-won")) {
                if(!opportunityRepository.existsById(Long.parseLong(input[1]))){
                    throw new NoSuchValueException("There is no Opportunity that matches that id.");
                }
                closeWon(input[1]);
            } else {

                switch (input[0] + input[1]) {
                    case "new" + "lead" -> newLead();
                    case "new" + "salesrep" -> newSalesRep();
                    case "show" + "leads" -> showLeads();
                    case "show" + "opportunities" -> showOpportunities();
                    case "show" + "contacts" -> showContacts();
                    case "show" + "accounts" -> showAccounts();
                    case "show" + "salesreps" -> showSalesReps();
                    case "view" + "reports" -> reportMenu();
                    case "main" + "menu" -> OS();
                    default -> throw new IllegalArgumentException();
                }
            }
        } catch (IllegalArgumentException | NullPointerException  e) {
            System.out.println(colorError + "\nInvalid input" + reset);
        }
        catch (NameContainsNumbersException | EmptyStringException | InvalidCountryException | EmailNotValidException | ExceedsMaxLength | PhoneNumberContainsLettersException | NoSuchValueException e){
            System.out.println(colorError + e.getMessage() + reset);
        }

        System.out.println(colorInput + "\nPress Enter to continue..." + reset);
        scanner.nextLine();
        OS();

    }


    // Method to create a new lead
    public Lead newLead() {

        valid = false;

        System.out.println(colorInput + "\nWould you like to create a new lead?" + colorTable +"   y / n " + reset);
        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    Lead newLead = new Lead();
                    leadRepository.save(newLead);

                    //checks if restrictions for Customer name are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the customer's name: " + reset);
                        try {
                            newLead.setName(scanner.nextLine().trim().toUpperCase());
                            valid = true;
                        } catch (EmptyStringException | NameContainsNumbersException | ExceedsMaxLength e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;

                    //checks if restrictions for Phone number are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the customer's phone number without spaces: " + reset);
                        try{
                            newLead.setPhoneNumber(scanner.nextLine().trim().toUpperCase());
                            valid = true;
                        }catch (EmptyStringException | PhoneNumberContainsLettersException | ExceedsMaxLength e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;


                    //checks if restrictions for E-mail address are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the customer's email address: " + reset);
                        try {
                            newLead.setEmail(scanner.nextLine().trim().toUpperCase());
                            valid = true;
                        }catch (EmptyStringException | EmailNotValidException | ExceedsMaxLength e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;

                    //checks if restrictions for Company name are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the customer's company name: " + reset);
                        try {
                            newLead.setCompanyName(scanner.nextLine().trim().toUpperCase());
                            valid = true;
                        }catch(EmptyStringException | ExceedsMaxLength e){
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    //theLeads.put(newLead.getId(), newLead);
                    System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "New Lead created" + colorMain + " ══════════════════════╦══════════════════════╦══════════════════════════════════════════╦═════════════════════════════════════════════╗" + reset);
                    System.out.println(newLead.toString());
                    leadRepository.save(newLead);
                    return newLead;
                }
                case "n" ->
                        OS();

                default -> throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException | AWTException | NoSuchValueException e) {

            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            newLead();
        }
        return null;
    }

    // Method to convert Lead to Opportunity
    public Opportunity convertLead(String id) throws NullPointerException {

        Lead lead = leadRepository.findById(Long.parseLong(id)).get();
        System.out.println(colorInput + "\nWould you like to convert " +
                                   colorTable + lead.getName().toUpperCase() +
                                   colorInput + " from " +
                                   colorTable + lead.getCompanyName().toUpperCase() +
                                   colorInput + " into an opportunity?" +
                                   colorTable + "    y / n " + reset);

        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    Opportunity newOpp = new Opportunity();
                    opportunityRepository.save(newOpp);

                    valid = false;

                    // checks if restrictions for Product are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the product that " + colorTable + lead.getCompanyName().toUpperCase() + colorInput + " is interested in: \n " +
                                                   colorTable + "HYBRID, FLATBED OR BOX" + reset);
                        try {
                            newOpp.setTruck(Truck.getTruck(scanner.nextLine().trim().toUpperCase(Locale.ROOT)));
                            valid = true;
                        }catch (EmptyStringException | InvalidEnumException e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;

                    // checks if restrictions for Quantity are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the quantity that " + colorTable + lead.getCompanyName().toUpperCase() + colorInput + " is interested in: " + reset);

                        try {
                            newOpp.setQuantity(Integer.parseInt(scanner.nextLine().trim()));
                            valid = true;
                        }catch (NumberFormatException  e) {
                            System.out.println(colorError + "You need to input a reasonable number. Please, try again.");
                        }catch (IllegalArgumentException e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;

                    Contact newContact = new Contact(lead.getName().toUpperCase(), lead.getPhoneNumber().toUpperCase(), lead.getEmail().toUpperCase(), lead.getCompanyName().toUpperCase(), lead.getSalesRep()); // Converts lead into contact
                    contactRepository.save(newContact);
                    newOpp.setDecisionMaker(newContact); // Assigns contact as the decisionMaker
                    //theContacts.put(newContact.getId(), newContact);  // Adds contact to contact Map
                    //theOpportunities.put(newOpp.getId(), newOpp); // Adds Opportunity to opportunities map
                    //theLeads.remove(lead.getId()); // Removes converted lead from Leads map ("Database")
                    newOpp.setSalesRep(lead.getSalesRep());
                    opportunityRepository.save(newOpp);
                    System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "New Opportunity created" + colorMain + " ════════════╦═══════════════════╗" + reset);
                    System.out.printf("%-1s %-17s %-1s %-27s %-1s %-24s %-1s %-24s %-1s\n",
                                      colorMain + "║",
                                      colorHeadlineBold + "ID",
                                      colorMain + "║",
                                      colorHeadlineBold + "Status",
                                      colorMain + "║",
                                      colorHeadlineBold + "Product",
                                      colorMain + "║",
                                      colorHeadlineBold + "Quantity",
                                      colorMain + "║\n" +
                                      colorMain + "╠════════════╬══════════════════════╬═══════════════════╬═══════════════════╣");
                    System.out.println(newOpp);
                    System.out.println(colorInput + "Press Enter to continue..." + reset);
                    scanner.nextLine();
                    System.out.println(colorMain + "╔════════════╦═════ " + colorMainBold + "New Contact created" + colorMain + " ═══════════════════╦══════════════════════╦══════════════════════════════════════════╦═════════════════════════════════════════════╗" + reset);
                    System.out.printf(String.format("%-1s %-17s %-1s %-50s %-1s %-27s %-1s %-47s %-1s %-50s %-1s\n",
                                                    colorMain + "║",
                                                    colorHeadlineBold + "ID",
                                                    colorMain + "║",
                                                    colorHeadlineBold + "Name",
                                                    colorMain + "║",
                                                    colorHeadlineBold + "Phone Number",
                                                    colorMain + "║",
                                                    colorHeadlineBold + "Email Address",
                                                    colorMain + "║",
                                                    colorHeadlineBold + "Company name",
                                                    colorMain + "║\n" +
                                                    colorMain + "╠════════════╬═════════════════════════════════════════════╬══════════════════════╬══════════════════════════════════════════╬═════════════════════════════════════════════╣" + reset));
                    System.out.println(newContact);
                    System.out.println(colorInput + "Press Enter to continue..." + reset);
                    scanner.nextLine();
                    leadRepository.delete(lead);// Removes lead from repo?
                    opportunityRepository.save(newOpp);

                    return newOpp;
                }
                case "n" ->
                    OS();
                default -> throw new IllegalArgumentException(colorError + "Invalid input - please start again" + reset);
            }
        } catch (Exception e) {

            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            //convertLead(id); // Catches errors and returns to start of method - Is there a simple alternative?
        }
        return null;
    }

    // Method called to create a new account
    public Account createAccount(Opportunity opportunity) {
        System.out.println(colorMain + "\n═════════════ " + colorMainBold + "Creating new Account" + colorMain + " ═════════════");
        Scanner scanner = new Scanner(System.in);
        try {
            Account newAccount = new Account(opportunity.getDecisionMaker(), opportunity);
            accountRepository.save(newAccount);
            valid = false;

            // checks if restrictions for Industry are met
            while (!valid) {

                System.out.println(colorInput + "\nPlease input the company industry: \n" +
                                           colorTable + "PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL OR OTHER" + reset);

                try {
                    newAccount.setIndustry(Industry.getIndustry(scanner.nextLine().trim().toUpperCase(Locale.ROOT))); // ENUM Selection
                    valid = true;
                } catch (EmptyStringException | InvalidEnumException e) {
                    System.out.println(colorError + e.getMessage());
                }
            }

            valid = false;

            // checks if restrictions for Employee count are met
            while (!valid) {
                System.out.println(colorInput + "\nPlease input the employee count for " + colorTable + newAccount.getCompanyName() + colorInput + ":  " + reset); //**Needs amending to display name in contact list
                try {
                    newAccount.setEmployeeCount(Integer.parseInt(scanner.nextLine().trim()));
                    valid = true;
                }catch (NumberFormatException  e) {
                    System.out.println(colorError + "You need to input a reasonable number. Please, try again.");
                } catch (IllegalArgumentException e) {
                    System.out.println(colorError + e.getMessage());
                }
            }

            valid = false;

            // checks if restrictions for City name are met
            while (!valid) {
                System.out.println(colorInput + "\nPlease input the city for " + colorTable + newAccount.getCompanyName() + colorInput + ":  " + reset);
                try {
                    newAccount.setCity(scanner.nextLine().trim().toUpperCase(Locale.ROOT));
                    valid = true;
                }catch (EmptyStringException | NameContainsNumbersException | ExceedsMaxLength e) {
                    System.out.println(colorError + e.getMessage());
                }
            }

            valid = false;

            // checks if Country is in country array
            while (!valid) {
                System.out.println(colorInput + "\nPlease input the Country for " + colorTable + newAccount.getCompanyName() + ":  " + reset);
                try {
                    newAccount.setCountry(scanner.nextLine().trim().toUpperCase());
                    valid = true;
                } catch (EmptyStringException | ExceedsMaxLength | InvalidCountryException e) {
                    System.out.println(colorError + e.getMessage());
                }
            }

            valid = false;

            // Assigns the Account to the contact(decision maker) of the opportunity
            opportunity.getDecisionMaker().setAccount(newAccount);
            contactRepository.save(opportunity.getDecisionMaker());
            accountRepository.save(newAccount);
            System.out.println(newAccount);

            return newAccount;
        } catch (Exception e) {

            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            //createAccount(opportunity); // Catches errors and returns to start of method - Is there a better way??
        }
        return null;
    }

    // showing all leads
    public void showLeads() {
        var allLeads = leadRepository.findAllLeads();
        System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Leads: " + allLeads.size() + colorMain + " ════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                          colorMain + "║",
                          colorHeadlineBold + "ID",
                          colorMain + "║",
                          colorHeadlineBold + "Name",
                          colorMain + "║");
        System.out.printf("%-1s%-12s%-1s%-45s%-1s\n",
                          colorMain + "╠",
                          "════════════",
                          "╬",
                          "═════════════════════════════════════════════",
                          "╣" + reset);
        for (int i = 0; i<allLeads.size(); i++) {
            System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                              colorMain + "║",
                              colorTable + allLeads.get(i)[0],
                              colorMain + "║",
                              colorTable + allLeads.get(i)[1].toString().toUpperCase(Locale.ROOT),
                              colorMain + "║" + reset);
        }
    }

    // showing all contacts
    public void showContacts() {
        var allContacts = contactRepository.findAllContacts();
        System.out.println(colorMain + "\n╔════════════╦════════ " + colorMainBold + "Total Number Of Contacts: " + allContacts.size() + colorMain + " ════════╦══════════════════════════════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-50s %-1s %-47s %-1s\n",
                          colorMain + "║",
                          colorHeadlineBold + "ID",
                          colorMain + "║",
                          colorHeadlineBold + "Name",
                          colorMain + "║",
                          colorHeadlineBold + "Company name",
                          colorMain + "║");
        System.out.printf("%-1s%-12s%-1s%-45s%-1s%-32s%-1s\n",
                          colorMain + "╠",
                          "════════════",
                          "╬",
                          "═════════════════════════════════════════════",
                          "╬",
                          "══════════════════════════════════════════",
                          "╣" + reset);
        for (int i = 0; i<allContacts.size(); i++) {
            System.out.printf("%-1s %-17s %-1s %-50s %-1s %-47s %-1s\n",
                              colorMain + "║",
                              colorTable + allContacts.get(i)[0],
                              colorMain + "║",
                              colorTable + allContacts.get(i)[1].toString().toUpperCase(Locale.ROOT),
                              colorMain + "║",
                              colorTable + allContacts.get(i)[2].toString().toUpperCase(Locale.ROOT),
                              colorMain + "║" + reset);
        }
    }

    public void showOpportunities() {
        var allOpps = opportunityRepository.findAllOpportunities();
        System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "Total Number Of Opportunities: " + allOpps.size() + colorMain + " ══════╦══════════════════════════════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-24s %-1s %-17s %-1s %-17s %-1s %-47s %-1s\n",
                          colorMain + "║",
                          colorHeadlineBold + "ID",
                          colorMain + "║",
                          colorHeadlineBold + "Contract status",
                          colorMain + "║",
                          colorHeadlineBold + "Product",
                          colorMain + "║",
                          colorHeadlineBold + "Quantity",
                          colorMain + "║",
                          colorHeadlineBold + "Decision maker",
                          colorMain + "║");
        System.out.printf("%-1s%-12s%-1s%-19s%-1s%-12s%-1s%-12s%-1s%-42s%-1s\n",
                          colorMain + "╠",
                          "════════════",
                          "╬",
                          "═══════════════════",
                          "╬",
                          "════════════",
                          "╬",
                          "════════════",
                          "╬",
                          "══════════════════════════════════════════",
                          "╣" + reset);
        for (int i = 0; i<allOpps.size(); i++) {
            System.out.printf("%-1s %-17s %-1s %-24s %-1s %-17s %-1s %-17s %-1s %-47s %-1s\n",
                              colorMain + "║",
                              colorTable + allOpps.get(i)[0],
                              colorMain + "║",
                              colorTable + allOpps.get(i)[1].toString().toUpperCase(Locale.ROOT),
                              colorMain + "║",
                              colorTable + allOpps.get(i)[2].toString().toUpperCase(Locale.ROOT),
                              colorMain + "║",
                              colorTable + allOpps.get(i)[3].toString().toUpperCase(Locale.ROOT),
                              colorMain + "║",
                              colorTable + allOpps.get(i)[4].toString().toUpperCase(Locale.ROOT),
                              colorMain + "║" + reset);
        }
    }

    public void showAccounts() {
        var allAccs = accountRepository.findAllAccounts();
        System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Accounts: " + allAccs.size() + colorMain + " ═════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                          colorMain + "║",
                          colorHeadlineBold + "ID",
                          colorMain + "║",
                          colorHeadlineBold + "Company name",
                          colorMain + "║");
        System.out.printf("%-1s%-12s%-1s%-45s%-1s\n",
                          colorMain + "╠",
                          "════════════",
                          "╬",
                          "═════════════════════════════════════════════",
                          "╣" + reset);
        for (int i = 0; i<allAccs.size(); i++) {
            System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                              colorMain + "║",
                              colorTable + allAccs.get(i)[0],
                              colorMain + "║",
                              colorTable + allAccs.get(i)[1].toString().toUpperCase(Locale.ROOT),
                              colorMain + "║" + reset);
        }
    }


    public String lookUpLeadId(String id) throws RuntimeException {

        System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "Lead details" + colorMain + " ══════════════════════════╦══════════════════════╦══════════════════════════════════════════╦═════════════════════════════════════════════╗" + reset);
        return leadRepository.findById(Long.parseLong(id)).toString();
    }

    // lookup opportunity by Id
    public String lookUpOppId(String id) throws RuntimeException {
        System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "Contract details" + colorMain + " ═╦═══════════════════╦═══════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-27s %-1s %-24s %-1s %-24s %-1s\n",
                              colorMain + "║",
                              colorHeadlineBold + "ID",
                              colorMain + "║",
                              colorHeadlineBold + "Contract status",
                              colorMain + "║",
                              colorHeadlineBold + "Product",
                              colorMain + "║",
                              colorHeadlineBold + "Quantity",
                              colorMain + "║\n" +
                              colorMain + "╠════════════╬══════════════════════╬═══════════════════╬═══════════════════╣" + reset);
        return opportunityRepository.findById(Long.parseLong(id)).toString() +
                            colorMain + "\n╔════════════╦═══ " + colorMainBold + "Decision maker details" + colorMain + " ══════════════════╦══════════════════════╦══════════════════════════════════════════╦═════════════════════════════════════════════╗\n" + reset +
                            String.format("%-1s %-17s %-1s %-50s %-1s %-27s %-1s %-47s %-1s %-50s %-1s\n",
                              colorMain + "║",
                              colorHeadlineBold + "ID",
                              colorMain + "║",
                              colorHeadlineBold + "Name",
                              colorMain + "║",
                              colorHeadlineBold + "Phone Number",
                              colorMain + "║",
                              colorHeadlineBold + "Email Address",
                              colorMain + "║",
                              colorHeadlineBold + "Company name",
                              colorMain + "║\n" + colorMain + "╠════════════╬═════════════════════════════════════════════╬══════════════════════╬══════════════════════════════════════════╬═════════════════════════════════════════════╣\n" + reset +
                              opportunityRepository.findById(Long.parseLong(id)).get().getDecisionMaker().toString());
    }

    //Change opportunity status to LOST
    public void closeLost(String id) {
        Opportunity opp = opportunityRepository.findById(Long.parseLong(id)).get();
        System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "Opportunity details" + colorMain + " ════════════════╦═══════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-27s %-1s %-24s %-1s %-24s %-1s\n",
                          colorMain + "║",
                          colorHeadlineBold + "ID",
                          colorMain + "║",
                          colorHeadlineBold + "Status",
                          colorMain + "║",
                          colorHeadlineBold + "Product",
                          colorMain + "║",
                          colorHeadlineBold + "Quantity",
                          colorMain + "║\n" +
                                  colorMain + "╠════════════╬══════════════════════╬═══════════════════╬═══════════════════╣");
        System.out.println(opp);
        System.out.println(colorInput + "Would you like to change the status of this opportunity to " + colorTable + "LOST?   y / n" + reset);

        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    opp.setStatus(Status.CLOSED_LOST);
                    opportunityRepository.save(opp); //does it override or creates a new instance?
                    System.out.println(colorMain + "\n═════════════ " + colorMainBold + "Status Changed!" + colorMain + " ═════════════" + reset);
                }
                case "n" ->
                        OS();

                default -> throw new IllegalArgumentException(colorError + "Invalid input - please try again" + reset);
            }

        } catch (Exception e) {
            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            closeLost(id);
        }
    }

    //Change opportunity status to Won
    public void closeWon(String id) {
        Opportunity opp = opportunityRepository.findById(Long.parseLong(id)).get();
        System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "Opportunity details" + colorMain + " ════════════════╦═══════════════════╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-27s %-1s %-24s %-1s %-24s %-1s\n",
                          colorMain + "║",
                          colorHeadlineBold + "ID",
                          colorMain + "║",
                          colorHeadlineBold + "Status",
                          colorMain + "║",
                          colorHeadlineBold + "Product",
                          colorMain + "║",
                          colorHeadlineBold + "Quantity",
                          colorMain + "║\n" +
                                  colorMain + "╠════════════╬══════════════════════╬═══════════════════╬═══════════════════╣");
        System.out.println(opp);
        System.out.println(colorInput + "Would you like to change the status of this opportunity to " + colorTable + "WON?   y / n" + reset);
        Scanner scanner = new Scanner(System.in);
        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    opp.setStatus(Status.CLOSED_WON);
                    opportunityRepository.save(opp);
                    System.out.println(colorMain + "\n═════════════ " + colorMainBold + "Status Changed!" + colorMain + " ═════════════" + reset);
                }
                case "n" ->
                        OS();

                default -> throw new IllegalArgumentException(colorError + "Invalid input - please try again" + reset);
            }

        } catch (Exception e) {
            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            closeWon(id);
        }
    }

    // Focuses cursor inside console for IntelliJ users
    public void consoleFocus() throws AWTException {
        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_4);
        robot.keyRelease(KeyEvent.VK_4);
        robot.keyRelease(KeyEvent.VK_ALT);
    }

    // Makes sure that method consoleFocus is run only once during the program runtime
    public void consoleFocusRunOnce() throws AWTException {
        if (!wasRun) {
            wasRun = true;
            consoleFocus();
        }
    }

    // Adjusts number of characters printed for different usernames
    public static StringBuilder insertLine() {
        StringBuilder line = new StringBuilder();
        for (int i = 1; i < (68 /*- Login.getUsername().length()*/); i++) {
            line.append(" ");
        }
        return line;
    }

    public SalesRep newSalesRep() {

        valid = false;

        System.out.println(colorInput + "\nWould you like to create a new sales representative?" + colorTable +"   y / n " + reset);
        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    SalesRep newSalesRep = new SalesRep();

                    //asks and validates customer's name
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the sales representative's name: " + reset);
                        try {
                            newSalesRep.setName(scanner.nextLine().trim().toUpperCase());
                            valid = true;
                        } catch (EmptyStringException | NameContainsNumbersException | ExceedsMaxLength e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;
                    salesRepRepository.save(newSalesRep);
                    System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "New Sales Representative created" + colorMain + " ════════╗" + reset);
                    System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                            colorMain + "║",
                            colorTable + newSalesRep.getId(),
                            colorMain + "║",
                            colorTable + newSalesRep.getRepName().toUpperCase(),
                            colorMain + "║" + reset);
                    return newSalesRep;
                }
                case "n" -> // Would normally go back in the menu at this point
                        OS();

                default -> throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException | AWTException | NoSuchValueException e) {

            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            newSalesRep();
        }
        return null;
    }

    public void showSalesReps() {
        var allReps = salesRepRepository.findAllSalesreps();
        System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Sales Representatives: " + allReps.size() + colorMain + " ╗" + reset);
        System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                colorMain + "║",
                colorHeadlineBold + "ID",
                colorMain + "║",
                colorHeadlineBold + "Name",
                colorMain + "║");
        System.out.printf("%-1s%-12s%-1s%-45s%-1s\n",
                colorMain + "╠",
                "════════════",
                "╬",
                "═════════════════════════════════════════════",
                "╣" + reset);
        for (int i = 0; i<allReps.size(); i++) {
            System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                    colorMain + "║",
                    colorTable + allReps.get(i)[0],
                    colorMain + "║",
                    colorTable + allReps.get(i)[1].toString().toUpperCase(Locale.ROOT),
                    colorMain + "║" + reset);
        }
    }


    public void reportMenu() throws NoSuchValueException, AWTException {

        System.out.println("\n" + colorHeadline + colorLogo
                + "                                                                                                \n" +
                "                                         *#### #####        ###################*   *####*         \n" +
                "                         #################### #####        ######################  #####          \n" +
                "                    ,######              ### #####        #####            ###### #####           \n" +
                "                  ####                  ### #####        #####    ############## #####            \n" +
                "                ####                   ### #####        #####      ###########  #####             \n" +
                "              ########################### #####        #####            ###### #####              \n" +
                "             ####################.###### ############ ###################### ############         \n" +
                "             ################ ####### # ############ #####################  ############          \n" + reset +
                colorHeadline + colorMain + "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n"
                + "║                                " + colorTable + "WELCOME TO LBL CRM SYSTEM REPORTING MENU" + colorMain + "                                          ║\n"
                + "╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                + "║ 1.  Display count of Leads by Sales Representative " + colorHeadline + "- type: 'report lead by salesrep'" + colorMain + "                             ║\n"
                + "║ 2.  Display count of all Opportunities by Sales Representative " + colorHeadline + "- type: 'report opportunity by salesrep'" + colorMain + "          ║\n"
                + "║ 3.  Display count of CLOSED-WON Opportunities by Sales Representative " + colorHeadline + "- type: 'report closed-won by salesrep'" + colorMain + "    ║\n"
                + "║ 4.  Display count of CLOSED-LOST Opportunities by Sales Representative " + colorHeadline + "- type: 'report closed-lost by salesrep'" + colorMain + "  ║\n"
                + "║ 5.  Display count of OPEN Opportunities by Sales Representative " + colorHeadline + "- type: - 'report open by salesrep'" + colorMain + "              ║\n"
                + "║ 6.  Display count of all Opportunities by the Product " + colorHeadline + "- type: 'report opportunity by product'" + colorMain + "                    ║\n"
                + "║ 7.  Display count of CLOSED-WON Opportunities by the Product " + colorHeadline + "- type: 'report closed-won by product'" + colorMain + "              ║\n"
                + "║ 8.  Display count of CLOSED-LOST Opportunities by the Product" + colorHeadline + "- type: 'report closed-lost by product'" + colorMain + "             ║\n"
                + "║ 9.  Display count of OPEN Opportunities by the Product" + colorHeadline + "- type: 'report open by product'" + colorMain + "                           ║\n"
                + "║ 10. Display count of all Opportunities by Country " + colorHeadline + "- type: - 'report opportunity by country'" + colorMain + "                      ║\n"
                + "║ 11. Display count of CLOSED-WON Opportunities by Country " + colorHeadline + "- type: 'report closed-won by country'" + colorMain + "                  ║\n"
                + "║ 12. Display count of CLOSED-LOST Opportunities by Country" + colorHeadline + "- type: 'report closed-lost by country'" + colorMain + "                 ║\n"
                + "║ 13. Display count of OPEN Opportunities by Country" + colorHeadline + "- type: 'report open by country'" + colorMain + "                               ║\n"
                + "║ 14. Display count of all Opportunities by City " + colorHeadline + "- type: - 'report opportunity by city'" + colorMain + "                            ║\n"
                + "║ 15. Display count of CLOSED-WON Opportunities by City " + colorHeadline + "- type: 'report closed-won by city'" + colorMain + "                        ║\n"
                + "║ 16. Display count of CLOSED-LOST Opportunities by City" + colorHeadline + "- type: 'report closed-lost by city'" + colorMain + "                       ║\n"
                + "║ 17. Display count of OPEN Opportunities by City" + colorHeadline + "- type: 'report open by city'" + colorMain + "                                     ║\n"
                + "║ 18. Display count of all Opportunities by Industry " + colorHeadline + "- type: - 'report opportunity by industry'" + colorMain + "                    ║\n"
                + "║ 19. Display count of CLOSED-WON Opportunities by Industry " + colorHeadline + "- type: 'report closed-won by industry'" + colorMain + "                ║\n"
                + "║ 20. Display count of CLOSED-LOST Opportunities by Industry" + colorHeadline + "- type: 'report closed-lost by industry'" + colorMain + "               ║\n"
                + "║ 21. Display count of OPEN Opportunities by Industry" + colorHeadline + "- type: 'report open by industry'" + colorMain + "                             ║\n"
                + "║ 22. Display MEAN EmployeeCount for Accounts" + colorHeadline + "- type: 'mean employeecount'" + colorMain + "                                          ║\n"
                + "║ 23. Display MEDIAN EmployeeCount for Accounts" + colorHeadline + "- type: 'median employeecount'" + colorMain + "                                      ║\n"
                + "║ 24. Display MAXIMUM EmployeeCount for Accounts" + colorHeadline + "- type: 'max employeecount'" + colorMain + "                                        ║\n"
                + "║ 25. Display MINIMUM EmployeeCount for Accounts" + colorHeadline + "- type: 'min employeecount'" + colorMain + "                                        ║\n"
                + "║ 26. Display MEAN quantity of products for Opportunities" + colorHeadline + "- type: 'mean quantity'" + colorMain + "                                   ║\n"
                + "║ 27. Display MEDIAN quantity of products for Opportunities" + colorHeadline + "- type: 'median quantity'" + colorMain + "                               ║\n"
                + "║ 28. Display MAXIMUM quantity of products for Opportunities" + colorHeadline + "- type: 'max quantity'" + colorMain + "                                 ║\n"
                + "║ 29. Display MINIMUM quantity of products for Opportunities" + colorHeadline + "- type: 'min quantity'" + colorMain + "                                 ║\n"
                + "║ 30. Display MEAN number of Opportunities per Account" + colorHeadline + "- type: 'mean opps per account'" + colorMain + "                              ║\n"
                + "║ 31. Display MEDIAN number of Opportunities per Account" + colorHeadline + "- type: 'median opps per account'" + colorMain + "                          ║\n"
                + "║ 32. Display MAXIMUM number of Opportunities per Account" + colorHeadline + "- type: 'max opps per account'" + colorMain + "                            ║\n"
                + "║ 33. Display MINIMUM number of Opportunities per Account" + colorHeadline + "- type: 'min opps per account'" + colorMain + "                            ║\n"
                + "║ 34. To return to the main menu " + colorHeadline + "- type: 'main menu'" + colorMain + "                                                               ║\n"
                + "║ 35. To quit " + colorHeadline + "- type: 'quit'" + colorMain + "                                                                                       ║\n"
                + "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n" + reset);

        try {

            // Creates String from scanner input
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.length() < 1) {
                throw new IllegalArgumentException();
            } else {
                switch (ReportCommands.getCommandType(input)){
                    case REPORT_LEAD_BY_SALESREP:
                        var leadByRep = leadRepository.findCountLeadByRepName();
                        if(leadByRep.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < leadByRep.size(); i++) {
                                System.out.println(leadByRep.get(i)[0] + ": " + leadByRep.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_OPP_BY_SALESREP:
                        var oppByRep = opportunityRepository.findCountOpportunityByRepName();
                        if(oppByRep.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppByRep.size(); i++) {
                                System.out.println(oppByRep.get(i)[0] + ": " + oppByRep.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_CLOSE_W_BY_SALESREP:
                        var oppCloseW = opportunityRepository.findCountOpportunityByRepNameForStatus(Status.CLOSED_WON.toString());
                        if(oppCloseW.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCloseW.size(); i++) {
                                System.out.println(oppCloseW.get(i)[0] + ": " + oppCloseW.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_CLOSE_L_BY_SALESREP:
                        var oppCloseL = opportunityRepository.findCountOpportunityByRepNameForStatus(Status.CLOSED_LOST.toString());
                        if(oppCloseL.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCloseL.size(); i++) {
                                System.out.println(oppCloseL.get(i)[0] + ": " + oppCloseL.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_OPEN_BY_SALESREP:
                        var oppOpen = opportunityRepository.findCountOpportunityByRepNameForStatus(Status.OPEN.toString());
                        if(oppOpen.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppOpen.size(); i++) {
                                System.out.println(oppOpen.get(i)[0] + ": " + oppOpen.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_OPP_BY_PRODUCT:
                        var countOppProd = opportunityRepository.findCountOppForProduct();
                        if(countOppProd.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < countOppProd.size(); i++) {
                                System.out.println(countOppProd.get(i)[0] + ": " + countOppProd.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_CLOSE_W_BY_PRODUCT:
                        var countProdW = opportunityRepository.findCountOpportunityByProductForStatus(Status.CLOSED_WON.toString());
                        if(countProdW.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < countProdW.size(); i++) {
                                System.out.println(countProdW.get(i)[0] + ": " + countProdW.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_CLOSE_L_BY_PRODUCT:
                        var countProdL = opportunityRepository.findCountOpportunityByProductForStatus(Status.CLOSED_LOST.toString());
                        if(countProdL.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < countProdL.size(); i++) {
                                System.out.println(countProdL.get(i)[0] + ": " + countProdL.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_OPEN_BY_PRODUCT:
                        var countProdOp = opportunityRepository.findCountOpportunityByProductForStatus(Status.OPEN.toString());
                        if(countProdOp.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < countProdOp.size(); i++) {
                                System.out.println(countProdOp.get(i)[0] + ": " + countProdOp.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_OPP_BY_COUNTRY:
                        var oppCountry = opportunityRepository.findCountOppForCountry();
                        if(oppCountry.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCountry.size(); i++) {
                                System.out.println(oppCountry.get(i)[0] + ": " + oppCountry.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_CLOSE_W_BY_COUNTRY:
                        var oppCountryW = opportunityRepository.findCountOpportunityByCountryForStatus(Status.CLOSED_WON.toString());
                        if(oppCountryW.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCountryW.size(); i++) {
                                System.out.println(oppCountryW.get(i)[0] + ": " + oppCountryW.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_CLOSE_L_BY_COUNTRY:
                        var oppCountryL = opportunityRepository.findCountOpportunityByCountryForStatus(Status.CLOSED_LOST.toString());
                        if(oppCountryL.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCountryL.size(); i++) {
                                System.out.println(oppCountryL.get(i)[0] + ": " + oppCountryL.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_OPEN_BY_COUNTRY:
                        var oppCountryOp = opportunityRepository.findCountOpportunityByCountryForStatus(Status.OPEN.toString());
                        if(oppCountryOp.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCountryOp.size(); i++) {
                                System.out.println(oppCountryOp.get(i)[0] + ": " + oppCountryOp.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_OPP_BY_CITY:
                        var oppCountCity = opportunityRepository.findCountOppForCity();
                        if(oppCountCity.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCountCity.size(); i++) {
                                System.out.println(oppCountCity.get(i)[0] + ": " + oppCountCity.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_CLOSE_W_BY_CITY:
                        var oppCityW = opportunityRepository.findCountOpportunityByCityForStatus(Status.CLOSED_WON.toString());
                        if(oppCityW.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCityW.size(); i++) {
                                System.out.println(oppCityW.get(i)[0] + ": " + oppCityW.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_CLOSE_L_BY_CITY:
                        var oppCityL = opportunityRepository.findCountOpportunityByCityForStatus(Status.CLOSED_LOST.toString());
                        if(oppCityL.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCityL.size(); i++) {
                                System.out.println(oppCityL.get(i)[0] + ": " + oppCityL.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_OPEN_BY_CITY:
                        var oppCityOp = opportunityRepository.findCountOpportunityByCityForStatus(Status.OPEN.toString());
                        if(oppCityOp.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCityOp.size(); i++) {
                                System.out.println(oppCityOp.get(i)[0] + ": " + oppCityOp.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_OPP_BY_INDUSTRY:
                        var oppCountInd = opportunityRepository.findCountOppForIndustry();
                        if(oppCountInd.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCountInd.size(); i++) {
                                System.out.println(oppCountInd.get(i)[0] + ": " + oppCountInd.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_CLOSE_W_BY_INDUSTRY:
                        var oppIndW = opportunityRepository.findCountOpportunityByIndustryForStatus(Status.CLOSED_WON.toString());
                        if(oppIndW.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppIndW.size(); i++) {
                                System.out.println(oppIndW.get(i)[0] + ": " + oppIndW.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_CLOSE_L_BY_INDUSTRY:
                        var oppIndL = opportunityRepository.findCountOpportunityByIndustryForStatus(Status.CLOSED_LOST.toString());
                        if(oppIndL.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppIndL.size(); i++) {
                                System.out.println(oppIndL.get(i)[0] + ": " + oppIndL.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_OPEN_BY_INDUSTRY:
                        var oppIndOp = opportunityRepository.findCountOpportunityByIndustryForStatus(Status.OPEN.toString());
                        if(oppIndOp.isEmpty()){
                            System.out.println("There are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppIndOp.size(); i++) {
                                System.out.println(oppIndOp.get(i)[0] + ": " + oppIndOp.get(i)[1]);
                            }
                        }
                        break;
                    case MEAN_EMPCOUNT:
                        System.out.println("Average number of employees is: " + accountRepository.findMeanEmployeeCount().get().doubleValue());
                        break;
                    case MEDIAN_EMPCOUNT:
                        System.out.println("Median number of employees is: " + getMedian(accountRepository.findMedianEmployeeCountStep1()));
                        break;
                    case MAX_EMPCOUNT:
                        System.out.println("Maximum number of employees is: " + accountRepository.findMaxEmployeeCount().get());
                        break;
                    case MIN_EMPCOUNT:
                        System.out.println("Minimum number of employees is: " + accountRepository.findMaxEmployeeCount().get());
                        break;
                    case MEAN_QUANT:
                        System.out.println("Average quantity of trucks is: " + opportunityRepository.findMeanProductQuantity().get().doubleValue());
                        break;
                    case MED_QUANT:
                        System.out.println("Median quantity of trucks is: " + getMedian(opportunityRepository.findMedianQuantityStep1()));
                        break;
                    case MAX_QUANT:
                        System.out.println("Maximum quantity of trucks is: " + opportunityRepository.findMaxProductQuantity().get());
                        break;
                    case MIN_QUANT:
                        System.out.println("Minimum quantity of trucks is: " + opportunityRepository.findMinProductQuantity().get());
                        break;
                    case MEAN_OPPS_PERR_ACC:
                        System.out.println("Average number of opportunities per account is: " + opportunityRepository.findMeanOpportunitiesPerAccount().get().doubleValue());
                        break;
                    case MED_OPPS_PERR_ACC:
                        System.out.println("Median number of opportunities per account is: " + getMedian(opportunityRepository.findMedianOppsPerAccountStep1()));
                        break;
                    case MAX_OPPS_PERR_ACC:
                        System.out.println("Maximum number of opportunities per account is: " + opportunityRepository.findMaxOpportunitiesPerAccount().get());
                        break;
                    case MIN_OPPS_PERR_ACC:
                        System.out.println("Minimum number of opportunities per account is: " + opportunityRepository.findMinOpportunitiesPerAccount().get());
                        break;
                    case MAIN_MENU: OS();
                    break;
                    case QUIT:
                        System.out.println(colorMainBold + "\nThank you for using our LBL CRM SYSTEM!" + reset);
                    System.out.println(colorError + "Exiting the program" + reset);
                    System.exit(0);
                    break;
                    default: throw new IllegalArgumentException();
                }
            }
        } catch (IllegalArgumentException | NullPointerException  e) {
            System.out.println(colorError + "\nInvalid input" + reset);
        }

        System.out.println(colorInput + "\nPress Enter to continue..." + reset);
        scanner.nextLine();
        reportMenu();
    }

    public int getMedian(int[] intArray){
        try {
            int sizeOfArray = intArray.length;
            if (sizeOfArray % 2 == 1) {
                return intArray[(sizeOfArray + 1) / 2 - 1];
            } else {
                return (intArray[sizeOfArray / 2 - 1] + intArray[sizeOfArray / 2]) / 2;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return 0;
        }
    }

    public void OSGuest() throws RuntimeException, AWTException {

        System.out.println("\n" + colorHeadline + colorLogo
                                   + "                                                                                                \n" +
                                   "                                         *#### #####        ###################*   *####*         \n" +
                                   "                         #################### #####        ######################  #####          \n" +
                                   "                    ,######              ### #####        #####            ###### #####           \n" +
                                   "                  ####                  ### #####        #####    ############## #####            \n" +
                                   "                ####                   ### #####        #####      ###########  #####             \n" +
                                   "              ########################### #####        #####            ###### #####              \n" +
                                   "             ####################.###### ############ ###################### ############         \n" +
                                   "             ################ ####### # ############ #####################  ############          \n" + reset +
                                   colorHeadline + colorMain + "╔═══════════════════════════════════════════════════════════════════════════════════════════════════╗\n"
                                   + "║                                " + colorTable + "WELCOME TO LBL CRM SYSTEM" + colorMain + "                                          ║\n"
                                   + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                                   + "║     " + colorTable + "WHAT WOULD YOU LIKE TO DO " + /*Login.getUsername().toUpperCase() + */ "?" + colorMain + insertLine() + "║\n"
                                   + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                                   + "║ 1.  To check Leads list " + colorHeadline + "- type: 'show leads'" + colorMain + "                                                      ║\n"
                                   + "║ 2.  To check individual Lead's details " + colorHeadline + "- type: 'lookup lead ' + Lead Id" + colorMain + "                           ║\n"
                                   + "║ 3.  To check Opportunity list " + colorHeadline + "- type: 'show opportunities'" + colorMain + "                                        ║\n"
                                   + "║ 4.  To check individual Opportunity's details " + colorHeadline + "- type: 'lookup opportunity ' + Opportunity Id" + colorMain + "      ║\n"
                                   + "║ 5.  To check Contact list " + colorHeadline + "- type: 'show contacts'" + colorMain + "                                                 ║\n"
                                   + "║ 6.  To check Account list " + colorHeadline + "- type: 'show accounts'" + colorMain + "                                                 ║\n"
                                   + "║ 7.  To quit " + colorHeadline + "- type: 'quit'" + colorMain + "                                                                        ║\n"
                                   + "╚═══════════════════════════════════════════════════════════════════════════════════════════════════╝\n" + reset);

        consoleFocusRunOnce();

        try {

            // Creates String array from scanner input
            String[] input = scanner.nextLine().trim().toLowerCase().split("\\s+");

            if (input[0].equals("quit")) {
                System.out.println(colorMainBold + "\nThank you for using our LBL CRM SYSTEM!" + reset);
                System.out.println(colorError + "Exiting the program" + reset);
                System.exit(0);
            }else if (input[0].equals("lookup") && input[1].equals("lead")) {
                System.out.println(lookUpLeadId(input[2]));
            } else if (input[0].equals("lookup") && input[1].equals("opportunity")) {
                System.out.println(lookUpOppId(input[2]));
            } else {

                switch (input[0] + input[1]) {
                    case "new" + "lead" -> newLead();
                    case "show" + "leads" -> showLeads();
                    case "show" + "opportunities" -> showOpportunities();
                    case "show" + "contacts" -> showContacts();
                    case "show" + "accounts" -> showAccounts();
                    default -> throw new IllegalArgumentException();
                }
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(colorError + "\nInvalid input" + reset);

        }
        System.out.println(colorInput + "\nPress Enter to continue..." + reset);
        scanner.nextLine();
        OSGuest();
    }
}


