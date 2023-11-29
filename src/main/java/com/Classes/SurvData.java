package com.Classes;

public class SurvData {

    String username;
    int highScore;

    public SurvData(String username, double highScore){
        this.username = username;
        this.highScore = (int) highScore;
    }

    public String getUsername() {
        return username;
    }

    public int getHighScore() {
        return highScore;
    }
}
