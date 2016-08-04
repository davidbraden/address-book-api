package com.davidbraden.address.book.api.services;

import com.davidbraden.address.book.api.models.Address;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FileAddressStore {

    private final File file;
    private List<Address> addressList;
    private AddressSerializer addressSerializer;

    private AtomicInteger idGenerator = new AtomicInteger();

    public FileAddressStore(AddressSerializer addressSerializer) throws IOException {
        this.addressSerializer = addressSerializer;;
        addressList = new ArrayList<>();
        file = new File("addressbook.db");
        if(!file.exists()) {
            file.createNewFile();
        }
        UpdateFromFile(addressSerializer);
    }

    public Address getAddress(int id) {
        for(Address a : listAddresses()) {
            if (id == a.getId())
                return a;
        }
        return null;
    }

    public Address addAddress(Address address) throws IOException {
        Address update = new Address(idGenerator.incrementAndGet(), address.getFirstName(), address.getLastName(), address.getEmail(), address.getCompany());
        addressList.add(update);
        commit();
        return update;
    }

    public void deleteAddress(Address address) throws IOException  {
        addressList.remove(address);
        commit();
    }

    public List<Address> listAddresses() {
        return addressList;
    }


    private void UpdateFromFile(AddressSerializer addressSerializer) throws IOException {
        List<String> records = Files.readLines(file, Charset.forName("utf-8"));
        for(String record : records) {
            addressList.add(addressSerializer.deserialize(record));
        }
    }

    private void commit() throws IOException {
        file.delete();
        file.createNewFile();
        Files.write(addressList.stream().map(x -> addressSerializer.serialize(x)).collect(Collectors.joining("\n")), file, Charset.forName("utf-8"));
    }
}
