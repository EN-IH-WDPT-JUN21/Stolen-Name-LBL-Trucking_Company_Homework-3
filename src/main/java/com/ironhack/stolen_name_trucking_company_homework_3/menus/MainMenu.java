package com.ironhack.stolen_name_trucking_company_homework_3.menus;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.*;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.*;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

@Component
public class MainMenu implements Variables {

    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;

    @Autowired
    ReportMainMenu reportMainMenu;

    @Autowired
    SalesRepReportMenu salesRepReportMenu;

    public MainMenu() {
    }

    private static boolean wasRun = false;
    private static boolean valid;


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
                + String.format("%-1s %-104s %-1s","║", colorTable + "WHAT WOULD YOU LIKE TO DO " + Login.getUsername().toUpperCase() + "?", colorMain + /*insertLine(68) +*/ "║\n")
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
            /*}else if(input[0].equals("rep")) {
                var leadByRep = leadRepository.findCountLeadByRepName();
                if(leadByRep.isEmpty()){
                    System.out.println("\nThere are no entries matching reporting criteria");
                } else {
                    System.out.println(printCountReport("Lead"));
                    for (int i = 0; i < leadByRep.size(); i++) {
                        printTableRow(leadByRep, i);
                    }
                }*/
            } else if (input.length < 2) {
                throw new IllegalArgumentException();
            }
            else if (input[0].equals("lookup") && input[1].equals("lead") && input.length>2) {
                if(!leadRepository.existsById(Long.parseLong(input[2]))){
                    throw new NoSuchValueException("There is no Lead that matches that id.");
                }
                lookUpLeadId(Long.parseLong(input[2]));
            } else if (input[0].equals("lookup") && input[1].equals("opportunity") && input.length>2) {
                if(!opportunityRepository.existsById(Long.parseLong(input[2]))){
                    throw new NoSuchValueException("There is no Opportunity that matches that id.");
                }
                lookUpOppId(input[2]);
            } else if (input[0].equals("convert")) { // throws null point exception if number not in array
                if(!leadRepository.existsById(Long.parseLong(input[1]))){
                    throw new NoSuchValueException("There is no Lead that matches that id.");
                }
                convertLead(input[1]);
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
                    case "view" + "reports" -> reportMainMenu.reportMainMenu();
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
        Scanner scanner = new Scanner(System.in);
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

                    /*valid = false;

                    //checks if restrictions for Company name are met
                    while (!valid) {
                        System.out.println(colorInput + "\nPlease input Sales Representative id: " + reset);
                        try {
                            newLead.setSalesRep(salesRepRepository.getById(Long.parseLong(scanner.nextLine().trim())));
                            valid = true;
                        }catch(EmptyStringException | ExceedsMaxLength e){
                            System.out.println(colorError + e.getMessage());
                        }
                    }*/

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
        Scanner scanner = new Scanner(System.in);
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
                        } catch (EmptyStringException | InvalidEnumException e) {
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
                        } catch (NumberFormatException e) {
                            System.out.println(colorError + "You need to input a reasonable number. Please, try again.");
                        } catch (IllegalArgumentException e) {
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
                    leadRepository.delete(lead);
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
                    System.out.println(colorInput + "Would you like to create a new Account?" + colorTable + " y / n" + reset);
                    switch (scanner.nextLine().trim().toLowerCase(Locale.ROOT)) {
                        case "y" -> {
                            createAccount(newOpp);
                        }
                        case "n" -> {

                            while (!valid) {
                                System.out.println(colorInput + "Please, input the account number you wish to link the " + colorTable + "Opportunity " + newOpp.getId() + colorInput + " to: " + reset);
                                //Account account = accountRepository.findById(Long.parseLong(scanner.nextLine().trim())).get();
                                try {
                                   // account.addContact(newContact);
                                   // account.addOpportunity(newOpp);
                                    Account account = accountRepository.findById(Long.parseLong(scanner.nextLine().trim())).get();
                                    newOpp.setAccount(account);
                                    valid = true;
                                } catch (IllegalArgumentException e) {
                                    System.out.println(colorError + "There is no account with this number. Please, try again" + reset);
                                }
                            }
                            valid = false;

                            newOpp.getDecisionMaker().setAccount(newOpp.getAccount());
                            contactRepository.save(newOpp.getDecisionMaker());
                            opportunityRepository.save(newOpp);
                            accountRepository.findById(newOpp.getAccount().getId()).get().addOpportunity(newOpp);
                            accountRepository.findById(newOpp.getAccount().getId()).get().addContact(newOpp.getDecisionMaker());
                            System.out.println(colorInput + "The Opportunity has been linked to " + colorInput + newOpp.getAccount().getCompanyName() + reset);
                            System.out.println(colorInput + "Press Enter to continue..." + reset);
                        }
                    }
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


    public void lookUpLeadId(Long id) throws RuntimeException {
        System.out.println(colorMain + "\n╔════════════╦═════ " + colorMainBold + "Lead details" + colorMain + " ══════════════════════════╦══════════════════════╦══════════════════════════════════════════╦═════════════════════════════════════════════╗" + reset);
        System.out.println(leadRepository.findById(id).get());
    }

    // lookup opportunity by Id
    public void lookUpOppId(String id) throws RuntimeException {
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
                              colorMain + "╠════════════╬══════════════════════╬═══════════════════╬═══════════════════╣\n" + reset+
                                  opportunityRepository.findById(Long.parseLong(id)).get() +
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
                              opportunityRepository.findById(Long.parseLong(id)).get().getDecisionMaker().toString()));
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
        Scanner scanner = new Scanner(System.in);
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

    public SalesRep newSalesRep() {

        valid = false;

        System.out.println(colorInput + "\nWould you like to create a new sales representative?" + colorTable +"   y / n " + reset);
        Scanner scanner = new Scanner(System.in);
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
        var allReps = salesRepRepository.findAllSalesReps();
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
                                   + String.format("%-1s %-73s %-1s", colorTable + "WHAT WOULD YOU LIKE TO DO " + Login.getUsername().toUpperCase() + "?", colorMain + /*insertLine(68) +*/ "║\n")
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
                //System.out.println(lookUpLeadId(input[2]));
                lookUpLeadId(Long.parseLong(input[2]));
            } else if (input[0].equals("lookup") && input[1].equals("opportunity")) {
                //System.out.println(lookUpOppId(input[2]));
                lookUpOppId(input[2]);
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


