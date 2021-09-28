# README #

This README documents the set up of Admin.

### What is this repository for? ###

The admin is a cli application that company administration would use to perform database operations.
Basically, administrators can get access to the database of the website, make edit, and add new data tables.

### How do I get set up? ###

First, packaging: DATABASE_URL='postgres://pkdkdvttlfzyfu:6368fa21b4ffd5891b25a4700c6ee3e85350bec637fd33aabd57879c6b97efe1@ec2-3-225-204-194.compute-1.amazonaws.com:5432/d7oeuj2oslhi4l?sslmode=require' mvn package

// clean and package the backend code
% cd backend
% DATABASE_URL=`DATABASE_URL` mvn clean package

// run locally
% DATABASE_URL=`DATABASE_URL` mvn exec:java
### Contribution guidelines ###

Postgres database connected!
[TD1*-+~q?] :> T
[TD1*-+~q?] :> 1
Enter the row ID :> 1
[TD1*-+~q?] :> +
Enter the subject :> adding comment
Enter the message :> testing
1 rows added
[TD1*-+~q?] :> 1
Enter the row ID :> 1
  [1] null
  --> testing


### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact