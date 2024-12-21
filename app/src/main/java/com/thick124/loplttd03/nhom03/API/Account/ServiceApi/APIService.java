package com.thick124.loplttd03.nhom03.API.Account.ServiceApi;

import com.ute.moneybuddyp2q.API.Account.Model.CloudinaryResponse;
import com.ute.moneybuddyp2q.API.Account.Model.UpdateAccountRequest;
import com.ute.moneybuddyp2q.API.Account.Model.UpdateAccountResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIService {
    @PUT("user/update/{userId}")
    Call<UpdateAccountResponse> update(@Path("userId") String userId, @Body UpdateAccountRequest updateAccountRequest);
    @Multipart
    @POST("cloudinary/upload") // Endpoint server dùng để upload ảnh
    Call<CloudinaryResponse> uploadAvatar(@Part MultipartBody.Part file);
}

