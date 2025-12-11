package br.com.diegoplaninscheck.n3_seguranca_backend.controller;

import br.com.diegoplaninscheck.n3_seguranca_backend.model.Customer;
import br.com.diegoplaninscheck.n3_seguranca_backend.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER_CREATE') || hasRole('admin')")
    @Operation(summary = "Create new Customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

        @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER_UPDATE') || hasRole('admin')")
    @Operation(summary = "Update Customer")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        try{
            return ResponseEntity.ok(customerService.update(id, customer));
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
