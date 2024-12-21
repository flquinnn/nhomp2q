package com.thick124.loplttd03.nhom03;

import java.util.Currency;
import java.util.Date;

public class itemGrid {
    private String id;
    private String sotien;
    private String ngaychi;
    private String ghichu;
    private String diadiem;
    private String title;
    private String image;
    private Boolean type;
    public itemGrid(String id,String sotien, String ngaychi, String ghichu, String diadiem, String title, String image,Boolean type) {
        this.id = id;
        this.sotien = sotien;
        this.ngaychi = ngaychi;
        this.ghichu = ghichu;
        this.diadiem = diadiem;
        this.title = title;
        this.image = image;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }

    public String getNgaychi() {
        return ngaychi;
    }

    public void setNgaychi(String ngaychi) {
        this.ngaychi = ngaychi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
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

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}
