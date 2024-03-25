package com.deandrej.controller;

import java.util.ArrayList;
import java.util.List;

import com.deandrej.model.User;
import com.deandrej.model.movie;
import com.deandrej.model.transaction;
import com.deandrej.movieservice.movieService;
import com.deandrej.transactiondao.transDao;
import com.deandrej.userservice.userService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class rentalController {


    movieService mvsv = new movieService();
    userService ussv = new userService();

    public rentalController()
    {
        this.mvsv = new movieService();
        this.ussv = new userService();
    }

    public Javalin startApi()
    {
        Javalin app = Javalin.create();

        app.post("/register", this::postuserRegistration);
        app.post("/verify", this::postuserVerify);
        app.get("/movies", this::getAllMovies);
        app.get("/movies/{movie_name}", this::getMovieByName);
        app.get("/movies/rent/{movie_name}", this::getRentMovies);
        app.get("/users/history", this::getAllUserTrans);
        app.delete("/user/remove", this::deleteUser);
        app.patch("/user/update/email/{email}", this::patchUpdateEmail);
        app.patch("/user/update/phone/{phone}", this::patchUpdatePhone);
        app.patch("/user/update/password/{pass}", this::patchUpdatePass);

        return app;
    }

    private void postuserRegistration(Context ctx) throws JsonMappingException, JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();

        User user = mapper.readValue(ctx.body(), User.class);

        User newUser = ussv.addUser(user);

        if(newUser == null)
        {
            ctx.status(400);
            ctx.result("invalid");
        }
        else
        {
            ctx.status(200);
            ctx.json(mapper.writeValueAsString(newUser));
        }
    }

    private void postuserVerify(Context ctx) throws JsonMappingException, JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();

        User user = mapper.readValue(ctx.body(), User.class);

        User login = ussv.verifyUser(user);

        if(login == null)
        {
            ctx.status(400);
            ctx.result("invalid");
            
        }
        else
        {
            ctx.status(200);
            ctx.json(mapper.writeValueAsString(login));
        }

    }

    private void getAllMovies(Context ctx) throws JsonMappingException, JsonProcessingException
    {
        List<movie> mvlist = new ArrayList<>();

        mvlist = mvsv.getAllMovies();

        ctx.json(mvlist);
    }

    private void getAllUserTrans(Context ctx) throws JsonMappingException, JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();

        User user = mapper.readValue(ctx.body(), User.class);

        List<transaction> history = new ArrayList<>();

        history = ussv.getAllUserTrans(user);

        ctx.json(history);
    }

    private void getMovieByName(Context ctx) throws JsonProcessingException
    {
        String mv = ctx.pathParam("movie_name");

        movie mov = ussv.getMovieByName(mv);

        if(mov == null)
        {
            ctx.status(400);
            ctx.result("invalid");
        }
        else
        {
            ctx.status(200);
            ctx.json(mov);
        }
    }

    private void getRentMovies(Context ctx) throws JsonMappingException, JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();

        User user = mapper.readValue(ctx.body(), User.class);

        User use = ussv.getUser(user);

        String movie = ctx.pathParam("movie_name");

        movie rented_movie = ussv.rentMovie(use, movie);

        if(use.getCard_number() == 0)
        {
            ctx.status(400);
            ctx.result("no card");
        }
        if(rented_movie == null)
        {
            ctx.status(400);
            ctx.result("Invalid");
        }
        else
        {
            ctx.status(200);
            ctx.result("enjoy your movie!");
        }
    }
    

    private void deleteUser(Context ctx) throws JsonMappingException, JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();

        User deletedUser = mapper.readValue(ctx.body(), User.class);

        if(ussv.deleteUser(deletedUser) == null)
        {
            ctx.status(400);
        }
        else
        {
            ctx.status(200);
            ctx.json(deletedUser);
        }
    }

    private void patchUpdateEmail(Context ctx) throws JsonMappingException, JsonProcessingException 
    {
        ObjectMapper mapper = new ObjectMapper();

        User updteEmail = mapper.readValue(ctx.body(), User.class);

        User use = ussv.getUser(updteEmail);

        String email = ctx.pathParam("email");

        User updated = ussv.updateEmail(use, email);

        if(updated == null)
        {
            ctx.status(200);
            
        }
        else
        {
            ctx.status(400);
            ctx.result("invalid");
            
        }

        
    }

    private void patchUpdatePhone(Context ctx) throws JsonMappingException, JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();

        User updtePhone = mapper.readValue(ctx.body(), User.class);

        User use = ussv.getUser(updtePhone);

        String phone = ctx.pathParam("phone");

        User updated = ussv.updatePhone(use, phone);

        if(updated == null)
        {
            ctx.status(400);
            
        }
        else
        {
            ctx.status(200);
            
            
        }
    }

    private void patchUpdatePass(Context ctx) throws JsonMappingException, JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();

        User updtePass = mapper.readValue(ctx.body(), User.class);

        User use = ussv.getUser(updtePass);

        String pass = ctx.pathParam("pass");

        User updated = ussv.updatePass(use, pass);

        if(updated == null)
        {
            ctx.status(400);
            
        }
        else
        {
            ctx.status(200);
            
        }
    }
}
