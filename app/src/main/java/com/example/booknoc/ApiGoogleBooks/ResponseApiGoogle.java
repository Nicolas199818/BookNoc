package com.example.booknoc.ApiGoogleBooks;

import java.util.List;

//Tout comme pour l'api new York Times, on code les différentes classes correspondant à notre JSON.
public class ResponseApiGoogle {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
