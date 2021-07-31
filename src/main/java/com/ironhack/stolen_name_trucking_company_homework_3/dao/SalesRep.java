package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sales_rep_id")
    private Long salesRepId;

    @Column(name="sales_rep_name")
    private String name;

    public SalesRep(String name){
        setName(name);
    }

}
