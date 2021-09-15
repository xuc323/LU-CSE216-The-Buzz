package edu.lehigh.cse216.xuc323.backend;

// Import the Spark package, so that we can make use of the "get" function to 
// create an HTTP GET route
import spark.Spark;

/**
 * For now, our app creates an HTTP server with only one route.
 * 
 * When an HTTP client connects to this server on the default SPARK port (4567),
 * and requests /hello, we return "Hello World". Otherwise, we produce an error
 */
public class App {
    public static void main(String[] args) {
        Spark.get("/hello", (req, res) -> {
            return "Hello World!";
        });
    }
}