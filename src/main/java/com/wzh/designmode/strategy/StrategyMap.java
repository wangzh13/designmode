package com.wzh.designmode.strategy;

import com.wzh.designmode.strategy.exception.StrategyEnum;
import com.wzh.designmode.strategy.exception.StrategyException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StrategyMap {

    private Map<String, Map<Object, Object>> data = new HashMap<>();


    public <T> void put(String type, Map<Object, T> map){

        if (map == null || type == null){
            throw new StrategyException(StrategyEnum.DATA_ERROR.getCode(),
                    StrategyEnum.DATA_ERROR.getMsg());
        }
        data.put(type, (Map<Object, Object>) map);
    }

    public <T> void put(Object key, T value, String type){

        if (key == null || value == null || type == null){
            throw new StrategyException(StrategyEnum.DATA_ERROR.getCode(),
                    StrategyEnum.DATA_ERROR.getMsg());
        }

        Map<Object, Object> map = data.get(type);

        if (map == null){
            Map<Object, Object> newMap = new HashMap<>();
            newMap.put(key, value);
            data.put(type, newMap);
        }else {
            map.put(key, value);
        }

    }

    public <T, R> R get(String type, Object key, Class<R> result){

        if (key == null || type == null){
            throw new StrategyException(StrategyEnum.DATA_ERROR.getCode(),
                    StrategyEnum.DATA_ERROR.getMsg());
        }
        return (R) data.get(type).get(key);
    }

}
