package com.ironhack.stolen_name_trucking_company_homework_3.enums;

public enum ConsoleTextColors {
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

    ConsoleTextColors(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
