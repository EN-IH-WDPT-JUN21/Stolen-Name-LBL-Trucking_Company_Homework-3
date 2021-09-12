package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.enums.ConsoleTextColors;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.ReportCommands;

import java.util.List;
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

    default String printCountReport(String header) {

        StringBuilder printCountStringBuilder = new StringBuilder(
                colorMain + "\n╔════ " + colorMainBold + "Report" + colorMain + " ════════════════════════════╦══════════════════════╗")
                .append(String.format("\n%-1s %-45s %-1s %-27s %-1s\n",
                                      colorMain + "║",
                                      colorHeadlineBold + "Name",
                                      colorMain + "║",
                                      colorHeadlineBold + header + " count",
                                      colorMain + "║"))
                .append(String.format("%-1s%-40s%-1s%-22s%-1s",
                                      colorMain + "╠",
                                      "════════════════════════════════════════",
                                      "╬",
                                      "══════════════════════",
                                      "╣"));
        return printCountStringBuilder.toString();
    }

    default void printTableRow(List<Object[]> list, int i) {
        System.out.println(String.format("%-1s %-45s %-1s %-27s %-1s",
                                         colorMain + "║",
                                         colorTable + list.get(i)[0],
                                         colorMain + "║",
                                         colorTable + list.get(i)[1],
                                         colorMain + "║"));
    }

    // Adjusts number of characters printed for different usernames
    default StringBuilder insertLine(int length) {
        StringBuilder line = new StringBuilder();
        for (int i = 1; i < (length - Login.getUsername().length()); i++) {
            line.append(" ");
        }
        return line;
    }

}
