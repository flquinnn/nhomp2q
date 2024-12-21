package com.thick124.loplttd03.nhom03.API.Login;

import android.app.Application;

import com.ute.moneybuddyp2q.API.Login.Model.User;

public class MyApp extends Application {
    private static MyApp instance;
    private User currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApp getInstance() {
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
