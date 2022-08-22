import 'dart:convert';

import 'dart:html' as html;
import 'package:exprimons_nous/loginview.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';

class Invitation {
  int? code;
  int? idCommunity;
  String? creationDate;
  String? endDate;

  Invitation({
    this.code,
    this.idCommunity,
    this.creationDate,
    this.endDate,
  });

  factory Invitation.fromJson(Map<String, dynamic> json) {
    return Invitation(
      code: json['code'],
      idCommunity: json['idCommunity'],
      creationDate: json['creationDate'],
      endDate: json['endDate'],
    );
  }
}

Future addInvitation(BuildContext context) async {
  Uri uri = Uri.parse("https://titan-photography.com/invite/create");

  var body =
      jsonEncode({"idCommunity": html.window.localStorage["idCommunity"]});

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

}

Future deleteInvitation(invitationCode,BuildContext context) async {
  Uri uri =
      Uri.parse("https://titan-photography.com/invite/delete/$invitationCode");

  final response = await http.delete(uri, headers: {
    "Access-Control-Allow-Origin": "*",
    // Required for CORS support to work
    "Access-Control-Allow-Headers":
        "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
    "Access-Control-Allow-Methods": "POST, OPTIONS",
    'Content-Type': 'application/json; charset=UTF-8',
    "token": html.window.localStorage["token"]!
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
