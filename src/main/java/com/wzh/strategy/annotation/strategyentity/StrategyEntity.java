package com.wzh.strategy.annotation.strategyentity;

import java.util.List;

public interface StrategyEntity<T> {

    List<T> rule();
}
