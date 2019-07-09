package com.example.booknoc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyBookService {
    //C'est ici que l'on va mettre les différentes méthodes correspondant aux requêtes que l'on veut faire.
    //Ce sera une méthode GET de l'api que tu vas utiliser.
    @GET("lists.json")
    Call<List<BookDTO>> getBooks(@Query("api-key") String key,@Query("list") String list);


}
