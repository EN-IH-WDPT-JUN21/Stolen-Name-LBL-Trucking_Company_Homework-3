package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class OpportunityTest {

    Opportunity opp;

    @BeforeEach
    void setUp() throws ExceedsMaxLength, NameContainsNumbersException, EmptyStringException, EmailNotValidException,
            PhoneNumberContainsLettersException {
         opp = new Opportunity(Truck.BOX, 200, new Contact("TestOne", "123546",
                "test1@test.gmail.com", "TestCompany1"));
    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void setQuantity_shouldWork() throws ExceedsMaxLength {
        opp.setQuantity(23);
        assertEquals(23, opp.getQuantity());
    }

    @Test
    void setQuantity_throwsException_IllegalArgument() {
        Assert.assertThrows(IllegalArgumentException.class, () -> { opp.setQuantity(-3);});
    }


}