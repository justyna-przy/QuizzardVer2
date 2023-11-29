package com.Services;

import com.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/*
    This class handles and stores the scores
 */

public class ScoreHandler {
    private static int storedScore = 0;
    private static int storedSurvScore = 0;

    public static void setScore(int score) throws IOException {

        List<String> wordsInFirstColumn = new ArrayList<>();

        storedScore = score;

        //store the score in the csv row belonging to the user currently logged in
        FileWriter fw3 = new FileWriter("src/main/resources/stats.csv", true);

        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/stats.csv"));
        String line;


        //adds all the usernames from the stats.csv to wordsInFirstColumn
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String usernameInStorage = parts[0].trim();
                wordsInFirstColumn.add(usernameInStorage);
            }

            //checks if current username matches any username stored in wordsInFirstColumn
            //if it matches it stores the users score at the end of the line
            for (String word : wordsInFirstColumn) {
                if (word.equals(Main.getCurrentUser().getUsername())) {
                    fw3.write(storedScore + ",");
                    break;
                }
            }
        }
        fw3.close();

    }

    public static int getScore() {
        return storedScore;
    }




    /*public static void setSurvScore(int survScore) throws IOException {

        List<String> wordsInFirstColumn = new ArrayList<>();

        storedSurvScore = survScore;

        //store the score in the csv row belonging to the user currently logged in
        FileWriter fw4 = new FileWriter("src/main/resources/surv.csv", true);

        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/surv.csv"));
        String line;


        //adds all the usernames from the stats.csv to wordsInFirstColumn
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0) {
                String usernameInStorage = parts[0].trim();
                wordsInFirstColumn.add(usernameInStorage);
            }

            //checks if current username matches any username stored in wordsInFirstColumn
            //if it matches it stores the users score at the end of the line
            for (String word : wordsInFirstColumn) {
                if (word.equals(Main.getCurrentUser().getUsername())) {
                    fw4.write(storedSurvScore + ",");
                    break;
                }
            }
        }
        fw4.close();

    }
    */
    public static int getSurvScore() {
        return storedSurvScore;
    }

    public static void setSurvScore(int survScore) throws IOException {
        storedSurvScore = survScore;

        List<String> lines = new ArrayList<>();

        // Read all lines from the CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/surv.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        // Update the lines with the new score for the current user
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            if (parts.length > 0) {
                String usernameInStorage = parts[0].trim();
                if (usernameInStorage.equals(Main.getCurrentUser().getUsername())) {
                    // Append the new score to the line
                    lines.set(i, lines.get(i) + "," + storedSurvScore);
                    break;
                }
            }
        }

        // Write the updated lines back to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/surv.csv"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
