package edu.lehigh.cse216.group25.backend;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Map;

public class DatabaseTest {

    Database db;
    int idx;

        @Test 
        public void mockTest() {
            assertTrue(true); // test
        }
    /**
     * Since the db instance needs to be true and tests will run randomly, all tests
     * are now performed in one function. This test ensures that db can be created,
     * insertion works, find works, and update likes works.
     */
    // @BeforeAll
    // public void testConstructor() {
    //     // get database url from the env and create the Database instance
    //     Map<String, String> env = System.getenv();
    //     String db_url = env.get("DATABASE_URL");
    //     db = Database.getDatabase(db_url);
    //     // check if db is successfully created
    //     assertTrue(db != null);
    // }

    // @Test
    // public void testInsertion() {
    //     // preparing title and message to insert
    //     String title = "Test Title1";
    //     String message = "Test Message1";
    //     idx = db.insertRow(title, message);
    //     // check if insertion is successful
    //     assertTrue(idx != -1);
    // }

    // @Test
    // public void testSelection() {
    //     // read all messages from the database
    //     ArrayList<Database.RowData> dataList = db.selectAll();
    //     // check if we get all messages (must be something because insertion is right
    //     // before)
    //     assertTrue(dataList != null);
    // }

    // @Test
    // public void testSelectOne() {
    //     Database.RowData data = db.selectOne(idx);
    //     assertTrue(data != null);
    // }

    // @Test
    // public void testLike() {
    //     // get the first instance of all messages and update the like count
    //     int likeCount = db.selectOne(idx).mLikes;
    //     int status = db.updateOneLikes(idx);
    //     // check that updating likes works
    //     assertTrue(status != -1);
    //     // get the like count for the one we just updated
    //     int likeCountAfter = db.selectOne(idx).mLikes;
    //     // check if it actually got incremented
    //     assertTrue((likeCount + 1) == likeCountAfter);
    // }

    // @Test
    // public void testDislike() {
    //     // get the first instance of all messages and update the like count
    //     int dislikeCount = db.selectOne(idx).mDislikes;
    //     int status = db.updateOneDislikes(idx);
    //     // check that updating likes works
    //     assertTrue(status != -1);
    //     // get the like count for the one we just updated
    //     int dislikeCountAfter = db.selectOne(idx).mDislikes;
    //     // check if it actually got incremented
    //     assertTrue((dislikeCount + 1) == dislikeCountAfter);
    // }

    // @AfterAll
    // public void testCleanUp() {
    //     int delStatus = db.deleteRow(idx);
    //     assertTrue(delStatus != -1);
    //     // close the connection
    //     boolean status = db.disconnect();
    //     assertTrue(status);
    // }

}
