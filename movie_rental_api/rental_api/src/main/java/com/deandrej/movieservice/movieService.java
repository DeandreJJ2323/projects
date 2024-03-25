package com.deandrej.movieservice;

import java.util.List;

import com.deandrej.model.movie;
import com.deandrej.moviedao.movieDao;

/*
 * The movie service class contains the business logic that sits between the web layer (Controller class) and persistence layer (DAO).
 * the Service class performs tasks that aren't done through the web or 
 * SQL: programming tasks like checking that the input is valid, conducting additional security checks, or saving the
 * actions undertaken by the API to a logging file.
 */
public class movieService {

    private movieDao dao;

    public movieService()
    {
        this.dao = new movieDao();
    }

    public movieService(movieDao mdao)
    {
        this.dao = mdao;
    }

    public movie addMovie(movie mv)
    {
        return dao.addMovie(mv);
    }

    public void deleteMovie(movie mv)
    {
        dao.deleteMovie(mv);
    }

    public List<movie> getAllMovies()
    {
        return dao.retrieveAllMovies();
    }

}
