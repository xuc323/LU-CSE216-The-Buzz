package edu.lehigh.cse216.group25.backend;

/**
 * SimpleRequest provides a format for clients to present title and message
 * strings to the server.
 * 
 * NB: since this will be created from JSON, all fields must be public, and we
 * do not need a constructor.
 */
public class SimpleRequest {
    /**
     * The title being provided by the client.
     */
    public String mTitle;

    /**
     * The message being provided by the client.
     */
    public String mMessage;

    /**
     * The indicator of likes. If not 0, then increment like count in database.
     */
    public int mLikes;

    /**
     * The indicator of dislikes. If not 0, then increment dislike count in
     * database.
     */
    public int mDislikes;
}