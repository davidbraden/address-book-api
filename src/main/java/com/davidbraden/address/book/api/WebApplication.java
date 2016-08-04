package com.davidbraden.address.book.api;

import com.davidbraden.address.book.api.controllers.AddressBookController;
import com.davidbraden.address.book.api.services.AddressSerializer;
import com.davidbraden.address.book.api.services.FileAddressStore;
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
        environment.jersey().register(new AddressBookController(new FileAddressStore(new AddressSerializer())));
    }

}
