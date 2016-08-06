package com.davidbraden.address.book.api.services;

import com.davidbraden.address.book.api.models.Company;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyFileStore {

    private final File file;
    private List<Company> companies;
    private Serializer serializer;

    public CompanyFileStore(Serializer serializer) throws IOException {
        this.serializer = serializer;;
        companies = new ArrayList<>();
        file = new File("company.db");
        if(!file.exists()) {
            file.createNewFile();
        }
        UpdateFromFile(serializer);
    }

    public Company getCompany(String name) {
        for(Company a : listCompanies()) {
            if (name.equals(a.getName()))
                return a;
        }
        return null;
    }

    public Company addCompany(Company company) throws IOException {
        if (getCompany(company.getName()) != null)
            throw new RuntimeException("Company already exists");
        companies.add(company);
        commit();
        return company;
    }

    public void deleteCompany(Company company) throws IOException  {
        companies.remove(company);
        commit();
    }

    public List<Company> listCompanies() {
        return companies;
    }


    private void UpdateFromFile(Serializer serializer) throws IOException {
        List<String> records = Files.readLines(file, Charset.forName("utf-8"));
        for(String record : records) {
            companies.add(serializer.deserializeCompany(record));
        }
    }

    private void commit() throws IOException {
        file.delete();
        file.createNewFile();
        Files.write(companies.stream().map(x -> serializer.serialize(x)).collect(Collectors.joining("\n")), file, Charset.forName("utf-8"));
    }
}
