package com.ironhack.stolen_name_trucking_company_homework_3.dao;//Extends com.ironhack.stolen_name_trucking_company_homework_3.ClientInformation class to retain Unique ID incrementing.

import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.ExceedsMaxLength;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name = "opportunity")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Opportunity extends ClientInformation {

    // This sets the status to Enum Open whenever an opportunity object is created
    @Enumerated(EnumType.STRING)
    private Status status = Status.OPEN;

    // com.ironhack.stolen_name_trucking_company_homework_3.Opportunity Specific variable - EnumTruck com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck, int quantity, ObjectContact DecisionMaker
    @Enumerated(EnumType.STRING)
    private Truck product;
    private Integer quantity;


    @OneToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact decisionMaker;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    @ManyToOne
    @JoinColumn(name = "sales_rep_id", referencedColumnName = "id")
    private SalesRep salesRep;


    private static final String colorMain = "\u001B[33m";
    private static final String colorMainBold = "\033[1;37m";
    private static final String colorTable = "\u001B[32m";
    private static final String colorHeadlineBold = "\033[1;34m";
    private static final String reset = "\u001B[0m";

    public Opportunity() {
    }

    public Opportunity(Truck product, int quantity, Contact decisionMaker) throws ExceedsMaxLength {
        setTruck(product);
        setQuantity(quantity);
        setDecisionMaker(decisionMaker);
    }

    public Opportunity(Truck product, int quantity, Contact decisionMaker, SalesRep salesRep) throws ExceedsMaxLength {
        setTruck(product);
        setQuantity(quantity);
        setDecisionMaker(decisionMaker);
        setSalesRep(salesRep);
    }


    public String getId() {
        return id;
    }

    public Truck getProduct() {
        return product;
    }

    public void setTruck(Truck product) {
        this.product = product;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws ExceedsMaxLength {
         if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive. Please try again.");
        }
        this.quantity = quantity;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public String toString() {
        return  String.format("%-1s %-15s %-1s %-25s %-1s %-22s %-1s %-22s %-1s\n",
                              colorMain + "║",
                              colorTable + id,
                              colorMain + "║",
                              colorTable + status,
                              colorMain + "║",
                              colorTable + product,
                              colorMain + "║",
                              colorTable + quantity,
                              colorMain + "║"+ reset);
    }
}
