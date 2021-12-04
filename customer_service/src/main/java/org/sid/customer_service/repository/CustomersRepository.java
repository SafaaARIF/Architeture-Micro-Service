package org.sid.customer_service.repository;

import org.sid.customer_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface CustomersRepository extends JpaRepository<Customer, Long> {
}


