package com.davidbraden.address.book.api.services;

import static org.junit.Assert.*;

import com.davidbraden.address.book.api.models.Address;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileAddressStoreTest {

    private FileAddressStore addressStore;

    @Before
    public void setUp() throws Exception {
        addressStore = new FileAddressStore(new AddressSerializer());
    }

    @After
    public void tearDown() throws Exception {
        for(Address a : Lists.newArrayList(addressStore.listAddresses())) {
            addressStore.deleteAddress(a);
        }
    }

    @Test
    public void add_address_sets_id() throws Exception {

        Address added = addressStore.addAddress(new Address(0, "first", "last", "email", "comp"));
        Address added2 = addressStore.addAddress(new Address(0, "first2", "last2", "email2", "comp2"));

        Assert.assertEquals(1, added.getId());
        Assert.assertEquals(2, added2.getId());
    }

    @Test
    public void list_addresses() throws Exception {

        addressStore.addAddress(new Address(0, "first", "last", "email", "comp"));
        addressStore.addAddress(new Address(0, "first2", "last2", "email2", "comp2"));

        Assert.assertEquals(addressStore.listAddresses().size(), 2);
    }

    @Test
    public void remove_address() throws Exception {

        addressStore.addAddress(new Address(0, "first", "last", "email", "comp"));
        Address second = addressStore.addAddress(new Address(0, "first2", "last2", "email2", "comp2"));
        addressStore.deleteAddress(second);

        Assert.assertEquals(addressStore.listAddresses().size(), 1);
    }

    @Test
    public void addresses_persisted() throws Exception {

        Address added = addressStore.addAddress(new Address(0, "first", "last", "email", "comp"));

        addressStore = new FileAddressStore(new AddressSerializer());

        Assert.assertNotNull(addressStore.getAddress(added.getId()));
    }
}