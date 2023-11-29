package com.QuizGameLogic;

import com.Classes.QuestionData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DifficultModel extends QuizModel implements SixQuestionQuiz{
    public DifficultModel(String difficulty) {

        super(); //call constructor of the superclass

        this.questions = loadDiff(difficulty); //get questions of certain difficulty and add to questions List
        Collections.shuffle(this.questions); //shuffle filtered questions.

        loadSixQuestions();

    }


    @Override
    public void loadSixQuestions() {

        //method used for getting only (the first) six questions
        questions = questions.subList(0, Math.min(6, questions.size()));
        currentIndex = 0;
    }

    private List<QuestionData> loadDiff(String difficulty) {

        List<QuestionData> filteredQuestions = new ArrayList<>();


        //iterate through every question and add it to filteredQuestions if it matches the difficulty. (easy, med, hard)
        for (QuestionData question : this.questions) {
            if (question.getDifficulty().equals(difficulty)) {
                filteredQuestions.add(question);
            }
        }

        return filteredQuestions;
    }

}
