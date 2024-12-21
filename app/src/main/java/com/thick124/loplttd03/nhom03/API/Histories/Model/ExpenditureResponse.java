package com.thick124.loplttd03.nhom03.API.Histories.Model;

public class ExpenditureResponse {
    private String message;
    private int statusCode;
    private Object data;


    public ExpenditureResponse(String message, int statusCode, Object data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
}