package com.ironhack.stolen_name_trucking_company_homework_3.repository;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.Lead;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LeadRepository extends JpaRepository<Lead, String> {

    //Report Lead by SalesRep
    @Query("SELECT r.repName, COUNT(l) FROM Lead l RIGHT JOIN l.salesRep r GROUP BY r.repName ORDER BY r.repName")
    List<Object[]> findCountLeadByRepName();



}
