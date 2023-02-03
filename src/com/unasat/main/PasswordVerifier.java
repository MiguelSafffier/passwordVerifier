package com.unasat.main;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Scanner;

public class PasswordVerifier {


    //alle methodes die niet buiten de class wordt gebruikt mag private worden.
    //alle data members moeten ook private, incapsolation

    public HashMap<String, String> userInfo = new HashMap<>();

    public String password;
    public String userName;
    public String passwordSignIn;
    public String userNameSignIn;
    public LocalDateTime timePasswordReset;
    public LocalDateTime timePasswordSet;

    public boolean hoofdLetterCheck(String password) {
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


    public boolean kleinLetterCheck(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                count++;
            }
        }
        if (count >= 5) {
            return true;
        }
        return false;
    }

    public boolean cijferCheck(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                count++;
            }
        }
        if (count >= 2) {
            return true;
        }
        return false;
    }

    public boolean symboolCheck(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (!Character.isDigit(password.charAt(i))
                    && !Character.isLetter(password.charAt(i))
                    && !Character.isWhitespace(password.charAt(i))) {
                count++;
            }
        }
        if (count >= 2) {
            return true;
        }
        return false;
    }

    public boolean passwordUsernameCheck(String password) {
        if (password.toLowerCase().contains(userName.toLowerCase())) {
            return false;
        } else return true;
    }

    public boolean minimaal12Characters(String password) {
        if (password.length() < 12) {
            return false;
        }
            return true;
    }

    public boolean minimalePasswordAge() {
        if ((ChronoUnit.MINUTES.between(timePasswordSet, timePasswordReset)) < 1) {      //pas aan naar wens
            return false;
        }
        return true;
    }

    public boolean passwordCheck(String password) {
        if (hoofdLetterCheck(password)
                && cijferCheck(password)
                && symboolCheck(password)
                && passwordUsernameCheck(password)
                && minimaal12Characters(password)
                && kleinLetterCheck(password)) {
            return true;
        }
        return false;
    }

    public void setPassword() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Password: ");
        password = myObj.nextLine();

        if (passwordCheck(password)) {
            verifySamePassword();
        } else {
            System.out.println("Password not strong enough! \nTry again");
            setPassword();
        }
    }

    public String setUserName() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Username: ");
        userName = myObj.nextLine();
        return userName;
    }

    public String signUp() {
        setUserName();
        setPassword();
        timePasswordSet = LocalDateTime.now();
        System.out.println("Sign Up Complete.");
        return userInfo.put(userName, password);
    }

    public String verifySamePassword() {
        String password1;
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Password Again: ");
        password1 = myObj.nextLine();
        if (password.equals(password1)) {
            return password;
        } else {
            System.out.println("Try again: ");
            return verifySamePassword();
        }
    }

    public void signIn() {
        int count = 0;
        verifyUserName();
        verifyPassword();
        if (userInfo.containsKey(userNameSignIn) && passwordSignIn.equals(userInfo.get(userNameSignIn))) {
            System.out.println("sign In Succesful.");
        } else {
            System.out.println("Incorrect Username or Password!");
            count++;
            signIn();
        }

        if (count >= 3 && count < 5) {
            try {
                Thread.sleep(120000); //5 minuut wachten
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (count >= 5) {
            try {
                Thread.sleep(600000);  //10 minuut wachten
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count = 0;                  //te lui om temporary password te coderen, daarmee sign in en daarna resetten
        }
    }

    public String verifyPassword() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Password: ");
        passwordSignIn = myObj.nextLine();
        return passwordSignIn;
    }

    public String verifyUserName() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter username: ");
        userNameSignIn = myObj.nextLine();
        return userNameSignIn;
    }

    public String resetPassword() {
        int count = 0;
       verifyUserName();
       verifyPassword();
       timePasswordReset = LocalDateTime.now();
        if (userInfo.containsKey(userNameSignIn) && passwordSignIn.equals(userInfo.get(userNameSignIn)) && minimalePasswordAge()) {
            System.out.println("Insert new password: ");
           setPassword();
        } else{
            System.out.println("Incorrect Username or Password!");
            count++;
            resetPassword();
        }

        if (count >= 3 && count <= 5) {
            try {
                Thread.sleep(120000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (count > 5) {
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count = 0;
        }
        return userInfo.put(userName,password);
    }

    public static void main(String[] args) {
        PasswordVerifier a = new PasswordVerifier();
        System.out.println("Signing Up: ");
        a.signUp();
//        System.out.println("");
        System.out.println("");
        System.out.println("Sign in");
        System.out.println("");
        a.signIn();
        System.out.println("");
        System.out.println("reset");
        System.out.println("");
        a.resetPassword();
    }
}




