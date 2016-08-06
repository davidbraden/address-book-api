package com.davidbraden.address.book.api.services;

import com.davidbraden.address.book.api.models.Person;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FilePersonStoreTest {

    private PersonFileStore personFileStore;

    @Before
    public void setUp() throws Exception {
        personFileStore = new PersonFileStore(new Serializer());
    }

    @After
    public void tearDown() throws Exception {
        for(Person a : Lists.newArrayList(personFileStore.listPeople())) {
            personFileStore.deletePerson(a);
        }
    }

    @Test
    public void add_person_sets_id() throws Exception {

        Person added = personFileStore.addPerson(new Person(0, "first", "last", "email", "comp"));
        Person added2 = personFileStore.addPerson(new Person(0, "first2", "last2", "email2", "comp2"));

        Assert.assertEquals(1, added.getId());
        Assert.assertEquals(2, added2.getId());
    }

    @Test
    public void people() throws Exception {

        personFileStore.addPerson(new Person(0, "first", "last", "email", "comp"));
        personFileStore.addPerson(new Person(0, "first2", "last2", "email2", "comp2"));

        Assert.assertEquals(personFileStore.listPeople().size(), 2);
    }

    @Test
    public void remove_person() throws Exception {

        personFileStore.addPerson(new Person(0, "first", "last", "email", "comp"));
        Person second = personFileStore.addPerson(new Person(0, "first2", "last2", "email2", "comp2"));
        personFileStore.deletePerson(second);

        Assert.assertEquals(personFileStore.listPeople().size(), 1);
    }

    @Test
    public void people_persisted() throws Exception {

        Person added = personFileStore.addPerson(new Person(0, "first", "last", "email", "comp"));

        personFileStore = new PersonFileStore(new Serializer());

        Assert.assertNotNull(personFileStore.getPerson(added.getId()));
    }
}