package com.entity.hello.configuration;

import com.entity.hello.repository.Customer;
import com.entity.hello.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;

@Configuration
public class DataInitializationConfiguration {

    @Bean
    @DependsOn({"customerRepository"})
    public Initializer initializer() {
        return new Initializer() {
            @Autowired
            private CustomerRepository repository;

            @PostConstruct
            @Override
            public void initializeCustomerData() {
                repository.save(new Customer("Jack", "Bauer"));
                repository.save(new Customer("Chloe", "O'Brian"));
                repository.save(new Customer("Kim", "Bauer"));
                repository.save(new Customer("David", "Palmer"));
                repository.save(new Customer("Michelle", "Dessler"));
            }
        };
    }

    public interface Initializer {
        void initializeCustomerData();
    }
}
