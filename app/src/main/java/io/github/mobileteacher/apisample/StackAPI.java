package io.github.mobileteacher.apisample;




import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StackAPI {

    //
    @GET("search?order=desc&sort=activity&site=stackoverflow")
    Call<ListWrapper<Question>> loadQuestions(@Query("tagged") String tagged, @Query("intitle") String intitle);

    @GET("search?order=desc&sort=activity&site=stackoverflow")
    Call<ResponseBody> loadRawQuestions(@Query("tagged") String tagged, @Query("intitle") String intitle);
}
