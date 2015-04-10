package com.example;

public class AuthenticatorImpl implements Authenticator {
    @Override
    public boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("12345");
    }
}
