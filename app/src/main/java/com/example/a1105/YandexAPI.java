package com.example.a1105;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YandexAPI {
    @GET("api/v1/predict.json/complete")
    Call<Model> predictor(@Query("key") String key , @Query("q") String q,@Query("lang") String lang);
}
