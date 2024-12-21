package com.thick124.loplttd03.nhom03.API.Histories.RequestApi;

public class UpdateRequest {
    private String title;
    private double money;
    private String note;
    private String address;
    private boolean typeOfExpenditure;

    public UpdateRequest(String title, double money, String note, String address, boolean typeOfExpenditure) {
        this.title = title;
        this.money = money;
        this.note = note;
        this.address = address;
        this.typeOfExpenditure = typeOfExpenditure;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
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

    public boolean isTypeOfExpenditure() {
        return typeOfExpenditure;
    }

    public void setTypeOfExpenditure(boolean typeOfExpenditure) {
        this.typeOfExpenditure = typeOfExpenditure;
    }
}
