package com.example.springbootcriteriaquery.service.impl;

import com.example.springbootcriteriaquery.model.Person;
import com.example.springbootcriteriaquery.repository.PersonRepository;
import com.example.springbootcriteriaquery.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.springbootcriteriaquery.repository.specification.PersonSpecification.*;
import static java.util.Objects.nonNull;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Override
    public List<Person> findAll(String email, String firstName, String lastName, String ssn, Integer age,
                                Boolean isDeleted) {
        var spec = getPersonSpecification(email, firstName, lastName, ssn, age, isDeleted);
        return repository.findAll(spec);
    }

    @Override
    public Page<Person> findAll(String email, String firstName, String lastName, String ssn, Integer age,
                                Boolean isDeleted, int pageNo, int pageSize, String sortBy, String sortDirection) {
        var spec = getPersonSpecification(email, firstName, lastName, ssn, age, isDeleted);
        var sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        var pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return repository.findAll(spec, pageable);
    }

    private static Specification<Person> getPersonSpecification(String email,
                                                                String firstName,
                                                                String lastName,
                                                                String ssn,
                                                                Integer age,
                                                                Boolean isDeleted) {
        Specification<Person> spec = where(null);

        if (StringUtils.hasText(email)) {
            spec = spec.and(emailLike(email));
        }

        if (StringUtils.hasText(firstName)) {
            spec = spec.and(firstNameLike(firstName));
        }

        if (StringUtils.hasText(lastName)) {
            spec = spec.and(lastNameLike(lastName));
        }

        if (StringUtils.hasText(ssn)) {
            spec = spec.and(ssnLike(ssn));
        }

        if (nonNull(age)) {
            spec = spec.and(ageIs(age));
        }

        if (nonNull(isDeleted)) {
            spec = spec.and(isDeletedIs(isDeleted));
        }

        return spec;
    }

}
