package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class Contact{

    //public static Map<String, Contact> theContacts = new HashMap<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "decisionMaker")
    private List<Opportunity> opportunity = new ArrayList<>();

    private static final String colorMain = "\u001B[33m";
    private static final String colorMainBold = "\033[1;37m";
    private static final String colorTable = "\u001B[32m";
    private static final String colorHeadlineBold = "\033[1;34m";
    private static final String reset = "\u001B[0m";

    public Contact(String name, String phoneNumber, String email, String companyName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
    }

    public Contact(String name, String phoneNumber, String email, String companyName, SalesRep salesRep) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesRep = salesRep;
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


    public void setCompanyName(String companyName) throws EmptyStringException, ExceedsMaxLength {
        if (companyName.isEmpty()) {
            throw new EmptyStringException("No company name input. Please, try again.");
        } else if (companyName.length()>43){
            throw new ExceedsMaxLength("Exceeds maximum value of 43 characters. Please, try again.");
        }

        this.companyName = companyName;
    }

}
