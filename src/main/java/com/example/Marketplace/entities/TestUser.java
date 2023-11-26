package com.example.Marketplace.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TestUser {
    @Id
    int id;
    String username;
    String pw;

    public TestUser() {
    }

    public TestUser(int id, String username, String pw) {
        this.id = id;
        this.username = username;
        this.pw = pw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
