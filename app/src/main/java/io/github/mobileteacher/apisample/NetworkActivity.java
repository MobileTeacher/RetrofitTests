package io.github.mobileteacher.apisample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class NetworkActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private final String BASE_URL = "https://github.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        progressBar = findViewById(R.id.progressBar);
    }



    public void getData(View view){

        EditText editText = findViewById(R.id.url_field);

        GetDataTask myTask = new GetDataTask();

        myTask.execute(editText.getText().toString());

    }


    public class GetDataTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                URL url = new URL(strings[0]);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                String fulltext = "";
                while (line != null){
                    fulltext += line;
                    line = bufferedReader.readLine();
                }

                return fulltext;

            } catch (Exception exception){
                return exception.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);

            TextView textView = findViewById(R.id.raw_response);
            textView.setText(result);

            textView = findViewById(R.id.cool_response);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(GithubRepo.class, new GithubRepoDeserializer())
                    .create();

            GithubRepo repo = gson.fromJson(result, GithubRepo.class);
            textView.setText(repo.getName());

        }
    }

}
