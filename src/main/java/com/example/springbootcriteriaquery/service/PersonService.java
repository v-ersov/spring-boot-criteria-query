package com.example.springbootcriteriaquery.service;

import com.example.springbootcriteriaquery.model.Person;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonService {

    List<Person> findAll(String email, String firstName, String lastName, String ssn, Integer age, Boolean isDeleted);

    Page<Person> findAll(String email, String firstName, String lastName, String ssn, Integer age, Boolean isDeleted,
                         int pageNo, int pageSize, String sortBy, String sortDirection);
}
