package src.main.java.edu.lehigh.cse216.group25.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.Date;

public class Database {
    /**
     * The connection to the database. When there is no connection, it should be
     * null. Otherwise, there is a valid open connection
     */
    private Connection mConnection;

    /**
     * A prepared statement for getting all data in the database
     */
    private PreparedStatement mSelectAll;

    /**
     * A prepared statement for getting one row from the database
     */
    private PreparedStatement mSelectOne;

    /**
     * A prepared statement for deleting a row from the database
     */
    private PreparedStatement mDeleteOne;

    /**
     * A prepared statement for inserting into the database
     */
    private PreparedStatement mInsertOne;

    /**
     * A prepared statement for updating a single row in the database
     */
    private PreparedStatement mUpdateOne;

    /**
     * A prepared statement for updating likes in the database
     */
    private PreparedStatement mUpdateOneLikes;

    /**
     * A prepared statement for updating dislikes in the database
     */
    private PreparedStatement mUpdateOneDislikes;
    /**
     * A prepared statement for creating the table in our database
     */
    private PreparedStatement mCreateTable;

    /**
     * A prepared statement for dropping the table in our database
     */
    private PreparedStatement mDropTable;

    private PreparedStatement oCreateTable; 

    private PreparedStatement oDropTable; 

    private PreparedStatement oSelectAll;

    private PreparedStatement oSelectOne;

    private PreparedStatement oDeleteOne;

    private PreparedStatement oInsertOne;

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
    public static class RowData {
        /**
         * The ID of this row of the database
         */
        int mId;

        /**
         * The title stored in this row
         */
        String mTitle;

        /**
         * The message stored in this row
         */
        String mMessage;

        /**
         * The number of likes
         */

        int mLikes;

        /**
         * The number of dislikes
         */

        int mDislikes;

        /**
         * Date created the post
         */

        Date mDate;

        /**
         * Construct a RowData object by providing values for its fields
         * 
         * @param id       The id of the row
         * @param title    The title/subject of the row
         * @param message  The message of the row
         * @param likes    The like counts of the row
         * @param dislikes The dislike counts of the row
         * @param date     The date created
         */
        public RowData(int id, String title, String message, int likes, int dislikes, Date date) {
            mId = id;
            mTitle = title;
            mMessage = message;
            mLikes = likes;
            mDislikes = dislikes;
            mDate = date;
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
            // TODO: change the SQL syntax so it will be able to modify 3 tables
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
                    "CREATE TABLE payload (email VARCHAR(30) PRIMARY KEY, first_name VARCHAR(15), last_name VARCHAR(20), picture_url VARCHAR(50))");
            db.oDropTable = db.mConnection.prepareStatement("DROP TABLE payload");
            db.oDeleteOne = db.mConnection.prepareStatement("DELETE FROM payload WHERE email = ?");
            db.oInsertOne = db.mConnection
                    .prepareStatement("INSERT INTO payload VALUES (default, ?, ?, default, default) RETURNING email");
            db.oSelectAll = db.mConnection.prepareStatement("SELECT * FROM payload");
            db.oSelectOne = db.mConnection.prepareStatement("SELECT * from payload WHERE email=?");

        /*
         * You need to define a database table that returns a list of all users registered to
         *  the database, and also a table that connects a user existing in the "payload"
         * table to a specific id. You are also able to add comments to the table as well. 
         * 
         * Conclusion: Table 1: Message information (No User Data)
         *             Table 2: User information (No Message Data)
         *             Table 3: Email + Message_ID + Comment_ID 
         *             Table 4: Comment information (No User Data) 
        */ 

        //Comments table initialization 
            db.cCreateTable = db.mConnection.prepareStatement(
                    "CREATE TABLE comments (id SERIAL, c_id INT, c_message VARCHAR(100) NOT NULL, cu_id VARCHAR(50), CONSTRAINT cid_id PRIMARY KEY (id,c_id)");

            db.cDropTable = db.mConnection.prepareStatement("DROP TABLE comments");
            db.cDeleteOne = db.mConnection.prepareStatement("DELETE FROM comments WHERE id = ?");
            db.cDeleteSingleComment = db.mConnection.prepareStatement("DELETE FROM comments where c_id = ?");
            db.cInsertOne = db.mConnection
                    .prepareStatement("INSERT INTO comments VALUES (default, ?, ?, ?) RETURNING id");
            db.cSelectAll = db.mConnection.prepareStatement("SELECT * FROM linkage");
            db.cSelectOne = db.mConnection.prepareStatement("SELECT * from linkage WHERE id = ?");


        //Like & Dislike table initialization 
            db.lCreateTable = db.mConnection
                    .prepareStatement("CREATE TABLE rating (id SERIAL PRIMARY KEY, m_email VARCHAR(50), like BIT, dislike BIT");

            db.lDropTable = db.mConnection.prepareStatement("DROP TABLE rating");
            db.lDeleteOne = db.mConnection.prepareStatement("DELETE FROM rating WHERE id = ?");
            db.lInsertOne = db.mConnection.prepareStatement("INSERT INTO rating VALUES (default, ?, ?, ?) RETURNING id");
            db.lSelectAll = db.mConnection.prepareStatement("SELECT * FROM rating");
            db.lSelectOne = db.mConnection.prepareStatement("SELECT * from rating WHERE id = ?");

            
            
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
    int insertRow(String title, String message) {
        int count = -1;
        try {
            mInsertOne.setString(1, title);
            mInsertOne.setString(2, message);
            mInsertOne.execute();
            ResultSet res = mInsertOne.getResultSet();
            if (res.next()) {
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    int safetyLikeCheck(int m_id, String lu_id) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT m_email, like, dislike FROM rating WHERE m_id = "+m_id+"");


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
                int m_like = rs.getInt("like");
                int m_dislike = rs.getInt("dislike");

                if (lu_id.equals(return_email)) {
                    int m_like = rs.getInt("like");
                    int m_dislike = rs.getInt("dislike");
                }
                
            }
            
        }
    }

    int addUserInfo(String u_id, int m_id) {
        try {
            Statement stmt = con.createStatement();
            String check_value = stmt.executeQuery("SELECT email FROM payload WHERE email = "+u_id+"");
            int num_comments = parseInt(check_value);


    }



    int insertComment(int id, String cu_id, String c_message) {
        int count = -1; 
        int c_id; 
        try {
            Statement stmt = con.createStatement();
            String check_value = stmt.executeQuery("SELECT COUNT(*) FROM comments WHERE id = "+id+"");
            int num_comments = parseInt(check_value);

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
                lInsertOne.setInt(1, c_id);
                lInsertOne.setString(2, c_message);
                lInsertOne.setString(3, cu_id);
                lInsertOne.execute();
                ResultSet res = lInsertOne.getResultSet();
                if (res.next()) {
                    count = res.getInt(1);
                }
          } else {
              c_id = 1;
              lInsertOne.setInt(1, c_id);
              lInsertOne.setString(2, c_message);
              lInsertOne.setString(3, cu_id);
              ResultSet res = lInsertOne.getResultSet();
              if (res.next()) {
                  count = res.getInt(1);
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
        ArrayList<RowData> res = new ArrayList<RowData>();
        try {
            ResultSet rs = mSelectAll.executeQuery();
            while (rs.next()) {
            // create new RowData instance and insert it into ArrayList
                res.add(new RowData(rs.getInt("id"), rs.getString("title"), rs.getString("message"), rs.getInt("likes"),
                rs.getInt("dislikes"), rs.getDate("date")));
            }
            rs.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get all data for a specific row, by ID
     * 
     * @param id The id of the row being requested
     * 
     * @return The data for the requested row, or null if the ID was invalid
     */
    RowData selectOne(int id) {
        RowData res = null;
        try {
            mSelectOne.setInt(1, id);
            ResultSet rs = mSelectOne.executeQuery();
            if (rs.next()) {
                // create new RowData instance and insert it into ArrayList
                res = new RowData(rs.getInt("id"), rs.getString("title"), rs.getString("message"), rs.getInt("likes"),
                        rs.getInt("dislikes"), rs.getDate("date"));
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
    int deleteRow(int id) {
        int res = -1;
        try {
            mDeleteOne.setInt(1, id);
            res += mDeleteOne.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    int deleteRow(int id) {
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
    int updateOneLikes(int id) {
        int res = -1;
        try {
            mUpdateOneLikes.setInt(1, id);
            res += mUpdateOneLikes.executeUpdate();
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