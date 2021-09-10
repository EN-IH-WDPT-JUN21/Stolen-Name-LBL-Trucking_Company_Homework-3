package com.ironhack.stolen_name_trucking_company_homework_3.enums;

import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.EmptyStringException;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.InvalidEnumException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndustryTest {

    @Test
    void getIndustry_produceCase() throws EmptyStringException, InvalidEnumException {
        assertEquals(Industry.PRODUCE, Industry.getIndustry("PRODUCE"));
    }
    @Test
    void getIndustry_ecommerceCase() throws EmptyStringException, InvalidEnumException {
        assertEquals(Industry.ECOMMERCE, Industry.getIndustry("ECOMMERCE"));
    }

    @Test
    void getIndustry_medicalCase() throws EmptyStringException, InvalidEnumException {
        assertEquals(Industry.MEDICAL, Industry.getIndustry("MEDICAL"));
    }

    @Test
    void getIndustry_otherCase() throws EmptyStringException, InvalidEnumException {
        assertEquals(Industry.OTHER, Industry.getIndustry("OTHER"));
    }

    @Test
    void getIndustry_manufacturingCase() throws EmptyStringException, InvalidEnumException {
        assertEquals(Industry.MANUFACTURING, Industry.getIndustry("MANUFACTURING"));
    }

    @Test
    void getIndustry_defaultCase() throws EmptyStringException, InvalidEnumException {
        Assert.assertThrows(InvalidEnumException.class, () -> {Industry.getIndustry("RANDOM STRING");});
    }

    @Test
    void getIndustry_emptyString() {
        Assert.assertThrows(EmptyStringException.class, () -> {Industry.getIndustry("");});
    }

}