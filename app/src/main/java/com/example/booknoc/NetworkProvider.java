package com.example.booknoc;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkProvider {
    MyBookService myBookService;
    String apiKey = "bJcf9kNxaqqFBu3DnnolIf2CLnAgOe0p";

    //On fait de cette classe un Singleton.
    private static NetworkProvider instance;

    public static NetworkProvider getInstance() {
        if (instance == null) {
            instance = new NetworkProvider();
        }
        return instance;
    }

    //On creer le constructeur de la classe.
    private NetworkProvider() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.nytimes.com/svc/books/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       myBookService = retrofit.create(MyBookService.class);
    }


    public void getBook(final Listener<List<Book>> listener) {
        Log.d("DEBUG","On rentre biens dans la fonction getBook de Network Provider");
        Log.d("DEDEBUG",myBookService.getBooks(apiKey,"hardcover-fiction").request().toString());
        myBookService.getBooks(apiKey,"hardcover-fiction").enqueue(new Callback<List<BookDTO>>() {

            @Override public void onResponse(Call call, Response response) {
                Log.d("DEBUG","On rentre bien dans la réponse à l'appel API. Ce qui est bizarre car ça devrait planter.");
                //Comment les transformer en book.
                //List<BookDTO> bookDTOList = response.body();
                //List<Book> bookList = BookMapper.map(bookDTOList);
                //listener.onSuccess(bookList);
            }

            @Override public void onFailure(Call<List<BookDTO>> call, Throwable t) {
                Log.d("DEBUG","On rentre bien dans la fonction qui indique que ça plante");
                listener.onError(t);
            }
        });
    }

    public interface Listener<T> {
        void onSuccess(T data);

        void onError(Throwable t);
    }
}

//En premier, on va essayer de récupérer les données de listes.json.