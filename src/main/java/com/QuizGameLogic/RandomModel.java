package com.QuizGameLogic;

import java.util.Collections;

public class RandomModel extends QuizModel implements SixQuestionQuiz{
    public RandomModel(){
        super();
        loadSixQuestions();
    }

    @Override
    public void loadSixQuestions() {

        //loading all the questions from the CSV and then shuffling them so that the order is random
        questions = loadFromCSV();
        Collections.shuffle(questions);

        //making a new list containing only (the first) six questions
        questions = questions.subList(0, Math.min(6, questions.size()));

        //reset the current index
        currentIndex = 0;
    }

}
