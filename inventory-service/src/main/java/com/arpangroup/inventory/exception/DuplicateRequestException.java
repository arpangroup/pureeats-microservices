package com.arpangroup.inventory.exception;

public class DuplicateRequestException extends Exception{
    public DuplicateRequestException() { super(); }
    public DuplicateRequestException(String message) { super(message); }
}
