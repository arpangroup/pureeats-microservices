package com.arpangroup.inventory.service;

import com.arpangroup.inventory.dto.request.ProductRequestDto;
import com.arpangroup.inventory.dto.request.StoreRequest;
import com.arpangroup.inventory.entity.ProductEntity;
import com.arpangroup.inventory.entity.StoreEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreService {
    public StoreEntity saveOrUpdate(StoreEntity request, MultipartFile file);
    public StoreEntity findById(int id);
    public StoreEntity findBySlug(String slug);
    public List<StoreEntity> findAll();
    public StoreEntity updateStore(int id, StoreRequest request);
    public void deleteStore(int id);
}
