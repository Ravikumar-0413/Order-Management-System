 package oms.order.Management.System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oms.order.Management.System.Entity.Customer;
import oms.order.Management.System.Repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

   
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    
    public Customer getCustomerById(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existing = getCustomerById(id);

        existing.setName(updatedCustomer.getName());
        existing.setEmail(updatedCustomer.getEmail());

        return customerRepo.save(existing);
    }

     
    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepo.delete(customer);
    }
}