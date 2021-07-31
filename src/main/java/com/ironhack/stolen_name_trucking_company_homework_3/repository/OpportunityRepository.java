package com.ironhack.stolen_name_trucking_company_homework_3.repository;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpportunityRepository extends JpaRepository<Opportunity, String> {

    //Report Opportunities by SalesRep
    @Query("SELECT r.repName, COUNT(o) FROM Opportunity o JOIN o.salesRep r GROUP BY r.repName ORDER BY r.repName")
    List<Object[]> findCountOpportunitySalesRepIdGroup();

    //Report Opportunities by Product
    @Query("SELECT o.product, count(o) FROM Opportunity o GROUP BY o.product ORDER BY o.product")
    List<Object[]> findCountOppForProduct();
}
