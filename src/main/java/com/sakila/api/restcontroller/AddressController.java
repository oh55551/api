package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.dto.AddressDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.service.AddressService;

@CrossOrigin
@RestController
public class AddressController {
	   private AddressService addressService;
	   
	   // 필드 주입 대신 생성자 주입을 사용
	   public AddressController(AddressService addressService) {
	      this.addressService = addressService;
	   }
	   
	    @PatchMapping("/address")
	    public ResponseEntity<String> updateAddress(@RequestBody AddressDto addressDto) {
	        addressService.update(addressDto);
	        return new ResponseEntity<>("수정성공", HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/address/{addressId}")
	    public ResponseEntity<String> deleteAddress(@PathVariable int addressId) {
	        boolean result = addressService.delete(addressId);
	        if (result) {
	            return new ResponseEntity<>("삭제성공", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("삭제실패", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	   
	    @PostMapping("/address")
	    public ResponseEntity<String> addAddress(@RequestBody AddressDto addressDto) {
	        addressService.save(addressDto);
	        return new ResponseEntity<>("입력성공", HttpStatus.OK);
	    }
	   
	   @GetMapping("/address")
	   public ResponseEntity<List<AddressEntity>> address() {
	      return new ResponseEntity<List<AddressEntity>>(addressService.findAll(), HttpStatus.OK);
	   }
}
