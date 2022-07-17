import 'dart:convert';

import 'package:exprimons_nous/Child/adduserview.dart';
import 'package:exprimons_nous/Child/invitationview.dart';
import 'package:exprimons_nous/Colors.dart';
import 'package:exprimons_nous/component/userlistline.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'Globals.dart';
import 'objects/user.dart';

class UserView extends StatefulWidget {
  const UserView({Key? key}) : super(key: key);

  @override
  State<UserView> createState() => _UserViewState();
}

class _UserViewState extends State<UserView> {
  var users = [];

  @override
  void initState() {
    refreshUsers();
    super.initState();
  }

  Future refreshUsers() async {
    //endpoint
    Uri uri = Uri.parse("https://www.titan-photography.com/user/all/${currentAdmin.idCommunity}");
    //methode get du package HTTP
    final response = await http.get(
      uri,
      headers: {
        "Access-Control-Allow-Origin": "*", // Required for CORS support to work
        "Access-Control-Allow-Headers":
        "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS"
      },
    );

    //parsing du JSON de la réponse
    var data = json.decode(response.body);

    this.users = [];
    setState(() {
      for (var i = 0; i < data.length; i++) {

        User unUser = User(
          idUser: data[i]['idUser'],
          firstName: data[i]['firstName'],
          lastName: data[i]['lastName'],
          birthDate: data[i]['birthDate'],
          gender: data[i]['gender'],
          areaCode: data[i]['areaCode'],
          email: data[i]['email'],
          password: data[i]['password'],
          points: data[i]['points'],
          signInDate: data[i]['signInDate'],

        );
        print(unUser.firstName);
        users.add(unUser);
      }

    });
  }



  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: double.infinity,
      color: ultraLightRedColor,
      child: Column(
        children: [
          Row(
            children: [
              Card(
                elevation: 2,
                color: Colors.white,
                child: TextButton(
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const AddUserView()),
                      );
                    },
                    child: Container(
                      width: 200,
                      height: 75,
                      child: Center(
                        child: Text("Add User"),
                      ),
                    )),

              ),
              Card(
                elevation: 2,
                color: Colors.white,
                child: TextButton(
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const InvitationView()),
                      );
                    },
                    child: Container(
                      width: 200,
                      height: 75,
                      child: Center(
                        child: Text("Invitation"),
                      ),
                    )),

              ),
            ],
          ),
          SizedBox(
            height: 100,
          ),
          Expanded(
            child: SingleChildScrollView(
              scrollDirection: Axis.vertical,
              child: ListView.builder(
                  scrollDirection: Axis.vertical,
                  shrinkWrap: true,
                  addRepaintBoundaries: false,
                  itemCount: users.length,
                  itemBuilder: (context, index) {
                    return UserListLine(user: users[index]);
                  }),
            ),
          )
        ],
      ),
    );
  }
}
