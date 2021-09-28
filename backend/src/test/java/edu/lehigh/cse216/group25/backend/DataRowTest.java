package edu.lehigh.cse216.group25.backend;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class DataRowTest {
    /**
     * Ensure that the constructor populates every field of the object it creates
     */
    @Test
    public void testConstructor() {
        String title = "Test Title";
        String content = "Test Content";
        int id = 17;
        DataRow d = new DataRow(id, title, content);

        assertTrue(d.mTitle.equals(title));
        assertTrue(d.mContent.equals(content));
        assertTrue(d.mId == id);
        assertFalse(d.mCreated == null);
    }

    /**
     * Ensure that the copy constructor works correctly
     */
    @Test
    public void testCopyconstructor() {
        String title = "Test Title For Copy";
        String content = "Test Content For Copy";
        int id = 177;
        DataRow d = new DataRow(id, title, content);
        DataRow d2 = new DataRow(d);
        assertTrue(d2.mTitle.equals(d.mTitle));
        assertTrue(d2.mContent.equals(d.mContent));
        assertTrue(d2.mId == d.mId);
        assertTrue(d2.mCreated.equals(d.mCreated));
    }
}