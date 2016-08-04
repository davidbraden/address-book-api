package com.davidbraden.address.book.api.controllers;


import com.davidbraden.address.book.api.models.Address;
import com.davidbraden.address.book.api.services.FileAddressStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/address")
@Produces(MediaType.APPLICATION_JSON)
public class AddressBookController {

    private FileAddressStore addressStore;

    public AddressBookController(FileAddressStore addressStore) {
        this.addressStore = addressStore;
    }

    @GET
    @Path("/all")
    public List<Address> listAddresses() {
        return addressStore.listAddresses();
    }

    @GET
    @Path("/{id}")
    public Address getAddress(@PathParam("id") int id) {
        for(Address address:addressStore.listAddresses()) {
            if (address.getId() == id)
                return address;
        }
        throw new NotFoundException();
    }

    @POST
    @Path("/")
    public Address addAddress(Address address) throws Exception {
        return addressStore.addAddress(address);
    }

    @DELETE
    @Path("/{id}")
    public void deleteAddress(@PathParam("id") int id) throws Exception {
        addressStore.deleteAddress(getAddress(id));
    }
}
