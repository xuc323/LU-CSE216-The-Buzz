package edu.lehigh.cse216.group25.backend;

import java.util.ArrayList;
import java.util.List;
//import edu.lehigh.cse216.group25.backend.Database.Comment; 


/*
 * Create a class CommentList that creates a list of comments from the Class
 * Comment above. 
 * 
 * Will eventually set all "Comment" objects and then arrange
 * them into lists. 
 * 
 * All comments associated with a particular message id will
 * be stored in CommentList 
 * 
 * The GET "/messages" route will return all the
 * messages, user id, picture_url, comments, comment_ids, user_ids for comments.
 */
 
public class CommentList {

    List<Comment> comments = new ArrayList<>();

    public void setComment(Comment insert) {
        
        comments.add(insert);
    }
}


