package edu.lehigh.cse216.group25.admin;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * UNIT TESTS (testing functionality of database.java)
     * Had to do Phase 1 and Phase 2 unit tests
     */

    // test mock data base connection/disconnection
    public void Test test_databaseConnection() {
        Database db = new Database(); // intialized mock connection
        assertNotNull("Testing if mock database was created", db); // if mock database was created, should not return NULL
        assertFalse(db.disconnect()); // should return false because the connection is NULL as it's a mock connection
    }

    // test function InsertRow();
    public void Test test_insertRow() {
        Database db = new Database(); // intialized mock connection
        try {
            assertEquals(db.insertRow("test", "test message"), 1); // database is not connected, therefore should return NULL
            fail("Null pointer exception");
        }
        catch(NullPointerException e) {
            // error
        }
        assertFalse(db.disconnect()); // return null because mock database is not connected
    }

    // test function safetyLikeCheck();
    // FIX ME (i think logic is wrong)
    public void Test test_safetyLikeCheck() {
        Database db = new Database(); // intialized mock connection
        try {
            // check if both are set to 1
            assertEquals(db.safetyLikeCheck(1), 1); // database is not connected, therefore should return NULL
            fail("Null pointer exception");
        }
        catch(NullPointerException e) {
            // error
        }
        assertFalse(db.disconnect()); // return null because mock database is not connected
    }

    // test function addUserInfo();
    public void Test test_addUserInfo() {
        Database db = new Database(); // intialized mock connection
        String u_id = "test@lehigh.edu";
        int m_id = 4;
        Database.RowData row = new Database.RowData(u_id, m_id);
        assertEquals(row.mId, u_id); // user id
        assertEquals(row.mId, m_id); // message id
        
        assertFalse(db.disconnect()); // return null because mock database is not connected
    }

    // test function insertComment();

    // test function selectOne();
    public void Test test_selectOne() {
        Database db = new Database(); // intialized mock connection
        try {
            assertNotNull(db.selectOne(7)); // database is not connected, therefore should return NULL
            fail("Null pointer exception");
        }
        catch(NullPointerException e) {
            // error
        }
        assertFalse(db.disconnect()); // return null because mock database is not connected
    }

    // test function deleteRow();
    public void Test test_deleteRow() {
        Database db = new Database(); // intialized mock connection
        try {
            assertEquals(db.deleteRow(1), 1); // database is not connected, therefore should return NULL
            fail("Null pointer exception");
        }
        catch(NullPointerException e) {
            // error
        }
        assertFalse(db.disconnect()); // return null because mock database is not connected
    }

    // test function updateOne();
    public void Test test_updateOne() {
        Database db = new Database(); // intialized mock connection
        try {
            assertEquals(db.updateOne(1, "Test Message"), 1); // // database is not connected, therefore should return NULL
            fail("Null pointer exception");
        }
        catch(NullPointerException e) {
            // error
        }
        assertFalse(db.disconnect()); // return null because mock database is not connected
    }

    // test function updateOneLikes();
    public void Test test_updateOneLikes() {
        Database db = new Database(); // intialized mock connection
        try {
            assertEquals(db.updateOneLikes(1), 1);
            fail("Null pointer exception");
        }
        catch(NullPointerException e) {
            // error
        }
        assertFalse(db.disconnect()); // return null because mock database is not connected
    }

    // test function udpateOneDislikes();
    public void Test test_updateOneDislikes() {
        Database db = new Database(); // intialized mock connection
        try {
            assertEquals(db.updateOneDislikes(1), 1);
            fail("Null pointer exception");
        }
        catch(NullPointerException e) {
            // error
        }
        assertFalse(db.disconnect()); // return null because mock database is not connected
    }
}

// Unit Testing:
// test that comment id is unique
// test unique emails
// test likes (can't be two 1's in table bc incorrect)
