import 'dart:convert';
import 'package:exprimons_nous/Globals.dart';
import 'package:http/http.dart' as http;

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
      nbChoice: json['nbChoice']
    );
  }
}

Future addOptionVotes(List<OptionVotes> optionVotes) async {
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
        "token":currentAdmin.token!
      },
      body: body);
}
