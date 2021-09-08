package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SalesRepTest {

    SalesRep sr1;
    Lead lead1;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, ExceedsMaxLength, PhoneNumberContainsLettersException, IdContainsLettersException {
        sr1 = new SalesRep(1L, "Sales Rep Name", new ArrayList<>(), new ArrayList<>());
        lead1 = new Lead("TestOne", "123546", "test1@test.gmail.com", "TestCompany1", sr1);
    }

    @AfterEach
    void tearDown() throws EmptyStringException, ExceedsMaxLength, IdContainsLettersException {
        sr1.removeLead(lead1);
    }

    @Test
    void setName_shouldWork() throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength {
        sr1.setName("Carlos Vasquéz");
        assertEquals("Carlos Vasquéz", sr1.getRepName());
    }

    @Test
    void setName_throwsException_EmptyString() {
        Assert.assertThrows(EmptyStringException.class, () -> sr1.setName(""));
    }

    @Test
    void setName_throwsException_ContainsNumbers() {
        Assert.assertThrows(NameContainsNumbersException.class, () -> sr1.setName("Lee1"));
    }

    @Test
    void setName_throwsException_ExceedsMaxCharacters() {
        Assert.assertThrows(ExceedsMaxLength.class, () -> sr1.setName("Arbitrary Name" +
                "to test if the name exceeds the max length of forty three characters"));
    }

   @Test
    void addLead_shouldWork() throws EmptyStringException, ExceedsMaxLength, IdContainsLettersException {
        int arraySizeBefore = sr1.getLeadList().size();
        sr1.addLead(lead1);
        int arraySizeAfter=sr1.getLeadList().size();
        assertEquals(arraySizeBefore+1, arraySizeAfter);
    }

   @Test
    void removeLead_shouldWork_ArrayNotEmpty() throws EmptyStringException, ExceedsMaxLength, IdContainsLettersException {
        sr1.addLead(lead1);
        int arraySizeBefore = sr1.getLeadList().size();
        sr1.removeLead(lead1);
        int arraySizeAfter=sr1.getLeadList().size();
        assertEquals(arraySizeBefore-1, arraySizeAfter);
    }

    @Test
    void removeLead_shouldWork_ArrayEmpty() throws EmptyStringException, ExceedsMaxLength, IdContainsLettersException {
        assertEquals(0,sr1.getLeadList().size());
        sr1.removeLead(lead1);
        int arraySizeAfter=sr1.getLeadList().size();
        assertEquals(0, arraySizeAfter);
    }


}