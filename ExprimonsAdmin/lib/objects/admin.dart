import 'dart:convert';

import 'package:http/http.dart' as http;
import 'dart:html' as html;

class Admin {
  int? idAdmin;
  int? idCommunity;
  String? token;

  Admin({this.idAdmin, this.idCommunity, this.token});

  factory Admin.fromJson(Map<String, dynamic> json) {
    return Admin(
        idAdmin: json['idAdmin'],
        idCommunity: json['idCommunity'],
        token: json['token']);
  }
}

Future logAdmin(String email, String password) async {
  Uri uri = Uri.parse("https://titan-photography.com/admin/loginSecure");
  var body = jsonEncode({"email": email, "password": password});

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

  var data = json.decode(response.body);

  if (data.length != 0) {
    html.window.localStorage["idAdmin"] = "${data['idAdmin']}";
    html.window.localStorage["idCommunity"] = "${data['idCommunity']}";
    html.window.localStorage["token"] = "${data['token']}";


  }
}
