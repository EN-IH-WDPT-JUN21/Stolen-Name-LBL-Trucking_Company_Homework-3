package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.EmptyStringException;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.ExceedsMaxLength;
import com.ironhack.stolen_name_trucking_company_homework_3.exceptions.NameContainsNumbersException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales_rep")
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="sales_rep_name")
    private String repName;

    @OneToMany(mappedBy = "salesRep")
    private List<Lead> leadList = new ArrayList<>();

    @OneToMany(mappedBy = "salesRep")
    private List<Opportunity> opportunityList = new ArrayList<>();

    public SalesRep(String name){
        setRepName(name);
    }

    public void setName(String repName) throws NameContainsNumbersException, EmptyStringException, ExceedsMaxLength {
        if (repName.isEmpty()) {
            throw new EmptyStringException("No name input. Please try again.");
        }
        else if(!repName.matches("[a-zA-Z\\u00C0-\\u00FF\\s]+")){
            throw new NameContainsNumbersException( "Name can not contain numbers. Please try again.");
        } else if(repName.length()>43){
            throw new ExceedsMaxLength("Exceeds maximum value of 43 characters. Please try again.");
        }

        this.repName = repName;
    }

}
