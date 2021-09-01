package com.ironhack.stolen_name_trucking_company_homework_3.repository;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.Contact;
import com.ironhack.stolen_name_trucking_company_homework_3.dao.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {

    @Query("SELECT c.id, c.name, c.companyName FROM Contact c")
    List<Object[]> findAllContacts();


}
