package za.ac.cput.GeneratorRental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.GeneratorRental.domain.Customer;
import za.ac.cput.GeneratorRental.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository REPOSITORY;

    public Customer create(Customer customer){
        return REPOSITORY.save(customer);
    }

    public Customer read(long id){
        try {
            return REPOSITORY.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Customer Id");
        }
    }

    public Customer update(Customer customer){
        try {
            Customer copy = this.read(customer.getCustomer_id());
            Customer updated = new Customer.Builder().copy(copy)
                    .firstName(customer.getFirstName())
                    .lastNameName(customer.getLastName())
                    .setCustomer_phoneNum(customer.getCustomer_phoneNum())
                    .setCustomer_email(customer.getCustomer_email())
                    .build();
            return REPOSITORY.save(updated);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid customer Id");
        }
    }

    public boolean delete(long id){
        try {
            REPOSITORY.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid customer Id");
        }
    }

    public List<Customer> getAll(){
        return REPOSITORY.findAll();
    }
}
