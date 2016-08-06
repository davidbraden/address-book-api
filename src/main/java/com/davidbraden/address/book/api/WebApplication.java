package com.davidbraden.address.book.api;

import com.davidbraden.address.book.api.controllers.CompanyController;
import com.davidbraden.address.book.api.controllers.PersonController;
import com.davidbraden.address.book.api.services.CompanyFileStore;
import com.davidbraden.address.book.api.services.Serializer;
import com.davidbraden.address.book.api.services.PersonFileStore;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WebApplication extends Application<WebConfiguration> {

    public static void main(final String[] args) throws Exception {
        new WebApplication().run(args);
    }

    @Override
    public String getName() {
        return "address-book-api";
    }

    @Override
    public void initialize(final Bootstrap<WebConfiguration> bootstrap) {
    }

    @Override
    public void run(final WebConfiguration configuration,
                    final Environment environment) throws Exception {

        Serializer serializer = new Serializer();
        PersonFileStore personFileStore = new PersonFileStore(serializer);
        CompanyFileStore companyFileStore = new CompanyFileStore(serializer);
        environment.jersey().register(new PersonController(companyFileStore, personFileStore));
        environment.jersey().register(new CompanyController(companyFileStore, personFileStore));
    }
}
