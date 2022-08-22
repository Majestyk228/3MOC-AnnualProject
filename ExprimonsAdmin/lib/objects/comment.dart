import 'dart:convert';
import 'dart:html' as html;
import 'package:exprimons_nous/loginview.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';

class Comments {
  int? idComment;
  String? body;//
  int? likes;//
  int? dislikes;//
  int? reports;//
  int? anonymous;
  int? idPost;
  int? idUser;
  String? date;

  Comments({
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

  factory Comments.fromJson(Map<String, dynamic> json) {
    return Comments(
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
Future deleteComment(idComment,BuildContext context) async {
  Uri uri =
  Uri.parse("https://titan-photography.com/comment/delete/$idComment");

  final response = await http.delete(uri, headers: {
    "Access-Control-Allow-Origin": "*",
    // Required for CORS support to work
    "Access-Control-Allow-Headers":
    "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
    "Access-Control-Allow-Methods": "POST, OPTIONS",
    'Content-Type': 'application/json; charset=UTF-8',
    "token":html.window.localStorage["token"]!
  });

  if(response.statusCode>=200 && response.statusCode<=299){

  }
  else if(response.statusCode==406){
    html.window.localStorage.clear();
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => Login(),
      ),
    );
  }
  else{
    //TODO Dialog of error
  }

}
