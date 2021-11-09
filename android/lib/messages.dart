// To parse this JSON data, do
//
//     final welcome = welcomeFromJson(jsonString);

import 'dart:convert';

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
