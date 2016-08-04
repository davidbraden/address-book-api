package com.davidbraden.address.book.api;

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
                    final Environment environment) {
    }

}
