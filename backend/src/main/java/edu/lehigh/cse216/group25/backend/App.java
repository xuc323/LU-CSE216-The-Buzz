package edu.lehigh.cse216.group25.backend;

// Import the Spark package, so that we can make use of the "get" function to 
// create an HTTP GET route
import spark.Spark;

// Import Google's JSON library
import com.google.gson.*;

import java.util.Collections;
// Import map to get env variables
import java.util.Map;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.security.GeneralSecurityException;
import java.io.IOException;

/**
 * For now, our app creates an HTTP server with only one route.
 * 
 * When an HTTP client connects to this server on the default SPARK port (4567),
 * and requests /hello, we return "Hello World". Otherwise, we produce an error
 */
public class App {


    public static void main(String[] args) {

        // gson provides us with a way to turn JSON into objects, and objects
        // into JSON.
        //
        // NB: it must be final, so that it can be accessed from our lambdas
        //
        // NB: Gson is thread-safe. See
        // https://stackoverflow.com/questions/10380835/is-it-ok-to-use-gson-instance-as-a-static-field-in-a-model-bean-reuse
        final Gson gson = new Gson();

        /*
            Defining our constants... CLIENT_WEB and CLIENT_ANDROID

        */ 
        final String CLIENT_ID = "496410238969-mvosj73q4tnp1dumhbpfbucato5ner3k.apps.googleusercontent.com";
        final String CLIENT_SECRET = "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";
        final String ID_TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImFkZDhjMGVlNjIzOTU0NGFmNTNmOTM3MTJhNTdiMmUyNmY5NDMzNTIiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYmYiOjE2MzQ2ODU2MjQsImF1ZCI6IjQ5NjQxMDIzODk2OS1tdm9zajczcTR0bnAxZHVtaGJwZmJ1Y2F0bzVuZXIzay5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwODYzMDc0MjM0MDUxMzE4MDg5NiIsImVtYWlsIjoidGJ6MjE2MDI1QGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiI0OTY0MTAyMzg5NjktbXZvc2o3M3E0dG5wMWR1bWhicGZidWNhdG81bmVyM2suYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJuYW1lIjoiQnV6eiBUaGUiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUFUWEFKeFZLNlEzb2V3d25DTjRoV3Q5TUw2eUtYYVZqcTRCTGE4eW5WMWM9czk2LWMiLCJnaXZlbl9uYW1lIjoiQnV6eiIsImZhbWlseV9uYW1lIjoiVGhlIiwiaWF0IjoxNjM0Njg1OTI0LCJleHAiOjE2MzQ2ODk1MjQsImp0aSI6ImZhNWFkOGRjMzBmZTg2ZGYzNjViODNkMzVjZDg3M2M3Yzc1NjVjMjYifQ.CK7HXCOnaUBYf-YQ2p2SH4mYudBK9SXkZQa8d4zGB_vZv6jJbXrOANJqwOKf08uPfMbSMfavMsNq-APNhAFklwcIeT35mG7OhW4rIURYoWJpZCKg-jmeKMG1JcGEI0wok3eROvk3YsPW7tHwkM1PDhqkw4tW4KrB0LtIs2eLjWjwvPQfo4Z_gdtRwCptu5qaX333Dv7vu-_LVF0ODbnmn_gZQ7Iww_hjALPAq0zxtQibS8KtV0rFbA9evbgttRxY3QwoN_djvbRJCweMhnALcY73l-2oCDf189m5-DMzKIv64oAByUvUO0f2Ey1W2M3F1AUWq2Ax6wdzA_PpLKMMnQ";
        final HttpTransport transport = new NetHttpTransport();
        final JsonFactory jsonFactory = new JacksonFactory();
        
        

        


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
            // db.dropTable();
            // db.createTable();
        }

        // Set up the location for serving static files. If the STATIC_LOCATION
        // environment variable is set, we will serve from it. Otherwise, serve
        // from "/web"
        String static_location_override = System.getenv("STATIC_LOCATION");
        if (static_location_override == null) {
            Spark.staticFileLocation("/build");
        } else {
            Spark.staticFiles.externalLocation(static_location_override);
        }


    /*
        CORS Coding: Enabling Cross-Server-Access. Important to connect the Android application
        to our pre-existing Frontend/Backend
    */
        // String cors_enabled = env.get("CORS_ENABLED");
        // if (cors_enabled.equals("True")) {
        //     final String acceptCrossOriginRequestsFrom = "*";
        //     final String acceptedCrossOriginRoutes = "GET,PUT,POST,DELETE,OPTIONS";
        //     final String supportedRequestHeaders = "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin";
        //     //enableCORS(acceptCrossOriginRequestsFrom, acceptedCrossOriginRoutes, supportedRequestHeaders);
        // }

        /*
        * Set up CORS headers for the OPTIONS verb, and for every response that the
        * server sends.  This only needs to be called once.
        * 
        * @param origin The server that is allowed to send requests to this server
        * @param methods The allowed HTTP verbs from the above origin
        * @param headers The headers that can be sent with a request from the above
        *                origin
        */
        // private static void enableCORS(String origin, String methods, String headers) {
        //     // Create an OPTIONS route that reports the allowed CORS headers and methods
        //     Spark.options("/*", (request, response) -> {
        //         String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
        //         if (accessControlRequestHeaders != null) {
        //             response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
        //         }
        //         String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
        //         if (accessControlRequestMethod != null) {
        //             response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
        //         }
        //         return "OK";
        //     });

            // 'before' is a decorator, which will run before any 
            // get/post/put/delete.  In our case, it will put three extra CORS
            // headers into the response
            // Spark.before((request, response) -> {
            //     response.header("Access-Control-Allow-Origin", origin);
            //     response.header("Access-Control-Request-Method", methods);
            //     response.header("Access-Control-Allow-Headers", headers);
            // });
        
            
        // (Receive idTokenString by HTTPS POST)
        Spark.post("/login", (request, response) -> {

            SimpleRequest res = gson.fromJson(request.body(), SimpleRequest.class);
            try { 
                GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                        // Specify the CLIENT_ID of the app that accesses the backend:
                        .setAudience(Collections.singletonList(CLIENT_ID))
                        .build();

                GoogleIdToken idToken = verifier.verify(res.id_token);
                if (idToken != null) {
                    Payload payload = idToken.getPayload();


            // Get profile information from payload
                String email = payload.getEmail();
                System.out.println(email);

                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");

                System.out.println(name + " : " + pictureUrl);

            /*
                Input new user information in database. 
                Once "registered", you can add U_ID (email) 
                to the hash table along with a randomly generated
                session key. 
            */

            } else {
                System.out.println("Invalid ID token.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

            return gson.toJson(new StructuredResponse("ok", "200", null));
            

        });
    


        //Code for Google API OAuth2.0 


        // Set up a route for serving the main page
        Spark.get("/", (req, res) -> {
            res.redirect("/index.html");
            return "";
        });

        // Spark.get("/user", (request, response) -> {

        //     db.oSelectOne(id)
        //     if (data == null) {
        //         return gson.toJson(new StructuredResponse("error", idx + " not found", null));
        //     } else {
        //         return gson.toJson(new StructuredResponse("ok", null, data));
        //     }
        // });



        // GET route that returns all message titles and Ids. All we do is get
        // the data, embed it in a StructuredResponse, turn it into JSON, and
        // return it. If there's no data, we return "[]", so there's no need
        // for error handling.
        Spark.get("/messages", (request, response) -> {
            // ensure status 200 OK, with a MIME type of JSON
            response.status(200);
            response.type("application/json");
            return gson.toJson(new StructuredResponse("ok", null, db.selectAll()));
        });

        // GET route that returns everything for a single row in the DataStore.
        // The ":m_id" suffix in the first parameter to get() becomes
        // request.params("m_id"), so that we can get the requested row ID. If
        // ":m_id" isn't a number, Spark will reply with a status 500 Internal
        // Server Error. Otherwise, we have an integer, and the only possible
        // error is that it doesn't correspond to a row with data.
        Spark.get("/messages/:m_id", (request, response) -> {
            int idx = Integer.parseInt(request.params("m_id"));
            // ensure status 200 OK, with a MIME type of JSON
            response.status(200);
            response.type("application/json");
            Database.RowData data = db.selectOne(idx);
            if (data == null) {
                return gson.toJson(new StructuredResponse("error", idx + " not found", null));
            } else {
                return gson.toJson(new StructuredResponse("ok", null, data));
            }
        });

        // POST route for adding a new element to the DataStore. This will read
        // JSON from the body of the request, turn it into a SimpleRequest
        // object, extract the title and message, insert them, and return the
        // ID of the newly created row.
        Spark.post("/messages", (request, response) -> {
            // NB: if gson.Json fails, Spark will reply with status 500 Internal
            // Server Error
            final String SESSION_KEY = request.queryParams("session_key");
            SimpleRequest req = gson.fromJson(request.body(), SimpleRequest.class);
            // ensure status 200 OK, with a MIME type of JSON
            // NB: even on error, we return 200, but with a JSON object that
            // describes the error.
            response.status(200);
            response.type("application/json");
            // NB: createEntry checks for null title and message
            int status = db.insertRow(req.mTitle, req.mMessage);
            if (status == 0) {
                return gson.toJson(new StructuredResponse("error", "error performing insertion", null));
            } else {
                return gson.toJson(new StructuredResponse("ok", "" + status, null));
            }
        });

        // Spark.post("/messages/:m_id/comments", (request, response) -> {
        //     SimpleRequest req = gson.fromJson(request.body(), SimpleRequest.class);
        //     int idx = Integer.parseInt(request.params("m_id"));
        //     response.status(200); 
        //     response.type("application/json");

        //     //Only pass the message body into the specific message id you want to make a comment for  
        //     int status = db.insertComment(idx, email, request.body());
        //     if (status == 0) { 
        //         return gson.toJson(new StructuredResponse("error", "error inserting comment...", null));
        //     } else {
        //         return gson.toJson(new StructuredResponse("ok", "" + status, null));
        //     }
        // });

        Spark.get("/messages/:m_id/comments/:c_id", (request, response) -> {
            int idx = Integer.parseInt(request.params("m_id"));
            int idc = Integer.parseInt(request.params("c_id"));
            // ensure status 200 OK, with a MIME type of JSON
            response.status(200);
            response.type("application/json");
            Database.RowData data = db.selectOne(idx);
            if (data == null) {
                return gson.toJson(new StructuredResponse("error", idx + " not found", null));
            } else {
                return gson.toJson(new StructuredResponse("ok", null, data));
            }
        });

        // PUT route for liking and disliking the post. This will read m_id from the url
        // and look for the id in database and increment like counts
        Spark.put("messages/:m_id/like", (request, response) -> {
            // parse the m_id attribute from the url
            int idx = Integer.parseInt(request.params("m_id"));
            // NB: if gson.Json fails, Spark will reply with status 500 Internal
            // Server Error
            // SimpleRequest req = gson.fromJson(request.body(), SimpleRequest.class);

            response.status(200);
            response.type("application/json");

            int status = db.updateOneLikes(idx); // if -1 indicates an error

            // check if the update is performed correctly
            if (status == -1) {
                return gson.toJson(new StructuredResponse("error", "error performing likes", null));
            } else {
                return gson.toJson(new StructuredResponse("ok", "" + status, null));
            }
        });

        // PUT route for liking and disliking the post. This will read m_id from the url
        // and look for the id in database and increment dislike counts
        Spark.put("messages/:m_id/dislike", (request, response) -> {
            // parse the m_id attribute from the url
            int idx = Integer.parseInt(request.params("m_id"));
            // NB: if gson.Json fails, Spark will reply with status 500 Internal
            // Server Error
            // SimpleRequest req = gson.fromJson(request.body(), SimpleRequest.class);

            response.status(200);
            response.type("application/json");

            int status = db.updateOneDislikes(idx); // if -1 indicates an error

            // check if the update is performed correctly
            if (status == -1) {
                return gson.toJson(new StructuredResponse("error", "error performing dislikes", null));
            } else {
                return gson.toJson(new StructuredResponse("ok", "" + status, null));
            }
        });

        // PUT route for updating a row in the DataStore. This is almost
        // exactly the same as POST. Takes the m_id from url and look for the index in
        // database
        Spark.put("/messages/:m_id", (request, response) -> {
            // If we can't get an ID or can't parse the JSON, Spark will send
            // a status 500
            int idx = Integer.parseInt(request.params("m_id"));
            SimpleRequest req = gson.fromJson(request.body(), SimpleRequest.class);
            // ensure status 200 OK, with a MIME type of JSON
            response.status(200);
            response.type("application/json");
            int result = db.updateOne(idx, req.mMessage);
            if (result == -1) {
                return gson.toJson(new StructuredResponse("error", "unable to update row " + idx, null));
            } else {
                return gson.toJson(new StructuredResponse("ok", "" + result, null));
            }
        });

        // DELETE route for removing a row from the DataStore
        Spark.delete("/messages/:m_id", (request, response) -> {
            // If we can't get an ID, Spark will send a status 500
            int idx = Integer.parseInt(request.params("m_id"));
            // ensure status 200 OK, with a MIME type of JSON
            response.status(200);
            response.type("application/json");
            // NB: we won't concern ourselves too much with the quality of the
            // message sent on a successful delete
            int result = db.deleteRow(idx);
            if (result == -1) {
                return gson.toJson(new StructuredResponse("error", "unable to delete row " + idx, null));
            } else {
                return gson.toJson(new StructuredResponse("ok", "" + result, null));
            }
        });

    }

    /**
     * Get an integer environment varible if it exists, and otherwise return the
     * default value.
     * 
     * @envar The name of the environment variable to get.
     * @defaultVal The integer value to use as the default if envar isn't found
     * 
     * @returns The best answer we could come up with for a value for envar
     */
    static int getIntFromEnv(String envar, int defaultVal) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get(envar) != null) {
            return Integer.parseInt(processBuilder.environment().get(envar));
        }
        return defaultVal;
    }
}
