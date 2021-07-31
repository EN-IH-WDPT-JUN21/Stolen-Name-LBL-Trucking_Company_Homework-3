package com.ironhack.stolen_name_trucking_company_homework_3.dao;

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
    //@Column(name="sales_rep_id")
    private Long id;

    @Column(name="sales_rep_name")
    private String name;

    @OneToMany(mappedBy = "salesRep")
    private List<Lead> leadList = new ArrayList<>();

    @OneToMany(mappedBy = "salesRep")
    private List<Opportunity> opportunityList = new ArrayList<>();

    public SalesRep(String name){
        setName(name);
    }

}
