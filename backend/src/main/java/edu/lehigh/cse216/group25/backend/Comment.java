package edu.lehigh.cse216.group25.backend;

/*
 * Create a class Comment that can be inserted as a RowData into the ArrayList
 * Each instance of this object Comment will be inserted for each message
 */


public class Comment {

    String cMessage;

    String user;

    int cId;

    public Comment(String body, String u_id, int comment_id) {
        cMessage = body;

        user = u_id;
        
        cId = comment_id;
    }
    
}
