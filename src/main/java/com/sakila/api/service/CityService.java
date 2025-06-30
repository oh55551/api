package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CityDto;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CountryRepository;

@Service
@Transactional
public class CityService {

	   private CityRepository cityRepository;
	   private CountryRepository countryRepository;

	   public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
	      this.cityRepository = cityRepository;
	      this.countryRepository=countryRepository;
	   }
	   
	   //country 삭제
	   public boolean delete(int cityId) {
		   // 이슈 : 자식테이블에 외래키로 참조하는 행이 있다면?
		   // 자식테이블에 참조하는 행이 없다면 (select count(*) from city where country_id=?)
		   if(0 == cityRepository.countByCountryEntity(countryRepository.findById(cityId).orElse(null))) {
			   cityRepository.deleteById(cityId);
			   return true;
		   } else {
			   System.out.println("자식 테이블에 외래키로 참조하는 행이 존재합니다.");
			   return false;
		   }
	   }
	   
	   //country 수정
	   public void update(CityDto cityDto) {
		   CityEntity updateCityEntity = cityRepository.findById(cityDto.getCityId()).orElse(null);
		   updateCityEntity.setCity(cityDto.getCity());
	   }
	   
		public void save(CityDto cityDto) {
		   CityEntity saveCityEntity = new CityEntity();
		   saveCityEntity.setCity(cityDto.getCity());
		   
		   // countryEntity
		   CountryEntity countryEntity = countryRepository.findById(cityDto.getCountryId()).orElse(null);
		   saveCityEntity.setCountryEntity(countryEntity);
		   
		   
		   cityRepository.save(saveCityEntity);
		}
	   
	   public List<CityEntity> findAll() {
	      return cityRepository.findAll();
	   }
}
