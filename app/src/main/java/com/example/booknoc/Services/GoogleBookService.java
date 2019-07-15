package com.example.booknoc.Services;

import com.example.booknoc.ApiGoogleBooks.ResponseApiGoogle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBookService {
    @GET("volumes")
    Call<ResponseApiGoogle> getBooksByNameAndAuthor(@Query("q") String q);
}
//https://www.googleapis.com/books/v1/