package com.wzh.strategy.exception;

public class StrategyException extends RuntimeException{

    public StrategyException(String message) {
        super(message);
    }

    public StrategyException(int code, String message) {
        super(message);
    }
}
