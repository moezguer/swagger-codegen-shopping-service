package io.swagger.service;

import io.swagger.database.model.CustomerEntity;
import io.swagger.database.model.ProductEntity;
import io.swagger.database.repository.CustomerRepository;
import io.swagger.dto.model.Customer;
import io.swagger.dto.model.Product;
import io.swagger.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {

        final CustomerEntity customerEntity = modelMapper.map(customer, CustomerEntity.class);
        return modelMapper.map(customerRepository.save(customerEntity), Customer.class);

    }

    public void deleteCustomer(String email) {
        customerRepository.delete(customerRepository.getCustomerEntityByEmail(email));
    }

    public Customer findCustomerById(String email) throws NotFoundException {

        final CustomerEntity customerEntity = customerRepository.findById(email)
                                 .orElseThrow(() -> new NotFoundException(404, "Order Not Found"));
        final Customer customer = modelMapper.map(customerEntity, Customer.class);
        return  customer;

    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll()
                                 .stream()
                                 .map(customerEntity -> modelMapper.map(customerEntity, Customer.class))
                                 .collect(Collectors.toList());
    }

    public void saveAll(List<Customer> customerList) {
        final List<CustomerEntity>
                customerEntityList = customerList.stream()
                                                 .map(customer -> modelMapper.map(customer, CustomerEntity.class))
                                                 .collect(Collectors.toList());
        customerRepository.saveAll(customerEntityList);
    }

    public Customer updateCustomer(Customer customer , String email) throws NotFoundException {

        final CustomerEntity customerEntity = modelMapper.map(customer, CustomerEntity.class);

        customerRepository.findById(email)
                         .map(x -> {
                             x.setName(customerEntity.getName());
                             x.setSurname(customerEntity.getSurname());
                             return customerRepository.save(x);
                         }).orElseThrow( () -> new NotFoundException(404, "Customer not found"));

        final Customer updatedCustomer = modelMapper.map(customerRepository.getCustomerEntityByEmail(email), Customer.class);

        return updatedCustomer;
    }
}
