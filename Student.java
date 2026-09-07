/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.time.*;

import java.time.format.DateTimeFormatter;



/**
 *
 * @author Brooklyn
 */
public class Student {
    //elapsed time
    // T/F, or True/False, not case sensitive
//    
//    public static HashSet<String> getCommonElements(HashSet<String> set1, HashSet<String> set2){
//        HashSet<String> common = new HashSet<String>();
//        String[] set1Array = set1.toArray(new String[0]);
//        
//        for (int i = 0; i < set1Array.length; i++){
//            if(set2.contains(set1Array[i])){
//                common.add(set1Array[i]);
//            }
//        }
//        return common;
//    }
    public static HashMap<String,String> CreateQuiz() throws IOException{
    //Look for and read file
        
        //Scanner keyboard = new Scanner(System.in);
        //TestBank.txt
        //Answers.txt
        // questions for quiz
        File fileTest = new File("TestBank.txt");
        Scanner Test = new Scanner(fileTest);
        //correct answers of quiz
        File fileAnswers = new File("Answers.txt");
        Scanner Answers = new Scanner(fileAnswers);
        
        HashMap<String,String> fullTest = new HashMap<String,String>();
        
        while(Test.hasNextLine() && Answers.hasNextLine()){
            //split and arrange text into hashmap
            String question = Test.nextLine();
            String answer = Answers.nextLine().toUpperCase(); //case sensitive
            fullTest.put(question,answer); //hashmap
        }
        //close files
        Test.close();
        Answers.close();
        return fullTest;
    }

    
    
    public static HashSet<String> GenerateQuiz(HashMap<String,String> fullTest)throws IOException{
        //calling hashmap from createquiz
        HashSet<String> questions = new HashSet<String>();
        Random rand = new Random();
        //get all questions from hashmap, convert questions to string array
        String[] questionArray = fullTest.keySet().toArray(new String[0]);
        //loop until 10 questions have been chosen, or out of questions
        while(questions.size() < 10 && questions.size() < questionArray.length){
            int i = rand.nextInt(questionArray.length);//select randomly using rand
            questions.add(questionArray[i]); //add to hashset, no duplicates
        }
        return questions; //return 10 questions
        
        }       
    public static String getAnswer(Scanner keyboard)throws IOException{
        //only accept True/False o T/F
        //scanner already created
        //Scanner keyboard = new Scanner(System.in);
        //using true but treating like boolean
        String input = "";
        while(!(input.equals("T") || input.equals("TRUE")
                || input.equals("F") || input.equals("FALSE"))){
            input = keyboard.nextLine().trim().toUpperCase();
        
            if(input.equals("T") || input.equals("TRUE")){
                return "TRUE";
            }
            if (input.equals("F") || input.equals("FALSE")){
                return "FALSE";
            }
            else {System.out.print("Invalid, please enter True/False or T/F: ");
            //return null;
            }   
        }
        return "";
    }
    public static HashMap<String, Object> runQuiz(HashMap<String, String> userData) throws IOException{
        // TODO code application logic here
        //
        Scanner keyboard = new Scanner(System.in);
        
        //Login loginData = new Login();
        //HashMap<String, String> userData = loginData.login();
        String firstName = userData.get("first");
        String lastName = userData.get("last");
        String username = userData.get("username");
        String password =  userData.get("password");
        String role = userData.get("role");
        
        //TestBank.txt
        //Answers.txt
//
//        File fileTest = new File("TestBank.txt");
//        Scanner Test = new Scanner(fileTest);
//        
//        File fileAnswers = new File("Answers.txt");
//        Scanner Answers = new Scanner(fileAnswers);
//        
        // get hashmap from createquiz method
        HashMap<String,String> fullTest = CreateQuiz();
        
        
        HashSet<String> quizQuestions = GenerateQuiz(fullTest);
        //get the randomized 10 questions from hashset, convert questions to string arra
        String [] quizArray = quizQuestions.toArray(new String[0]);
        //user answers and score
        HashMap<String,String> userAnswers = new HashMap<String,String>();
        int score = 0;
        
        //Time
        long startTime = System.currentTimeMillis();
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM.dd.yyyy_HH.mm");
        String date = LocalDateTime.now().format(format);
        //Hashset to array for looping
        System.out.println("Begin Quiz");
        
        int count = 1;
        for (int i = 0; i < quizArray.length; i++){
            String question = quizArray[i];
            System.out.println(count+ ". " + question);
            System.out.print("(T/F): ");
            
            String userAnswer = getAnswer(keyboard);
            String correctAnswer = fullTest.get(question);
            
            userAnswers.put(question, userAnswer);
            
            if (userAnswer.equals(correctAnswer)){
                score++;
            }
            count++;
            System.out.println();
        }
        //end time
        //LocalDateTime end = LocalDateTime.now();
        long endTime = System.currentTimeMillis();
        long seconds = (endTime - startTime) / 1000;
        HashMap<String, Object> quizResults = new HashMap<String, Object>();
        quizResults.put("userData",userData);
        quizResults.put("first", firstName);
        quizResults.put("last", lastName);
        quizResults.put("username", username);
        quizResults.put("score", score);
        quizResults.put("time", seconds );
        quizResults.put("date", date );
        
        quizResults.put("answers", userAnswers);
        quizResults.put("questions", quizArray);
        quizResults.put("fullTest", fullTest);
        return quizResults;
    }
    
    public static void Report(HashMap<String, Object> data)throws IOException{
        //had to look up typecasting
        //convert all hashmaps from <string,object> back to string string
        HashMap<String,String> userData = (HashMap<String,String>) data.get("userData");
        if (userData == null){
            System.out.println("Error, user data missing.");
            return;
        }
        HashMap<String,String> userAnswers = (HashMap<String,String>) data.get("answers");
        HashMap<String,String> fullTest = (HashMap<String,String>) data.get("fullTest");      
        String[] quizArray = (String[]) data.get("questions");
        
        String first = userData.get("first");
        String last = userData.get("last");
        String username = userData.get("username");
        String date = (String)data.get("date");
        
        
        long seconds = (long)data.get("time");
        int score = (int)data.get("score");
            //output file take from login.java
            //userName_COSC_3053_Quiz_Date_Time
            String fileName = username + "_COSC_3053_Quiz_"+date+".txt";
            FileWriter fwriter = new FileWriter(fileName);
            PrintWriter outputFile = new PrintWriter(fwriter);
            
            outputFile.println("Quiz Report\n");
            String studentName = first + " " + last;
            outputFile.println(studentName); //first name
//String studentScore = "Score: " + score + "/10\n";
            
            outputFile.println("Score: " + score + "/10");
            outputFile.println("Elapsed Time: " + seconds +" seconds");
            
            for (int i = 0; i < quizArray.length; i++){
                String question = quizArray[i];
                outputFile.println(question);
                outputFile.println("Your Answer: " + userAnswers.get(question));
                outputFile.println("Correct Answer: " + fullTest.get(question));
            }
            outputFile.close();
            //screen output
            System.out.println("Quiz Report\n");
            System.out.println(studentName); //first name
            System.out.println("Score: " + score + "/10");
            System.out.println("Elapsed Time: " + seconds +" seconds");
            
            for (int i = 0; i < quizArray.length; i++){
                String question = quizArray[i];
                System.out.println(question);
                System.out.println("Your Answer: " + userAnswers.get(question));
                System.out.println("Correct Answer: " + fullTest.get(question));
            }
    }
    
}
