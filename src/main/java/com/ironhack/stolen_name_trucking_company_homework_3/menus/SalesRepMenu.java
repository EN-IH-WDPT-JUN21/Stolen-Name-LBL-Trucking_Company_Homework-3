package com.ironhack.stolen_name_trucking_company_homework_3.menus;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.MainMenu;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.ReportMainMenu;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.Variables;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.ReportCommands;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.NoSuchValueException;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;

import java.awt.*;
import java.util.Scanner;

public class SalesRepMenu implements Variables {

    LeadRepository leadRepository;
    AccountRepository accountRepository;
    ContactRepository contactRepository;
    OpportunityRepository opportunityRepository;
    SalesRepRepository salesRepRepository;

    public void salesRepMenu() throws NoSuchValueException, AWTException {

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
                                   + "║ 1. Display count of Leads by Sales Representative " + colorHeadline + "- type: 'report lead by salesrep'" + colorMain + "                             ║\n"
                                   + "║ 2. Display count of all Opportunities by Sales Representative " + colorHeadline + "- type: 'report opportunity by salesrep'" + colorMain + "          ║\n"
                                   + "║ 3. Display count of CLOSED-WON Opportunities by Sales Representative " + colorHeadline + "- type: 'report closed-won by salesrep'" + colorMain + "    ║\n"
                                   + "║ 4. Display count of CLOSED-LOST Opportunities by Sales Representative " + colorHeadline + "- type: 'report closed-lost by salesrep'" + colorMain + "  ║\n"
                                   + "║ 5. Display count of OPEN Opportunities by Sales Representative " + colorHeadline + "- type: - 'report open by salesrep'" + colorMain + "              ║\n"
                                   + "║ 6. To return to the Report menu " + colorHeadline + "- type: 'back'" + colorMain + "                                                               ║\n"
                                   + "║ 7. To return to the Main menu " + colorHeadline + "- type: 'main menu'" + colorMain + "                                                               ║\n"
                                   + "║ 8. To quit " + colorHeadline + "- type: 'quit'" + colorMain + "                                                                                       ║\n"
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
                    case BACK: new ReportMainMenu().reportMainMenu();
                    case MAIN_MENU: new MainMenu().OS();
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
        salesRepMenu();
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
}
