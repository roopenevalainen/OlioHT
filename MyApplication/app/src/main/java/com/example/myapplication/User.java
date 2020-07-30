package com.example.myapplication;

import java.io.Serializable;

public class User implements Serializable { // this one is also in progress but in future would fetch users data from same database than swing data
    String username;// but differend document. Is unnecessary for applications working so far but would also be nice extra touch for future development
    String password;

    public void setUsername(String s) {
        username = s;

    }

    public void setPassword(String n) {
        password = n;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

















