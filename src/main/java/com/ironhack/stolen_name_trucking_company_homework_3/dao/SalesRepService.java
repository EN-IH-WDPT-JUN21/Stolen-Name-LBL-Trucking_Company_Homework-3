package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.repository.LeadRepository;
import com.ironhack.stolen_name_trucking_company_homework_3.repository.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesRepService {

    @Autowired
    SalesRepRepository salesRepRepository;

    @Autowired
    LeadRepository leadRepository;

    public void addLeadToDb(Lead lead){
        leadRepository.save(lead);
    }
    //Not currently sure we need this
    public void removeLeadFromDb(Lead lead){
        leadRepository.delete(lead);
    }

   public void addSalesRep(SalesRep salesRep){
        salesRepRepository.save(salesRep);
    }
}
