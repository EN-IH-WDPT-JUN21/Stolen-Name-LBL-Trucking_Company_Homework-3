package com.ironhack.stolen_name_trucking_company_homework_3.repository;

import com.ironhack.stolen_name_trucking_company_homework_3.dao.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,String> {
}
