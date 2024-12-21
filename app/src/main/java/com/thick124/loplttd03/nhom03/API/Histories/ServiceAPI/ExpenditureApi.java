package com.thick124.loplttd03.nhom03.API.Histories.ServiceAPI;

import com.ute.moneybuddyp2q.API.Histories.Model.ExpenditureResponse;
import com.ute.moneybuddyp2q.API.Histories.RequestApi.ExpenditureRequest;
import com.ute.moneybuddyp2q.API.Histories.RequestApi.UpdateRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

// API interface
public interface ExpenditureApi {
    @POST("/expenditure")
    Call<ExpenditureResponse> createExpenditure(@Body ExpenditureRequest request);
    @PUT("/expenditure/update/6752be52086f5fbe8912fd3c")
    Call<ExpenditureResponse> update( @Body UpdateRequest request);
}