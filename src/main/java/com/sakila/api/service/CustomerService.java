package com.sakila.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.entity.CustomerMapping;
import com.sakila.api.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {
	private CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
    public boolean delete(int customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }

    public void update(CustomerEntity customerEntity) {
        CustomerEntity updateTarget = customerRepository.findById(customerEntity.getCustomerId()).orElse(null);
        if (updateTarget != null) {
            updateTarget.setFirstName(customerEntity.getFirstName());
            updateTarget.setLastName(customerEntity.getLastName());
            updateTarget.setEmail(customerEntity.getEmail());
            updateTarget.setActive(customerEntity.getActive());
            updateTarget.setCreateDate(customerEntity.getCreateDate());
            updateTarget.setLastUpdate(customerEntity.getLastUpdate());
            updateTarget.setStoreEntity(customerEntity.getStoreEntity());
            updateTarget.setAddressEntity(customerEntity.getAddressEntity());
        }
    }

    public void save(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
    }

    public Page<CustomerMapping> findAll(int currentPage) {
	  int pageSize = 10;
	  int pageNumber = currentPage-1;
	  Sort sort = Sort.by("customerId").ascending();
	  PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
        return customerRepository.findAllBy(pageable);
    }

    public CustomerEntity findById(int customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }
}
