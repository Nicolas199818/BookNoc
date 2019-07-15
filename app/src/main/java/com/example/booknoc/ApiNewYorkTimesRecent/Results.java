package com.example.booknoc.ApiNewYorkTimesRecent;

import java.util.List;

public class Results {
    private String list_name;
    private String published_date;
    private String amazon_product_url;
    private List<BookDetail> book_details;

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getAmazon_product_url() {
        return amazon_product_url;
    }

    public void setAmazon_product_url(String amazon_product_url) {
        this.amazon_product_url = amazon_product_url;
    }

    public List<BookDetail> getBook_details() {
        return book_details;
    }

    public void setBook_details(List<BookDetail> book_details) {
        this.book_details = book_details;
    }

}
