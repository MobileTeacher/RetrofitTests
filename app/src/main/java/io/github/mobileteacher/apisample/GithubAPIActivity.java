package io.github.mobileteacher.apisample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GithubAPIActivity extends AppCompatActivity {

    Retrofit retrofit;
    GithubAPI githubAPI;
    Call<ResponseBody> rawCall;
    Call<List<GithubRepo>> call;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        retrofit = RetrofitManager.getInstance();

        githubAPI = retrofit.create(GithubAPI.class);

        progressBar = findViewById(R.id.progressBar);


    }


    public void getData(View view){
        EditText editText = findViewById(R.id.url_field);

        rawCall = githubAPI.getRawUserRepos(editText.getText().toString());

        call = githubAPI.getUserRepos(editText.getText().toString());

        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                String repos = "";

                for ( GithubRepo repository: response.body()) {
                    repos += String.format("%d %s\n%s\n\n",
                            repository.getId(),
                            //repository.getOwner(),
                            repository.getName(),
                            repository.getDescription());
                }

                TextView textView = findViewById(R.id.cool_response);
                textView.setText(repos);
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Toast.makeText(GithubAPIActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                TextView textView = findViewById(R.id.cool_response);
                textView.setText("XABU");
            }
        });


        progressBar.setVisibility(View.VISIBLE);
        rawCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressBar.setVisibility(View.GONE);
                TextView textView = findViewById(R.id.raw_response);
                try {
                    textView.setText(response.body().string());
                } catch (IOException exception){
                    Toast.makeText(GithubAPIActivity.this,
                            exception.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(GithubAPIActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }


}
