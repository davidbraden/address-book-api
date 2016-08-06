package com.davidbraden.address.book.api.controllers;


import com.davidbraden.address.book.api.models.Company;
import com.davidbraden.address.book.api.models.Person;
import com.davidbraden.address.book.api.services.CompanyFileStore;
import com.davidbraden.address.book.api.services.PersonFileStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyController {

    private CompanyFileStore companyFileStore;
    private PersonFileStore personFileStore;

    public CompanyController(CompanyFileStore companyFileStore, PersonFileStore personFileStore) {
        this.companyFileStore = companyFileStore;
        this.personFileStore = personFileStore;
    }

    @GET
    @Path("/all")
    public List<Company> listCompanies() {
        return companyFileStore.listCompanies();
    }

    @GET
    @Path("/{name}")
    public Company getCompany(@PathParam("name") String name) {
        Company company = companyFileStore.getCompany(name);
        if (company != null)
            return company;
        throw new NotFoundException();
    }


    @GET
    @Path("/{name}/people")
    public List<Person> getPeople(@PathParam("name") String name) {
        return personFileStore.listPeople().stream().filter(p -> p.getCompany().equals(name)).collect(Collectors.toList());
    }

    @POST
    @Path("/")
    public Company addPerson(Company person) throws Exception {
        return companyFileStore.addCompany(person);
    }

    @DELETE
    @Path("/{name}")
    public void deletePerson(@PathParam("name") String name) throws Exception {
        companyFileStore.deleteCompany(getCompany(name));
    }
}
