# PM

This README documents the deployment of Heroku, "The Buzz" UI/how to use it, and the APIs.

### Documentation of Deploying Heroku

Heroku allows our team to launch our app on the Heroku server where the web interface is hosted by Heroku and the Android app can reach it.

1. Install the Heroku CLI and create an account.
2. In the Heroku dashboard, I created our app and named it "the-buzz-group25".
3. Use the terminal command "heroku login", enter credentials, and type the command "heroku apps" and you can see our app.
4. To add the Maven Heroku Plugin, I added the following dependencies to the pom.xml file in the admin branch:

```
    <dependency>
      <groupId>com.heroku.sdk</groupId>
      <artifactId>heroku-maven-plugin</artifactId>
      <version>3.0.4</version>
    </dependency>
```

5. With Heroku as a dependency and the .jar file is running, we want Heroku to use it so I added the following code as plugins:

```
<plugin>
  <groupId>com.heroku.sdk</groupId>
  <artifactId>heroku-maven-plugin</artifactId>
  <version>3.0.4</version>
  <configuration>
    <jdkVersion>1.8</jdkVersion>
    <appName>the-buzz-group25</appName>
    <processTypes>
      <web>java -jarjava -jar ./target/backend-1.0-SNAPSHOT-jar-with-dependencies.jar ./target/backend-1.0-SNAPSHOT.jar</web>
    </processTypes>
  </configuration>
</plugin>
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
        <archive>
            <manifest>
                <mainClass>edu.lehigh.cse216.sts223.backend.App</mainClass>
            </manifest>
        </archive>
    </configuration>
</plugin>
```

6. Also changed the web tag in the pom.xml to refer to:

```
java -jar ./target/backend-1.0-SNAPSHOT-jar-with-dependencies.jar
```

7. In the Heroku dashboard, I added on the "Heroku Postgres" and stayed on the "Hobby Dev" tier to start the database.
8. In the settings of Heroku, I went to "Reveal Config Vars" to get the database url and inserted the following lines into the main method of App.java:

```
String db_url = env.get("DATABASE_URL from Heroku");
// Give the Database object a connection, fail if we cannot get one
try {
    Class.forName("org.postgresql.Driver");
    URI dbUri = new URI(db_url);
    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
    Connection conn = DriverManager.getConnection(dbUrl, username, password);
    if (conn == null) {
        System.err.println("Error: DriverManager.getConnection() returned a null object");
        return null;
    }
    db.mConnection = conn;
} catch (SQLException e) {
    System.err.println("Error: DriverManager.getConnection() threw a SQLException");
    e.printStackTrace();
    return null;
} catch (ClassNotFoundException cnfe) {
    System.out.println("Unable to find postgresql driver");
    return null;
} catch (URISyntaxException s) {
    System.out.println("URI Syntax Error");
    return null;
}
```

9. Finally, I ran mvn package; mvn heroku:deploy which allowed developers to connect to it.

### Documentation of "The Buzz" UI

Image of our plan/sketch of our finite state machine is included in the "Downloads" section of the repository. The FSM concerns the actions associated with user voting (down-vote/up-vote/no interaction).

### Information on the use of "The Buzz" & APIs

Included in each branch's README.md file

### Phase 3 Documentation

> Strategy for updating the database schema:

- Right now we have three tables that stores **comments**, **ratings**, and **users**. Some of the data points are referenced by ids from other tables, so updating the schema will need to take that into consideration. The best way to update the schema is to create an updated table first, then compare the attributes and copy over the data to the matched attributes. Then we will be fine to delete the old table.

> For phase 4:

- Our group will need to choose option #1 because we still need to eliminate more technical debt. As for the new API, we will likely use Google Analytics or Google Translate. The first option will give us more information on how the website runs. If there's crashes or so. The second option will make our website more accessible for non-English speakers.
