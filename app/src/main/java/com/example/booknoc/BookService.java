package com.example.booknoc;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {
    @GET("lists.json")
    Call<ResponseApi> getBooks(@Query("api-key") String key, @Query("list") String list);
}
