package com.production.hometech.jsonparsingdemo.model;

import java.util.List;

/**
 * Created by Arpit on 21-May-17.
 */

public class AndroidHive {


    /**
     * title : Dawn of the Planet of the Apes
     * image : http://api.androidhive.info/json/movies/1.jpg
     * rating : 8.3
     * releaseYear : 2014
     * genre : ["Action","Drama","Sci-Fi"]
     */

    private String title;
    private String image;
    private double rating;
    private int releaseYear;
    private List<String> genre;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }
}
