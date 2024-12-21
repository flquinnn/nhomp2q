package com.thick124.loplttd03.nhom03.API.Login.Model;

public class User {
    private String _id;
    private String email;
    private String username;
    private String avatar;
    private String birthday;
    private String gender;
    private String createdAt;

    public User(String _id, String email, String username, String avatar, String birthday, String gender, String createdAt) {
        this._id = _id;
        this.email = email;
        this.username = username;
        this.avatar = avatar;
        this.birthday = birthday;
        this.gender = gender;
        this.createdAt = createdAt;
    }

    public User(String email, String username, String avatar, String birthday, String gender, String createdAt) {
        this.email = email;
        this.username = username;
        this.avatar = avatar;
        this.birthday = birthday;
        this.gender = gender;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public String getId() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
