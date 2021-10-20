package com.wzh.designmode.strategy;

import com.wzh.designmode.strategy.annotation.strategyentity.StrategyEntity;
import com.wzh.designmode.strategy.annotation.StrategyType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class StrategyRunListener implements SpringApplicationRunListener {


    SpringApplication springApplication;

    public StrategyRunListener(SpringApplication springApplication, String[] contextArgs) {
        this.springApplication = springApplication;
    }

    @Override
    @SuppressWarnings("all")
    public void started(ConfigurableApplicationContext context) {

        StrategyMap strategyMap = context.getBean(StrategyMap.class);

        Map<String, StrategyEntity> beans = context.getBeansOfType(StrategyEntity.class);

        Map map = new HashMap();
        beans.forEach((key, value)->{

            generateKey(value.getClass(), (item, item2)->{

                if (item.equals("")){
                    return;
                }

                for (int i = 0; i < value.rule().length; i++) {
                    if ( value.rule()[i].getClass() != item2.value()){
                        return;
                    }
                    map.put(value.rule()[i], value);
                }
                strategyMap.put(item, map);
            });
        });
    }

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
