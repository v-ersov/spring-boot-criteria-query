package com.example.springbootcriteriaquery.controller;

import com.example.springbootcriteriaquery.model.Person;
import com.example.springbootcriteriaquery.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    public ResponseEntity<List<Person>> findAll(@RequestParam(required = false) String email,
                                                @RequestParam(required = false) String firstName,
                                                @RequestParam(required = false) String lastName,
                                                @RequestParam(required = false) String ssn,
                                                @RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) Boolean isDeleted) {
        var all = personService.findAll(email, firstName, lastName, ssn, age, isDeleted);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Person>> findAll(@RequestParam(required = false) String email,
                                                @RequestParam(required = false) String firstName,
                                                @RequestParam(required = false) String lastName,
                                                @RequestParam(required = false) String ssn,
                                                @RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) Boolean isDeleted,
                                                @RequestParam(defaultValue = "1") int pageNo,
                                                @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy,
                                                @RequestParam(defaultValue = "ASC") String sortDirection) {
        var all = personService.findAll(email, firstName, lastName, ssn, age, isDeleted, pageNo, pageSize,
                sortBy, sortDirection);
        return ResponseEntity.ok(all);
    }

}
