package com.ironhack.stolen_name_trucking_company_homework_3.enums;

import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.EmptyStringException;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.InvalidEnumException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TruckTest {

    @Test
    void getTruck_hybridCase() throws EmptyStringException, InvalidEnumException {
        assertEquals(Truck.HYBRID, Truck.getTruck("HYBRID"));
    }
    @Test
    void getTruck_flatbedCase() throws EmptyStringException, InvalidEnumException {
        assertEquals(Truck.FLATBED, Truck.getTruck("FLATBED"));
    }

    @Test
    void getTruck_boxCase() throws EmptyStringException, InvalidEnumException {
        assertEquals(Truck.BOX, Truck.getTruck("BOX"));
    }

    @Test
    void getTruck_defaultCase() throws EmptyStringException, InvalidEnumException {
        Assert.assertThrows(InvalidEnumException.class, () -> {Truck.getTruck("RANDOM STRING");});
    }

    @Test
    void getTruck_emptyString() {
        Assert.assertThrows(EmptyStringException.class, () -> {Truck.getTruck("");});
    }

}