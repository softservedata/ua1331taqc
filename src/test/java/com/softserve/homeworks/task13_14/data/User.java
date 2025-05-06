package com.softserve.homeworks.task13_14.data;

import java.util.Objects;

public class User {
    private String email;
    private String password;
    private String username;

    protected User() {
        email = "email@com.ua";
        password = "password";
        username = "username";
    }

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    protected User setEmail(String email) {
        this.email = email;
        return this;
    }

    protected User setPassword(String password) {
        this.password = password;
        return this;
    }

    protected User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, username);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}