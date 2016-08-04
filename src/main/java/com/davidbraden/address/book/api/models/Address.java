package com.davidbraden.address.book.api.models;


public class Address {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String company;

    public Address(int id, String firstName, String lastName, String email, String company) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }
}
