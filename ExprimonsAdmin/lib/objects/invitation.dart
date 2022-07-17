import 'dart:convert';

import '../Globals.dart';
import 'package:http/http.dart' as http;
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
Future addInvitation() async {
  Uri uri = Uri.parse("https://titan-photography.com/invite/create");
  print(uri);
  var body = jsonEncode({
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

