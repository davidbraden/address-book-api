package com.davidbraden.address.book.api.services;

import com.davidbraden.address.book.api.models.Address;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;


public class AddressSerializer {


    public String serialize(Address address) {

        List<String> fields = Lists.newArrayList(Integer.toString(address.getId()), address.getFirstName(),
                address.getLastName(), address.getEmail(), address.getCompany());
        return fields.stream().collect(Collectors.joining("\t"));
    }

    public Address deserialize(String data) {
        List<String> fields = Lists.newArrayList(data.split("\t"));
        if (fields.size() != 5) {
            throw new RuntimeException("Bad data");
        }
        int id = Integer.parseInt(fields.get(0));
        return new Address(id, fields.get(1), fields.get(2), fields.get(3), fields.get(4));
    }
}
