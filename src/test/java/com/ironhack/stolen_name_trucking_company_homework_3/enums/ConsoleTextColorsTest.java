package com.ironhack.stolen_name_trucking_company_homework_3.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTextColorsTest {

    @Test
    void getColor_shouldWork() {
        assertEquals("\033[0;33m", ConsoleTextColors.ANSI_YELLOW.getColor() );
    }
}