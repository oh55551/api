package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.repository.AddressRepository;

@Service
@Transactional
public class AddressService {
	   private AddressRepository addressRepository;

	   public AddressService(AddressRepository addressRepository) {
	      this.addressRepository = addressRepository;
	   }
	   
		
		public void save(AddressEntity addressEntity) {
			addressRepository.save(addressEntity);
		}
	   
	   public List<AddressEntity> findAll() {
	      return addressRepository.findAll();
	   }
}
