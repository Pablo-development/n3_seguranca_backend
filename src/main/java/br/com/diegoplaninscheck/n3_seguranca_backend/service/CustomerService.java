package br.com.diegoplaninscheck.n3_seguranca_backend.service;

import br.com.diegoplaninscheck.n3_seguranca_backend.Repository.CustomerRepository;
import br.com.diegoplaninscheck.n3_seguranca_backend.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete (Long id) {
        customerRepository.deleteById(id);
    }

    public Customer update(Long id, Customer newCustomer) {
        return customerRepository.findById(id).map(customer -> {
            customer.setName(newCustomer.getName());
            customer.setCnpj(newCustomer.getCnpj());
            customer.setRazaoSocial(newCustomer.getRazaoSocial());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found!"));
    }
}
