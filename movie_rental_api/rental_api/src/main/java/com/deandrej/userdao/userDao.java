package com.deandrej.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.deandrej.dbconnnection.mysqlconnection;
import com.deandrej.model.User;
import com.deandrej.model.movie;

public class userDao {

    //Method that Registers a user to the database.
     public User registUser(User newUser)
    {

        Connection conn = mysqlconnection.getConnection();

        try {

            String sql = "INSERT INTO user(name, email, phone, password, card_num) VALUES(?, ?, ?, ?, ?);"; 

            PreparedStatement pStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pStatement.setString(1, newUser.getName());
            pStatement.setString(2, newUser.getEmail());
            pStatement.setString(3, newUser.getPhone());
            pStatement.setString(4, newUser.getPassword());
            pStatement.setInt(5, newUser.getCard_number());

            pStatement.executeUpdate();
            conn.commit();
            ResultSet pkeyresultSet = pStatement.getGeneratedKeys();

            while(pkeyresultSet.next())
            {
                int generated_id = pkeyresultSet.getInt(1);

                return new User(generated_id, newUser.getName(), newUser.getEmail(), newUser.getPhone(), newUser.getPassword(), newUser.getCard_number());

            }

            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;
    }


    //Method that verifies if a user is in the database.
    public User verifyUser(User account)
    {

        Connection con = mysqlconnection.getConnection();

        try {

            String sql = "select * from user where email = ? and password = ?;";

            PreparedStatement pstatemnt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstatemnt.setString(1, account.getEmail());
            pstatemnt.setString(2, account.getPassword());

            
            ResultSet resultSet = pstatemnt.executeQuery();

            while (resultSet.next()) {

                return new User(resultSet.getInt("user_id"), resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("phone"), resultSet.getString("password"), resultSet.getInt("card_num"));
                
            }

            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;
    }


    //Method that checks if email exists.
    public boolean doesEmailExist(User account)
    {
        Connection con = mysqlconnection.getConnection();
        boolean doesexist = false;

        try {

            String sql = "select * from user where email = ?;";

            PreparedStatement pStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pStatement.setString(1, account.getEmail());

           doesexist = pStatement.execute();

           return doesexist;
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return doesexist;
    }

    //Method that checks if email exists.
    public boolean doesEmailExist(String email)
    {
        Connection con = mysqlconnection.getConnection();
        boolean doesexist = false;

        try {

            String sql = "select * from user where email = ?;";

            PreparedStatement pStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pStatement.setString(1, email);

           doesexist = pStatement.execute();

           return doesexist;
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return doesexist;
    }


    //Mehod that returns a movie to the user.
    public movie selecteMovieByName(String movie_name)
    {
        
        Connection con = mysqlconnection.getConnection();

        try {

            String sql = "select * from movie where title = ?;";

            PreparedStatement pstatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstatement.setString(1, movie_name);

            ResultSet resultSet = pstatement.executeQuery();

            while (resultSet.next()) {

                return new movie(resultSet.getInt("movie_id"), resultSet.getString("title"), resultSet.getString("genre"), resultSet.getInt("movie_price"));
                
            }
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;
    }


    //Method that checks if a users card is present.
    public boolean isCardInfoPresent(User account)
    {

        Connection con = mysqlconnection.getConnection();

        boolean is_present = false;

        try {

            String sql = "select * from user where email = ? and card_num = 0;";

            PreparedStatement pStatement = con.prepareStatement(sql);
            pStatement.setString(1, account.getEmail());

            is_present = pStatement.execute();

            return is_present;
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return is_present;

    }


    //Method that returns a list of all users.
    public List<User> retrieveAllUsers()
    {
        List<User> allUsers = new ArrayList<>();
        Connection con = mysqlconnection.getConnection();

        try {

            String sql = " select * from user;";

            PreparedStatement pstatement = con.prepareStatement(sql);

            ResultSet rset = pstatement.executeQuery();

            while(rset.next())
            {
                allUsers.add(new User(rset.getInt("user_id"), rset.getString("name"), rset.getString("email"), rset.getString("phone"), rset.getString("password"), rset.getInt("card_num")));
    
            }
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return allUsers;
    }


    //Method that deletes a user from the database.
    public void deleteUser(User user)
    {
        Connection con = mysqlconnection.getConnection();

        try {

            String sql = "delete from user where email = ?;";

            PreparedStatement pStatement = con.prepareStatement(sql);

            pStatement.setString(1, user.getEmail());

            pStatement.executeUpdate();

            con.commit();
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    //Method that gets a users id.
    public int getUserId(User user)
    {
        Connection con = mysqlconnection.getConnection();

        int id = -1;

        try {

            String sql = "select * from user where email = ?;";

            PreparedStatement pStatement = con.prepareStatement(sql);

             pStatement.setString(1, user.getEmail());

             ResultSet rset = pStatement.executeQuery();

             while (rset.next()) {
                //User member = new User(rset.getInt("user_id"), rset.getString("name"), rset.getString("email"), rset.getString("phone"), rset.getString("password"), rset.getInt("card_num"));
                id = rset.getInt("user_id");
             }

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return id;
    }

    //Method that returns a users account info.
    public User getUser(User Account)
    {
        Connection con = mysqlconnection.getConnection();

        try {

            String sql = " select * from user where user_id = ?;";

            PreparedStatement pStatement = con.prepareStatement(sql);

            pStatement.setInt(1, getUserId(Account));

            ResultSet rset = pStatement.executeQuery();

            while(rset.next())
            {
                return new User(rset.getString("name"), rset.getString("email"), rset.getString("phone"), rset.getString("password"));
            }
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;
    }

    public User getUserwvard(User Account)
    {
        Connection con = mysqlconnection.getConnection();

        try {

            String sql = " select * from user where user_id = ?;";

            PreparedStatement pStatement = con.prepareStatement(sql);

            pStatement.setInt(1, getUserId(Account));

            ResultSet rset = pStatement.executeQuery();

            while(rset.next())
            {
                return new User(rset.getString("name"), rset.getString("email"), rset.getString("phone"), rset.getString("password"), rset.getInt("card_num"));
            }
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;
    }

    //Method that updates a users email.
    public void updateEmail(User user, String email)
    {
        Connection con = mysqlconnection.getConnection();
        
        try {

            String sql = "update user set email = ? where user_id = ?;";

            PreparedStatement pStatement = con.prepareStatement(sql);

            pStatement.setString(1, email);
            pStatement.setInt(2, getUserId(user));

            pStatement.executeUpdate();

            con.commit();
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    //Method that updates a users phone number.
    public void updatePhone(User user, String phone)
    {
        Connection con = mysqlconnection.getConnection();
        
        try {

            String sql = "update user set phone = ? where user_id = ?;";

            PreparedStatement pStatement = con.prepareStatement(sql);

            pStatement.setString(1, phone);
            pStatement.setInt(2, getUserId(user));

            pStatement.executeUpdate();

            con.commit();
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    //Method that updates a users password.
    public void updatePass(User user, String pass)
    {
        Connection con = mysqlconnection.getConnection();
        
        try {

            String sql = "update user set password = ? where user_id = ?;";

            PreparedStatement pStatement = con.prepareStatement(sql);

            pStatement.setString(1, pass);
            pStatement.setInt(2, getUserId(user));

            pStatement.executeUpdate();

            con.commit();
            
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}

