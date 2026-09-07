/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project;

/**
 *
 * @author Brooklyn
 */
public class Node {
    
    String username;
    int score;
    long time;
    Node next;

    Node(String u,int s,long t, Node n) {
        username = u;
        score = s;
        time = t;
        next = n;
    }

    Node(String u,int s,long t) {
        username = u;
        score = s;
        time = t;
        next = null;
    }
}
