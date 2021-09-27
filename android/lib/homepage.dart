import 'package:flutter/material.dart';
import 'package:startup_namer/api.dart';
import 'package:startup_namer/messages.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  late Future<Message> _message;

  @override
  void initState() {
    _message = API_Manager().getNews();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Buzz Blog App',
            style: TextStyle(
                fontSize: 30,
                fontStyle: FontStyle.italic,
                fontWeight: FontWeight.w700)),
        backgroundColor: Colors.purple[900],
      ),
      body: Container(
        child: FutureBuilder<Message>(
          future: _message,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return ListView.builder(
                  itemCount: snapshot.data!.mData.length,
                  itemBuilder: (context, index) {
                    var article = snapshot.data!.mData[index];
                    return Container(
                      height: 100,
                      margin: const EdgeInsets.all(8),
                      child: Row(
                        children: <Widget>[
                          SizedBox(width: 16),
                          Flexible(
                            child: Column(
                              children: <Widget>[
                                Text(article.mTitle),
                                Text(
                                  article.mMessage,
                                  overflow: TextOverflow.ellipsis,
                                  style: TextStyle(
                                      fontSize: 20,
                                      fontWeight: FontWeight.bold),
                                ),
                              ],
                            ),
                          ),
                        ],
                      ),
                    );
                  });
            } else
              return Center(child: CircularProgressIndicator());
          },
        ),
      ),
    );
  }
}
