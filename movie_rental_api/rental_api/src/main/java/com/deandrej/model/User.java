package com.deandrej.model;

/*
 * The User class represents the user object that will be used throught the project
 */

public class User {

    private int user_id;

    private String name;

    private String email;

    private String phone;
    
    private String password;

    private int card_number;

    public User()
    {

    }

    public User(int use_id, String nam, String ema, String pho, String pass, int c_num)
    {
        this.user_id = use_id;
        this.name = nam;
        this.email = ema;
        this.phone = pho;
        this.password = pass;
        this.card_number = c_num;
    }

    public User(String nam, String ema, String pho, String pass, int c_num)
    {
        
        this.name = nam;
        this.email = ema;
        this.phone = pho;
        this.password = pass;
        this.card_number = c_num;
    }

    public User(String nam, String ema, String pho, String pass)
    {
        
        this.name = nam;
        this.email = ema;
        this.phone = pho;
        this.password = pass;
        
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getCard_number() {
        return card_number;
    }

    public void setCard_number(int card_number) {
        this.card_number = card_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


}
