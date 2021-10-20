package com.wzh.designmode.strategy.example;

import com.wzh.designmode.strategy.annotation.StrategyType;

@StrategyType(Integer.class)
public interface Product {

    String speak();
}
