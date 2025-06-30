package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.entity.StoreEntity;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class StoreService {
	private StoreRepository storeRepository;
	
	public StoreService(StoreRepository storeRepository) {
		// 주입전에 선행작업 or 테스트, ...
		this.storeRepository = storeRepository;
	}
	
    public boolean delete(int storeId) {
        if (storeRepository.existsById(storeId)) {
            storeRepository.deleteById(storeId);
            return true;
        }
        return false;
    }

    public void update(StoreEntity storeEntity) {
        StoreEntity updateTarget = storeRepository.findById(storeEntity.getStoreId()).orElse(null);
        if (updateTarget != null) {
            updateTarget.setManagerStaffId(storeEntity.getManagerStaffId());
            updateTarget.setLastUpdate(storeEntity.getLastUpdate());
            updateTarget.setAddressEntity(storeEntity.getAddressEntity());
        }
    }

    public void save(StoreEntity storeEntity) {
        storeRepository.save(storeEntity);
    }

    public List<StoreEntity> findAll() {
        return storeRepository.findAll();
    }

    public StoreEntity findById(int storeId) {
        return storeRepository.findById(storeId).orElse(null);
    }
    
    
}

