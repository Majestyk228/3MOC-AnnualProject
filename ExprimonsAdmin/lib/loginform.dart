import 'dart:html' as html;
import 'dart:ui';

import 'package:exprimons_nous/objects/Colors.dart';
import 'dart:html' as html;
import 'package:exprimons_nous/objects/TextStyle.dart';
import 'package:exprimons_nous/homeview.dart';
import 'package:exprimons_nous/objects/admin.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LoginForm extends StatefulWidget {
  const LoginForm({Key? key}) : super(key: key);

  @override
  State<LoginForm> createState() => _LoginFormState();
}

class _LoginFormState extends State<LoginForm> {
  late TextEditingController email;
  late TextEditingController password;

  //String gender = 'Choisisez';

  @override
  void initState() {
    super.initState();
    email = TextEditingController();
    password = TextEditingController();
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () async {
        return false;
      },
      child: Container(
        height: 500,
        width: 600,
        child: Card(
          shape:
              RoundedRectangleBorder(borderRadius: BorderRadius.circular(25)),
          color: Colors.white,
          elevation: 50,
          shadowColor: DarkRedColor,
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
              children: [
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Text(
                    "Connexion ADMIN",
                    style: TextStyle(
                      fontFamily: 'QuickSand',
                      fontWeight: FontWeight.bold,
                      fontSize: 48,
                    ),
                  ),
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                  child: TextField(
                    controller: email,
                    obscureText: false,
                    decoration: InputDecoration(
                        focusedBorder: OutlineInputBorder(
                            borderSide:
                                BorderSide(color: Color(0xFFFF616F), width: 2)),
                        border: OutlineInputBorder(
                          borderSide: const BorderSide(
                              color: Color(0xFFFFCBD0), width: 3.0),
                        ),
                        labelText: 'Email',
                        floatingLabelStyle: TextStyle(color: Colors.red)),
                  ),
                ),
                Padding(
                  padding: EdgeInsets.all(8.0),
                  child: TextField(
                    controller: password,
                    obscureText: true,
                    decoration: InputDecoration(
                      focusedBorder: OutlineInputBorder(
                          borderSide:
                              BorderSide(color: Color(0xFFFF616F), width: 2)),
                      border: OutlineInputBorder(
                        borderSide: const BorderSide(
                            color: Color(0xFFFFEFFB), width: 3.0),
                      ),
                      labelText: 'Password',
                    ),
                  ),
                ),
                Container(
                  width: 400,
                  height: 75,
                  child: Padding(
                    padding: EdgeInsets.all(8.0),
                    child: Card(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(25)),
                      color: DarkRedColor,
                      elevation: 5,
                      child: TextButton(
                          onPressed: () async {
                            await logAdmin(email.text, password.text,context);

                            if (html.window.localStorage["idAdmin"] == null) {
                              showDialog(
                                context: context,
                                builder: (BuildContext context) => AlertDialog(
                                  title: const Text('Alerte'),
                                  content: const Text(
                                      'votre email ou votre mot de passe est erroné'),
                                  actions: <Widget>[
                                    TextButton(
                                      onPressed: () async {
                                        Navigator.pop(context, 'ok');
                                      },
                                      child: const Text('Ok'),
                                    ),
                                  ],
                                ),
                              );
                            } else {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (context) => HomeView(),
                                ),
                              );
                            }
                          },
                          child: Text(
                            "LOGIN",
                            style: RedButtonStyle,
                          )),
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
