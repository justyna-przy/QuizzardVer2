package com.Classes;

public class Score {

    private int score = 0;

    private int survScore = 0;

    public int getSurvScore() {
        return survScore;
    }

    public void incSurvScore(){
        survScore++;
    }

    public int getScore(){
        return score;
    }

    public void incScore(){
        score++;
    }
}
