package com.wzh.designmode.strategy.handler;

import com.wzh.designmode.strategy.StrategyMap;
import com.wzh.designmode.strategy.annotation.StrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(StrategyMap.class)
public class StrategyHandler {

    @Autowired
    private StrategyMap strategyMap;

    public <T, R> R get(Class<R> obj, T rule){

        String keyType = obj.getAnnotation(StrategyType.class)
                .value()
                .toString()
                .replace("class", "")
                .trim();

        String key = obj
                .toString()
                .replace("class", "")
                .trim();

        return strategyMap.get(keyType + "_" + key, rule, obj);
    }

}
