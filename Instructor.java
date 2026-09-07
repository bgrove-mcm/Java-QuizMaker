/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project;

/**
 *
 * @author Brooklyn
 */
import java.io.*;
import java.util.*;

public class Instructor {
    QuizLinkedList quizHistory;
    
    public Instructor() {
        quizHistory = new QuizLinkedList();
        Scanner keyboard = new Scanner(System.in);
        
    }
    public void addQuizReport(String username,int score,long time){
        quizHistory.add(username,score,time);
    }
    public void menuDisplay()throws IOException{

        Scanner keyboard = new Scanner(System.in);
        String input = "";
        while (!input.equals("4")){
            System.out.println("1. Register a new student");
            System.out.println("2. Display stats");
            System.out.println("3. Add new questions");
            System.out.println("4. Terminate session");
            System.out.print("Input 1,2,3, or 4: ");

            input = keyboard.nextLine();

            if (input.equals("1")){
                registerStudent();
            }
            else if (input.equals("2")){
                displayStats();
            }
            else if (input.equals("3")){
                addQuestion();
            }
            else if (!input.equals("4")){
                System.out.print("Invalid input, please only enter 1,2, or 3.\n");
            }
        }


    }
    public void registerStudent() throws IOException{
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("First Name: ");
        String first = keyboard.nextLine();
        
        System.out.print("Last Name: ");
        String last = keyboard.nextLine();
            
        System.out.print("Username: ");
        String username = keyboard.nextLine();
            
        System.out.print("Password: ");
        String password = keyboard.nextLine();
        
        String role = "Student";
        
        //append to UsersInfo.txt
        FileWriter fwriter = new FileWriter("UsersInfo.txt", true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        outputFile.print("\n"+first+"\t"+last+"\t"+username+"\t"+password+"\t"+role);
        outputFile.close();
        System.out.println("Student registered to UsersInfo.txt");
    }
    public void displayStats() throws IOException{
        Scanner keyboard = new Scanner(System.in);
        if (quizHistory.isEmpty()){
            System.out.println("No quizzes have been completed.");
            return;
        }
        Node current = quizHistory.head;
        
        int totalQuizzes = 0;
        int totalScore = 0;
        int lowest = current.score;
        int highest = current.score;
        int passRate = 0;
        
        HashSet<String> uniqueStudents = new HashSet<String>();
        while (current != null){
            totalQuizzes++;
            totalScore+= current.score;
            
            if (current.score < lowest){
                lowest = current.score;
            }
            if (current.score > highest){
                highest = current.score;
            }
            uniqueStudents.add(current.username);
            
            double scorePercent = (current.score / 10.0) * 100;
            if (scorePercent >= 60){
                passRate++;
            }
            current = current.next;
        }
        double scoreAverage = totalScore / totalQuizzes;
        double passing = ((double)passRate/totalQuizzes) * 100;
        //display
        System.out.println("Lowest score: "+ lowest);
        System.out.println("Highest score: "+ highest);
        System.out.println("Average score: "+ scoreAverage);
        System.out.println("Total number of quizzes taken: "+ totalQuizzes);
        System.out.println("Number of unique students who have taken a quiz: "+ uniqueStudents.size());
        System.out.println("Pass rate (passing threshold = 60%): " + passing + "%");
        System.out.println();
        
        
    }
    public void addQuestion() throws IOException{
        //append to TestBank.txt and corrosponding Answers.txt
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Enter question: ");
        String question = keyboard.nextLine();
        String answer = "";
        while(answer.equals("")){
            System.out.print("Enter answer as TRUE or FALSE: ");
            String input = keyboard.nextLine().toUpperCase();
        
            if(input.equals("T") || input.equals("TRUE")){
                answer = "TRUE";
            }
            else if (input.equals("F") || input.equals("FALSE")){
                answer = "FALSE";
            }
            else {
                System.out.print("Invalid, please enter True/False or T/F: ");
            }
        }
        FileWriter fwriterQ = new FileWriter("TestBank.txt", true);
        PrintWriter outputFileQ = new PrintWriter(fwriterQ);
        outputFileQ.print("\n"+question);
        outputFileQ.close();
        FileWriter fwriterA = new FileWriter("Answers.txt", true);
        PrintWriter outputFileA = new PrintWriter(fwriterA);
        outputFileA.print("\n"+answer);
        outputFileA.close();
            
        System.out.println("Question added to TestBank.txt");
        System.out.println("Answer added to Answers.txt");
            
        }
        
    }
    

