package com.deandrej.dbconnnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * The mysqlconnection class implements a methid that creates and returns a instance of a connection
 * to the movie rental database
 */
public class mysqlconnection {
    static String url = "jdbc:mysql://localhost:3306/movierental";

        static String username = "root";

        static String password = "Irise0323!";


       static private Connection connection = null;


        public static Connection getConnection(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
                System.out.println("Connaetion to " + url + " is successful!");
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
}
}
