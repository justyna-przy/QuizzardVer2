package com.Classes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class QuizModel {

    private List<QuestionData> questions;
    private int currentIndex;

    public QuizModel() {
        // Load questions from the CSV file or any other data source.
        questions = loadFromCSV();
        Collections.shuffle(questions); // Shuffle questions.
        currentIndex = 0;
    }

    public QuestionData getNextQuestion() {
        if (currentIndex < questions.size()) {
            return questions.get(currentIndex++);
        }
        return null; // No more questions.
    }


    private List<QuestionData> loadFromCSV(){
        List<QuestionData> questionList = new ArrayList<>();
        try {
            String filePath = "src/main/resources/q.csv";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String question = parts[0].trim();
                    String ans1 = parts[1].trim();
                    String ans2 = parts[2].trim();
                    String ans3 = parts[3].trim();
                    String ans4 = parts[4].trim();

                    List<String> options = new ArrayList<>();
                    options.add(ans1);
                    options.add(ans2);
                    options.add(ans3);
                    options.add(ans4);

                    // Shuffle the answer options.
                    Collections.shuffle(options);

                    // Create a QuestionData object and add it to the list.
                    QuestionData questionData = new QuestionData(question, options, ans1);
                    questionList.add(questionData);
                }
            }
            reader.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return questionList;
    }

}