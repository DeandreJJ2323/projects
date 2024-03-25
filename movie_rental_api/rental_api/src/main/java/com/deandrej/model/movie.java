package com.deandrej.model;
/*
 * The movie class represents the movie object that will be used throught the project
 */
public class movie {

    private int movie_id;

    private String title;

    private String genre;

    private int price;

    public movie()
    {

    }

    public movie(int id, String tit, String gen, int pri)
    {
        this.title = tit;
        this.genre = gen;
        this.price = pri;
        this.movie_id = id;
    }
    
    public movie(String tit, String gen, int pri)
    {
        
        this.title = tit;
        this.genre = gen;
        this.price = pri;
    }

    public int get_movie_id()
    {
        return movie_id;
    }


    public String get_title()
    {
        return title;
    }

    public String get_genre()
    {
        return genre;
    }

    public int get_price()
    {
        return price;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
