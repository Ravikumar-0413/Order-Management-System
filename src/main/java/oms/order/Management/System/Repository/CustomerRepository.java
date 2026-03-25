 package oms.order.Management.System.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import oms.order.Management.System.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // find customer by email
    Optional<Customer> findByEmail(String email);
}