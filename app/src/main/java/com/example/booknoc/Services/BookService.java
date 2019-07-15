package com.example.booknoc.Services;

import com.example.booknoc.APINewYorkTimesSearch.ResponseApiNYSearch;
import com.example.booknoc.ApiNewYorkTimesHistory.ResponseApiNYHistory;
import com.example.booknoc.ApiNewYorkTimesRecent.ResponseApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookService {
    @GET("lists.json")
    Call<ResponseApi> getBooks(@Query("api-key") String key, @Query("list") String list);
    @GET("lists/{date}/{list}.json")
    Call<ResponseApiNYSearch> getBooksAdvancedSearch(@Path("date") String date, @Path("list") String list,@Query("api-key") String key );
    @GET("lists/best-sellers/history.json")
    Call<ResponseApiNYHistory> getBooksHistory(@Query("api-key") String key);
}


