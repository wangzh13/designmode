package com.wzh.designmode.strategy.example;

import com.wzh.designmode.strategy.annotation.strategyentity.StrategyEntity;
import org.springframework.stereotype.Component;

@Component
public class Consumer3 implements Product, StrategyEntity<Integer> {

    @Override
    public Integer[] rule() {
        Integer[] integers = new Integer[3];
        integers[0] = 1;
        integers[1] = 2;
        integers[2] = 3;
        return integers;
    }

    @Override
    public String speak() {
        return "我是小猫";
    }
}
