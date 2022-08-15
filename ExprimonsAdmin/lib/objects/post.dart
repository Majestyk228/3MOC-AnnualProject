import 'dart:convert';
import 'package:http/http.dart' as http;
import 'dart:html' as html;

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
    this.title,//
    this.body,//
    this.date,//
    this.time,//
    this.likes,//
    this.dislikes,//
    this.idCommunity,
    this.idUser,
    this.idAdmin,
    this.reported,//
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
    "idCommunity": html.window.localStorage["idCommunity"]!,
    "idUser": null,
    "idAdmin": html.window.localStorage["idCommunity"]!
  });

  final response = await http.post(uri,
      headers: {
        "Access-Control-Allow-Origin": "*",
        // Required for CORS support to work
        "Access-Control-Allow-Headers":
        "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS",
        'Content-Type': 'application/json; charset=UTF-8',
        "token":html.window.localStorage["token"]!
      },
      body: body);
}

Future updatePost(int idPost,String Title,String Body) async {
  Uri uri = Uri.parse("https://titan-photography.com/post/updatePost");

  var body = jsonEncode({
    "idPost":idPost,
    "title": Title,
    "body": Body

  });

  final response = await http.put(uri,
      headers: {
        "Access-Control-Allow-Origin": "*",
        // Required for CORS support to work
        "Access-Control-Allow-Headers":
        "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS",
        'Content-Type': 'application/json; charset=UTF-8',
        "token":html.window.localStorage["token"]!
      },
      body: body);
}

Future deletePost(int idPost) async{
  Uri uri = Uri.parse("https://titan-photography.com/post/delete/${idPost}");



  final response = await http.delete(uri,
      headers: {
        "Access-Control-Allow-Origin": "*",
        // Required for CORS support to work
        "Access-Control-Allow-Headers":
        "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS",
        'Content-Type': 'application/json; charset=UTF-8',
        "token":html.window.localStorage["token"]!
      },
      );
}