package com.sakila.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.AddressDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.AddressMapping;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CityRepository;

@Service
@Transactional
public class AddressService {
	   private AddressRepository addressRepository;
	   private CityRepository cityRepository;

	   public AddressService(AddressRepository addressRepository, CityRepository cityRepository) {
	      this.addressRepository = addressRepository;
	      this.cityRepository = cityRepository;
	   }

	    // address 삭제
	    public boolean delete(int addressId) {
	        if (addressRepository.existsById(addressId)) {
	            addressRepository.deleteById(addressId);
	            return true;
	        } else {
	            System.out.println("삭제 대상이 존재하지 않음");
	            return false;
	        }
	    }
		
	    public void update(AddressDto addressDto) {
	 	   AddressEntity updateAddressEntity = addressRepository.findById(addressDto.getAddressId()).orElse(null);
	 	   updateAddressEntity.setAddress(addressDto.getAddress());
	    }
	 	
	    public void save(AddressDto dto) {
	    	   AddressEntity entity = new AddressEntity();
	    	   entity.setAddress(dto.getAddress());
	    	   entity.setAddress2(dto.getAddress2());
	    	   entity.setDistrict(dto.getDistrict());
	    	   entity.setPostalCode(dto.getPostCode());
	    	   entity.setPhone(dto.getPhone()); 
	    	   entity.setCityEntity(cityRepository.findById(dto.getCityId()).orElse(null)); 

	    	   addressRepository.save(entity);
	    	}
	    
		public void save(AddressEntity addressEntity) {
			addressRepository.save(addressEntity);
		}
	   
		
	   public Page<AddressMapping> findAll(int currentPage) {
		  int pageSize = 10;
		  int pageNumber = currentPage-1;
		  Sort sort = Sort.by("addressId").ascending();
		  PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
	      return addressRepository.findAllBy(pageable);
	   }
}
