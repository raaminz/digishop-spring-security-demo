package com.demisco.digishop.repository;

import com.demisco.digishop.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PersonRepository extends JpaRepository<Person,Long> {

     Person findByUsername(String username);

}
