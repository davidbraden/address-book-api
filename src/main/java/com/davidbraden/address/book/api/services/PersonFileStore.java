package com.davidbraden.address.book.api.services;

import com.davidbraden.address.book.api.models.Person;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PersonFileStore {

    private final File file;
    private List<Person> personList;
    private Serializer serializer;

    private AtomicInteger idGenerator = new AtomicInteger();

    public PersonFileStore(Serializer serializer) throws IOException {
        this.serializer = serializer;;
        personList = new ArrayList<>();
        file = new File("person.db");
        if(!file.exists()) {
            file.createNewFile();
        }
        UpdateFromFile(serializer);
    }

    public Person getPerson(int id) {
        for(Person a : listPeople()) {
            if (id == a.getId())
                return a;
        }
        return null;
    }

    public Person addPerson(Person person) throws IOException {
        Person update = new Person(idGenerator.incrementAndGet(), person.getFirstName(), person.getLastName(), person.getEmail(), person.getCompany());
        personList.add(update);
        commit();
        return update;
    }

    public void deletePerson(Person person) throws IOException  {
        personList.remove(person);
        commit();
    }

    public List<Person> listPeople() {
        return personList;
    }


    private void UpdateFromFile(Serializer serializer) throws IOException {
        List<String> records = Files.readLines(file, Charset.forName("utf-8"));
        for(String record : records) {
            personList.add(serializer.deserializePerson(record));
        }
    }

    private void commit() throws IOException {
        file.delete();
        file.createNewFile();
        Files.write(personList.stream().map(x -> serializer.serialize(x)).collect(Collectors.joining("\n")), file, Charset.forName("utf-8"));
    }
}
