// Camera plugin to take pictures
// referenced "Take a picture using the camera" plugin page on flutter.dev

import 'dart:async';
import 'dart:io';

import 'package:camera/camera.dart';
import 'package:flutter/material.dart';
import 'package:path/path.dart';
import 'file.dart' as file;

Future<void> main() async {
  WidgetsFlutterBinding
      .ensureInitialized(); // make sure camera plugin is initialized

  final cameras =
      await availableCameras(); // get list of available cameras for device

  final firstCamera = cameras.first; // get specific camera from list

  runApp(
    MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: TakePictureScreen(
        camera:
            firstCamera, // pass the correct camera to TakePictureScreen widget
      ),
    ),
  );
}

// screen that allows users to take a picture using the camera
class TakePictureScreen extends StatefulWidget {
  const TakePictureScreen({
    Key? key,
    required this.camera,
  }) : super(key: key);

  final CameraDescription camera;

  @override
  TakePictureScreenState createState() => TakePictureScreenState();
}

class TakePictureScreenState extends State<TakePictureScreen> {
  late CameraController _controller;
  late Future<void> _initializeControllerFuture;

  @override
  void initState() {
    super.initState();
    _controller = CameraController(
      // dispay the current output from camera (preview)
      widget.camera, // get a specific camera from the list
      ResolutionPreset.medium, // set the resolution
    );

    _initializeControllerFuture =
        _controller.initialize(); // initialize controller and return a Future
  }

  // dispose of the controller when the widget is disposed
  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Take a picture')),
      body: FutureBuilder<void>(
        // display loading spinner until controller is initialized
        future: _initializeControllerFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.done) {
            // future complete, display camera preview
            return CameraPreview(_controller);
          } else {
            return const Center(
                child:
                    CircularProgressIndicator()); // else display loading spinner
          }
        },
      ),
      floatingActionButton: FloatingActionButton(
        // make a button
        onPressed: () {
          file.upload(imageFile);
        },
        //() async {
        //   try {
        //     // try taking a picture
        //     await _initializeControllerFuture; // make sure the camera is initialized

        //     final image = await _controller
        //         .takePicture(); // try to take a picture and get the file where it was saved

        //     await Navigator.of(context).push(
        //       // if picture was taken, display on new screen
        //       MaterialPageRoute(
        //         builder: (context) => DisplayPictureScreen(
        //           // pass the generated path to DisplayPictureScreen widget
        //           imagePath: image.path,
        //         ),
        //       ),
        //     );
        //   } catch (e) {
        //     print(e); // if error, print log to console
        //   }
        // },
        child: const Icon(Icons.camera_alt),
      ),
    );
  }
}

// widget that displays the picture
class DisplayPictureScreen extends StatelessWidget {
  final String imagePath;

  const DisplayPictureScreen({Key? key, required this.imagePath})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Display the Picture')),
      body: Image.file(File(imagePath)), // image is stored as a file on device
    );
  }
}

  // attempt to update the the UI to display the files attached to messages and comments using cache
  /*
  var file = await FirebaseCacheManager().getSingleFile(url); //

  class DefaultCacheManager {
    getSingleFile(Context url) {

      }
  }
  
  Stream<FileResponse> getImageFile(String url, {
    String? key,
    Map<String, String>? headers,
    bool? withProgress,
    int? maxHeight,
    int? maxWidth,
})
*/