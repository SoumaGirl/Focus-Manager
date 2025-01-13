package com.focus.Model;

public class User {
    private int id;
    private String email;
    private String password;

    // Constructor
    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    // Constructor without ID (for signup)
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Override toString() if needed (for debugging or logging)
    @Override
    public String toString() {
        return "User{id=" + id + ", email='" + email + "', password='" + password + "'}";
    }
}
