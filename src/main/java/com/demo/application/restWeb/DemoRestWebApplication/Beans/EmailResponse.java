package com.demo.application.restWeb.DemoRestWebApplication.Beans;

public class EmailResponse {
    private int code;
    private String message;

    public EmailResponse() {
    }

    public EmailResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EmailResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
