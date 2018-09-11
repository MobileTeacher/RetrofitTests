package io.github.mobileteacher.apisample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static Retrofit retrofitInstance;
    private static String localURL = "";

    public static Retrofit getInstance(String baseURL){

        if (retrofitInstance == null || !localURL.equals(baseURL)){
            localURL = baseURL;
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofitInstance = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(localURL).build();
        }
        return retrofitInstance;
    }
}
