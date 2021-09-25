package edu.lehigh.cse216.group25.backend;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Map;

public class DatabaseTest extends TestCase {
    /**
     * Create the test case
     * 
     * @param testName name of the test case
     */
    public DatabaseTest(String testName) {
        super(testName);
    }

    /**
     *
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(DatabaseTest.class);
    }

    /**
     * Since the db instance needs to be true and tests will run randomly, all tests
     * are now performed in one function. This test ensures that db can be created,
     * insertion works, find works, and update likes works.
     */
    public void testConstructor() {
        // get database url from the env and create the Database instance
        Map<String, String> env = System.getenv();
        String db_url = env.get("DATABASE_URL");
        Database db = Database.getDatabase(db_url);
        // check if db is successfully created
        assertTrue(db != null);

        // preparing title and message to insert
        String title = "Test Title";
        String message = "Test Message";
        int count = db.insertRow(title, message);
        // check if insertion is successful
        assertTrue(count == 1);

        // read all messages from the database
        ArrayList<Database.RowData> dataList = db.selectAll();
        // check if we get all messages (must be something because insertion is right
        // before)
        assertTrue(dataList != null);

        // get the first instance of all messages and update the like count
        Database.RowData data = dataList.get(0);
        int likeCount = data.mLikes;
        int status = db.updateOneLikes(data.mId, data.mLikes);
        // check that updating likes works
        assertTrue(status != -1);

        // get the like count for the one we just updated
        int likeCountAfter = db.selectOne(data.mId).mLikes;
        // check if it actually got incremented
        assertTrue((likeCount + 1) == likeCountAfter);

        // close the connection
        db.disconnect();
    }

}
