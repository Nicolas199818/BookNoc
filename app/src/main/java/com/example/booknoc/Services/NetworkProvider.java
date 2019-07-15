package com.example.booknoc.Services;

import android.util.Log;

import com.example.booknoc.APINewYorkTimesSearch.Books;
import com.example.booknoc.APINewYorkTimesSearch.ResponseApiNYSearch;
import com.example.booknoc.ApiGoogleBooks.ResponseApiGoogle;
import com.example.booknoc.ApiNewYorkTimesHistory.ResponseApiNYHistory;
import com.example.booknoc.ApiNewYorkTimesHistory.Result;
import com.example.booknoc.ApiNewYorkTimesRecent.ResponseApi;
import com.example.booknoc.ApiNewYorkTimesRecent.Results;
import com.example.booknoc.Data_Application.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkProvider {
    BookService bookService;
    GoogleBookService googleBookService;
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

        Retrofit retrofit2 = new Retrofit.Builder().baseUrl("https://www.googleapis.com/books/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        googleBookService = retrofit2.create(GoogleBookService.class);
    }

    public void getBook(String category,final Listener<List<Book>> listener){
        Call<ResponseApi> call = bookService.getBooks(apiKey,"hardcover-fiction");

        call.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                Log.v("DEBUG",String.valueOf(response.body().getResults().get(0).getBook_details().get(0).getTitle()));
                //C'est ici que l'on transmettra les données
                int i = 0;
                List<Book> listBook = new ArrayList<>();
                for (Results result : response.body().getResults()){
                    listBook.add(new Book(result.getBook_details().get(0).getTitle(),result.getBook_details().get(0).getAuthor(),result.getBook_details().get(0).getPrice()));
                }
                listener.onSuccess(listBook);
            }

            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                Log.d("DEBUG","Fuyer, fuyer pauvres fo");


            }
        });
    }

    //On fait une fonction qui à partir d'un nom de livre va retourner l'image correspondante grâce à l'api google books
    public void getBookGoogleImage(String titleBook,final Listener<String> listener){
        Call<ResponseApiGoogle> call = googleBookService.getBooksByNameAndAuthor(titleBook);

        call.enqueue(new Callback<ResponseApiGoogle>() {
            @Override
            public void onResponse(Call<ResponseApiGoogle> call, Response<ResponseApiGoogle> response) {
                if(response.body()!=null){
                    if(response.body().getItems().get(0).getVolumeInfo().getImageLinks()!=null) {
                        Log.v("DEBUGGOOGLE", String.valueOf(response.body().getItems().get(0).getVolumeInfo().getImageLinks().getThumbnail()));
                        listener.onSuccess(response.body().getItems().get(0).getVolumeInfo().getImageLinks().getThumbnail());
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseApiGoogle> call, Throwable t) {
                Log.d("DEBUG","Fuyer, fuyer pauvres fo");

            }
        });
    }


    public void getBookHistory(String category,final Listener<List<Book>> listener){
        Call<ResponseApiNYHistory> call = bookService.getBooksHistory(apiKey);

        call.enqueue(new Callback<ResponseApiNYHistory>() {
            @Override
            public void onResponse(Call<ResponseApiNYHistory> call, Response<ResponseApiNYHistory> response) {
                Log.v("DEBUG",String.valueOf(response.body()));
                //C'est ici que l'on transmettra les données
                int i = 0;
                List<Book> listBook = new ArrayList<>();
                for (Result result : response.body().getResults()){
                    listBook.add(new Book(result.getTitle(),result.getAuthor(),(int)result.getPrice()));
                }
                listener.onSuccess(listBook);
            }

            @Override
            public void onFailure(Call<ResponseApiNYHistory> call, Throwable t) {
                Log.d("DEBUG","Test du fail");
                t.printStackTrace();

            }
        });
    }

    public void getBookSearch(String category,String date,final Listener<List<Book>> listener){
        Call<ResponseApiNYSearch> call = bookService.getBooksAdvancedSearch("current",category,apiKey);

        call.enqueue(new Callback<ResponseApiNYSearch>() {
            @Override
            public void onResponse(Call<ResponseApiNYSearch> call, Response<ResponseApiNYSearch> response) {
                Log.v("DEBUG",String.valueOf(response.body()));
                //C'est ici que l'on transmettra les données
                int i = 0;
                List<Book> listBook = new ArrayList<>();
                for (Books books : response.body().getResults().getBooks()){
                    listBook.add(new Book(books.getTitle(),books.getAuthor(),(int)books.getPrice()));
                }
                listener.onSuccess(listBook);
            }

            @Override
            public void onFailure(Call<ResponseApiNYSearch> call, Throwable t) {
                Log.d("DEBUG","Test du fail");
                t.printStackTrace();

            }
        });
    }


    public interface Listener<T>{
        void onSuccess(T data);
        void onError(Throwable t);
    }
}
