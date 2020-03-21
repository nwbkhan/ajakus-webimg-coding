package com.abcd.seleniumwebpagecpature.exceptions;

public class CaptureException extends RuntimeException {
    public CaptureException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CaptureException(String s) {
        super(s);
    }
}
