package com.codegym.englishbackend.basic;

public class AuthenticationBean {
    private String message;

    public AuthenticationBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Authenticate{" +
                "message='" + message + '\'' +
                '}';
    }
}
