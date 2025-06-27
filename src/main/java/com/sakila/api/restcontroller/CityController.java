package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.dto.CityDto;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.service.CityService;

@RestController
public class CityController {
private CityService cityService;
// 필드 주입 대신 생성자 주입을 사용
public CityController(CityService cityService) {
   this.cityService = cityService;
}

	@PostMapping("/city")
	public ResponseEntity<String> city(@RequestBody CityDto cityDto){
		cityService.save(cityDto);
		return new ResponseEntity<String>("입력성공", HttpStatus.OK);
	}
	
	   @GetMapping("/city")
	   public ResponseEntity<List<CityEntity>> city() {
	      return new ResponseEntity<List<CityEntity>>(cityService.findAll(), HttpStatus.OK);
	   }
}
