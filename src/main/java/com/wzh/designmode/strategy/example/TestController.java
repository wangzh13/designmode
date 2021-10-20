package com.wzh.designmode.strategy.example;

import com.wzh.designmode.strategy.handler.StrategyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private StrategyHandler strategyHandler;

    @RequestMapping("/alive/{id}")
    public String ss(@PathVariable Integer id){
        return strategyHandler.get(Product.class, id).speak();
    }

}
