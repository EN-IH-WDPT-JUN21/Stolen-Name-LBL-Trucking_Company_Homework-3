package com.ironhack.stolen_name_trucking_company_homework_3.menus;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.MainMenu;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.ReportMainMenu;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.Variables;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.ReportCommands;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.NoSuchValueException;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class IndustryReportMenu implements Variables {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    ReportMainMenu reportMainMenu;

    @Autowired
    MainMenu mainMenu;

    public void industryReportMenu() throws NoSuchValueException, AWTException {

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
                                   + "║                                                 " + colorTable + "INDUSTRY REPORTING MENU" + colorMain + "                                          ║\n"
                                   + "╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                                   + "║ 1. Display count of all Opportunities by Industry " + colorHeadline + "- type: - 'report opportunity by industry'" + colorMain + "                     ║\n"
                                   + "║ 2. Display count of CLOSED-WON Opportunities by Industry " + colorHeadline + "- type: 'report closed-won by industry'" + colorMain + "                 ║\n"
                                   + "║ 3. Display count of CLOSED-LOST Opportunities by Industry" + colorHeadline + "- type: 'report closed-lost by industry'" + colorMain + "                ║\n"
                                   + "║ 4. Display count of OPEN Opportunities by Industry" + colorHeadline + "- type: 'report open by industry'" + colorMain + "                              ║\n"
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
        industryReportMenu();
    }
}
