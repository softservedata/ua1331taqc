package com.softserve.edu.pr_task13_14;

public class SignInPositiveData {
    private String email;
    private String password;

    public SignInPositiveData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}