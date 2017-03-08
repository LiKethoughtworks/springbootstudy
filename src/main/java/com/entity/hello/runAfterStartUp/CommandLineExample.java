package com.entity.hello.runAfterStartUp;

import com.entity.hello.repository.Customer;
import com.entity.hello.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineExample implements CommandLineRunner{
    @Autowired
    CustomerRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Customer("Jack1", "Bauer1"));
        repository.save(new Customer("Chloe1", "O'Brian1"));
        repository.save(new Customer("Kim1", "Bauer1"));
        repository.save(new Customer("David1", "Palmer1"));
        repository.save(new Customer("Michelle1", "Dessler1"));
    }
}
