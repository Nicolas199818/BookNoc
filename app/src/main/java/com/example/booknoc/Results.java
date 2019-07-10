package com.example.booknoc;

import java.util.ArrayList;
import java.util.List;

public class Results {
    private String list_name;
    private String display_name;
    private String bestsellers_date;
    private String published_date;
    private int rank;
    private int rank_last_week;
    private int weeks_on_list;
    private int asterisk;
    private int dagger;
    private String amazon_product_url;
    private List<Isbns> isbns = new ArrayList<>();
    private List<BookDetail> book_details;
    private List<Review> reviews;

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getBestsellers_date() {
        return bestsellers_date;
    }

    public void setBestsellers_date(String bestsellers_date) {
        this.bestsellers_date = bestsellers_date;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank_last_week() {
        return rank_last_week;
    }

    public void setRank_last_week(int rank_last_week) {
        this.rank_last_week = rank_last_week;
    }

    public int getWeeks_on_list() {
        return weeks_on_list;
    }

    public void setWeeks_on_list(int weeks_on_list) {
        this.weeks_on_list = weeks_on_list;
    }

    public int getAsterisk() {
        return asterisk;
    }

    public void setAsterisk(int asterisk) {
        this.asterisk = asterisk;
    }

    public int getDagger() {
        return dagger;
    }

    public void setDagger(int dagger) {
        this.dagger = dagger;
    }

    public String getAmazon_product_url() {
        return amazon_product_url;
    }

    public void setAmazon_product_url(String amazon_product_url) {
        this.amazon_product_url = amazon_product_url;
    }

    public List<Isbns> getIsbns() {
        return isbns;
    }

    public void setIsbns(List<Isbns> isbns) {
        this.isbns = isbns;
    }

    public List<BookDetail> getBook_details() {
        return book_details;
    }

    public void setBook_details(List<BookDetail> book_details) {
        this.book_details = book_details;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
