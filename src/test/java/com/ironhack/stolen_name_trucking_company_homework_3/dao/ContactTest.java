package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    Contact contact1;

    @BeforeEach
    void setUp() throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength, EmailNotValidException, PhoneNumberContainsLettersException {
        contact1 = new Contact("TestOne", "123546", "test1@test.gmail.com", "TestCompany1");
    }

    @Test
    void setName_shouldWork() throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength {
        contact1.setName("Carlos Vasquéz");
        assertEquals("Carlos Vasquéz", contact1.getName());
    }

    @Test
    void setName_throwsException_EmptyString() {
        Assert.assertThrows(EmptyStringException.class, () -> { contact1.setName("");});
    }

    @Test
    void setName_throwsException_ContainsNumbers() {
        Assert.assertThrows(NameContainsNumbersException.class, () -> { contact1.setName("Lee1");});
    }

    @Test
    void setName_throwsException_ExceedsMaxCharacters() {
        Assert.assertThrows(ExceedsMaxLength.class, () -> { contact1.setName("Arbitrary Name" +
                "to test if the name exceeds the max length of forty three characters");});
    }


    @Test
    void setPhoneNumber_shouldWork() throws EmptyStringException, ExceedsMaxLength, PhoneNumberContainsLettersException {
        contact1.setPhoneNumber("123456");
        assertEquals("123456", contact1.getPhoneNumber());

    }

    @Test
    void setPhoneNumber_throwsException_EmptyString() {
        Assert.assertThrows(EmptyStringException.class, () -> { contact1.setPhoneNumber("");});
    }

    @Test
    void setPhoneNumber_throwsException_ContainsNumbers() {
        Assert.assertThrows(PhoneNumberContainsLettersException.class, () -> { contact1.setPhoneNumber("123a56");});
    }

    @Test
    void setPhoneNumber_throwsException_ExceedsMaxCharacters() {
        Assert.assertThrows(ExceedsMaxLength.class, () -> { contact1.setPhoneNumber("12345678901234567890123");});
    }

    @Test
    void setEmail_shouldWork() throws EmptyStringException, EmailNotValidException, ExceedsMaxLength {
        contact1.setEmail("contact1@mail.com");
        assertEquals("contact1@mail.com", contact1.getEmail());
    }

    @Test
    void setEmail_throwsException_EmptyString() {
        Assert.assertThrows(EmptyStringException.class, () -> { contact1.setEmail("");});
    }

    @Test
    void setEmail_throwsException_InvalidEmail() {

        //Wrong Format - Missing @
        Assert.assertThrows(EmailNotValidException.class, () -> { contact1.setEmail("contact1mail.com");});

        //Wrong Format - Missing .
        Assert.assertThrows(EmailNotValidException.class, () -> { contact1.setEmail("contact1@mailcom");});

        // Invalid TLD
        Assert.assertThrows(EmailNotValidException.class, () -> { contact1.setEmail("contact1@mail.crom");});
    }

    @Test
    void setEmail_throwsException_ExceedsMaxCharacters() {
        Assert.assertThrows(ExceedsMaxLength.class, () -> { contact1.setEmail("lead1withalotofcharactersfortesting@mail.com");});
    }

    @Test
    void setCompanyName_shouldWork() throws EmptyStringException, ExceedsMaxLength {
        contact1.setCompanyName("Company of Contact 1");
        assertEquals("Company of Contact 1", contact1.getCompanyName());
    }


    @Test
    void setCompanyName_throwsException_EmptyString() {
        Assert.assertThrows(EmptyStringException.class, () -> { contact1.setCompanyName("");});
    }

    @Test
    void setCompanyName_throwsException_ExceedsMaxCharacters() {
        Assert.assertThrows(ExceedsMaxLength.class, () -> { contact1.setCompanyName("This is an extra long company" +
                "name with some extra characters");});
    }


}