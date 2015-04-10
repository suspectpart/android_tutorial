package com.example;

public class AlwaysAuthenticated implements Authenticator {

    @Override
    public boolean authenticate(String username, String password) {
        return true;
    }
}