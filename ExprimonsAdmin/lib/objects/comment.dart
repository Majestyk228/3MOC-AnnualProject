import 'dart:convert';
import 'package:exprimons_nous/Globals.dart';
import 'package:http/http.dart' as http;

class Comment {
  int? idComment;
  String? body;//
  int? likes;//
  int? dislikes;//
  int? reports;//
  int? anonymous;
  int? idPost;
  int? idUser;
  String? date;

  Comment({
    this.idComment,
    this.body,
    this.likes,
    this.dislikes,
    this.reports,
    this.anonymous,
    this.idPost,
    this.idUser,
    this.date,
  });

  factory Comment.fromJson(Map<String, dynamic> json) {
    return Comment(
      idComment: json['idComment'],
      body: json['body'],
      likes: json['likes'],
      dislikes: json['dislikes'],
      reports: json['reports'],
      anonymous: json['anonymous'],
      idPost: json['idPost'],
      idUser: json['idUser'],
      date: json['date'],
    );
  }
}
Future deleteComment(idComment) async {
  Uri uri =
  Uri.parse("https://titan-photography.com/comment/delete/$idComment");

  final response = await http.delete(uri, headers: {
    "Access-Control-Allow-Origin": "*",
    // Required for CORS support to work
    "Access-Control-Allow-Headers":
    "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
    "Access-Control-Allow-Methods": "POST, OPTIONS",
    'Content-Type': 'application/json; charset=UTF-8',
    "token":currentAdmin.token!
  });
}
