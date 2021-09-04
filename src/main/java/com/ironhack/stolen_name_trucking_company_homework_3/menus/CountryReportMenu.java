package com.ironhack.stolen_name_trucking_company_homework_3.menus;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.Variables;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.ReportCommands;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.NoSuchValueException;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class CountryReportMenu implements Variables {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    ReportMainMenu reportMainMenu;

    @Autowired
    MainMenu mainMenu;

    public void countryReportMenu() throws NoSuchValueException, AWTException {

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
                                   + "║                                                 " + colorTable + "COUNTRY REPORTING MENU" + colorMain + "                                           ║\n"
                                   + "╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                                   + "║ 1. Display count of all Opportunities by Country " + colorHeadline + "- type: - 'report opportunity by country'" + colorMain + "                       ║\n"
                                   + "║ 2. Display count of CLOSED-WON Opportunities by Country " + colorHeadline + "- type: 'report closed-won by country'" + colorMain + "                   ║\n"
                                   + "║ 2. Display count of CLOSED-LOST Opportunities by Country" + colorHeadline + "- type: 'report closed-lost by country'" + colorMain + "                  ║\n"
                                   + "║ 4. Display count of OPEN Opportunities by Country" + colorHeadline + "- type: 'report open by country'" + colorMain + "                                ║\n"
                                   + "║ 5. To return to the Report menu " + colorHeadline + "- type: 'back'" + colorMain + "                                                                   ║\n"
                                   + "║ 6. To return to the Main menu " + colorHeadline + "- type: 'main menu'" + colorMain + "                                                                ║\n"
                                   + "║ 7. To quit " + colorHeadline + "- type: 'quit'" + colorMain + "                                                                                        ║\n"
                                   + "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n" + reset);

        try {

            // Creates String from scanner input
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.length() < 1) {
                throw new IllegalArgumentException();
            } else {
                switch (ReportCommands.getCommandType(input)){
                    case REPORT_OPP_BY_COUNTRY:
                        var oppCountry = opportunityRepository.findCountOppForCountry();
                        if(oppCountry.isEmpty()){
                            System.out.println("\nThere are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCountry.size(); i++) {
                                System.out.println(oppCountry.get(i)[0] + ": " + oppCountry.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_CLOSE_W_BY_COUNTRY:
                        var oppCountryW = opportunityRepository.findCountOpportunityByCountryForStatus(Status.CLOSED_WON.toString());
                        if(oppCountryW.isEmpty()){
                            System.out.println("\nThere are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCountryW.size(); i++) {
                                System.out.println(oppCountryW.get(i)[0] + ": " + oppCountryW.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_CLOSE_L_BY_COUNTRY:
                        var oppCountryL = opportunityRepository.findCountOpportunityByCountryForStatus(Status.CLOSED_LOST.toString());
                        if(oppCountryL.isEmpty()){
                            System.out.println("\nThere are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCountryL.size(); i++) {
                                System.out.println(oppCountryL.get(i)[0] + ": " + oppCountryL.get(i)[1]);
                            }
                        }
                        break;
                    case REPORT_OPEN_BY_COUNTRY:
                        var oppCountryOp = opportunityRepository.findCountOpportunityByCountryForStatus(Status.OPEN.toString());
                        if(oppCountryOp.isEmpty()){
                            System.out.println("\nThere are no entries matching reporting criteria");
                        } else {
                            for (int i = 0; i < oppCountryOp.size(); i++) {
                                System.out.println(oppCountryOp.get(i)[0] + ": " + oppCountryOp.get(i)[1]);
                            }
                        }
                        break;
                    case BACK: reportMainMenu.reportMainMenu();
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
        countryReportMenu();
    }
}