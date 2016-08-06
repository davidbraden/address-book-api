package com.davidbraden.address.book.api.models;


public class Company {
    private String name;
    private String phoneNumber;

    public Company() {
    }

    public Company(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
