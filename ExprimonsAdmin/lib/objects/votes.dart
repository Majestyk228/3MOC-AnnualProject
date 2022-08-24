import 'dart:convert';
import 'package:exprimons_nous/loginview.dart';
import 'package:flutter/material.dart';
import 'dart:html' as html;
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
Future<int> addVotes(String Title,String Body,String nbChoice,bool important,BuildContext context) async {
  Uri uri = Uri.parse("https://titan-photography.com/vote/create");

  var body = jsonEncode({
    "title": Title,
    "body": Body,
    "nbChoice": int.parse(nbChoice),
    "important": important,
    "idAdmin": html.window.localStorage["idAdmin"]!,
    "idCommunity": html.window.localStorage["idCommunity"]!
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
  if(response.statusCode>=200 && response.statusCode<=299){
    var data = json.decode(response.body);

    return data['idVote'];
  }
  else if(response.statusCode==406){
    html.window.localStorage.clear();
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => Login(),
      ),
    );
    return 0;
  }
  else{
    showDialog<String>(
        context: context,
        builder: (BuildContext context) => AlertDialog(
          title: Text('Erreur'),
          content: const Text(
              'Une erreur est survenue veuillez réessayer ultérieurement'),
          actions: <Widget>[
            TextButton(
              onPressed: () async {
                Navigator.pop(context, 'OK');
              },
              child: const Text('Ok'),
            ),
          ],
        ));
    return 0;
  }


}

Future deleteVotes(int idVote,BuildContext context) async {
  Uri uri = Uri.parse("https://titan-photography.com/vote/delete/${idVote}");



  final response = await http.delete(uri,
      headers: {
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
    showDialog<String>(
        context: context,
        builder: (BuildContext context) => AlertDialog(
          title: Text('Erreur'),
          content: const Text(
              'Une erreur est survenue veuillez réessayer ultérieurement'),
          actions: <Widget>[
            TextButton(
              onPressed: () async {
                Navigator.pop(context, 'OK');
              },
              child: const Text('Ok'),
            ),
          ],
        ));
  }
}
