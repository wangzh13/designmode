package com.wzh.designmode.strategy.example;

import com.wzh.designmode.strategy.annotation.strategyentity.StrategyEntity;
import org.springframework.stereotype.Component;

@Component
public class Consumer1 implements Product, StrategyEntity<Integer> {

    @Override
    public Integer[] rule() {
        Integer[] integers = new Integer[3];
        integers[0] = 13678123;
        integers[1] = 136123123;
        integers[2] = 136744423;
        return integers;
    }

    @Override
    public String speak() {
        return "我是小老虎";
    }
}
