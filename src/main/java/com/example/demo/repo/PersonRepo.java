package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Person;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM PERSON")
    List<Person> getAll();

}