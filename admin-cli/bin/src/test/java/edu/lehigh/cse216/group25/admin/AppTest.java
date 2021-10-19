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
    public void test_databaseConnection() {
        Database db = new Database(); // intialized mock connection
        assertNotNull("Testing if mock database was created", db); // if mock database was created, should not return NULL
        assertFalse(db.disconnect()); // should return false because the connection is NULL as it's a mock connection
    }

    // test function InsertRow();
    public void test_insertRow() {
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

    // test function addUserInfo();

    // test function insertComment();

    // test function selectOne();
    public void test_selectOne() {
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
    public void test_deleteRow() {
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
    public void test_updateOne() {
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

    // test function udpateOneDislikes();

}
// Unit Testing:
// test that comment id is unique
// test unique emails
// test likes (can't be two 1's in table bc incorrect)
