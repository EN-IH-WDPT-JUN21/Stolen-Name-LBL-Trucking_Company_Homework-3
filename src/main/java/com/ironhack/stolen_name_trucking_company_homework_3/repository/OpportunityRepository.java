package com.ironhack.stolen_name_trucking_company_homework_3.repository;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.Opportunity;
import com.ironhack.stolen_name_trucking_company_homework_3.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, String> {

    //Report Opportunities by SalesRep
    @Query("SELECT r.repName, COUNT(o) FROM Opportunity o JOIN o.salesRep r GROUP BY r.repName ORDER BY r.repName")
    List<Object[]> findCountOpportunitySalesRepIdGroup();

    //Report CLOSED-WON, CLOSED-LOST, and OPEN opportunities by SalesRep (takes a parameter argument)
    @Query("SELECT r.repName, COUNT(o) FROM Opportunity o Join o.salesRep r WHERE status = :status GROUP BY r.repName ORDER BY r.repName")
    List<Object[]> findCountOpportunityByRepNameForStatus(@Param("status") String status);

    //Report Opportunities by Product
    @Query("SELECT o.product, count(o) FROM Opportunity o GROUP BY o.product ORDER BY o.product")
    List<Object[]> findCountOppForProduct();

    //Report CLOSED-WON, CLOSED-LOST, and OPEN opportunities by Product (takes a parameter argument)
    @Query("SELECT o.product, COUNT(o) FROM Opportunity o WHERE status = :status  GROUP BY o.product ORDER BY o.product")
    List<Object[]> findCountOpportunityByProductForStatus(@Param("status") Status status);

    //Report Opportunities by Country
    @Query("SELECT r.country, COUNT(o) FROM Opportunity o JOIN o.account r GROUP BY r.country ORDER BY r.country")
    List<Object[]> findCountOppForCountry();

    //Report CLOSED-WON, CLOSED-LOST, and OPEN opportunities by Country (takes a parameter argument)
    @Query("SELECT r.country, COUNT(o) FROM Opportunity o JOIN o.account r  WHERE status = :status  GROUP BY r.country ORDER BY r.country")
    List<Object[]> findCountOpportunityByCountryForStatus(@Param("status") String status);

    //Report Opportunities by City
    @Query("SELECT r.city, COUNT(o) FROM Opportunity o JOIN o.account r GROUP BY r.city ORDER BY r.city")
    List<Object[]> findCountOppForCity();

    //Report CLOSED-WON, CLOSED-LOST, and OPEN opportunities by City (takes a parameter argument)
    @Query("SELECT r.city, COUNT(o) FROM Opportunity o JOIN o.account r  WHERE status = :status  GROUP BY r.city ORDER BY r.city")
    List<Object[]> findCountOpportunityByCityForStatus(@Param("status") String status);

    //Report Opportunities by Industry
    @Query("SELECT r.industry, COUNT(o) FROM Opportunity o JOIN o.account r GROUP BY r.industry ORDER BY r.industry")
    List<Object[]> findCountOppForIndustry();

    //Report CLOSED-WON, CLOSED-LOST, and OPEN opportunities by Industry (takes a parameter argument)
    @Query("SELECT r.industry, COUNT(o) FROM Opportunity o JOIN o.account r  WHERE status = :status  GROUP BY r.industry ORDER BY r.industry")
    List<Object[]> findCountOpportunityByIndustryForStatus(@Param("status") String status);

    //Report mean number products quantity for all Opportunities
    @Query("SELECT AVG(quantity) FROM Opportunity")
    Optional<Double> findMeanProductQuantity();

    // *** Median Report is needed JPQL can give list of all quantities in an ordered int array, needs a second step to find the median from this ***
    @Query("SELECT quantity FROM Opportunity order by quantity")
    int[]findMedianQuantityStep1();

    //Report Maximum  employee count for all Accounts
    @Query("SELECT MAX(quantity) FROM Opportunity")
    Optional<Integer> findMaxProductQuantity();

    //Report Minimum  employee count for all Accounts
    @Query("SELECT MIN(quantity) FROM Opportunity")
    Optional<Integer> findMinProductQuantity();

    //Report Mean number of Opportunities associated with an account
    @Query(value = "select avg(a.count_opportunity) from (select count(distinct id) as count_opportunity from opportunity group by account_id order by count_opportunity) a", nativeQuery = true)
    Optional<Double>findMeanOpportunitiesPerAccount();

    // *** Median Report is needed JPQL can give list of all opportunitycounts in an ordered int array, needs a second step to find the median from this ***
    @Query(value = "select count(distinct id) as count_opportunity from opportunity group by account_id order by count_opportunity", nativeQuery = true)
    int[]findMedianOppsPerAccountStep1();

    //Report Max number of Opportunities associated with an account
    @Query(value = "select max(a.count_opportunity) from (select count(distinct id) as count_opportunity from opportunity group by account_id order by count_opportunity) a", nativeQuery = true)
    Optional<Double>findMaxOpportunitiesPerAccount();

    //Report Min number of Opportunities associated with an account
    @Query(value = "select min(a.count_opportunity) from (select count(distinct id) as count_opportunity from opportunity group by account_id order by count_opportunity) a", nativeQuery = true)
    Optional<Double>findMinOpportunitiesPerAccount();


}
