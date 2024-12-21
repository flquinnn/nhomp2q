package com.thick124.loplttd03.nhom03.API.Histories.ServiceAPI;
import com.ute.moneybuddyp2q.API.Histories.Model.Histories;
import com.ute.moneybuddyp2q.API.Histories.Model.HistoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIGetHistories {
    @GET("history/{userId}?page=1") // Endpoint ví dụ từ JSONPlaceholder
    Call<HistoryResponse> getPosts(@Path("userId") String userId);
    @GET("history/{userId}/getPost?postId={postId}") // Endpoint ví dụ từ JSONPlaceholder
    Call<Histories> getPost(@Path("userId") String userId, @Path("postId") String postId);
}