package com.deandrej.userservice;

import java.util.List;

import com.deandrej.model.User;
import com.deandrej.model.movie;
import com.deandrej.model.transaction;
import com.deandrej.moviedao.movieDao;
import com.deandrej.transactiondao.transDao;
import com.deandrej.userdao.userDao;


public class userService {

    private userDao dao;
    private movieDao movidao;
    private transDao tdao;

    public userService()
    {
        this.dao = new userDao();
        this.movidao = new movieDao();
        this.tdao = new transDao();
    }

    public userService(userDao doa, movieDao mdao, transDao trdao)
    {
        this.dao = doa;
        this.movidao = mdao;
        this.tdao = trdao;
    }

    public User addUser(User user)
    {
        if(dao.doesEmailExist(user) == false)
        {
            System.out.println(user.getEmail() + " already exists");
            return null;
        }
        else if( user.getPassword().length() < 8)
        {
            System.out.println(user.getPassword() + "is invalid. ");
            return null;
        }
        else if(user.getPassword().isEmpty())
        {
            System.out.println("Password is empty");
            return null;
        }
        else
        {
            user = dao.registUser(user);
        }
        return user;
    }

    public User verifyUser(User Account)
    {
        if(dao.verifyUser(Account) == null)
        {
            return null;
        }

        return dao.verifyUser(Account);
    }

    public movie rentMovie(User user, String movie)
    {
        movie mv = new movie();
        mv = dao.selecteMovieByName(movie);

        if(mv == null)
        {
            System.out.println("No result of " + movie);
            return null;
        }
       
        if(user.getCard_number() == 0)
        {
            System.out.println("Add credit card to account ");
            return null;   
        }

        tdao.addTrans(user, mv);

        return mv;

    }
    
    public User deleteUser(User usr)
    {
        if(dao.verifyUser(usr) == null)
        {
            return null;
        }
        else
        {
            dao.deleteUser(usr);
            return usr;
        }

        
    }

    public int get_id(User user)
    {
       return dao.getUserId(user);
    }

    public List<transaction> getAllUserTrans(User user)
    {
        return tdao.allUserTransaction(get_id(user));
    }

    public movie getMovieByName(String movie)
    {
        return dao.selecteMovieByName(movie);
    }

    public User updateEmail(User user, String email)
    {
        if(dao.doesEmailExist(email) == false || email.length() < 3 || email.isEmpty())
        {
            System.out.println("Email invalid");
            return null;
        }
        else
        {
            dao.updateEmail(user, email);
            User updated_email = dao.getUser(user);
            return updated_email;
        }
    }

    public User updatePhone(User user, String phone)
    {
        if(phone.length() < 12 || phone.isEmpty())
        {
            return null;
        }
        else
        {
            dao.updatePhone(user, phone);
            User updated_phone = dao.getUser(user);
            return updated_phone;
        }
    }

    public User updatePass(User user, String pass)
    {
        if(pass.length() < 8 || pass.isEmpty())
        {
            return null;
        }
        else
        {
            dao.updatePass(user, pass);
            User updated_pass = dao.getUser(user);
            return updated_pass;
        }
    }

    public User getUser(User user)
    {
        return dao.getUserwvard(user);
    }

}
