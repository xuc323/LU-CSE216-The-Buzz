# Android

A new Flutter project.

## Getting Started

This project is a starting point for a Flutter application.

A few resources to get you started if this is your first Flutter project:

- [Lab: Write your first Flutter app](https://flutter.dev/docs/get-started/codelab)
- [Cookbook: Useful Flutter samples](https://flutter.dev/docs/cookbook)

For help getting started with Flutter, view our
[online documentation](https://flutter.dev/docs), which offers tutorials,
samples, guidance on mobile development, and a full API reference.

Top examples are EXTREMELY beneficial to understanding Flutter. 


Here are some additional resources to help you get started in coding Dart (Flutter). 
- [Convert JSON to an Object class](https://app.quicktype.io)
- [Example JSON to practice parsing](https://jsonplaceholder.typicode.com/posts)
- [Postman: EXTREMELY USEFUL IN API OPERATIONS!(Download and post link to Heroku App)](https://www.postman.com)

Now to begin... 

Flutter is very interesting because it uses UI functions, APIs, and Formatting in a much more fluid and "simple" way than Android Studio. 

Simple is very subjective, but Flutter does have a LOT of helpful documentation on the web. Plus, it is newer, so there are a bunch of intensely useful features. 
    - Hot Reload: When you press "Command+S" (Save your file), or press the Lighting icon when you run your application, changes made to your program files with AUTOMATICALLY be reflected on your visual app. Use this feature in earnest, it will defintely be your saving grace when short on time. 

    - Automated Debugging: Flutter gives you every sort of guidance imaginable to get your program to run properly. For example, if you don't initialize your variables properly, then Flutter will send you a helpful notice to do a "Quick Fix" and resolve the issues immediately. 


There is no pom.xml file in your Flutter repository, but you do have an alternative for dependencies: pubspec.yaml 

pubspec.yaml is very useful in applying your "http" packages, adding additional fonts, icons, posting links to images to use in your program, etc.. 

Now, let's talk about my particular Flutter repository.. 

4 Parts to my Flutter Program: 
- api.dart: Connects to the Heroku Web App 
- homepage.dart: The entire UI design for the app 
- main.dart: Running the "main file" called MaterialApp()
- messages.dart: The object file to support parsing our JSON


I won't get into the nitty-gritty talking about UI implementations, but it would be helpful to see how I connected to the Heroku app through Flutter. 

```dart
class API_Manager {
  Future<Message> getNews() async { //Futures return a value in the probable future. 
    var client = http.Client();
    var messageModel;

    /*
      When calling a "Future" function, you need to add an async and an await call
      which allows you to wait for a particular element to be returned
    */

    try {
      var response = await client 
      //"awaiting" a response from the client, a "future" response
          .get(Uri.parse('https://the-buzz-group25.herokuapp.com/messages'));

      var jsonString = response.body; 
      //Stores the entire unparsed JSON into the body of response

      return MessagefromJson(jsonString);
      //This MessagefromJson function is called in the messages.dart, it is automatically created when you make the object file through the app.quicktype.io website I referenced above 

    } catch (Exception) {
      return messageModel;
    }
  }
}
```

Phase 2




