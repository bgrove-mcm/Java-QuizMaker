/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkgfinal.project;
import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
/**
 *
 * @author Brooklyn
 */
public class FinalProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
            //login method - hashmap from userinfo
            //differentiate from student and instructor
            
            //if student - hashset quiz random generator
            // disply resuls and T/F format
            //have elapsed time
            //disply output file
//            
//            userName_COSC_3053_Quiz_Date_Time
//            Where:
//            userName = actual student username
//            Date_Time = date and time at the start of the quiz
//            
            //if instructor, give 1,2,3 option
                //1 colect and the APPEND to userinfo
                //2 display quiz stats based on quizes taken
                //3 add question and APPEND to TestBank
                
            
//enter done in username to terminate program
        Scanner keyboard = new Scanner(System.in);
        
        Instructor instructor = new Instructor();
        Login loginData = new Login();
        HashMap<String, String> userData = loginData.login();
                  
        while (userData != null){      
            //STUDENT
            String role = userData.get("role");
            if (role.equalsIgnoreCase("student")){
                HashMap<String,Object> quizData = Student.runQuiz(userData);
                String username = userData.get("username");
                int score = (int) quizData.get("score");
                long time = (long) quizData.get("time");
                instructor.addQuizReport(username,score,time);
                Student.Report(quizData);
            }
            else if(role.equalsIgnoreCase("Instructor")){
                //Instructor instructor = new Instructor();
                instructor.menuDisplay();
            }
            else{
                System.out.println("Role could not be configured");
                System.out.println("Please check UsersInf.txt");
                
            }
            loginData = new Login();
            userData = loginData.login();
        }
    }
    
}
