package com.deandrej.moviedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.deandrej.dbconnnection.mysqlconnection;
import com.deandrej.model.movie;

public class movieDao {

    //Method that adds a movie to the database
    public movie addMovie(movie new_movie)
    {
        Connection con = mysqlconnection.getConnection();

        try {

            String sql = "INSERT INTO movie(title, genre, movie_price) VALUES(?, ?, ?);";

            PreparedStatement pStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pStatement.setString(1, new_movie.get_title());
            pStatement.setString(2, new_movie.get_genre());
            pStatement.setInt(3, new_movie.get_price());

            pStatement.executeUpdate();

            con.commit();

            return new_movie;
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;
    }

    // Method that deletes a movie from the database
    public void deleteMovie(movie deletedMovie)
    {
        Connection con = mysqlconnection.getConnection();

        try {

            String sql = " DELETE FROM movie WHERE title=?;";

            PreparedStatement pstatement = con.prepareStatement(sql);

            pstatement.setString(1, deletedMovie.get_title());

            pstatement.executeUpdate();

            con.commit();
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    // Method that retrieves all movies within the movie table in the database
    public List<movie> retrieveAllMovies()
    {
        List<movie> movieList = new ArrayList<>();

        Connection con = mysqlconnection.getConnection();

        try {

            String sql = "select * from movie;";

            PreparedStatement pstatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rset = pstatement.executeQuery();

            while(rset.next())
            {
                movieList.add(new movie(rset.getString("title"), rset.getString("genre"), rset.getInt("movie_price")));
            }
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return movieList;

    }


}
