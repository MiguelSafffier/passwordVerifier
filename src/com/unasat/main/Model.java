package com.unasat.main;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Model {
    private HashMap<String, String> userInfo = new HashMap<>();
    private String password;
    private String userName;
    private String passwordSignIn;
    private String userNameSignIn;
    private LocalDateTime timePasswordReset;
    private LocalDateTime timePasswordSet;

    public HashMap<String, String> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(HashMap<String, String> userInfo) {
        this.userInfo = userInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordSignIn() {
        return passwordSignIn;
    }

    public void setPasswordSignIn(String passwordSignIn) {
        this.passwordSignIn = passwordSignIn;
    }

    public String getUserNameSignIn() {
        return userNameSignIn;
    }

    public void setUserNameSignIn(String userNameSignIn) {
        this.userNameSignIn = userNameSignIn;
    }

    public LocalDateTime getTimePasswordReset() {
        return timePasswordReset;
    }

    public void setTimePasswordReset(LocalDateTime timePasswordReset) {
        this.timePasswordReset = timePasswordReset;
    }

    public LocalDateTime getTimePasswordSet() {
        return timePasswordSet;
    }

    public void setTimePasswordSet(LocalDateTime timePasswordSet) {
        this.timePasswordSet = timePasswordSet;
    }

    public static boolean hoofdLetterCheck(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                count++;
            }
        }
        if (count >= 3) {
            return true;
        }
        return false;
    }

}
