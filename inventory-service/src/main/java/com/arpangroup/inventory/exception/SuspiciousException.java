package com.arpangroup.inventory.exception;

public class SuspiciousException extends Exception {
    public SuspiciousException(String msg){
        super(msg);
    }

    public SuspiciousException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
