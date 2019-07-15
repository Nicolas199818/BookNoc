package com.example.booknoc;

import java.util.ArrayList;
import java.util.List;

public class CateogoryBestSellerTitle {
    List<String> listCategory;

    private static CateogoryBestSellerTitle instance;


    public static  CateogoryBestSellerTitle getInstance(){
        if(instance == null){
            instance = new CateogoryBestSellerTitle();
        }
        return instance;
    }

    //On fait le constructeur :
    private CateogoryBestSellerTitle(){
        this.listCategory = new ArrayList<>();
        this.listCategory.add(new String("Travel"));
        this.listCategory.add(new String("Sports"));
        this.listCategory.add(new String("Science"));
        this.listCategory.add(new String("Religion Spirituality and Faith"));
        this.listCategory.add("Race and Civil Rights");
        this.listCategory.add("Family");
        this.listCategory.add("Relationships");
        this.listCategory.add("Humor");
        this.listCategory.add("Health");
        this.listCategory.add("Espionage");
        this.listCategory.add("Education");
        this.listCategory.add("Culture");
        this.listCategory.add("Celebrities");
    }

    //On fait une fonction qui renvoie la liste des catégories :
    public List<String> getListCategory(){
        return this.listCategory;
    }


}
