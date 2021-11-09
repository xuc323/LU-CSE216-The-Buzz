package edu.lehigh.cse216.group25.backend;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Database {
    /**
     * The connection to the database. When there is no connection, it should be
     * null. Otherwise, there is a valid open connection
     */
    private Connection mConnection;


    //PREPARED STATEMENTS FOR THE MESSAGES TABLE
    private PreparedStatement mSelectAll;
    private PreparedStatement mSelectOne;
    private PreparedStatement mDeleteOne;
    private PreparedStatement mInsertOne;
    private PreparedStatement mUpdateOne;
    private PreparedStatement mUpdateOneLikes;
    private PreparedStatement mUpdateOneDislikes;
    private PreparedStatement mCreateTable;
    private PreparedStatement mDropTable;

    //PREPARED STATEMENTS FOR THE USER TABLE
    private PreparedStatement oCreateTable; 
    private PreparedStatement oDropTable; 
    private PreparedStatement oSelectAll;
    private PreparedStatement oSelectOne;
    private PreparedStatement oDeleteOne;
    private PreparedStatement oInsertOne;

    //PREPARED STATEMENTS FOR THE RATING TABLE
    private PreparedStatement lCreateTable;
    private PreparedStatement lDropTable;
    private PreparedStatement lSelectAll;
    private PreparedStatement lSelectOne;
    private PreparedStatement lDeleteOne;
    private PreparedStatement lInsertOneLike;
    private PreparedStatement lInsertOneDislike;

    //PREPARED STATEMENTS FOR THE COMMENTS TABLE
    private PreparedStatement cCreateTable;
    private PreparedStatement cDropTable;
    private PreparedStatement cSelectAll;
    private PreparedStatement cSelectOne;
    private PreparedStatement cDeleteOne;
    private PreparedStatement cInsertOne;
    private PreparedStatement cDeleteSingleComment;


    private PreparedStatement checkUser; 
    private PreparedStatement checkRating; 
    private PreparedStatement getAllComMessage;
    private PreparedStatement getAllLikes;
    private PreparedStatement getAllDislikes;
    private PreparedStatement getAllMessageUser;


    private PreparedStatement getComMessage;
    private PreparedStatement getLikes;
    private PreparedStatement getDislikes;
    private PreparedStatement getMessageUser;











//End prepared statements ----------------------------------------------------


    /**
     * RowData is like a struct in C: we use it to hold data, and we allow direct
     * access to its fields. In the context of this Database, RowData represents the
     * data we'd see in a row.
     * 
     * We make RowData a static class of Database because we don't really want to
     * encourage users to think of RowData as being anything other than an abstract
     * representation of a row of the database. RowData and the Database are tightly
     * coupled: if one changes, the other should too.
     */
    public class RowData {

        int mId;
        String mTitle;
        String mMessage;
        int mLikes;
        int mDislikes;
        String uId;
        String uName; 
        String uUrl; 
        CommentList mComments; 
        Date mDate;
    

        /**
         * Construct a RowData object by providing values for its fields
         * @param string3
         * @param string2
         * @param string
         * 
         * @param id       The id of the row
         * @param title    The title/subject of the row
         * @param message  The message of the row
         * @param newCommentList
         * @param likes    The like counts of the row
         * @param dislikes The dislike counts of the row
         * @param date     The date created
         */
        public RowData(String email, String name, String pic_url, int id, String title, String message, CommentList commentList, int likes, int dislikes, Date date) {
            mId = id;
            mTitle = title;
            mMessage = message;
            mLikes = likes;
            mDislikes = dislikes;
            uId = email; 
            uName = name; 
            uUrl = pic_url; 
            mComments = commentList; 
            mDate = date;
        }

        public RowData(String email, String name, String picture_url) {
            uId = email; 
            uName = name; 
            uUrl = picture_url;
        }
    }

    /**
     * The Database constructor is private: we only create Database objects through
     * the getDatabase() method.
     */
    private Database() {

    }

    /**
     * Get a fully-configured connection to the database
     * 
     * @param url The Postgres url to connect to Heroku Postgres database
     * 
     * @return A Database object, or null if we cannot connect properly
     */
    static Database getDatabase(String url) {
        // Create an un-configured Database object
        Database db = new Database();

        // Give the Database object a connection, fail if we cannot get one
        try {
            Class.forName("org.postgresql.Driver");
            URI dbUri = new URI(url);
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            if (conn == null) {
                System.err.println("Error: DriverManager.getConnection() returned a null object");
                return null;
            }
            db.mConnection = conn;
        } catch (SQLException e) {
            System.err.println("Error: DriverManager.getConnection() threw a SQLException");
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Unable to find postgresql driver");
            return null;
        } catch (URISyntaxException s) {
            System.out.println("URI Syntax Error");
            return null;
        }

        // Attempt to create all of our prepared statements. If any of these
        // fail, the whole getDatabase() call should fail
        try {
            // NB: we can easily get ourselves in trouble here by typing the
            // SQL incorrectly. We really should have things like "messages"
            // as constants, and then build the strings for the statements
            // from those constants.

            // Note: no "IF NOT EXISTS" or "IF EXISTS" checks on table
            // creation/deletion, so multiple executions will cause an exception
            db.mCreateTable = db.mConnection.prepareStatement(
                    "CREATE TABLE messages (id SERIAL PRIMARY KEY, title VARCHAR(50) NOT NULL, message VARCHAR(500) NOT NULL, mu_id VARCHAR(50) NOT NULL)");
            db.mDropTable = db.mConnection.prepareStatement("DROP TABLE messages");

            // Standard CRUD operations
            db.mDeleteOne = db.mConnection.prepareStatement("DELETE FROM messages WHERE id = ?");
            db.mInsertOne = db.mConnection
                    .prepareStatement("INSERT INTO messages VALUES (default, ?, ?, ?) RETURNING id");
            db.mSelectAll = db.mConnection.prepareStatement("SELECT * FROM messages");
            db.mSelectOne = db.mConnection.prepareStatement("SELECT * from messages WHERE id=?");
            db.mUpdateOne = db.mConnection.prepareStatement("UPDATE messages SET message = ? WHERE id = ?");
            
        

        /*
         * Defining the new relation "Payload", which includes the attributes: - Email
         * (Primary Key) - First Name - Last Name - Email_Verified - Locale
         */ 
            db.oCreateTable = db.mConnection.prepareStatement(
                    "CREATE TABLE payload (email VARCHAR(30) PRIMARY KEY, name VARCHAR(50), picture_url VARCHAR(50))");
            db.oDropTable = db.mConnection.prepareStatement("DROP TABLE payload");
            db.oDeleteOne = db.mConnection.prepareStatement("DELETE FROM payload WHERE email = ?");
            db.oInsertOne = db.mConnection
                    .prepareStatement("INSERT INTO payload VALUES (?, ?, ?) RETURNING email");
            db.oSelectAll = db.mConnection.prepareStatement("SELECT * FROM payload");
            db.oSelectOne = db.mConnection.prepareStatement("SELECT * from payload WHERE email = ?");


        //Comments table initialization 
            db.cCreateTable = db.mConnection.prepareStatement(
                    "CREATE TABLE comments (id INT, c_id INT, c_message VARCHAR(100) NOT NULL, cu_id VARCHAR(50), CONSTRAINT cid_id PRIMARY KEY (id,c_id)");

            db.cDropTable = db.mConnection.prepareStatement("DROP TABLE comments");
            db.cDeleteOne = db.mConnection.prepareStatement("DELETE FROM comments WHERE id = ?");
            db.cDeleteSingleComment = db.mConnection.prepareStatement("DELETE FROM comments where c_id = ?");
            db.cInsertOne = db.mConnection
                    .prepareStatement("INSERT INTO comments VALUES (?, ?, ?, ?) RETURNING id");
            db.cSelectAll = db.mConnection.prepareStatement("SELECT * FROM linkage");
            db.cSelectOne = db.mConnection.prepareStatement("SELECT * from linkage WHERE id = ?");


        //Rating table initialization 
            db.lCreateTable = db.mConnection
                    .prepareStatement("CREATE TABLE rating (id SERIAL, m_email VARCHAR(50), like BIT, dislike BIT, CONSTRAINT mId_user PRIMARY KEY (id, m_email))");

            db.lDropTable = db.mConnection.prepareStatement("DROP TABLE rating");
            db.lDeleteOne = db.mConnection.prepareStatement("DELETE FROM rating WHERE id = ?");
            db.lInsertOneLike = db.mConnection.prepareStatement("INSERT INTO rating VALUES (?, ?, ?, 0) RETURNING id");
            db.lInsertOneDislike = db.mConnection.prepareStatement("INSERT INTO rating VALUES (?, ?, 0, ?) RETURNING id");
            db.lSelectAll = db.mConnection.prepareStatement("SELECT * FROM rating");
            db.lSelectOne = db.mConnection.prepareStatement("SELECT * from rating WHERE id = ?");


        //Additional SQL queries to get more information 
            db.checkUser = db.mConnection.prepareStatement(
                "SELECT email, name FROM payload WHERE email = ?");

            db.checkRating = db.mConnection.prepareStatement(
                "SELECT m_email, like, dislike FROM rating WHERE m_id = ?");

            db.getAllComMessage = db.mConnection.prepareStatement(
                "SELECT * FROM messages LEFT OUTER JOIN comments ON messages.id = comments.id");
            
            db.getAllLikes = db.mConnection.prepareStatement(
                "SELECT id, COUNT(like) as sum_like FROM rating GROUP BY id");

            db.getAllDislikes = db.mConnection.prepareStatement(
                "SELECT id, COUNT(dislike) as sum_dislike FROM rating GROUP BY id");
            
            db.getAllMessageUser = db.mConnection.prepareStatement(
                "SELECT * FROM messages right outer join payload ON messages.mu_id = payload.email");

            db.getComMessage = db.mConnection
                    .prepareStatement("SELECT * FROM messages LEFT OUTER JOIN comments ON messages.id = comments.id WHERE messages.id = ?");

            db.getLikes = db.mConnection
                    .prepareStatement("SELECT id, COUNT(like) as sum_like FROM rating WHERE id = ? GROUP BY id");

            db.getDislikes = db.mConnection
                    .prepareStatement("SELECT id, COUNT(dislike) as sum_dislike FROM rating WHERE id = ? GROUP BY id");

            db.getMessageUser = db.mConnection.prepareStatement(
                    "SELECT * FROM messages RIGHT OUTER JOIN payload ON messages.mu_id = payload.email");
            

            
            
        } catch (SQLException e) {
            System.err.println("Error creating prepared statement");
            e.printStackTrace();
            db.disconnect();
            return null;
        }
        return db;
    }

    /**
     * Close the current connection to the database, if one exists.
     * 
     * NB: The connection will always be null after this call, even if an error
     * occurred during the closing operation.
     * 
     * @return True if the connection was cleanly closed, false otherwise
     */
    boolean disconnect() {
        if (mConnection == null) {
            System.err.println("Unable to close connection: Connection was null");
            return false;
        }
        try {
            mConnection.close();
        } catch (SQLException e) {
            System.err.println("Error: Connection.close() threw a SQLException");
            e.printStackTrace();
            mConnection = null;
            return false;
        }
        mConnection = null;
        return true;
    }

    /*
     *
     * Insert a row into the database
     * 
     * @param title   The title for this new row
     * @param message The message body for this new row
     * 
     * @return The number of rows that were inserted
     */
    int insertRow(String title, String message, String u_id) {
        int count = -1;
        try {
            mInsertOne.setString(1, title);
            mInsertOne.setString(2, message);
            mInsertOne.setString(3, u_id);
            mInsertOne.execute();
            ResultSet res = mInsertOne.getResultSet();
            if (res.next()) {
                count = count + 1; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    int safetyLikeCheck(int m_id, String lu_id) {
        int m_like = 0;
        int m_dislike = 0; 

        try {
            mInsertOne.setInt(1, m_id);
            ResultSet rs = checkRating.getResultSet(); 
         /*
            While the ResultSet res is gathering all of the tuples returned from the database, 
            we will iterate through the tuples and find the one that has a matching lu_id with the
            email registered on the "Rating" table. 

            If lu_id == m_email, then we will begin to check if the like and dislike attributes 
            are populated. 

            If both are set to '1', then the function will return a -1 indicating a failure. 
            The function will also decrement the "dislike" to a 0, if the condition above is met. 
        */
            while (rs.next()) {

            // create new RowData instance and insert it into ArrayList

                String return_email = rs.getString("m_email");

                if (lu_id.equals(return_email)) {
                    m_like = rs.getInt("like");
                    m_dislike = rs.getInt("dislike");
                }
            }

            System.out.println(m_like);
            System.out.println(m_dislike);
            

            return 0; 
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; 
    }
    

    int addUserInfo(String u_id, String name, String pic_url) {
        int count = -1; // count number of inserts

        try {

        /*
            Checking to see if the user is already a member of the Payload table. 
        */
            checkUser.setString(1, u_id);
            ResultSet rs = checkUser.getResultSet();
            while (rs.next()) {
                if (rs.getString("email") == u_id) {
                    return -1; 
                }
            }
            oInsertOne.setString(1, u_id);
            oInsertOne.setString(2, name);
            oInsertOne.setString(3, pic_url); 
            ResultSet rs2 = oInsertOne.executeQuery();
            if (rs2.next()) {
                count = rs2.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return count; 
    }



    int insertComment(int id, String cu_id, String c_message) {
        int count = -1; 
        int c_id; 
        try {
            int num_comments = 0; 

        /*
            Block of code below takes the results from the "check_value" 
            variable and figures out number of pre-existing comments for the specific 
            message_id. 

            If for example, Message #3 has 4 comments already in the database...
            Then when you POST another comment to "/messages/:m_id/comments", 
            the c_id for that comment would automatically be incremented by one.
        */
            if (num_comments > 0) {
                c_id = num_comments + 1;
                cInsertOne.setInt(1, id);
                cInsertOne.setInt(2, c_id);
                cInsertOne.setString(3, c_message);
                cInsertOne.setString(4, cu_id);
                cInsertOne.execute();
                ResultSet res = cInsertOne.getResultSet();
                if (res.next()) {
                    count = count + 1;
                }
          } else {
                c_id = 1;
                cInsertOne.setInt(1, id);
                cInsertOne.setInt(2, c_id);
                cInsertOne.setString(3, c_message);
                cInsertOne.setString(4, cu_id);
                ResultSet res = cInsertOne.getResultSet();
                if (res.next()) {
                    count = count + 1; 
                }
          }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count; 
    }

    /**
     * Query the database for a list of all titles and their IDs
     * 
     * @return All rows, as an ArrayList
     */
    ArrayList<RowData> selectAll() {
        ArrayList<RowData> res = new ArrayList<>();
        Comment newComment = new Comment();
        CommentList newCommentList = new CommentList();
        try {

            ResultSet rs = getAllMessageUser.executeQuery();
            ResultSet rs2 = getAllComMessage.executeQuery();
            ResultSet rs3 = getAllLikes.executeQuery();
            ResultSet rs4 = getAllDislikes.executeQuery(); 

            while (rs.next()) {
                rs3.next();
                rs4.next();
            // create new RowData instance and insert it into ArrayList
                while (rs2.next() && rs2.getInt("messages.id") == rs.getInt("messages.id")) {
                    newComment.setComment(rs2.getString("comments.c_message"), rs2.getString("comments.cu_id"), rs2.getInt("comments.c_id"));
                    newCommentList.setComment(newComment);
                }

                res.add(new RowData(rs.getString("payload.email"), rs.getString("payload.name"), rs.getString("payload.pic_url"), rs.getInt("messages.id"),
                    rs.getString("messages.title"), rs.getString("messages.message"), newCommentList, rs3.getInt("sum_like"), rs3.getInt("sum_dislike"),
                    rs.getDate("date")));

                newComment.clearComment();
                newCommentList.deleteList();
                
            }
            rs.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in your selectAll() function in Database.java");
            return new ArrayList<>();
        }
    }

    /* 
        Selects one person from the payload table and then retrieves their name and picture_url 
    */
    RowData selectOneUser(String u_id) {
        ResultSet res = null; 
        RowData row = null; 

        try {
            oSelectOne.setString(1, u_id);
            res = oSelectOne.executeQuery();

            while(res.next()) {
                row = new RowData(u_id, res.getString("name"), res.getString("picture_url"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in Function RowData selectOneUser");

        }
        
        return row; 
    }


    /**
     * Get all data for a specific row, by ID
     * 
     * @param id The id of the row being requested
     * 
     * @return The data for the requested row, or null if the ID was invalid
     */
    RowData selectOne(int id) {

        Comment newComment = new Comment(); 
        CommentList newCommentList = new CommentList(); 
        RowData res = null; 

        try {
            getMessageUser.setInt(1, id);
            getComMessage.setInt(1, id);
            getLikes.setInt(1, id);
            getDislikes.setInt(1, id);

            ResultSet rs = getMessageUser.executeQuery();
            ResultSet rs2 = getComMessage.executeQuery();
            ResultSet rs3 = getLikes.executeQuery();
            ResultSet rs4 = getDislikes.executeQuery();

            while (rs.next()) {
                rs3.next();
                rs4.next();

                // create new RowData instance and insert it into ArrayList
                while (rs2.next() && rs2.getInt("messages.id") == rs.getInt("messages.id")) {
                    newComment.setComment(rs2.getString("comments.c_message"), rs2.getString("comments.cu_id"),
                            rs2.getInt("comments.c_id"));
                    newCommentList.setComment(newComment);
                }

                res = new RowData(rs.getString("payload.email"), rs.getString("payload.name"),
                        rs.getString("payload.pic_url"), rs.getInt("messages.id"), rs.getString("messages.title"),
                        rs.getString("messages.message"), newCommentList, rs3.getInt("sum_like"),
                        rs3.getInt("sum_dislike"), rs.getDate("date"));

                newComment.clearComment();
                newCommentList.deleteList(); 

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Delete a row by ID
     * 
     * @param id The id of the row to delete
     * 
     * @return The number of rows that were deleted. -1 indicates an error.
     */
    int deleteMessageRow(int id) {
        int res = -1;
        try {
            mDeleteOne.setInt(1, id);
            res += mDeleteOne.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    /**
     * Update the message for a row in the database
     * 
     * @param id      The id of the row to update
     * @param message The new message contents
     * 
     * @return The number of rows that were updated. -1 indicates an error.
     */
    int updateOne(int id, String message) {
        int res = -1;
        try {
            mUpdateOne.setString(1, message);
            mUpdateOne.setInt(2, id);
            res += mUpdateOne.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Increment the like count for the row at id in the database
     * 
     * @param id The id of the row to update
     * @return The number of rows that were updated. -1 indicates an error.
     */
    int updateOneLikes(int id, String ru_id) {
        int res = -1;
        try {
            lInsertOneLike.setInt(1, id);
            lInsertOneLike.setString(2, ru_id);
            lInsertOneLike.setInt(3, 1);
            res += lInsertOneLike.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Increment the dislike count for the row at id in the database
     * 
     * @param id The id of the row to update
     * @return The number of rows that were updated. -1 indicates an error.
     */
    int updateOneDislikes(int id) {
        int res = -1;
        try {
            mUpdateOneDislikes.setInt(1, id);
            res += mUpdateOneDislikes.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Create messages. If it already exists, this will print an error
     */
    void createTable() {
        try {
            mCreateTable.execute();
            oCreateTable.execute();
            cCreateTable.execute();
            lCreateTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Remove messages from the database. If it does not exist, this will print an
     * error.
     */
    void dropTable() {
        try {
            mDropTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}