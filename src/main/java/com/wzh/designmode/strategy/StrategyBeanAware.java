package com.wzh.designmode.strategy;

import com.wzh.designmode.strategy.annotation.StrategyType;
import com.wzh.designmode.strategy.annotation.strategyentity.StrategyEntity;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class StrategyBeanAware implements ApplicationContextAware {

    @Override
    @SuppressWarnings("all")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        StrategyMap strategyMap = applicationContext.getBean(StrategyMap.class);
        Map<String, StrategyEntity> beans = applicationContext.getBeansOfType(StrategyEntity.class);
        beans.forEach((key, value)->{

            Map map = new HashMap();
            generateKey(value.getClass(), (item, item2)->{
                if (item.equals("")){
                    return;
                }

                value.rule().forEach(val -> {
                    if ( val.getClass() != item2.value()){
                        return;
                    }
                    map.put(val, value);
                });

                Map data = strategyMap.get(item);
                if (data == null){
                    strategyMap.put(item, map);
                }else {
                    data.putAll(map);
                    strategyMap.put(item, data);
                }
            });
        });
    }

    @SuppressWarnings("all")
    private <T> void generateKey (Class<T> data, BiConsumer<String, StrategyType> consumer) {

        StringBuilder stringBuilder = new StringBuilder();
        Class[] classes = data.getInterfaces();
        Integer count = 0;
        for (int i = 0; i < classes.length; i++) {
            Annotation[] annotations = classes[i].getAnnotations();

            for (int j = 0; j < annotations.length; j++) {

                if (annotations[j].annotationType().getTypeName().
                        equals("com.wzh.designmode.strategy.annotation.StrategyType")) {
                    count = count + 1;
                }
            }
            if (count == 1) {

                StrategyType strategyType = (StrategyType) classes[i].getAnnotation(StrategyType.class);
                String keyType = strategyType
                        .value()
                        .toString()
                        .replace("class", "")
                        .trim();
                String key = classes[i]
                        .toString()
                        .replace("class", "")
                        .trim();
                stringBuilder.append(keyType).append("_").append(key);
                consumer.accept(stringBuilder.toString(), strategyType);
            }
            count = 0;
        }
    }

}
