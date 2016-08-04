package com.davidbraden.address.book.api.controllers;


import com.davidbraden.address.book.api.models.Address;
import com.google.common.collect.Lists;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/address")
@Produces(MediaType.APPLICATION_JSON)
public class AddressBookController {

    private List<Address> addresses = Lists.newArrayList(new Address(1, "david", "braden", "david.braden@gmail.com", "Skyscanner"));


    @GET
    @Path("/list")
    public List<Address> listAddresses() {
        return addresses;
    }

    @GET
    @Path("/list/{id}")
    public Address getAddress(@PathParam("id") int id) {
        for(Address address:addresses) {
            if (address.getId() == id)
                return address;
        }
        throw new NotFoundException();
    }
}
