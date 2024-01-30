package controllers;

@FunctionalInterface
public interface LoginSuccessListener {
    void onLoginSuccess(String loggedInUsername);
}