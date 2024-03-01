package com.healthqr.healthqr.repository;

import com.healthqr.healthqr.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE " +
            "LOWER(CAST(p.personId AS STRING)) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "p.firstName LIKE CONCAT('%', :query, '%') OR " +
            "p.lastName LIKE CONCAT('%', :query, '%')")
    List<Person> searchPersons(String query);


}
