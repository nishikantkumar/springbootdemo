package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.repo.PersonRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class PersonRestController {

       @Autowired
       PersonRepo personRepo;

        @GetMapping("/persons")
        public ResponseEntity<List<Person>> getAllPerson() {
            try {
                List<Person> li = personRepo.findAll();
                return new ResponseEntity<>(li, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/person/{id}")
        public ResponseEntity<Person> getPersonById(@PathVariable("id") int id) {
            Optional<Person> person = personRepo.findById(id);

            if (person.isPresent()) {
                return new ResponseEntity<>(person.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping("/person")
        public ResponseEntity<Integer> createPerson(@RequestBody Person person) {
            try {
                personRepo.save(person);
                return new ResponseEntity<>(person.getId(), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PutMapping("/person/{id}")
        public ResponseEntity<Person> updatePerson(@PathVariable("id") @NotNull int id, @RequestBody @Valid Person person) {

            Optional<Person> persondb = personRepo.findById(id);

            if (persondb.isPresent()) {
                person.setId(persondb.get().getId());
                personRepo.save(person);
                return new ResponseEntity<>(person, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/person/{id}")
        public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") @NotNull int id) {
            try {
                personRepo.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @DeleteMapping("/person")
        public ResponseEntity<HttpStatus> deleteAllPerson() {
            try {
                personRepo.deleteAll();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }