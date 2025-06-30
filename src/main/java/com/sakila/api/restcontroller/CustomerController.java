package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.service.CustomerService;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
        boolean result = customerService.delete(customerId);
        return result ? new ResponseEntity<>("삭제성공", HttpStatus.OK)
                      : new ResponseEntity<>("삭제실패", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/customer")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerEntity customerEntity) {
        customerService.update(customerEntity);
        return new ResponseEntity<>("수정성공", HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerEntity customerEntity) {
        customerService.save(customerEntity);
        return new ResponseEntity<>("입력성공", HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable int customerId) {
        CustomerEntity customer = customerService.findById(customerId);
        return customer != null ? new ResponseEntity<>(customer, HttpStatus.OK)
                                 : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

