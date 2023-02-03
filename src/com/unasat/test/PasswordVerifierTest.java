package com.unasat.test;

import com.unasat.main.PasswordVerifier;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class PasswordVerifyerTest {

    PasswordVerifier a = new PasswordVerifier();

    @Test
    void welEenHoofdLetter(){
        Boolean actual = a.hoofdLetterCheck("Welcome");
        assertFalse(actual);
    }

    @Test
    void welDrieHoofdLetters(){
        Boolean actual = a.hoofdLetterCheck("WELcome");
        assertTrue(actual);
    }

    @Test
    void geenHoofdLetter(){
        Boolean actual = a.hoofdLetterCheck("welcome");
        assertFalse(actual);
    }

    @Test
    void welVijfKleinLetters(){
        Boolean actual = a.kleinLetterCheck("welcome");
        assertTrue(actual);
    }

    @Test
    void geenVijfKleinLetters(){
        Boolean actual = a.kleinLetterCheck("Welll");
        assertFalse(actual);
    }

    @Test
    void welTweeCijfers(){
        Boolean actual = a.cijferCheck("welcome01");
        assertTrue(actual);
    }

    @Test
    void welEenCijfer(){
        Boolean actual = a.cijferCheck("welcome1");
        assertFalse(actual);
    }

    @Test
    void geenCijfer(){
        Boolean actual = a.cijferCheck("welcome");
        assertFalse(actual);
    }

    @Test
    void welTweeSymbool(){
        Boolean actual = a.symboolCheck("welcome$!");
        assertTrue(actual);
    }

    @Test
    void welEenSymbool(){
        Boolean actual = a.symboolCheck("welcome$");
        assertFalse(actual);
    }

    @Test
    void geenSymbool(){
        Boolean actual = a.symboolCheck("welcome");
        assertFalse(actual);
    }

    @Test
    void minimaal12CharactersFalse(){
        boolean actual = a.minimaal12Characters("Welcome");
        assertFalse(actual);
    }
    @Test
    void minimaal12CharactersTrue(){
        boolean actual = a.minimaal12Characters("Welcome123456");
        assertTrue(actual);
    }

    @Test
    void minimaalPasswordAgeTrue(){
        a.timePasswordReset = LocalDateTime.now();
        a.timePasswordSet = LocalDateTime.now().minus(3, ChronoUnit.MINUTES);
        boolean actual = a.minimalePasswordAge();
        assertTrue(actual);
    }

    @Test
    void minimaalPasswordAgeFalse(){
        a.timePasswordReset = LocalDateTime.now();
        a.timePasswordSet = LocalDateTime.now().minus(55, ChronoUnit.SECONDS);
        boolean actual = a.minimalePasswordAge();
        assertFalse(actual);
    }

    @Test
    void userNameNietDeelVanPassword(){
        a.userName = "Mark";
        boolean actual = a.passwordUsernameCheck("werkarkschool");
        assertTrue(actual);
    }

    @Test
    void userNameWelDeelVanPassword(){
        a.userName = "Mark";
        boolean actual = a.passwordUsernameCheck("werkmarkschool");
        assertFalse(actual);
    }

    @Test
    void passwordChecker(){
        a.userName = "Mark";
        boolean actual = a.passwordCheck("WELecome123456&!");
        assertTrue(actual);
    }
}