

package com.QuizGameLogic;

import com.Classes.QuestionData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class  TopicModel extends QuizModel implements SixQuestionQuiz{
    public TopicModel(String topic) {

        super(); //call constructor of the super class

        //loading the topic questions from the CSV and shuffling
        this.questions = loadTopic(topic);
        Collections.shuffle(this.questions);

        loadSixQuestions();
    }

    @Override
    public void loadSixQuestions() {

        //method used for getting only (the first) six questions
        questions = questions.subList(0, Math.min(6, questions.size()));
        currentIndex = 0;
    }

    private List<QuestionData> loadTopic(String topic) { //works the same as in Difficult model

        List<QuestionData> filteredQuestions = new ArrayList<>();

        //iterates over each QuestionData object in the question list (inherited from QuizModel)
        for (QuestionData question : this.questions) {
            //checks if the question topic is equal to the specified topic
            if (question.getTopic().equals(topic)) {
                //adding the question to the filtered list
                filteredQuestions.add(question);
            }
        }

        return filteredQuestions;
    }


}
