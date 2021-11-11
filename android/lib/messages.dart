// To parse this JSON data, do
//
//     final welcome = welcomeFromJson(jsonString);

import 'dart:convert';
//import 'package:image_picker/image_picker.dart';

Message MessagefromJson(String str) => Message.fromJson(json.decode(str));

String MessagetoJson(Message data) => json.encode(data.toJson());

class Message {
  Message({
    required this.mStatus,
    required this.mData,
  });

  String mStatus;
  List<MDatum> mData;

  factory Message.fromJson(Map<String, dynamic> json) => Message(
        mStatus: json["mStatus"],
        mData: List<MDatum>.from(json["mData"].map((x) => MDatum.fromJson(x))),
        // pick media or take picture to attach to message
        //final ImagePicker _picker = ImagePicker();
        //final camera? image = await _picker.pickImage(source: ImageSource.gallery);
        //final camera? photo = await _picker.pickImage(source: ImageSource.camera);
        //final camera? image = await _picker.pickVideo(source: ImageSource.gallery);
        //final camera? video = await _picker.pickVideo(source: ImageSource.camera);
        //final List<camera>? images = await _picker.pickMultiImage();
        //getFileStream(url); // to get image
      );

  Map<String, dynamic> toJson() => {
        "mStatus": mStatus,
        "mData": List<dynamic>.from(mData.map((x) => x.toJson())),
      };
}

class MDatum {
  MDatum({
    required this.mId,
    required this.mTitle,
    required this.mMessage,
    required this.mLikes,
    required this.mDislikes,
    required this.mDate,
  });

  int mId;
  String mTitle;
  String mMessage;
  int mLikes;
  int mDislikes;
  String mDate;

  factory MDatum.fromJson(Map<String, dynamic> json) => MDatum(
        mId: json["mId"],
        mTitle: json["mTitle"],
        mMessage: json["mMessage"],
        mLikes: json["mLikes"],
        mDislikes: json["mDislikes"],
        mDate: json["mDate"],
      );

  String? get urlToImage => null;

  String? get description => null;

  Map<String, dynamic> toJson() => {
        "mId": mId,
        "mTitle": mTitle,
        "mMessage": mMessage,
        "mLikes": mLikes,
        "mDislikes": mDislikes,
        "mDate": mDate,
      };
}