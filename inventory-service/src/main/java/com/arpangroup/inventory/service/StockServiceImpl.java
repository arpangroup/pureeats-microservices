package com.arpangroup.inventory.service;

import com.arpangroup.inventory.entity.StockEntity;
import com.arpangroup.inventory.repository.StockRepository;
import com.arpangroup.inventory.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;

    public void insert(StockEntity stockEntity) {
        stockRepository.save(stockEntity);
    }

    public Optional<StockEntity> findById(int id) {
        return stockRepository.findById(id);
    }

    public Iterable<StockEntity> findAll() {
        return stockRepository.findAll();
    }

    public void updateStock(StockEntity stockEntity) {
        stockRepository.save(stockEntity);
    }

    public void deleteStock(StockEntity stockEntity) {
        stockRepository.delete(stockEntity);
    }
}
