package com.wzh.designmode.strategy.util;

import com.wzh.designmode.strategy.exception.StrategyException;

import static com.wzh.designmode.strategy.exception.StrategyEnum.TYPE_NOT_MATCH_ERROR;

public class TypeUtil {

    public static Object convertType(Object obj){

        if (obj instanceof Integer){
            Integer integer = (Integer) obj;
            return integer.intValue();
        }else if (obj instanceof Boolean){
            Boolean aBoolean = (Boolean) obj;
            return aBoolean.booleanValue();
        }else if (obj instanceof Double){
            Double aDouble = (Double) obj;
            return aDouble.doubleValue();
        }else if (obj instanceof Character){
            Character character = (Character) obj;
            return character.charValue();
        }else if (obj instanceof Byte){
            Byte aByte = (Byte) obj;
            return aByte.byteValue();
        }else if (obj instanceof Long){
            Long aLong = (Long) obj;
            return aLong.longValue();
        }else if (obj instanceof Float){
            Float aFloat = (Float) obj;
            return aFloat.floatValue();
        }else if (obj instanceof Short){
            Short aShort = (Short) obj;
            return aShort.shortValue();
        }else if (obj.getClass().isEnum()){
            return obj;
        }else if (obj.getClass() == Class.class){
            return obj;
        }else {
            throw new StrategyException(TYPE_NOT_MATCH_ERROR.getMsg());
        }
    }

}
