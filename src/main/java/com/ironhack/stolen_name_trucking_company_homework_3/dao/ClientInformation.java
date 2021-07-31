package com.ironhack.stolen_name_trucking_company_homework_3.dao;//Holds and increments the Unique ID for all created objects

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class ClientInformation {


    static Integer uniqueID = 0;
    @Id
    protected String id;

    public Integer generateID() {
        uniqueID++;
        return uniqueID;
    }

    public ClientInformation() {
        id = generateID().toString();
    }

    public static Integer getUniqueID() {
        return uniqueID;
    }

    public static void setUniqueID(Integer uniqueID) {
        ClientInformation.uniqueID = uniqueID;
    }
}
