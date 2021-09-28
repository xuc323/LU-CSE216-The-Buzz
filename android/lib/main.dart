import 'package:flutter/material.dart';
import './buzz.dart';
import 'package:english_words/english_words.dart';
import 'dart:convert';

void main() => runApp(BuzzApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        theme: ThemeData(primaryColor: Colors.purple[900]), home: BuzzApp());
  }
}
