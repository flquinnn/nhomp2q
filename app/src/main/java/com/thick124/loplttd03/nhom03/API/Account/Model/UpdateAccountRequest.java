package com.thick124.loplttd03.nhom03.API.Account.Model;

public class UpdateAccountRequest {
    private String current_password;
    private String new_password;
    private String username;
    private String birthday;
    private String gender;
    private String avatar;
    private UpdateAccountRequest(Builder builder) {
        this.current_password = builder.current_password;
        this.new_password = builder.new_password;
        this.username = builder.username;
        this.birthday = builder.birthday;
        this.gender = builder.gender;
        this.avatar = builder.avatar;
    }

    public static class Builder {
        private String current_password;
        private String new_password;
        private String username;
        private String birthday;
        private String gender;
        private String avatar;
        public Builder setCurrentPassword(String current_password) {
            this.current_password = current_password;
            return this;
        }

        public Builder setNewPassword(String new_password) {
            this.new_password = new_password;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setBirthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }
        public Builder setAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public UpdateAccountRequest build() {
            return new UpdateAccountRequest(this);
        }
    }

    // Getters
    public String getCurrent_password() {
        return current_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public String getUsername() {
        return username;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getAvatar() {
        return avatar;
    }
}
