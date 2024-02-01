package com.example.springbootcriteriaquery.repository.specification;

import com.example.springbootcriteriaquery.model.Person;
import org.springframework.data.jpa.domain.Specification;

public class PersonSpecification {

    public static Specification<Person> emailLike(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("email"), "%" + email + "%");
    }

    public static Specification<Person> firstNameLike(String firstName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
    }

    public static Specification<Person> lastNameLike(String lastName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%");
    }

    public static Specification<Person> ssnLike(String ssn) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("ssn"), "%" + ssn + "%");
    }

    public static Specification<Person> ageIs(int age) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("age"), age);
    }

    public static Specification<Person> isDeletedIs(boolean isDeleted) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isDeleted"), isDeleted);
    }

}
