package com.wzh.designmode.strategy.example;

import com.wzh.designmode.strategy.annotation.strategyentity.StrategyEntity;
import com.wzh.designmode.strategy.exception.StrategyEnum;
import org.springframework.stereotype.Component;

@Component
public class Consumer2 implements Product, StrategyEntity<Integer> {

    @Override
    public Integer[] rule() {
        Integer[] integers = new Integer[3];
        integers[0] = 198;
        integers[1] = 126;
        integers[2] = 132;
        return integers;
    }

    @Override
    public String speak() {
        return "我是小狗";
    }
}
