package com.arpangroup.inventory.controller.rest.ext;

import com.arpangroup.inventory.entity.StockEntity;
import com.arpangroup.inventory.service.StockService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Hidden
@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    private StockService stockService;
    //@Autowired private StockLogService stockLogService;

    @RequestMapping("")
    public Iterable<StockEntity> getAllStock() {
        return stockService.findAll();
    }

    @RequestMapping("/{id}")
    public Optional<StockEntity> searchStock(@PathVariable int id) {
        return stockService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public void addStock(@RequestBody StockEntity stockEntity) {
        stockService.insert(stockEntity);
        //stockLogService.insert(TheLogConverter.stockLogConverter(stock));
    }

    @RequestMapping(method = RequestMethod.PUT,value ="/{id}")
    public void updateStock(@RequestBody StockEntity stockEntity) {
        stockService.updateStock(stockEntity);
        //stockLogService.insert(TheLogConverter.stockLogConverter(stock));
    }

    @RequestMapping(method = RequestMethod.DELETE,value ="/{id}")
    public void deleteStock(@RequestBody StockEntity stockEntity) {
        stockService.deleteStock(stockEntity);
        //stockLogService.insert(TheLogConverter.stockLogConverter(stock));
    }

}
