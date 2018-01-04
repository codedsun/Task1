package com.example.suneetsrivastava.task1;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by suneetsrivastava on 04/01/18.
 */

public interface FetchService {

    @GET("/tutorial/jsonparsetutorial.txt")
    Call<WorldPopulation> fetch();
}
