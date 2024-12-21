package com.thick124.loplttd03.nhom03.API.Histories.RequestApi;

import java.util.Date;

public class ExpenditureRequest {
    private String userId;
    private String title;
    private String image;
    private double money;
    private Date date;
    private Date time;
    private String note;
    private String address;
    private boolean typeOfExpenditure;

    // Constructor
    public ExpenditureRequest(String userId, String title, String image, double money,
                              Date date, Date time, String note, String address,
                              boolean typeOfExpenditure) {
        this.userId = userId;
        this.title = title;
        this.image = image;
        this.money = money;
        this.date = date;
        this.time = time;
        this.note = note;
        this.address = address;
        this.typeOfExpenditure = typeOfExpenditure;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public double getMoney() { return money; }
    public void setMoney(double money) { this.money = money; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Date getTime() { return time; }
    public void setTime(Date time) { this.time = time; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public boolean isTypeOfExpenditure() { return typeOfExpenditure; }
    public void setTypeOfExpenditure(boolean typeOfExpenditure) { this.typeOfExpenditure = typeOfExpenditure; }
}