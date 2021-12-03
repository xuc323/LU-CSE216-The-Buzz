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

Simple is very subjective, but Flutter does have a LOT of helpful documentation on the web. Plus, it is newer, so there are a bunch of intensely useful features. - Hot Reload: When you press "Command+S" (Save your file), or press the Lighting icon when you run your application, changes made to your program files with AUTOMATICALLY be reflected on your visual app. Use this feature in earnest, it will defintely be your saving grace when short on time.

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

## Phase 2

In Phase 2, we are implementing the Google OAuth system into our software. I did it by using Firebase, which is like a backend that receive our user's information, and get the login token from Firebase, goes back to our software, by successfully log in to google, It shows the messages that we got.

Since there's not much information on coursesite or piazza, I recommended the rest of people who will do this part to learn more about it by looking up tutorials on Youtube. There's a lot of content creators who are flutter engineers and giving us tutorials step by step. Very useful, and I basically did what I need to do by following the tutorials.

Here are some links that might be helpful to get to know about how to set up firebase and google oauth.

https://youtu.be/1k-gITZA9CI -- I followed his steps about how to do these

https://firebase.google.com/docs/flutter/setup?platform=ios firebase setup

Also, there are some dependencies that you need to add in the pubspec.yaml. If you are using vscode, just add them into the pubspec.yaml and it automatically runs flutter get, which will give you the dependencies you need. In this phase, I added these dependencies(phase 1's dependencies not included).
firebase_auth: ^1.1.4
google_sign_in: ^5.0.3
font_awesome_flutter: ^9.0.0
provider: ^5.0.0

## Phase 3

In Phase 3, we are tasked with implementing the Camera and Gallery APIs to take pictures then attaching them to messages/comments, implementing a local cache, and updating the UI to display these files. A great resource in learning more about how to implement these tasks was https://flutter.dev/ as it provided examples and explanations for creating a local cache specifically for firebase and using the camera API.

The dependencies that I added to the pubspec.yaml were:

Dependencies for camera plugin

```
camera:
path_provider:
path:
image_picker:
```

Dependency for local storage

```
cached_network_image: ^3.1.0+1
```

Dependency for local cache for images

```flutter_cache_manager_firebase: ^2.0.1

```

# Developer's Manual

**Git Repo:** https://bitbucket.org/chm321/cse216_group25/src/master/

**Branches:**

- master: the main branch
- admin: the branch for admin role
- android: the branch for admin role
- backend: the branch for admin role
- web: the branch for admin role
- all_files: the working branch (master branch might contain corrupted files because of merging issues)
- phase1: all files up to phase 1 for grading purposes
- phase2: all files up to phase 2 for grading purposes
- phase_3: all files up to phase 3 for grading purposes

**Documentation:**

[Flutter](https://docs.flutter.dev)

Each `README.md` will contain basic documentations for that role. `backend` should have the most because it describes all APIs.

## Configuration

**OS:** MacOS, Windows

**Software:** VSCode

**Environment Variables:** Git, NPM, MVN, Flutter, sh

## Known bugs, limitations

Backend would need to incorporate the user sign-in feature in order for the app to work properly.
