package controllers;

public class AdminLoginController { 
    public boolean cekLogin(String username, String password) { 
        return username.equals("raianzu") && password.equals("raianzu123");
    }
}
