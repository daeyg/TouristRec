package com.r4sh33d.tourister.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String TAG = RetrofitClient.class.getSimpleName();

    public Retrofit build() {
        return new Retrofit.Builder()
                .baseUrl("http://34.230.76.25:80")
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getHttpClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }
}
