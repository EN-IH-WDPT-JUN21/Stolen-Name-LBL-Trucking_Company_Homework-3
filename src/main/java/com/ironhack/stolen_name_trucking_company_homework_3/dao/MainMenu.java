package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.enums.Industry;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


@Component
public class MainMenu {


    final LeadRepository leadRepository;
    final AccountRepository accountRepository;
    final ContactRepository contactRepository;
    final OpportunityRepository opportunityRepository;
    final SalesRepRepository salesRepRepository;

    public static Map<String, Lead> theLeads = new HashMap<>();
    public static Map<String, Account> theAccounts = new HashMap<>();
    public static Map<String, Contact> theContacts = new HashMap<>();
    public static Map<String, Opportunity> theOpportunities = new HashMap<>();
    public static Map<String, SalesRep> theSalesReps = new HashMap<>();

    Scanner scanner = new Scanner(System.in);

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
                + "║     " + colorTable + "WHAT WOULD YOU LIKE TO DO " /*+ Login.getUsername().toUpperCase()*/ +  "?" + colorMain /*+ insertLine()*/ + "║\n"
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
                + "║ 13. To check available Reports " + colorHeadline + "- type: 'view reports'" + colorMain + "                                             ║\n" //shall we create another 'menu' explaining reporting options?
                + "║ 14. To quit " + colorHeadline + "- type: 'quit'" + colorMain + "                                                                        ║\n"
                + "╚═══════════════════════════════════════════════════════════════════════════════════════════════════╝\n" + reset);

        //consoleFocusRunOnce();

        try {

            // Creates String array from scanner input
            String[] input = scanner.nextLine().trim().toLowerCase().split("\\s+");

            if (input[0].equals("quit")) {
                System.out.println(colorMainBold + "\nThank you for using our LBL CRM SYSTEM!" + reset);
                System.out.println(colorError + "Exiting the program" + reset);
                System.exit(0);

            } else if (input.length < 2) {
                throw new IllegalArgumentException();
            }
            else if (input[0].equals("lookup") && input[1].equals("lead") && input.length>2) {
                if(!theLeads.containsKey((input[2]).toString())){
                    throw new NoSuchValueException("There is no Lead that matches that id.");
                }
                System.out.println(lookUpLeadId(input[2]).toString());
            } else if (input[0].equals("lookup") && input[1].equals("opportunity") && input.length>2) {
                if(!theOpportunities.containsKey((input[2]).toString())){
                    throw new NoSuchValueException("There is no Opportunity that matches that id.");
                }
                System.out.println(lookUpOppId(input[2]).toString());
            } else if (input[0].equals("convert")) { // throws null point exception if number not in array
                if(!theLeads.containsKey((input[1]).toString())){
                    throw new NoSuchValueException("There is no Lead that matches that id.");
                }
                createAccount(convertLead(input[1]));
            } else if (input[0].equals("close-lost")) {
                if(!theOpportunities.containsKey((input[1]).toString())){
                    throw new NoSuchValueException("There is no Opportunity that matches that id.");
                }
                closeLost(input[1]);
            } else if (input[0].equals("close-won")) {
                if(!theOpportunities.containsKey((input[1]).toString())){
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
        catch (NoSuchValueException e){
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

                    //asks and validates customer's name
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

                    //asks and validates customer's phone number
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the customer's phone number: " + reset);
                        try{
                            newLead.setPhoneNumber(scanner.nextLine().trim().toUpperCase());
                            valid = true;
                        }catch (EmptyStringException | PhoneNumberContainsLettersException | ExceedsMaxLength e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;


                    //asks and validates customer's e-mail address
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


                    //asks and validates customer's company name
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the customer's company name: " + reset);
                        try {
                            newLead.setCompanyName(scanner.nextLine().trim().toUpperCase());
                            valid = true;
                        }catch(EmptyStringException | ExceedsMaxLength e){
                            System.out.println(colorError + e.getMessage());
                        }

                    }

                    theLeads.put(newLead.getId(), newLead);
                    System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "New Lead created" + colorMain + " ══════════════════════╦══════════════════════╦══════════════════════════════════════════╦═════════════════════════════════════════════╗" + reset);
                    System.out.println(theLeads.get(newLead.getId()));
                    leadRepository.save(newLead);
                    return newLead;
                }
                case "n" -> // Would normally go back in the menu at this point
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

        Lead lead = theLeads.get(id);
        System.out.println(colorInput + "\nWould you like to convert " +
                           colorTable + lead.getName() +
                           colorInput + " from " +
                           colorTable + lead.getCompanyName() +
                           colorInput + " into an opportunity?" +
                           colorTable + "    y / n " + reset);

        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    Opportunity newOpp = new Opportunity();

                    valid = false;

                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the product that " + colorTable + lead.getCompanyName() + colorInput + " is interested in: \n " +
                                colorTable + "HYBRID, FLATBED OR BOX" + reset);
                        try {
                            newOpp.setTruck(Truck.getTruck(scanner.nextLine().trim().toUpperCase(Locale.ROOT)));
                            valid = true;
                        }catch (EmptyStringException e) {
                            System.out.println(colorError + e.getMessage());
                        } catch (InvalidEnumException e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;

                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input the quantity that " + colorTable + lead.getCompanyName() + colorInput + " is interested in: " + reset);

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

                    Contact newContact = new Contact(lead.getName().toUpperCase(), lead.getPhoneNumber().toUpperCase(), lead.getEmail().toUpperCase(), lead.getCompanyName().toUpperCase()); // Converts lead into contact
                    contactRepository.save(newContact);
                    newOpp.setDecisionMaker(newContact); // Assigns contact as the decisionMaker
                    theContacts.put(newContact.getId(), newContact);  // Adds contact to contact Map
                    theOpportunities.put(newOpp.getId(), newOpp); // Adds Opportunity to opportunities map
                    theLeads.remove(lead.getId()); // Removes converted lead from Leads map ("Database")
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
                    System.out.println(theOpportunities.get(newOpp.getId()));
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
                    System.out.println(theContacts.get(newContact.getId()));
                    System.out.println(colorInput + "Press Enter to continue..." + reset);
                    scanner.nextLine();
                    opportunityRepository.save(newOpp);

                    return newOpp;
                    //createAccount(newContact, newOpp); // Not sure whether to put this here or in Menu
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
        String country;
        try {

            Account newAccount = new Account(opportunity.getDecisionMaker(), opportunity);

            valid = false;

            while (!valid) {

                System.out.println(colorInput + "\nPlease input the company industry: \n" +
                        colorTable + "PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL OR OTHER" + reset);

                try {
                    newAccount.setIndustry(Industry.getIndustry(scanner.nextLine().trim().toUpperCase(Locale.ROOT))); // ENUM Selection
                    valid = true;
                } catch (EmptyStringException e) {
                    System.out.println(colorError + e.getMessage());
                } catch (InvalidEnumException e) {
                    System.out.println(colorError + e.getMessage());
                }
            }
            valid = false;

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

            while (!valid) {
                System.out.println(colorInput + "\nPlease input the city for " + colorTable + newAccount.getCompanyName() + colorInput + ":  " + reset);

                try {
                    newAccount.setCity(scanner.nextLine().trim().toUpperCase(Locale.ROOT));
                    valid = true;
                }catch (EmptyStringException e) {
                    System.out.println(colorError + e.getMessage());
                } catch (NameContainsNumbersException e) {
                    System.out.println(colorError + e.getMessage());
                } catch (ExceedsMaxLength e) {
                    System.out.println(colorError + e.getMessage());
                }
            }
            valid = false;

            while (!valid) {
                System.out.println(colorInput + "\nPlease input the Country for " + newAccount.getCompanyName() + ":  " + reset);
                try {
                    newAccount.setCountry(scanner.nextLine().trim().toUpperCase());
                    valid = true;
                } catch (EmptyStringException e) {
                    System.out.println(colorError + e.getMessage());
                }catch(ExceedsMaxLength e){
                    System.out.println(colorError + e.getMessage());
                }catch(InvalidCountryException e){
                    System.out.println(colorError + e.getMessage());
                }
            }

            valid = false;


            theAccounts.put(newAccount.getId(), newAccount); // Adds new account to Accounts Map (database)
            //System.out.println(colorMain + "\n ═════════════ New Account Created ═════════════\n");
            System.out.println(theAccounts.get(newAccount.getId()));
            accountRepository.save(newAccount);
            opportunity.setAccount(newAccount);
            opportunityRepository.save(opportunity);
            opportunity.getDecisionMaker().setAccount(newAccount);
            contactRepository.save(opportunity.getDecisionMaker());
            return newAccount;
        } catch (Exception e) {

            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            createAccount(opportunity); // Catches errors and returns to start of method - Is there a better way??
        }
        return null;
    }

    public void showLeads() {
        System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "TOTAL NUMBER OF LEADS: " + theLeads.size() + colorMain + " ════════════════╗" + reset);
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
        for (String key : theLeads.keySet()) {
            System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                              colorMain + "║",
                              colorTable + key,
                              colorMain + "║",
                              colorTable + theLeads.get(key).getName().toUpperCase(),
                              colorMain + "║" + reset);
        }
    }

    public void showContacts() {
        System.out.println(colorMain + "\n╔════════════╦════════ " + colorMainBold + "Total Number Of Contacts: " + theContacts.size() + colorMain + " ════════╦══════════════════════════════════════════╗" + reset);
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
        for (String key : theContacts.keySet()) {
            System.out.printf("%-1s %-17s %-1s %-50s %-1s %-47s %-1s\n",
                              colorMain + "║",
                              colorTable + key,
                              colorMain + "║",
                              colorTable + theContacts.get(key).getName().toUpperCase(),
                              colorMain + "║",
                              colorTable + theContacts.get(key).getCompanyName().toUpperCase(),
                              colorMain + "║" + reset);
        }
    }

    public static void showOpportunities() {
        System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "Total Number Of Opportunities: " + theOpportunities.size() + colorMain + " ══════╦══════════════════════════════════════════╗" + reset);
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
        for (String key : theOpportunities.keySet()) {
            System.out.printf("%-1s %-17s %-1s %-24s %-1s %-17s %-1s %-17s %-1s %-47s %-1s\n",
                              colorMain + "║",
                              colorTable + key,
                              colorMain + "║",
                              colorTable + theOpportunities.get(key).getStatus(),
                              colorMain + "║",
                              colorTable + theOpportunities.get(key).getProduct(),
                              colorMain + "║",
                              colorTable + theOpportunities.get(key).getQuantity(),
                              colorMain + "║",
                              colorTable + theOpportunities.get(key).getDecisionMaker().getName().toUpperCase(),
                              colorMain + "║" + reset);
        }
    }

    public static void showAccounts() {
        System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Accounts: " + theAccounts.size() + colorMain + " ═════════════╗" + reset);
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
        for (String key : theAccounts.keySet()) {
            System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                              colorMain + "║",
                              colorTable + key,
                              colorMain + "║",
                              colorTable + theAccounts.get(key).getCompanyName().toUpperCase(),
                              colorMain + "║" + reset);
        }
    }


    public Lead lookUpLeadId(String id) throws RuntimeException {

        System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "Lead details" + colorMain + " ══════════════════════════╦══════════════════════╦══════════════════════════════════════════╦═════════════════════════════════════════════╗" + reset);
        return theLeads.get(id);
    }

    //look up opportunity by Id
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
        return theOpportunities.get(id) +
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
                              colorMain + "║\n" +
                                      colorMain + "╠════════════╬═════════════════════════════════════════════╬══════════════════════╬══════════════════════════════════════════╬═════════════════════════════════════════════╣\n"
                                      + reset +
                              theOpportunities.get(id).getDecisionMaker());
    }

    //Change opportunity status to LOST
    public void closeLost(String id) {
        Opportunity opp = theOpportunities.get(id);
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
        // Scanner scanner = new Scanner(System.in);
        try {
            switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                case "y": {
                    opp.setStatus(Status.CLOSED_LOST);
                    opportunityRepository.save(opp);
                    System.out.println(colorMain + "\n═════════════ " + colorMainBold + "Status Changed!" + colorMain + " ═════════════" + reset);
                }
                break;
                case "n": {
                    OS();
                }
                break;

                default:
                    throw new IllegalArgumentException(colorError + "Invalid input - please try again" + reset);
            }

        } catch (Exception e) {
            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            closeLost(id);
        }
    }

    //Change opportunity status to Won
    public void closeWon(String id) {
        Opportunity opp = theOpportunities.get(id);
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
                case "y": {
                    opp.setStatus(Status.CLOSED_WON);
                    opportunityRepository.save(opp);
                    System.out.println(colorMain + "\n═════════════ " + colorMainBold + "Status Changed!" + colorMain + " ═════════════" + reset);
                }
                break;
                case "n": {
                    OS();
                }
                break;

                default:
                    throw new IllegalArgumentException(colorError + "Invalid input - please try again" + reset);
            }

        } catch (Exception e) {
            System.out.println(colorError + "\nInvalid input - please start again\n" + reset);
            closeWon(id);
        }
    }

    public void consoleFocus() throws AWTException {
        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_4);
        robot.keyRelease(KeyEvent.VK_4);
        robot.keyRelease(KeyEvent.VK_ALT);
    }

    public void consoleFocusRunOnce() throws AWTException {
        if (!wasRun) {
            wasRun = true;
            consoleFocus();
        }
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
                        } catch (EmptyStringException e) {
                            System.out.println(colorError + e.getMessage());
                        } catch (NameContainsNumbersException e) {
                            System.out.println(colorError + e.getMessage());
                        } catch (ExceedsMaxLength e) {
                            System.out.println(colorError + e.getMessage());
                        }
                    }

                    valid = false;
                    salesRepRepository.save(newSalesRep);
                    theSalesReps.put(newSalesRep.getId().toString(), newSalesRep);
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

    public static void showSalesReps() {
        System.out.println(colorMain + "\n╔════════════╦═══ " + colorMainBold + "Total Number Of Sales Representatives: " + theSalesReps.size() + colorMain + " ╗" + reset);
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
        for (String key : theSalesReps.keySet()) {
            System.out.printf("%-1s %-17s %-1s %-50s %-1s\n",
                    colorMain + "║",
                    colorTable + key,
                    colorMain + "║",
                    colorTable + theSalesReps.get(key).getRepName().toUpperCase(),
                    colorMain + "║" + reset);
        }
    }


    public static void reportMenu(){

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
                + "║     " + colorTable + "REPORTING MENU " /*+ Login.getUsername().toUpperCase()*/  + colorMain /*+ insertLine()*/ + "║\n"
                + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                + "║ 1.  Display count of Leads by Sales Representative " + colorHeadline + "- type: 'report lead by salesrep'" + colorMain + "                                                         ║\n"
                + "║ 2.  Display count of all Opportunities by Sales Representative " + colorHeadline + "- type: 'report opportunity by salesrep'" + colorMain + "                                     ║\n"
                + "║ 3.  Display count of CLOSED-WON Opportunities by Sales Representative " + colorHeadline + "- type: 'report closed-won by salesrep'" + colorMain + "                                                      ║\n"
                + "║ 4.  Display count of CLOSED-LOST Opportunities by Sales Representative " + colorHeadline + "- type: 'report closed-lost by salesrep'" + colorMain + "                           ║\n"
                + "║ 5.  Display count of OPEN Opportunities by Sales Representative " + colorHeadline + "- type: - 'report open by salesrep'" + colorMain + "                               ║\n"
                + "║ 6.  Display count of all Opportunities by the Product " + colorHeadline + "- type: 'report opportunity by the product'" + colorMain + "                                        ║\n"
                + "║ 7.  Display count of CLOSED-WON Opportunities by the Product " + colorHeadline + "- type: 'report closed-won by the product'" + colorMain + "      ║\n"
                + "║ 8.  Display count of CLOSED-LOST Opportunities by the Product" + colorHeadline + "- type: 'report closed-lost by the product'" + colorMain + "                       ║\n"
                + "║ 9.  Display count of OPEN Opportunities by the Product" + colorHeadline + "- type: 'report open by the product'" + colorMain + "                     ║\n"
                + "║ 10. Display count of all Opportunities by Country " + colorHeadline + "- type: - 'report opportunity by country'" + colorMain + "                                                 ║\n"
                + "║ 11. Display count of CLOSED-WON Opportunities by Country " + colorHeadline + "- type: 'report closed-won by country'" + colorMain + "                                                 ║\n"
                + "║ 12. Display count of CLOSED-LOST Opportunities by Country" + colorHeadline + "- type: 'report closed-lost by country'" + colorMain + "                                  ║\n"
                + "║ 13. Display count of OPEN Opportunities by Country" + colorHeadline + "- type: 'report open by country'" + colorMain + "                     ║\n"
                + "║ 14. Display count of all Opportunities by City " + colorHeadline + "- type: - 'report opportunity by city'" + colorMain + "                                                 ║\n"
                + "║ 15. Display count of CLOSED-WON Opportunities by City " + colorHeadline + "- type: 'report closed-won by city'" + colorMain + "                                                 ║\n"
                + "║ 16. Display count of CLOSED-LOST Opportunities by City" + colorHeadline + "- type: 'report closed-lost by city'" + colorMain + "                                  ║\n"
                + "║ 17. Display count of OPEN Opportunities by City" + colorHeadline + "- type: 'report open by city'" + colorMain + "                     ║\n"
                + "║ 18. Display count of all Opportunities by Industry " + colorHeadline + "- type: - 'report opportunity by industry'" + colorMain + "                                                 ║\n"
                + "║ 19. Display count of CLOSED-WON Opportunities by Industry " + colorHeadline + "- type: 'report closed-won by industry'" + colorMain + "                                                 ║\n"
                + "║ 20. Display count of CLOSED-LOST Opportunities by Industry" + colorHeadline + "- type: 'report closed-lost by industry'" + colorMain + "                                  ║\n"
                + "║ 21. Display count of OPEN Opportunities by Industry" + colorHeadline + "- type: 'report open by industry'" + colorMain + "                     ║\n"
                + "║ 22. Display MEAN EmployeeCount for Accounts" + colorHeadline + "- type: 'mean employeecount'" + colorMain + "                     ║\n"
                + "║ 23. Display MEDIAN EmployeeCount for Accounts" + colorHeadline + "- type: 'median employeecount'" + colorMain + "                     ║\n"
                + "║ 24. Display MAXIMUM EmployeeCount for Accounts" + colorHeadline + "- type: 'max employeecount'" + colorMain + "                     ║\n"
                + "║ 25. Display MINIMUM EmployeeCount for Accounts" + colorHeadline + "- type: 'min employeecount'" + colorMain + "                     ║\n"
                + "║ 26. Display MEAN quantity of products for Opportunities" + colorHeadline + "- type: 'mean quantity'" + colorMain + "                     ║\n"
                + "║ 27. Display MEDIAN quantity of products for Opportunities" + colorHeadline + "- type: 'median quantity'" + colorMain + "                     ║\n"
                + "║ 28. Display MAXIMUM quantity of products for Opportunities" + colorHeadline + "- type: 'max quantity'" + colorMain + "                     ║\n"
                + "║ 29. Display MINIMUM quantity of products for Opportunities" + colorHeadline + "- type: 'min quantity'" + colorMain + "                     ║\n"
                + "║ 30. Display MEAN number of Opportunities per Account" + colorHeadline + "- type: 'mean opps per account'" + colorMain + "                     ║\n"
                + "║ 31. Display MEDIAN number of Opportunities per Account" + colorHeadline + "- type: 'median opps per account'" + colorMain + "                     ║\n"
                + "║ 32. Display MAXIMUM number of Opportunities per Account" + colorHeadline + "- type: 'max opps per account'" + colorMain + "                     ║\n"
                + "║ 33. Display MINIMUM number of Opportunities per Account" + colorHeadline + "- type: 'min opps per account'" + colorMain + "                     ║\n"
                + "║ 34. To return to the main menu " + colorHeadline + "- type: 'main menu'" + colorMain + "                                             ║\n" //shall we create another 'menu' explaining reporting options?
                + "║ 35. To quit " + colorHeadline + "- type: 'quit'" + colorMain + "                                                                        ║\n"
                + "╚═══════════════════════════════════════════════════════════════════════════════════════════════════╝\n" + reset);

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
                                   + "║     " + colorTable + "WHAT WOULD YOU LIKE TO DO " + /*Login.getUsername().toUpperCase() + */ "?" + colorMain /*+ insertLine()*/ + "║\n"
                                   + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                                   + "║ 1.  To check Leads list " + colorHeadline + "- type: 'show leads'" + colorMain + "                                                      ║\n"
                                   + "║ 2.  To check individual Lead's details " + colorHeadline + "- type: 'lookup lead ' + Lead Id" + colorMain + "                           ║\n"
                                   + "║ 3.  To check Opportunity list " + colorHeadline + "- type: 'show opportunities'" + colorMain + "                                        ║\n"
                                   + "║ 4.  To check individual Opportunity's details " + colorHeadline + "- type: 'lookup opportunity ' + Opportunity Id" + colorMain + "      ║\n"
                                   + "║ 5.  To check Contact list " + colorHeadline + "- type: 'show contacts'" + colorMain + "                                                 ║\n"
                                   + "║ 6.  To check Account list " + colorHeadline + "- type: 'show accounts'" + colorMain + "                                                 ║\n"
                                   + "║ 7.  To quit " + colorHeadline + "- type: 'quit'" + colorMain + "                                                                        ║\n"
                                   + "╚═══════════════════════════════════════════════════════════════════════════════════════════════════╝\n" + reset);

        //consoleFocusRunOnce();

        try {

            // Creates String array from scanner input
            String[] input = scanner.nextLine().trim().toLowerCase().split("\\s+");

            if (input[0].equals("quit")) {
                System.out.println(colorMainBold + "\nThank you for using our LBL CRM SYSTEM!" + reset);
                throw new RuntimeException(colorError + "Exiting the program" + reset);
            }else if (input[0].equals("lookup") && input[1].equals("lead")) {
                System.out.println(lookUpLeadId(input[2]).toString());
            } else if (input[0].equals("lookup") && input[1].equals("opportunity")) {
                System.out.println(lookUpOppId(input[2]).toString());
            } else {

                switch (input[0] + input[1]) {
                    //String x = input.substring(input.indexOf("Lead") + 3, input.length());
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


