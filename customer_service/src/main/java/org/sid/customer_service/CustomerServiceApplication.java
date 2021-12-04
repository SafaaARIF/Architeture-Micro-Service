package org.sid.customer_service;

import org.sid.customer_service.entities.Customer;
import org.sid.customer_service.repository.CustomersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }


    @Bean // pour indiquer qu'il sera exécuter en début
    CommandLineRunner Start(CustomersRepository customersRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Customer.class);
        return args -> {

            customersRepository.save(new Customer(null, "OUM", "OUM@gmail.com"));
            customersRepository.save(new Customer(null, "SAF", "SAF@gmail.com"));
            customersRepository.save(new Customer(null, "IMA", "IMA@gmail.com"));
            customersRepository.save(new Customer(null, "MER", "MER@gmail.com"));
            customersRepository.findAll().forEach(customer -> {
                System.out.println(customer.toString());
            });
        };
    }
}

