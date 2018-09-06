package io.github.mobileteacher.apisample;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubAPI {

    //resposta crua
    @GET("users/{user}/repos")
    Call<ResponseBody> getRawUserRepos(@Path("user") String username);

    @GET("users/{user}/repos")
    Call<List<GithubRepo>> getUserRepos(@Path("user") String username);
}
