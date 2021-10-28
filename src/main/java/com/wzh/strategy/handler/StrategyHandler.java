package com.wzh.strategy.handler;

import com.wzh.strategy.StrategyMap;
import com.wzh.strategy.annotation.StrategyType;
import com.wzh.strategy.exception.StrategyException;
import com.wzh.strategy.exception.StrategyEnum;
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

        R r = strategyMap.get(keyType + "_" + key, rule, obj);

        if (r == null){
            throw new StrategyException(StrategyEnum.TYPE_NOT_MATCH_ERROR.getMsg());
        }
        return r;
    }

}
