package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.entity.AddressEntity;
import com.sakila.api.service.AddressService;

@RestController
public class AddressController {
	   private AddressService addressService;
	   
	   // 필드 주입 대신 생성자 주입을 사용
	   public AddressController(AddressService addressService) {
	      this.addressService = addressService;
	   }
	   
//	   @PostMapping("/address")
//	   public ResponseEntity<String> address(@RequestBody AddressDto addressDto){
//		   // json"{.......}" -> countryDTO로 변경
//		   addressService.save(addressDto);
		   
//		   return new ResponseEntity<String>("입력성공", HttpStatus.OK);
//	   }
	   
	   @GetMapping("/address")
	   public ResponseEntity<List<AddressEntity>> address() {
	      return new ResponseEntity<List<AddressEntity>>(addressService.findAll(), HttpStatus.OK);
	   }
}
