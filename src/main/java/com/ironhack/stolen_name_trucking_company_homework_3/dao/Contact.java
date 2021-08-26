package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "contact")
public class Contact extends Lead {

    //public static Map<String, Contact> theContacts = new HashMap<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToOne(mappedBy = "decisionMaker")
    private Opportunity opportunity;

    private static final String colorMain = "\u001B[33m";
    private static final String colorMainBold = "\033[1;37m";
    private static final String colorTable = "\u001B[32m";
    private static final String colorHeadlineBold = "\033[1;34m";
    private static final String reset = "\u001B[0m";

    public Contact(String name, String phoneNumber, String email, String companyName) throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {
        super(name, phoneNumber, email, companyName);
    }
    public Contact(String name, String phoneNumber, String email, String companyName, SalesRep salesRep) throws NameContainsNumbersException, EmptyStringException, EmailNotValidException, PhoneNumberContainsLettersException, ExceedsMaxLength {
        super(name, phoneNumber, email, companyName, salesRep);
    }

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public String toString() {
        return  String.format("%-1s %-15s %-1s %-48s %-1s %-25s %-1s %-45s %-1s %-48s %-1s\n",
                              colorMain + "║",
                              colorTable + id,
                              colorMain + "║",
                              colorTable + name.toUpperCase(),
                              colorMain + "║",
                              colorTable + phoneNumber,
                              colorMain + "║",
                              colorTable + email.toUpperCase(),
                              colorMain + "║",
                              colorTable + companyName.toUpperCase(),
                              colorMain + "║"+ reset);
    }
}
