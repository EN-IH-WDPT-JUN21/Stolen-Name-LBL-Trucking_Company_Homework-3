package com.ironhack.stolen_name_trucking_company_homework_3.dao;

import com.ironhack.stolen_name_trucking_company_homework_3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
abstract class ReportMethods implements Variables{

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    SalesRepRepository salesRepRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ContactRepository contactRepository;

    /*void findCount(String header) {
        if (header.equals("Lead")) {
            var leadByRep = leadRepository.findCountLeadByRepName();
            if (leadByRep.isEmpty()) {
                System.out.println("\nThere are no entries matching reporting criteria");
            } else {
                System.out.println(printCountReport(header));
                for (int i = 0; i < leadByRep.size(); i++) {
                    //System.out.println(leadByRep.get(i)[0] + ": " + leadByRep.get(i)[1]);
                        System.out.println(String.format("%-1s %-45s %-1s %-22s %-1s",
                                      colorMain + "║",
                                      colorTable + leadByRep.get(i)[0],
                                      colorMain + "║",
                                      colorTable + leadByRep.get(i)[1],
                                      colorMain + "║"));
                    printTableRow(leadByRep, i);
                }
            }

        }
    }*/
    /*void findCountOpportunity(String s, String selector) {
        if (selector.equals("City") || selector.equals("Country") || selector.equals("Industry")) {
            var oppByRep = opportunityRepository.findCountOpportunity(selector);
            if (oppByRep.isEmpty()) {
                System.out.println("\nThere are no entries matching reporting criteria");
            } else {
                System.out.println(printCountReport(s));
                for (int i = 0; i < oppByRep.size(); i++) {
                    System.out.println(oppByRep.get(i)[0] + ": " + oppByRep.get(i)[1]);
                    printTableRow(oppByRep, i);
                }
            }
    }*/
}
