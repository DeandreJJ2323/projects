package com.deandrej.transactiondao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deandrej.dbconnnection.mysqlconnection;
import com.deandrej.model.User;
import com.deandrej.model.movie;
import com.deandrej.model.transaction;
import com.deandrej.userdao.userDao;


public class transDao {

    userDao uDao = new userDao();

    //Method that adds a users Transactions to the trans_history table in the database.
    public movie addTrans(User Account, movie movie)
    {
        
        Connection con = mysqlconnection.getConnection();
        int id = uDao.getUserId(Account);

        try {
             String sql = "INSERT INTO trans_his(movie_id, use_id, date) VALUES(?, ?, CURDATE());";

             PreparedStatement psatement = con.prepareStatement(sql);

             psatement.setInt(1, movie.get_movie_id());
             psatement.setInt(2, id);

             psatement.executeUpdate();

             con.commit();

             return movie;
             
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;
    }


    //Methods that returns all of a users transactions
    public List<transaction> allUserTransaction(int userAccount)
    {
        List<transaction> arr = new ArrayList<>();

        Connection con = mysqlconnection.getConnection();

        try {
            
            String sql = "select movie.title, user.name, trans_his.date from trans_his inner join user on trans_his.use_id = user.user_id inner join movie on trans_his.movie_id = movie.movie_id where use_id = ?;";

            PreparedStatement pstatement = con.prepareStatement(sql);

            pstatement.setInt(1, userAccount);

            ResultSet rset = pstatement.executeQuery();

            while(rset.next())
            {
                arr.add(new transaction(rset.getString("title"), rset.getString("name"), rset.getDate("date")));
            }


        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return arr;
    }
    

}
