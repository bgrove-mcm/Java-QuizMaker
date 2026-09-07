/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgfinal.project;

/**
 *
 * @author Brooklyn
 */
public class QuizLinkedList {
    
    //Reference to the head node in the list
    Node head;
    Node last;
    //The constructor sets first and last to null:
    public QuizLinkedList() {
        head = null;
        last = null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    //traversing the list while incrementing a count for each
    //node visited:
    public int size() {
        int count = 0;
        Node p = head;
        while (p != null) {
            // There is an element at p
            count++;
            p = p.next;
        }
        return count;
    }

    public void add(String username,int score,long time) {
        //Node p = new Node(username, score, time);
        if (isEmpty()) {
            head = new Node(username, score, time);
            last = head;
        } else {
            // Add to end of existing list
            last.next = new Node(username, score, time);
            last = last.next;
        }
    }
}
