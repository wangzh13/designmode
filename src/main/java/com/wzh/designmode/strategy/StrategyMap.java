package com.wzh.designmode.strategy;

import com.wzh.designmode.strategy.exception.StrategyEnum;
import com.wzh.designmode.strategy.exception.StrategyException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StrategyMap {

    private Map<String, Map<Object, Object>> data = new HashMap<>();

    public <T> void put(String key, Map<Object, T> map){

        if (map == null || key == null){
            throw new StrategyException(StrategyEnum.DATA_ERROR.getCode(),
                    StrategyEnum.DATA_ERROR.getMsg());
        }
        data.put(key, (Map<Object, Object>) map);
    }

    public <T, R> R get(String key, Object entityKey, Class<R> result){

        if (entityKey == null || key == null){
            throw new StrategyException(StrategyEnum.DATA_ERROR.getCode(),
                    StrategyEnum.DATA_ERROR.getMsg());
        }
        return (R) data.get(key).get(entityKey);
    }

    public Map get(String key){
        return data.get(key);
    }

}
