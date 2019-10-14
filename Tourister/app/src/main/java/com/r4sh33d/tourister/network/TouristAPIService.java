package com.r4sh33d.tourister.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TouristAPIService {

    @POST("trip/v1/")
    Call<RecommendationListModel> registerUserEmail(@Body RecommendationRequestData payload);

}
