package com.example.booknoc;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkProvider {
    BookService bookService;
    String apiKey = "bJcf9kNxaqqFBu3DnnolIf2CLnAgOe0p";

    private static NetworkProvider instance;

    public static NetworkProvider getInstance() {
        if (instance == null) {
            instance = new NetworkProvider();
        }
        return instance;
    }

    private NetworkProvider() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.nytimes.com/svc/books/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bookService = retrofit.create(BookService.class);
    }

    public void getBook(final Listener<List<Book>> listener){
        Call<ResponseApi> call = bookService.getBooks(apiKey,"hardcover-fiction");

        call.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                Log.v("DEBUG",String.valueOf(response.body().getResults().get(0).getBook_details().get(0).getTitle()));
                //C'est ici que l'on transmettra les données
                List<Book> listBook = new ArrayList<>();
                listBook.add(new Book("Harry Potter"));
                listBook.add(new Book("Lord of the ring"));
                listBook.add(new Book("Harry"));
                listener.onSuccess(listBook);
            }

            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                Log.d("DEBUG","Fuyer, fuyer pauvres fo");

            }
        });
    }

    public interface Listener<T>{
        void onSuccess(T data);
        void onError(Throwable t);
    }
}
//On pourra récupérer avec le Listener.