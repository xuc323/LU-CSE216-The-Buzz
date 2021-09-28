import 'package:english_words/english_words.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'dart:convert';

void main() {
  runApp(BuzzApp());
}

// #docregion MyApp
class BuzzApp extends StatefulWidget {
  @override
  _BuzzAppState createState() => _BuzzAppState();
}

class _BuzzAppState extends State<BuzzApp> {
  final url = "https://jsonplaceholder.typicode.com/posts";
  List<String> _postsJson = [];

  void fetchPosts() async {
    try {
      final response =
          await get(Uri.parse("https://jsonplaceholder.typicode.com/posts"));
      final List<String> jsonData = jsonDecode(response.body);

      setState(() {
        _postsJson = jsonData;
      });
    } catch (e) {}
  }

  @override
  void initState() {
    super.initState();
    fetchPosts();
  }

  Widget _buildList() {
    return ListView.builder(
        padding: const EdgeInsets.all(16.0),
        itemBuilder: /*1*/ (context, i) {
          List<String> post = _postsJson;
          if (i.isOdd) return const Divider(); /*2*/

          return _buildRow(post);
        });
  }

  Widget _buildRow(List<String> text) {
    final alreadySaved = _postsJson.contains(text);
    return ListTile(
      title: Text(text[0],
          style: const TextStyle(fontSize: 18.0, fontWeight: FontWeight.w800)),
      trailing: Icon(
          alreadySaved ? Icons.thumb_up : Icons.thumb_up_off_alt_outlined,
          color: alreadySaved ? Colors.blue[800] : null),
    );
  }

  Widget build(BuildContext context) {
    String stringy = 'Buzz Messaging App';
    return Scaffold(
        appBar: AppBar(
          title: Text(stringy,
              style: TextStyle(
                  fontWeight: FontWeight.bold, fontStyle: FontStyle.italic)),
          backgroundColor: Colors.purple[900],
          actions: <Widget>[
            IconButton(
                icon: Icon(Icons.list),
                onPressed: () {
                  print("Hello World");
                })
          ],
        ),
        body: _buildList());
  }
}









/*




  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Buzz Messaging App',
      theme: ThemeData(
        primaryColor: Colors.white,
      ),
      home: MessageList(),
    );
  }
  // #enddocregion build
}
// #enddocregion MyApp

class Message {
  final int userId;
  final int id;
  final String title;

  const Message({
    required this.userId,
    required this.id,
    required this.title,
  });

//This function essentially deserializes the JSON from the HTTP get request
  factory Message.fromJson(Map<String, dynamic> json) {
    return Message(
      userId: json['userId'],
      id: json['id'],
      title: json['title'],
    );
  }
}

// #docregion RWS-var
class _MessageListState extends State<MessageList> {
  final listSavedMessages = <Message>[];
  final listMessages = <Message>{};

  final _biggerFont = const TextStyle(fontSize: 18.0);
  // #enddocregion RWS-var

  // #docregion _buildSuggestions
  Widget _buildSuggestions() {
    return ListView.builder(
        padding: const EdgeInsets.all(16.0),
        itemBuilder: /*1*/ (context, i) {
          if (i.isOdd) return const Divider(); /*2*/

          final index = i ~/ 2; /*3*/
          if (index >= _suggestions.length) {
            _suggestions.addAll(generateWordPairs().take(10)); /*4*/
          }
          return _buildRow(_suggestions[index]);
        });
  }

  // #enddocregion _buildSuggestions

  // #docregion _buildRow
  Widget _buildRow(WordPair pair) {
    final alreadySaved = _saved.contains(pair);
    return ListTile(
      title: Text(
        pair.asPascalCase,
        style: _biggerFont,
      ),
      trailing: Icon(
        alreadySaved ? Icons.favorite : Icons.favorite_border,
        color: alreadySaved ? Colors.red : null,
        semanticLabel: alreadySaved ? 'Remove from saved' : 'Save',
      ),
      onTap: () {
        setState(() {
          if (alreadySaved) {
            _saved.remove(pair);
          } else {
            _saved.add(pair);
          }
        });
      },
    );
  }
  // #enddocregion _buildRow

  // #docregion RWS-build
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.purple[900],
        title: const Text(
          'Buzz',
          style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.list),
            onPressed: _pushSaved,
            tooltip: 'Liked Messages',
          ),
        ],
      ),
      body: _buildSuggestions(),
    );
  }
  // #enddocregion RWS-build

  void _pushSaved() {
    Navigator.of(context).push(
      MaterialPageRoute<void>(
        // NEW lines from here...
        builder: (context) {
          final tiles = _saved.map(
            (pair) {
              return ListTile(
                title: Text(
                  pair.asPascalCase,
                  style: _biggerFont,
                ),
              );
            },
          );
          final divided = tiles.isNotEmpty
              ? ListTile.divideTiles(
                  context: context,
                  tiles: tiles,
                ).toList()
              : <Widget>[];

          return Scaffold(
            appBar: AppBar(
              backgroundColor: Colors.purple[900],
              title: const Text('Saved Messages',
                  style: TextStyle(
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                  )),
            ),
            body: ListView(children: divided),
          );
        }, //...to here.
      ),
    );
  }
  // #docregion RWS-var
}
// #enddocregion RWS-var

class MessageList extends StatefulWidget {
  @override
  State<MessageList> createState() => _MessageListState();
}
*/