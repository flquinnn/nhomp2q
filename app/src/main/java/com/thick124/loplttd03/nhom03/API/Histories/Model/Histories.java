package com.thick124.loplttd03.nhom03.API.Histories.Model;
public class Histories {
    private String _id;
    private String userId;
    private double money;
    private String date;
    private String time;
    private String note;
    private String address;
    private Boolean typeOfExpenditure;
    private String title;
    private String image;

    public Histories( String _id,double money, String date, String time, String note, String address, Boolean typeOfExpenditure, String title, String image) {
        this._id = _id;
        this.money = money;
        this.date = date;
        this.time = time;
        this.note = note;
        this.address = address;
        this.typeOfExpenditure = typeOfExpenditure;
        this.title = title;
        this.image = image;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getTypeOfExpenditure() {
        return typeOfExpenditure;
    }

    public void setTypeOfExpenditure(Boolean typeOfExpenditure) {
        this.typeOfExpenditure = typeOfExpenditure;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
