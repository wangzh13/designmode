package com.wzh.designmode.strategy;

import com.wzh.designmode.strategy.annotation.StrategyType;
import com.wzh.designmode.strategy.example.Consumer1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {



    public static void main(String[] args) {

        String sss = "com.wzh.designmode.strategy.annotation.StrategyType";

        StrategyMap strategyMap = new StrategyMap();
        StrategyMap strategyMap1 = new StrategyMap();

        Consumer1 consumer1 = new Consumer1();


    }

}
