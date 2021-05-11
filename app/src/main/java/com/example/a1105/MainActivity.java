package com.example.a1105;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String URL ="https://predictor.yandex.net/";
    public static final String KEY = "pdct.1.1.20210511T133909Z.819b5e0869a35da4.e73ffd702ff9833179cbe4850371671124a79d5b";
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //запрос в яндекс
                doRequest();
            }
        });
    }

    public void doRequest() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        YandexAPI yandexAPI=retrofit.create((YandexAPI.class));
        Call<Model> call =yandexAPI.predictor(KEY,editText.getText().toString(),"en");
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.code()==200){
                    boolean endOfWord=response.body().getEndOfWord();
                    int pos = response.body().getPos();
                    List<String> list = response.body().getText();
                    textView.setText((list.get(0)));
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e("RRR",t.getMessage());
            }
        });
    }
}