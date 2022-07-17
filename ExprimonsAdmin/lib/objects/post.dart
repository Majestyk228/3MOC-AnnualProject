import 'dart:convert';
import 'package:http/http.dart' as http;
import '../Globals.dart';

class Post {
  int? idPost;
  String? title;
  String? body;
  String? date;
  String? time;
  int? likes;
  int? dislikes;
  int? idCommunity;
  int? idUser;
  int? idAdmin;
  int? reported;

  Post({
    this.idPost,
    this.title,
    this.body,
    this.date,
    this.time,
    this.likes,
    this.dislikes,
    this.idCommunity,
    this.idUser,
    this.idAdmin,
    this.reported,
  });

  factory Post.fromJson(Map<String, dynamic> json) {
    return Post(
      idPost: json['idPost'],
      title: json['title'],
      body: json['body'],
      date: json['date'],
      time: json['time'],
      likes: json['likes'],
      dislikes: json['dislikes'],
      idCommunity: json['idCommunity'],
      idUser: json['idUser'],
      idAdmin: json['idAdmin'],
      reported: json['reported'],
    );
  }


}
Future addPost(String Title,String Body) async {
  Uri uri = Uri.parse("https://titan-photography.com/post/create");

  var body = jsonEncode({
    "title": Title,
    "body": Body,
    "idCommunity": currentAdmin.idCommunity,
    "idUser": null,
    "idAdmin": currentAdmin.idAdmin
  });
  print(body);
  final response = await http.post(uri,
      headers: {
        "Access-Control-Allow-Origin": "*",
        // Required for CORS support to work
        "Access-Control-Allow-Headers":
        "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS",
        'Content-Type': 'application/json; charset=UTF-8'
      },
      body: body);
}
