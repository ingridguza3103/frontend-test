package com.example.Marketplace.controller;

import com.example.Marketplace.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    User getUser;
    User setUser;

    @BeforeEach
    public void setUp() {
        // setup the user for testing the getters
        getUser = new User();
        getUser.setUsername("test");
        getUser.setPw("test");

        // setup the user for testing the setter methods
        setUser = new User();
    }
    @Test
    void testGetUsername() {
        //assert
        assertEquals("test", getUser.getUsername(), "Get username assertion");
    }

    @Test
    void testGetPw() {
        //assert
        assertEquals("test", getUser.getPw(), "Get Pw assertion");
    }

    @Test
    void testSetUsername() {
        //set the username
        setUser.setUsername("settest");

        //assert
        assertEquals("settest", setUser.getUsername(), "set username assertion");
    }
    @Test
    void testSetPw() {
        //set the pw
        setUser.setPw("settest");
        //assert
        assertEquals("settest", setUser.getPw(), "set pw assertion");
    }


    @AfterEach
    public void tearDown(){
        getUser = null;
        setUser = null;
    }
}
