import 'dart:convert';
import 'package:exprimons_nous/Globals.dart';
import 'package:http/http.dart' as http;
class Admin {
  int? idAdmin;
  String? email;
  String? password;
  int? idCommunity;

  Admin({
    this.idAdmin,
    this.email,
    this.password,
    this.idCommunity,
  });

  factory Admin.fromJson(Map<String, dynamic> json) {
    return Admin(
      idAdmin: json['idAdmin'],
      email: json['email'],
      password: json['password'],
      idCommunity: json['idCommunity'],
    );
  }
}

Future logAdmin(String email, String password) async {
  Uri uri = Uri.parse("https://titan-photography.com/admin/login");
  var body = jsonEncode({
    "email": email,
    "password": password
  });
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
  currentAdmin=Admin(
    idAdmin: data[0]['idAdmin'],
    email: data[0]['email'],
    password: data[0]['password'],
    idCommunity: data[0]['idCommunity'],

  ) ;
  return true;
}
