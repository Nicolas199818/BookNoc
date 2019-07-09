package com.example.booknoc;

import com.google.gson.annotations.SerializedName;

public class BookDTO {
    //On associe nos attributs au json (lors serialization ou deserealisation)
    @SerializedName("name") private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString(){
        return "BookDTO{" + "title='" + title + '}';
    }
}
