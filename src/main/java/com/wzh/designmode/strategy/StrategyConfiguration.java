package com.wzh.designmode.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StrategyConfiguration {

    @Bean
    public StrategyMap createStrategyMap(){
        return new StrategyMap();
    }
}
