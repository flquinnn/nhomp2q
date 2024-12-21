package com.thick124.loplttd03.nhom03.API.Login.ServiceAPI;

import com.ute.moneybuddyp2q.API.Login.Model.LoginRequest;
import com.ute.moneybuddyp2q.API.Login.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST("user/signin")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
