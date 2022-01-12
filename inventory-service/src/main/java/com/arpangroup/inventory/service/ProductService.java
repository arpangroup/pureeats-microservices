package com.arpangroup.inventory.service;

import com.arpangroup.inventory.dto.request.ProductRequestDto;
import com.arpangroup.inventory.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    public ProductEntity insert(ProductRequestDto request);
    public ProductEntity findById(int id);
    public ProductEntity findByName(String productName);
    public List<ProductEntity> findAll();
    public ProductEntity updateProduct(int id, ProductRequestDto request);
    public void deleteProduct(int id);
}
