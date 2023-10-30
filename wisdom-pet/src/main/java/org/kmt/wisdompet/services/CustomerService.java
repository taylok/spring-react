package org.kmt.wisdompet.services;

import org.kmt.wisdompet.data.entities.CustomerEntity;
import org.kmt.wisdompet.data.repositories.CustomerRepository;
import org.kmt.wisdompet.web.errors.NotFoundException;
import org.kmt.wisdompet.web.models.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private CustomerEntity translateWebToDB(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getCustomerId());
        entity.setFirstName(customer.getFirstName());
        entity.setLastName(customer.getLastName());
        entity.setEmail(customer.getEmailAddress());
        entity.setPhone(customer.getPhoneNumber());
        entity.setAddress(customer.getAddress());
        return entity;
    }

    public List<Customer> getAllCustomers(String filterEmail) {
        List<Customer> customers = new ArrayList<>();
        if (StringUtils.hasLength(filterEmail)) {
            CustomerEntity entity = this.customerRepository.findByEmail(filterEmail);
            customers.add(this.translateDbToWeb(entity));
        } else {
            Iterable<CustomerEntity> entities = this.customerRepository.findAll();
            entities.forEach(entity -> customers.add(this.translateDbToWeb(entity)));
        }
        return customers;
    }

    public Customer getCustomer(long id) {
        Optional<CustomerEntity> optional = this.customerRepository.findById(id);
        if(optional.isEmpty()) {
            throw new NotFoundException("Customer not found with id");
        }
        return this.translateDbToWeb(optional.get());
    }

    public Customer createOrUpdate(Customer customer) {
        CustomerEntity entity = this.translateWebToDB(customer);
        entity = this.customerRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public void deleteCustomer(long id) {
        this.customerRepository.deleteById(id);
    }

    private Customer translateDbToWeb(CustomerEntity entity) {
        return new Customer(entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress());
    }
}
