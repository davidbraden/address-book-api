package com.davidbraden.address.book.api.controllers;


import com.davidbraden.address.book.api.models.Person;
import com.davidbraden.address.book.api.services.CompanyFileStore;
import com.davidbraden.address.book.api.services.PersonFileStore;
import com.google.common.base.Strings;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonController {

    private CompanyFileStore companyFileStore;
    private PersonFileStore personFileStore;

    public PersonController(CompanyFileStore companyFileStore, PersonFileStore personFileStore) {
        this.companyFileStore = companyFileStore;
        this.personFileStore = personFileStore;
    }

    @GET
    @Path("/all")
    public List<Person> listPeople() {
        return personFileStore.listPeople();
    }

    @GET
    @Path("/{id}")
    public Person getPeople(@PathParam("id") int id) {
        Person person = personFileStore.getPerson(id);
        if (person != null)
            return person;

        throw new NotFoundException();
    }

    @POST
    @Path("/")
    public Person addPerson(Person person) throws Exception {
        if (!Strings.isNullOrEmpty(person.getCompany()) && companyFileStore.getCompany(person.getCompany()) == null)
            throw new BadRequestException("company does not exist");
        return personFileStore.addPerson(person);
    }

    @DELETE
    @Path("/{id}")
    public void deletePerson(@PathParam("id") int id) throws Exception {
        personFileStore.deletePerson(getPeople(id));
    }
}
