package com.wzh.designmode.strategy.exception;

public enum StrategyEnum {

    DATA_ERROR(1,"Map数据不可为null"),

    TYPE_NOT_MATCH_ERROR(2,"未找到对应的策略模式");

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
