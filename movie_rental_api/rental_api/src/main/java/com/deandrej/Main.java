package com.deandrej;

import com.deandrej.controller.rentalController;

import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {

        rentalController controller = new rentalController();

        Javalin app = controller.startApi();

        app.start();
        
        }
}
