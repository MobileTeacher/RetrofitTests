package io.github.mobileteacher.apisample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StackActivity extends AppCompatActivity implements Callback<ResponseBody> {

    Retrofit retrofit;
    private final String BASE_URL = "https://api.stackexchange.com/2.2/";
    Call<ListWrapper<Question>> call;
    Call<ResponseBody> rawCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL).build();

    }


    public void getData(View view){
        StackAPI stackAPI = retrofit.create(StackAPI.class);
        EditText editText = findViewById(R.id.url_field);
        rawCall = stackAPI.loadRawQuestions("android", editText.getText().toString());
        rawCall.enqueue(this);

        call = stackAPI.loadQuestions("android", editText.getText().toString());
        call.enqueue(new Callback<ListWrapper<Question>>() {
            @Override
            public void onResponse(Call<ListWrapper<Question>> call, Response<ListWrapper<Question>> response) {
                List<Question> questions = response.body().items;
                String questionsData = "";
                for (Question question: questions) {
                    questionsData += String.format("%d %s\nlink: %s\n\n",
                        question.getQuestionId(), question.getTitle(), question.getLink());
                //subjects = subjects + ++i + " " + change.getSubject() + "\nInser\n";
                //Log.d("CALLLLLLL", String.valueOf(i));
                }

                TextView textView = findViewById(R.id.cool_response);
                textView.setText(questionsData);
            }

            @Override
            public void onFailure(Call<ListWrapper<Question>> call, Throwable t) {
                Toast.makeText(StackActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



//        @Override
//        public void onResponse(Call<List<List<Question>>> call, Response<List<List<Question>>> response) {
//            String questions = "";
//            int i = 0;
//            for (Question question: response.body()) {
//                questions += String.format("%d %s\nlink: %s\n\n",
//                        question.getQuestionId(), question.getTitle(), question.getLink());
//                //subjects = subjects + ++i + " " + change.getSubject() + "\nInser\n";
//                //Log.d("CALLLLLLL", String.valueOf(i));
//            }
//
//            TextView textView = findViewById(R.id.cool_response);
//            textView.setText(questions);
//        }
//
//        @Override
//        public void onFailure(Call<List<List<Question>>> call, Throwable t) {
//            Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        TextView textView = findViewById(R.id.raw_response);
        if (response.isSuccessful()){
            try{
                textView.setText(response.body().string());
            } catch (IOException exception){
                textView.setText(exception.getMessage());
            }
        }


    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Toast.makeText(StackActivity.this,
                t.getMessage(), Toast.LENGTH_LONG).show();
    }
}
