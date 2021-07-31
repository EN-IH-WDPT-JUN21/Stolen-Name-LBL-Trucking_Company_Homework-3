package com.ironhack.stolen_name_trucking_company_homework_3.repository;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    //Report mean number employee count for all Accounts
    @Query("SELECT AVG(employeeCount) FROM Account")
    Optional<Double> findMeanEmployeeCount();

    // *** Median Report is needed ***

    //Report Maximum  employee count for all Accounts
    @Query("SELECT MAX(employeeCount) FROM Account")
    Optional<Integer> findMaxEmployeeCount();

    //Report Minimum  employee count for all Accounts
    @Query("SELECT MIN(employeeCount) FROM Account")
    Optional<Integer> findMinEmployeeCount();

}
