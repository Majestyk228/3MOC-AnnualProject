import 'dart:convert';

import 'dart:html' as html;
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:exprimons_nous/loginview.dart';

class User {
  int? idUser;
  String? firstName;
  String? lastName;
  String? birthDate;
  String? gender;
  String? areaCode;
  String? email;
  String? password;
  int? points;
  String? signInDate;

  User({
    this.idUser,
    this.firstName,
    this.lastName,
    this.birthDate,
    this.gender,
    this.areaCode,
    this.email,
    this.password,
    this.points,
    this.signInDate,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      idUser: json['idUser'],
      firstName: json['firstName'],
      lastName: json['lastName'],
      birthDate: json['birthDate'],
      gender: json['gender'],
      areaCode: json['areaCode'],
      email: json['email'],
      password: json['password'],
      points: json['points'],
      signInDate: json['signInDate'],
    );
  }
}

Future addUser(
    String firstName,
    String lastName,
    String birthDate,
    String gender,
    String areaCode,
    String email,
    String password,
    BuildContext context) async {
  Uri uri = Uri.parse("https://titan-photography.com/user/register/admin");

  var body = jsonEncode({
    "firstName": firstName,
    "lastName": lastName,
    "birthDate": birthDate,
    "gender": gender,
    "areaCode": areaCode,
    "email": email,
    "password": password,
    "idCommunity": html.window.localStorage["idCommunity"]
  });

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
  if (response.statusCode >= 200 && response.statusCode <= 299) {
  } else if (response.statusCode == 406) {
    html.window.localStorage.clear();
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => Login(),
      ),
    );
  } else {
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

Future deleteUser(int idUser, BuildContext context) async {
  Uri uri = Uri.parse("https://titan-photography.com/user/delete/${idUser}");

  final response = await http.delete(uri, headers: {
    "Access-Control-Allow-Origin": "*",
    // Required for CORS support to work
    "Access-Control-Allow-Headers":
        "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
    "Access-Control-Allow-Methods": "POST, OPTIONS",
    'Content-Type': 'application/json; charset=UTF-8',
    "token": html.window.localStorage["token"]!
  });
  if (response.statusCode >= 200 && response.statusCode <= 299) {
  } else if (response.statusCode == 406) {
    html.window.localStorage.clear();
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => Login(),
      ),
    );
  } else {
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
