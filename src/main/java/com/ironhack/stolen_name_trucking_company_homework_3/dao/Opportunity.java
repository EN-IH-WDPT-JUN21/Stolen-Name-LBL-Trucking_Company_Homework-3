package com.ironhack.stolen_name_trucking_company_homework_3.dao;//Extends com.ironhack.stolen_name_trucking_company_homework_3.ClientInformation class to retain Unique ID incrementing.

import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Truck;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.ExceedsMaxLength;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "opportunity")
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This sets the status to Enum Open whenever an opportunity object is created
    @Enumerated(EnumType.STRING)
    private Status status = Status.OPEN;

    @Enumerated(EnumType.STRING)
    private Truck product;
    private Integer quantity;


    @ManyToOne
    @JoinColumn(name = "decision_maker", referencedColumnName = "id")
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

    public Truck getProduct() {
        return product;
    }

    public void setTruck(Truck product) {
        this.product = product;
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
