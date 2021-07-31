package com.ironhack.stolen_name_trucking_company_homework_3.dao;//For creating basic Leads. Extends Client information in order to retain a unique ID counter.

import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "leads")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Lead extends ClientInformation {

    @Column(name="contact_name")
    protected String name;

    @Column(name= "phone_number")
    protected String phoneNumber;

    protected String email;

    @Column(name="company_name")
    protected String companyName;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id", referencedColumnName = "id")
    private SalesRep salesRep;


    private static final String colorMain = "\u001B[33m";
    private static final String colorMainBold = "\033[1;37m";
    private static final String colorTable = "\u001B[32m";
    private static final String colorHeadlineBold = "\033[1;34m";
    private static final String reset = "\u001B[0m";

    public Lead() {
    }

    public Lead(String name, String phoneNumber, String email, String companyName) throws NameContainsNumbersException, EmptyStringException, PhoneNumberContainsLettersException, EmailNotValidException, ExceedsMaxLength {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
    }

    //New Reporting constructor for testing purposes, to replace existing constructor above later
    public Lead(String name, String phoneNumber, String email, String companyName, SalesRep salesRep) throws NameContainsNumbersException, EmptyStringException, PhoneNumberContainsLettersException, EmailNotValidException, ExceedsMaxLength {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
        setSalesRep(salesRep);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength {
        if (name.isEmpty()) {
            throw new EmptyStringException("No name input. Please try again.");
        }
        else if(!name.matches("[a-zA-Z\\u00C0-\\u00FF\\s]+")){
            throw new NameContainsNumbersException( "Name can not contain numbers. Please try again.");
        } else if(name.length()>43){
            throw new ExceedsMaxLength("Exceeds maximum value of 43 characters. Please try again.");
        }

        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws EmptyStringException, PhoneNumberContainsLettersException, ExceedsMaxLength {


        if (phoneNumber.isEmpty()) {
            throw new EmptyStringException("No Phone Number input. Please try again.");
        }
        else if(!phoneNumber.matches("[0-9]+")) {
            throw new PhoneNumberContainsLettersException("The phone number must only contain numbers. Please try again.");
        } else if(phoneNumber.length()>20) {
            throw new ExceedsMaxLength("Exceeds maximum value of 20 characters. Please try again.");
        }

        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailNotValidException, EmptyStringException, ExceedsMaxLength {
        if (email.isEmpty()) {
            throw new EmptyStringException("No email input. Please, try again.");
        }
        else if (!EmailValidator.getInstance().isValid(email)){
            throw new EmailNotValidException("The email should follow the format \"***@***.**\". Please, try again.");
        } else if (email.length()>40){
            throw new ExceedsMaxLength("Exceeds maximum value of 40 characters. Please, try again.");
        }

        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) throws EmptyStringException, ExceedsMaxLength {
        if (companyName.isEmpty()) {
            throw new EmptyStringException("No company name input. Please, try again.");
        } else if (companyName.length()>43){
            throw new ExceedsMaxLength("Exceeds maximum value of 43 characters. Please, try again.");
        }

        this.companyName = companyName;
    }


    public String getId() {
        return id;
    }

    public String toString() {
        System.out.printf(String.format("%-1s %-17s %-1s %-50s %-1s %-27s %-1s %-47s %-1s %-50s %-1s \n",
                                        colorMain + "║",
                                        colorHeadlineBold + "ID",
                                        colorMain + "║",
                                        colorHeadlineBold + "Name",
                                        colorMain + "║",
                                        colorHeadlineBold + "Phone Number",
                                        colorMain + "║",
                                        colorHeadlineBold + "Email Address",
                                        colorMain + "║",
                                        colorHeadlineBold + "Company Name",
                                        colorMain + "║\n" +
                                        colorMain + "╠════════════╬═════════════════════════════════════════════╬══════════════════════╬══════════════════════════════════════════╬═════════════════════════════════════════════╣"
                                        + reset));
        return  String.format("%-1s %-15s %-1s %-48s %-1s %-25s %-1s %-45s %-1s %-48s %-1s\n",
                              colorMain + "║",
                              colorTable + getId(),
                              colorMain + "║",
                              colorTable + getName().toUpperCase(),
                              colorMain + "║",
                              colorTable + getPhoneNumber(),
                              colorMain + "║",
                              colorTable + getEmail().toUpperCase(),
                              colorMain + "║",
                              colorTable + getCompanyName().toUpperCase(),
                              colorMain + "║"+ reset);
    }

}


