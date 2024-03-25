package com.deandrej.model;

import java.sql.Date;

/*
 * The Transaction class represents the Transaction object that will be used throught the project
 */
public class transaction {

    private int trans_id;

    private int movie_id;

    private int user_id;

    private Date date;

    private String title;

    private String customer;


    public transaction()
    {

    }

    public transaction(int mov, int use, Date dat)
    {
        this.date = dat;
        this.movie_id = mov;
        this.user_id = use;
    }

    public transaction(String title, String name, Date date)
    {
        this.title = title;
        this.customer = name;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public int getTrans_id() {
        return trans_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getCustomer() {
        return customer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
