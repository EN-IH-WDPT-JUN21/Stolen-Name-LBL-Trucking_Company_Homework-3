package com.ironhack.stolen_name_trucking_company_homework_3.menus;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.Variables;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.ReportCommands;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.NoSuchValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ReportMainMenu implements Variables {

    @Autowired
    MainMenu mainMenu;
    @Autowired
    SalesRepReportMenu salesRepReportMenu;
    @Autowired
    ProductReportMenu productReportMenu;
    @Autowired
    CountryReportMenu countryReportMenu;
    @Autowired
    CityReportMenu cityReportMenu;
    @Autowired
    IndustryReportMenu industryReportMenu;
    @Autowired
    EmployeeCountReportMenu employeeCountReportMenu;
    @Autowired
    QuantityReportMenu quantityReportMenu;
    @Autowired
    OpportunityReportMenu opportunityReportMenu;

    public void reportMainMenu() throws NoSuchValueException, AWTException {

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
                                   colorMain + "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n"
                                   + "║                                " + colorTable + "WELCOME TO LBL CRM SYSTEM REPORTING MENU" + colorMain + "                                          ║\n"
                                   + "╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                                   + "║ 1.  Display all reports for Sales Representatives " + colorHeadline + "- type: 'salesrep'" + colorMain + "                                             ║\n"
                                   + "║ 2.  Display all reports for Products " + colorHeadline + "- type: 'product'" + colorMain + "                                                           ║\n"
                                   + "║ 3.  Display all reports for Countries " + colorHeadline + "- type: 'country'" + colorMain + "                                                          ║\n"
                                   + "║ 4.  Display all reports for Cities " + colorHeadline + "- type: 'city'" + colorMain + "                                                                ║\n"
                                   + "║ 5.  Display all reports for Industries " + colorHeadline + "- type: 'industry'" + colorMain + "                                                        ║\n"
                                   + "║ 6.  Display all reports for Employee Count States" + colorHeadline + "- type: 'employee'" + colorMain + "                                              ║\n"
                                   + "║ 7.  Display all reports for Quantity States " + colorHeadline + "- type: 'quantity'" + colorMain + "                                                   ║\n"
                                   + "║ 8.  Display all reports for Opportunity states " + colorHeadline + "- type: 'opportunity'" + colorMain + "                                             ║\n"
                                   + "║ 9.  To return to the main menu " + colorHeadline + "- type: 'main menu'" + colorMain + "                                                               ║\n"
                                   + "║ 10. To quit " + colorHeadline + "- type: 'quit'" + colorMain + "                                                                                       ║\n"
                                   + "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n" + reset);

        try {

            // Creates String from scanner input
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.length() < 1) {
                throw new IllegalArgumentException();
            } else {
                switch (ReportCommands.getCommandType(input)){
                    case SALESREP:
                        while(true) {
                            salesRepReportMenu.salesRepReportMenu();
                        }
                    case PRODUCT:
                        while(true) {
                            productReportMenu.productReportMenu();
                        }
                    case COUNTRY:
                        while(true) {
                            countryReportMenu.countryReportMenu();
                        }
                    case CITY:
                        while(true) {
                            cityReportMenu.cityReportMenu();
                        }
                    case INDUSTRY:
                        while(true) {
                            industryReportMenu.industryReportMenu();
                        }
                    case EMPLOYEE:
                        while(true) {
                            employeeCountReportMenu.employeeCountReportMenu();
                        }
                    case QUANTITY:
                        while(true) {
                            quantityReportMenu.quantityReportMenu();
                        }
                    case OPPORTUNITY:
                        while(true) {
                            opportunityReportMenu.opportunityReportMenu();
                        }
                    case MAIN_MENU: mainMenu.OS();
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
        reportMainMenu();
    }
}
