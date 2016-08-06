package com.davidbraden.address.book.api.services;

import com.davidbraden.address.book.api.models.Company;
import com.davidbraden.address.book.api.models.Person;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;


public class Serializer {


    public String serialize(Person person) {

        List<String> fields = Lists.newArrayList(Integer.toString(person.getId()), person.getFirstName(),
                person.getLastName(), person.getEmail(), person.getCompany());
        return fields.stream().collect(Collectors.joining("\t"));
    }

    public Person deserializePerson(String data) {
        List<String> fields = Lists.newArrayList(data.split("\t"));
        if (fields.size() != 5) {
            throw new RuntimeException("Bad data");
        }
        int id = Integer.parseInt(fields.get(0));
        return new Person(id, fields.get(1), fields.get(2), fields.get(3), fields.get(4));
    }

    public String serialize(Company company) {

        List<String> fields = Lists.newArrayList(company.getName(), company.getPhoneNumber());
        return fields.stream().collect(Collectors.joining("\t"));
    }

    public Company deserializeCompany(String data) {
        List<String> fields = Lists.newArrayList(data.split("\t"));
        if (fields.size() != 2) {
            throw new RuntimeException("Bad data");
        }
        return new Company(fields.get(0), fields.get(1));
    }
}
