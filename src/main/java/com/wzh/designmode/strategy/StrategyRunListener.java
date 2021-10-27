package com.wzh.designmode.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Map;

public class StrategyRunListener implements SpringApplicationRunListener {

    private final Logger log= LoggerFactory.getLogger(StrategyRunListener.class);

    SpringApplication springApplication;

    public StrategyRunListener(SpringApplication springApplication, String[] contextArgs) {
        this.springApplication = springApplication;
    }

    @Override
    @SuppressWarnings("all")
    public void started(ConfigurableApplicationContext context) {

        StrategyMap strategyMap = context.getBean(StrategyMap.class);
        String resourcePath = null;
        String classPath = null;
        try {
            classPath = context.getResource("classpath:").getURL().getPath();

            if (classPath.contains("/build/classes/")) {
                resourcePath = classPath.substring(0, classPath.indexOf("/build/classes/"))
                        + "/src/main/resources/strategy";
            } else if (classPath.contains("/target/classes/")) {
                resourcePath = classPath.substring(0, classPath.indexOf("/target/classes/"))
                        + "/src/main/resources/strategy";
            }

            Path path = Paths.get(resourcePath);
            Path pathFile = Paths.get(resourcePath,"strategy.txt");
            if (!Files.exists(path, new LinkOption[0])) {
                Files.createDirectories(path);
            }

            if (!Files.exists(pathFile, new LinkOption[0])) {

                try (BufferedWriter writer = Files.newBufferedWriter(pathFile)) {
                    writer.write("======= 当前策略模式注册相应关系 =======\n");
                }
            }

            Field field = strategyMap.getClass().getDeclaredField("data");
            field.setAccessible(true);
            Map<String, Map<Object, Object>> data  = (Map<String, Map<Object, Object>>) field.get(strategyMap);

            try (BufferedWriter writer = Files.newBufferedWriter(pathFile,
                    StandardCharsets.UTF_8, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {

                for (Map.Entry<String, Map<Object, Object>> entry : data.entrySet()){
                    writer.write("模式名称：" + entry.getKey() + "\n");
                    for (Map.Entry<Object, Object> entry2 : entry.getValue().entrySet()){
                        writer.write("    具体实现：" +
                                entry2.getKey().toString() +
                                "：" +
                                entry2.getValue().getClass().getName() +
                                "\n");
                    }
                    writer.write("\n");
                    writer.write("\n");
                }
            }

        } catch (IOException e) {
            log.error("StrategyMap值输出文件失败", e);
        } catch (NoSuchFieldException e) {
            log.error("获取StrategyMap值失败", e);
        } catch (IllegalAccessException e) {
            log.error("StrategyMap值转换json失败", e);
        }
    }

}
