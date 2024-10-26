package com.ooad.online_quiz_system.model;

import com.ooad.online_quiz_system.Observer;

public class User implements Observer {
    private String username;

    public User(String username){this.username = username;}

    public void update(){
        System.out.println("User "+username+" has been notified that the quiz was submitted");
    }
}
