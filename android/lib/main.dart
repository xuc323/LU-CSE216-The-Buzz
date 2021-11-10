import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:startup_namer/SignInHomepage.dart';
import 'package:firebase_core/firebase_core.dart';
import 'google_sign_in.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();

  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) => ChangeNotifierProvider(
        create: (context) => GoogleSignInProvider(),
        child: MaterialApp(
          debugShowCheckedModeBanner: false,
          title: 'Sign Up Page',
          theme: ThemeData(
            primarySwatch: Colors.blue,
          ),
          home: SignInHomePage(),
        ),
      );
  // tried to implement local cache for images
  /*
  CachedNetworkImage(
      imageUrl: "http://via.placeholder.com/350x150",
      placeholder: (context, url) => CircularProgressIndicator(),
      errorWidget: (context, url, error) => Icon(Icons.error),
      );
  */
}
