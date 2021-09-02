package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.enums.ConsoleTextColors;
import com.ironhack.stolen_name_trucking_company_homework_3.menus.SalesRepMenu;

import java.util.Scanner;

public interface Variables {

    String colorMain = ConsoleTextColors.ANSI_YELLOW.getColor();
    String colorMainBold = ConsoleTextColors.WHITE_BOLD.getColor();
    String colorInput = ConsoleTextColors.CYAN_BOLD.getColor();
    String colorHeadline = ConsoleTextColors.ANSI_BLUE.getColor();
    String colorHeadlineBold = ConsoleTextColors.BLUE_BOLD.getColor();
    String colorTable = ConsoleTextColors.GREEN_BOLD.getColor();
    String colorError = ConsoleTextColors.ANSI_RED.getColor();
    String colorLogo = ConsoleTextColors.GREEN_BOLD.getColor();
    String reset = ConsoleTextColors.ANSI_RESET.getColor();

    Scanner scanner = new Scanner(System.in);

    SalesRepMenu salesRepMenu = new SalesRepMenu();

}
