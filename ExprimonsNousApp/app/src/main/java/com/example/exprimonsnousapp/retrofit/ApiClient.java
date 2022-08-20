package com.example.exprimonsnousapp.retrofit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // ROOT OF THE API
    private static final String BASE_URL = "https://www.titan-photography.com";

    //OkHttpClient myclient= new OkHttpClient.Builder().addInterceptor(new MyInterceptor()).build();

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
