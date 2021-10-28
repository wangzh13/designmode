package com.wzh.designmode.strategy.handler;

import com.wzh.designmode.strategy.StrategyMap;
import com.wzh.designmode.strategy.annotation.StrategyType;
import com.wzh.designmode.strategy.exception.StrategyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import static com.wzh.designmode.strategy.exception.StrategyEnum.TYPE_NOT_MATCH_ERROR;

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
            throw new StrategyException(TYPE_NOT_MATCH_ERROR.getMsg());
        }
        return r;
    }

}
