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

import com.sakila.api.entity.StoreEntity;
import com.sakila.api.service.StoreService;

@CrossOrigin
@RestController
public class StoreController {
    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @DeleteMapping("/store/{storeId}")
    public ResponseEntity<String> deleteStore(@PathVariable int storeId) {
        boolean result = storeService.delete(storeId);
        return result ? new ResponseEntity<>("삭제성공", HttpStatus.OK)
                      : new ResponseEntity<>("삭제실패", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping("/store")
    public ResponseEntity<String> updateStore(@RequestBody StoreEntity storeEntity) {
        storeService.update(storeEntity);
        return new ResponseEntity<>("수정성공", HttpStatus.OK);
    }

    @PostMapping("/store")
    public ResponseEntity<String> saveStore(@RequestBody StoreEntity storeEntity) {
        storeService.save(storeEntity);
        return new ResponseEntity<>("입력성공", HttpStatus.OK);
    }

    @GetMapping("/store")
    public ResponseEntity<List<StoreEntity>> getAllStores() {
        return new ResponseEntity<>(storeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<StoreEntity> getStoreById(@PathVariable int storeId) {
        StoreEntity store = storeService.findById(storeId);
        return store != null ? new ResponseEntity<>(store, HttpStatus.OK)
                              : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
