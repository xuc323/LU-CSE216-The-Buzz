import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:startup_namer/messages.dart';

// ignore: camel_case_types
class API_Manager {
  Future<Message> getNews() async {
    var client = http.Client();
    var messageModel;

    try {
      var response = await client
          .get(Uri.parse('https://the-buzz-group25.herokuapp.com/messages'));
      var jsonString = response.body;
      //var jsonMap = json.decode(jsonString);
      //messageModel = Message.fromJson(jsonMap);
      return MessagefromJson(jsonString);
    } catch (Exception) {
      return messageModel;
    }
  }
}
