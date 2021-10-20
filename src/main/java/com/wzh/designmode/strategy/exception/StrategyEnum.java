package com.wzh.designmode.strategy.exception;

public enum StrategyEnum {

    DATA_ERROR(1,"Map数据不可为null"),

    TYPE_NOT_MATCH_ERROR(2,"类型不符合要求，类型只可以是（基础数据类型，Class类对象，枚举）");

    private int code;
    private String msg;

    StrategyEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
