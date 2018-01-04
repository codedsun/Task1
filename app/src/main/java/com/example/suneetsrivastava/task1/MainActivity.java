package com.example.suneetsrivastava.task1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Retrofit retrofit;
    int permissionCheck;
    RecyclerView.LayoutManager layoutManager;
    private static final String TAG="Log";
    private ArrayList<Country> countries;
    public static String BASE_URL="http://www.androidbegin.com";
    Call<WorldPopulation> worldPolpulationCall;
    FetchService fetchService;
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.imageRecylerView);


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
             retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
             fetchService = retrofit.create(FetchService.class);
             worldPolpulationCall= fetchService.fetch();
             worldPolpulationCall.enqueue(new Callback<WorldPopulation>() {
                 @Override
                 public void onResponse(Call<WorldPopulation> call, Response<WorldPopulation> response) {
                     Toast.makeText(MainActivity.this,"Data Fetched",Toast.LENGTH_LONG).show();
                     countries=response.body().getCountries();
                     layoutManager = new GridLayoutManager(MainActivity.this,4);
                     recyclerView.setLayoutManager(layoutManager);
                     recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,countries);
                     recyclerView.setAdapter(recyclerViewAdapter);

                 }

                 @Override
                 public void onFailure(Call<WorldPopulation> call, Throwable t) {
                     Toast.makeText(MainActivity.this, "Failure in Fetching Data", Toast.LENGTH_SHORT).show();
                 }
             });

            }
        });
        permissionCheck= ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET);
        Log.e(TAG, "onCreate: "+permissionCheck );
        if(permissionCheck!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},1);
        }
        else
            t.run();


    }
}
