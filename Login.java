/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project;
import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
/**
 *
 * @author Brooklyn
 */
public class Login {
    public HashMap<String, String> login() throws IOException{
       //Look for and read file
        
        Scanner keyboard = new Scanner(System.in);
        //UsersInfo.txt
        File file = new File("UsersInfo.txt");
        Scanner Users = new Scanner(file);
        //create hashmap
        HashMap<String,String> UserRole = new HashMap<String,String>(); //<String,String>
        HashMap<String,String> UserPass = new HashMap<String,String>(); //<String,String>
        HashMap<String,String> FirstLast = new HashMap<String,String>(); //<String,String>
        
        while(Users.hasNextLine()){
            //split and arrange text into hashmap
            String line = Users.nextLine().trim();
            if(!line.isEmpty()){
                String [] UserArr = line.split("\t");
                String first = UserArr[0];
                String last = UserArr[1];
                String username = UserArr[2];
                String password = UserArr[3];
                String role = UserArr[4];

                //username, role
                UserRole.put(username,role); // username , role(student, instructor)
                //username and password
                UserPass.put(username,password); // username , password
                FirstLast.put(username, first + " " + last); //username key --> first name, last name
            }
        }
        //prompt the user
        int attempts = 0; //count for user attempts
        
        
        while(attempts < 3){
            
            System.out.print("Enter the username (or type 'done'): ");
            String username = keyboard.nextLine();
            if(username.equalsIgnoreCase("done")){
                return null;
            }
            System.out.print("Enter the password: ");
            String password = keyboard.nextLine();
        
            if(UserPass.containsKey(username) && UserPass.get(username).equals(password)){
                System.out.println("Login Successful");
                String role = UserRole.get(username);
                String firstlastName = FirstLast.get(username);
                String[] fullname = firstlastName.split(" ");
            
                HashMap<String, String> userData = new HashMap<String,String>();
                userData.put("first", fullname[0]);
                userData.put("last", fullname[1]);
                userData.put("username", username);
                userData.put("password", password);
                userData.put("role", role);
                
                attempts = 3; //end loop
                return userData;
            }else {
                attempts++;
                if(attempts < 3){
                    System.out.println("Login UnSuccessful, Invalid Username or Password.");
               }else{
                    System.out.println("Maximum Attempts Reached. Please try again later.");
                }
           }
        }
        return null;
    }
}

