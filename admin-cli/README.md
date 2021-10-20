# README #

This README documents the set up of Admin.

### What is this repository for? ###

The admin is a cli application that company administration would use to perform database operations.
Basically, administrators can get access to the database of the website, make edit, and add new data tables.

### How do I get set up? ###

First, packaging: DATABASE_URL= /* your_Url */  mvn package
Our url is postgres://pkdkdvttlfzyfu:6368fa21b4ffd5891b25a4700c6ee3e85350bec637fd33aabd57879c6b97efe1@ec2-3-225-204-194.compute-1.amazonaws.com:5432/d7oeuj2oslhi4l?sslmode=require
Test locally:
% DATABASE_URL=/* your_Url */ mvn exec:java
### Contribution guidelines ###
After running the codes, this is what it cames 
Postgres database connected!
[TD1*-+~q?] :> 
static void menu() {
        System.out.println("Main Menu");
        System.out.println("  [T] Create table");
        System.out.println("  [D] Drop table");
        System.out.println("  [1] Query for a specific row");
        System.out.println("  [*] Query for all rows");
        System.out.println("  [-] Delete a row");
        System.out.println("  [+] Insert a new row");
        System.out.println("  [~] Update a row");
        System.out.println("  [q] Quit Program");
        System.out.println("  [?] Help (this message)");
    }
    Those symbols are the meaning of each action admin want to perform.

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact