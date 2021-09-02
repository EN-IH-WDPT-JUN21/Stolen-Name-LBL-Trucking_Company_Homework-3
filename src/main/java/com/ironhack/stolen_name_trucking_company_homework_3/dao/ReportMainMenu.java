package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.enums.ReportCommands;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.NoSuchValueException;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ReportMainMenu implements Variables {

    LeadRepository leadRepository;
    AccountRepository accountRepository;
    ContactRepository contactRepository;
    OpportunityRepository opportunityRepository;
    SalesRepRepository salesRepRepository;

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
                                   + "║ 1.  Display all reports for Sales Representative " + colorHeadline + "- type: 'salesrep'" + colorMain + "                             ║\n"
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
                    case SALESREP:
                        salesRepMenu.salesRepMenu();
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
        reportMainMenu();
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
