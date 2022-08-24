import 'dart:convert';
import 'dart:html' as html;
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:exprimons_nous/loginview.dart';

class OptionVotes {
  String? label;
  int? idVote;
  int? nbChoice;

  OptionVotes({
    this.label,
    this.idVote,
    this.nbChoice,
  });

  factory OptionVotes.fromJson(Map<String, dynamic> json) {
    return OptionVotes(
        label: json['label'],
        idVote: json['idVote'],
        nbChoice: json['nbChoice']);
  }
}

Future addOptionVotes(List<OptionVotes> optionVotes,BuildContext context) async {
  Uri uri = Uri.parse("https://titan-photography.com/vote/newVoteOptions");

  var body = jsonEncode([
    for (int i = 0; i < optionVotes.length; i++)
      {
        "label": optionVotes[i].label,
        "idVote": optionVotes[i].idVote,
      }
  ]);

  final response = await http.post(uri,
      headers: {
        "Access-Control-Allow-Origin": "*",
        // Required for CORS support to work
        "Access-Control-Allow-Headers":
            "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS",
        'Content-Type': 'application/json; charset=UTF-8',
        "token": html.window.localStorage["token"]!
      },
      body: body);
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
