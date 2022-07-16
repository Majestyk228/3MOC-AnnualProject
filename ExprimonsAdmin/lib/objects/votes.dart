import 'dart:convert';

import 'package:exprimons_nous/Globals.dart';
import 'package:http/http.dart' as http;
class Vote {
  int? idVote;
  String? title;
  String? body;
  int? nbChoice;
  int? important;
  int? idUser;
  int? idAdmin;
  String? voteBegins;
  String? voteEnds;
  int? idCommunity;

  Vote(
      {this.idVote,
      this.title,
      this.body,
      this.nbChoice,
      this.important,
      this.idUser,
      this.idAdmin,
      this.voteBegins,
      this.voteEnds,
      this.idCommunity});

  factory Vote.fromJson(Map<String, dynamic> json) {
    return Vote(
      idVote: json['idVote'],
      title: json['title'],
      body: json['body'],
      nbChoice: json['nbChoice'],
      important: json['important'],
      idUser: json['idUser'],
      idAdmin: json['idAdmin'],
      voteBegins: json['voteBegins'],
      voteEnds: json['voteEnds'],
      idCommunity: json['idCommunity'],
    );
  }

}
Future addVotes(String Title,String Body,String nbChoice,bool important) async {
  Uri uri = Uri.parse("https://titan-photography.com/vote/create");
  print(uri);
  var body = jsonEncode({
    "title": Title,
    "body": Body,
    "nbChoice": int.parse(nbChoice),
    "important": important,
    "idAdmin": currentAdmin.idAdmin,
    "idCommunity": currentAdmin.idCommunity
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
