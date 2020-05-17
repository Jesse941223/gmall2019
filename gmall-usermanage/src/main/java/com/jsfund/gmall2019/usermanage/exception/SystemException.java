package com.jsfund.gmall2019.usermanage.exception;

/**
 * 自定义异常
 */
public class SystemException extends Exception {

    private String massage;

    public SystemException(String massage){
        this.massage = massage;

    }

    public String getMassage() {
        return massage;
    }

    @Override
    public String toString() {
        return "SystemException{" +
                "massage='" + massage + '\'' +
                '}';
    }
}
