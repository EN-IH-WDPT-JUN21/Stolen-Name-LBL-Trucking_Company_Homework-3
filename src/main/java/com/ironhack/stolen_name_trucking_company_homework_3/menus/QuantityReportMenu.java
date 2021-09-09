package com.ironhack.stolen_name_trucking_company_homework_3.menus;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.Variables;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.ReportCommands;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.NoSuchValueException;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class QuantityReportMenu implements Variables {

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    ReportMainMenu reportMainMenu;

    @Autowired
    MainMenu mainMenu;

    public void quantityReportMenu() throws NoSuchValueException, AWTException {

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
                                   + "║                                                 " + colorTable + "QUANTITY REPORTING MENU" + colorMain + "                                          ║\n"
                                   + "╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                                   + "║ 1. Display MEAN quantity of products for Opportunities" + colorHeadline + "- type: 'mean quantity'" + colorMain + "                                    ║\n"
                                   + "║ 2. Display MEDIAN quantity of products for Opportunities" + colorHeadline + "- type: 'median quantity'" + colorMain + "                                ║\n"
                                   + "║ 3. Display MAXIMUM quantity of products for Opportunities" + colorHeadline + "- type: 'max quantity'" + colorMain + "                                  ║\n"
                                   + "║ 4. Display MINIMUM quantity of products for Opportunities" + colorHeadline + "- type: 'min quantity'" + colorMain + "                                  ║\n"
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
                    case MEAN_QUANT:
                        System.out.println("Average quantity of trucks is: " + opportunityRepository.findMeanProductQuantity().get());
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
        quantityReportMenu();
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
