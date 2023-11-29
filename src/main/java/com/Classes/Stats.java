package com.Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stats {
    /*
        This Class contains the methods for calculating the means, medians and SD's.

     */

    private List<UserScores> scores;
    public Stats(String filepath){

        scores = loadCSVScores(filepath);
    }


    //loads all the csv scores from csv file and adds them to a List
    public List<UserScores> loadCSVScores(String filepath) {

        List<UserScores> userScoreList = new ArrayList<>();
        try{

            BufferedReader reader = new BufferedReader(new FileReader(filepath));

            String line;
            while((line = reader.readLine()) != null) {

                List<Double> yourScores = new ArrayList<>();
                List<String> parts = List.of(line.split(","));
                String username = parts.get(0).trim();
                double userTotal = 0;
                for (int i = 1; i < parts.size(); i++){

                    String trimmedScoreStr = parts.get(i).trim();
                    Double trimmedScore = Double.parseDouble(trimmedScoreStr);
                    yourScores.add(trimmedScore);
                    userTotal = userTotal + trimmedScore;



                }


                UserScores userScores = new UserScores(username, yourScores, userTotal);
                userScoreList.add(userScores);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return userScoreList;
    }

    public List<Double> getAllUserScoreList() {
        List<Double> innerScores = new ArrayList<>();
        for (UserScores userScore : scores) {
            for (double score : userScore.getUserScores()) {
                innerScores.add(score);
            }
        }
        return innerScores;
    }


    public double calcUserMean() {
        List<Double> userScoreList = getAllUserScoreList();


        List<Double> innerScores = new ArrayList<>();

        for (UserScores userScore : scores) {

            for (double score : userScore.getUserScores()) {

                innerScores.add(score);

            }

        }



        double sumOfScores = 0.0;

        for (double score : innerScores) {

            sumOfScores += score;

        }



        int numberScores = innerScores.size();

        double meanAverage = sumOfScores / numberScores;


        DecimalFormat df = new DecimalFormat("0.0");
        double roundedValue = Double.parseDouble(df.format(meanAverage));
        return roundedValue;
    }


    public double calcUserMedian() {

        List<Double> userScoreList = getAllUserScoreList();



        userScoreList.sort(Double::compareTo);



        int size = userScoreList.size();



        // If the number of scores is odd, return the middle value

        if (size % 2 != 0) {

            DecimalFormat df = new DecimalFormat("0.0");
            double roundedValue = Double.parseDouble(df.format(userScoreList.get(size / 2)));
            return roundedValue;


        } else {

            // If the number of scores is even, calculate the average of the two middle values

            int middle = size / 2;

            double median = (userScoreList.get(middle - 1) + userScoreList.get(middle)) / 2.0;

            DecimalFormat df = new DecimalFormat("0.0");
            double roundedValue = Double.parseDouble(df.format(median));
            return roundedValue;


        }



    }



    public double calcUserSD() {

        List<Double> userScoreList = getAllUserScoreList();



        double mean = calcUserMean(); // Calculate the mean first



        // Calculate the sum of squared differences

        double sumOfSquaredDifferences = 0.0;

        for (double score : userScoreList) {

            double difference = score - mean;

            sumOfSquaredDifferences += difference * difference;

        }



        // Calculate the variance (average of squared differences)

        double variance = sumOfSquaredDifferences / userScoreList.size();



        // Calculate the standard deviation (square root of variance)

        double standardDeviation = Math.sqrt(variance);

        DecimalFormat df = new DecimalFormat("0.0");
        double roundedValue = Double.parseDouble(df.format(standardDeviation));
        return roundedValue;



    }






    public List<Double> findYourScore(String yourUsername, String filePath){
        List<Double> yourScores = new ArrayList<>();
        try{

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while((line = reader.readLine()) != null) {

                List<String> parts = List.of(line.split(","));
                String username = parts.get(0).trim();
                if(username.equals(yourUsername)) {
                    for (int i = 1; i < parts.size(); i++){

                        String trimmedScoreStr = parts.get(i).trim();
                        Double trimmedScore = Double.parseDouble(trimmedScoreStr);
                        yourScores.add(trimmedScore);

                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return yourScores;
    }

    public List<String> getUsernameList(){
        List<String> usernameList = new ArrayList<>();

        try{
            String filePath = "src/main/resources/surv.csv";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while((line = reader.readLine()) != null) {

                List<String> parts = List.of(line.split(","));
                String username = parts.get(0).trim();
                usernameList.add(username);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return usernameList;
    }

    public List<SurvData> findHighScores(){
        List<SurvData> highScoreList = new ArrayList<>();
        List<String> usernameList = getUsernameList();
        double highScore;

        for(String username: usernameList){
            List<Double> yourHighScore = findYourScore(username, "src/main/resources/surv.csv");
            Collections.sort(yourHighScore);

            if (!yourHighScore.isEmpty()) {
                int lastIndex = yourHighScore.size() - 1;
                String lastElement = String.valueOf(yourHighScore.get(lastIndex));
                highScore = Double.parseDouble(lastElement);

                SurvData survData = new SurvData(username, highScore);
                highScoreList.add(survData);
            }
        }

        return highScoreList;
    }


    public double findYourMean(List<Double> yourScores) {

        double sum = 0.0;

        for (Double score : yourScores) {

            sum += score;

        }
        DecimalFormat df = new DecimalFormat("0.0");
        double roundedValue = Double.parseDouble(df.format(sum / yourScores.size()));
        return roundedValue;

    }



    public double findYourMedian(List<Double> yourScores) {

        List<Double> sortedScores = new ArrayList<>(yourScores);

        sortedScores.sort(Double::compareTo);



        int size = sortedScores.size();



        // If the number of scores is odd, return the middle value

        if (size % 2 != 0) {

            DecimalFormat df = new DecimalFormat("0.0");
            double roundedValue = Double.parseDouble(df.format(sortedScores.get(size / 2)));
            return roundedValue;

        } else {



            // If the number of scores is even, calculate the average of the two middle values

            int middle = size / 2;

            double median = (sortedScores.get(middle - 1) + sortedScores.get(middle)) / 2.0;

            DecimalFormat df = new DecimalFormat("0.0");
            double roundedValue = Double.parseDouble(df.format(median));
            return roundedValue;
            //TODO fix the median bug

        }

    }



    // Calculate the standard deviation for an individual user

    public double findYourSD(List<Double> yourScores) {

        double mean = findYourMean(yourScores); // Calculate the mean first



        // Calculate the sum of squared differences

        double sumOfSquaredDifferences = 0.0;

        for (Double score : yourScores) {

            double difference = score - mean;

            sumOfSquaredDifferences += difference * difference;

        }



        // Calculate the variance (average of squared differences)

        double variance = sumOfSquaredDifferences / yourScores.size();



        // Calculate the standard deviation (square root of variance)

        double standardDeviation = Math.sqrt(variance);


        DecimalFormat df = new DecimalFormat("0.0");
        double roundedValue = Double.parseDouble(df.format(standardDeviation));
        return roundedValue;

    }

}
