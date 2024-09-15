package com.chemi.lab.auth.repos;



import com.chemi.lab.auth.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

    Optional<Customer> findByEmail (String email);
}
