package com.arpangroup.inventory.service;

import com.arpangroup.inventory.entity.StockEntity;

import java.util.Optional;

public interface StockService {
    public void insert(StockEntity stockEntity);
    public Optional<StockEntity> findById(int id);
    public Iterable<StockEntity> findAll();
    public void updateStock(StockEntity stockEntity);
    public void deleteStock(StockEntity stockEntity);
}
