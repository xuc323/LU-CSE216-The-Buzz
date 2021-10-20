package edu.lehigh.cse216.group25.admin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import spark.Spark;
import java.util.ArrayList;
import java.util.Map;
import com.google.gson.*;
import java.sql.Statement;

/**
 * App is our basic admin app. For now, it is a demonstration of the six key
 * operations on a database: connect, insert, update, query, delete, disconnect
 */
public class App {

    /**
     * Print the menu for our program
     */
    static void menu() {
        System.out.println("Main Menu");
        System.out.println("  [T] Create table");
        System.out.println("  [D] Drop table");
        System.out.println("  [1] Query for a specific row");
        System.out.println("  [*] Query for all rows");
        System.out.println("  [-] Delete a row");
        System.out.println("  [+] Insert a new row");
        System.out.println("  [~] Update a row");
        System.out.println("  [Q] Quit Program");
        System.out.println("  [?] Help (this message)");
    }

    /**
     * Ask the user to enter a menu option; repeat until we get a valid option
     * 
     * @param in A BufferedReader, for reading from the keyboard
     * 
     * @return The character corresponding to the chosen menu option
     */
    static char prompt(BufferedReader in) {
        // The valid actions:
        String actions = "TD1*-+~q?";

        // We repeat until a valid single-character option is selected
        while (true) {
            System.out.print("[" + actions + "] :> ");
            String action;
            try {
                action = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            if (action.length() != 1)
                continue;
            if (actions.contains(action)) {
                return action.charAt(0);
            }
            System.out.println("Invalid Command");
        }
    }

    /**
     * Ask the user to enter a String message
     * 
     * @param in      A BufferedReader, for reading from the keyboard
     * @param message A message to display when asking for input
     * 
     * @return The string that the user provided. May be "".
     */
    static String getString(BufferedReader in, String message) {
        String s;
        try {
            System.out.print(message + " :> ");
            s = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return s;
    }

    /**
     * Ask the user to enter an integer
     * 
     * @param in      A BufferedReader, for reading from the keyboard
     * @param message A message to display when asking for input
     * 
     * @return The integer that the user provided. On error, it will be -1
     */
    static int getInt(BufferedReader in, String message) {
        int i = -1;
        try {
            System.out.print(message + " :> ");
            i = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }
    static int getIntFromEnv(String envar, int defaultVal) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get(envar) != null) {
            return Integer.parseInt(processBuilder.environment().get(envar));
        }
        return defaultVal;
    }

    /**
     * The main routine runs a loop that gets a request from the user and processes
     * it
     * 
     * @param argv Command-line options. Ignored by this program.
     */
    public static void main(String[] argv) {
        // Get the port on which to listen for requests
        final Gson gson = new Gson();

        // Get the port on which to listen for requests
        Spark.port(getIntFromEnv("PORT", 4567));

        // get the Postgres configuration from the environment
        Map<String, String> env = System.getenv();
        String db_url = env.get("DATABASE_URL");
        // Get a fully-configured connection to the database, or exit
        // immediately
        Database db = Database.getDatabase(db_url);
        if (db == null) {
            return;
        } else {
            // print out if database is connected successfully
            System.out.println("Postgres database connected!");
        }

        // Set up the location for serving static files. If the STATIC_LOCATION
        // environment variable is set, we will serve from it. Otherwise, serve
        // from "/web"
        String static_location_override = System.getenv("STATIC_LOCATION");
        if (static_location_override == null) {
            Spark.staticFileLocation("/web");
        } else {
            Spark.staticFiles.externalLocation(static_location_override);
        }

        // Set up a route for serving the main page
        Spark.get("/", (req, res) -> {
            res.redirect("/index.html");
            return "";
        });

        // Start our basic command-line interpreter:
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            // Get the user's request, and do it
            //
            // NB: for better testability, each action should be a separate
            // function call
            char action = prompt(in);
            if (action == '?') {
                println(menu());
            } else if (action == 'Q') {
                println("Exiting command-line");
                break;
            } else if (action == 'T') { // if user enters T, then create the 4 tables
                db.createTable();
                //db.mCreateTable(); // message table
                //db.oCreateTable(); // payload table
                //db.cCreateTable(); // comment table
                //db.lCreateTable(); // rating table
            } else if (action == 'D') {
                db.dropTable();
                //db.mdropTable(); // message table
                //db.odropTable(); // payload table
                //db.cdropTable(); // comment table
                //db.ldropTable(); // rating table
            } else if (action == '1') { // [1] Query for a specific row
                int id = getInt(in, "Enter the message ID");
                if (id == -1) // if error, continue
                    continue;
                Database.RowData res = db.selectOne(id);
                if (res != null) {
                    System.out.println("  [" + res.mId + "] ");
                    System.out.println("  --> " + res.mMessage);
                }
            } else if (action == '*') { // [*] Query for all rows
                ArrayList<Database.RowData> res = db.selectAll();
                if (res == null)
                    continue;
                System.out.println("  Current Database Contents");
                System.out.println("  -------------------------");
                for (Database.RowData rd : res) { // print all row data
                    System.out.println("  [" + rd.mId + "] ");
                    System.out.println("  [" + rd.mTitle + "] ");
                    System.out.println("  [" + rd.mMessage + "] ");
                    System.out.println("  [" + rd.mLikes + "] ");
                    System.out.println("  [" + rd.mDislikes + "] ");
                    System.out.println("  [" + rd.mDate + "] ");
                }
            } else if (action == '-') { // [-] Delete a row
                int id = getInt(in, "Enter the message ID");
                String email = getString(in, "Enter valid email");
                if (id == -1)
                    continue;
                int res = db.deleteRow(id, email); // delete message pertaining to specified user
                if (res == -1)
                    continue;
                System.out.println("  " + res + " rows deleted");
            } else if (action == '+') { // Insert a new row
                String title = getString(in, "Enter the title");
                String message = getString(in, "Enter the message");
                if (message.equals("")) // if a message was entered, insert the new row
                    continue;
                int res = db.insertRow(title, message);
                System.out.println(res + " rows added");
            } else if (action == '~') { // Update a row
                int id = getInt(in, "Enter the row ID :> ");
                if (id == -1)
                    continue;
                String newMessage = getString(in, "Enter the new message");
                int res = db.updateOne(id, newMessage);
                if (res == -1)
                    continue;
                System.out.println("  " + res + " rows updated");
            }
        }
        // Always remember to disconnect from the database when the program
        // exits
        db.disconnect();
    }
}