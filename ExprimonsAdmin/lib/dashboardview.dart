import 'dart:convert';

import 'package:exprimons_nous/Colors.dart';
import 'package:exprimons_nous/TextStyle.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'Globals.dart';

class DashboardView extends StatefulWidget {
  const DashboardView({Key? key}) : super(key: key);

  @override
  State<DashboardView> createState() => _DashboardViewState();
}

class _DashboardViewState extends State<DashboardView> {
  late String title = "Loading";
  late String nbReportedPosts = "Loading";
  late String nbReportedComments = "Loading";

  @override
  void initState() {
    refreshUsers();
    refreshNbReportedPost();
    refreshNbReportedComments();
    super.initState();
  }

  Future refreshUsers() async {
    //endpoint
    Uri uri = Uri.parse(
        "https://www.titan-photography.com/community/${currentAdmin.idCommunity}");
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

    setState(() {
      this.title = data[0]['label'];
    });
  }

  Future refreshNbReportedPost() async {
    //endpoint
    Uri uri = Uri.parse(
        "https://www.titan-photography.com/post/nbReportedPosts/${currentAdmin.idCommunity}");
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

    setState(() {
      this.nbReportedPosts = "${data[0]['nbReportedPost']}";
    });
  }

  Future refreshNbReportedComments() async {
    //endpoint
    Uri uri = Uri.parse(
        "https://www.titan-photography.com/comment/nbReportedCommentsAll/${currentAdmin.idCommunity}");
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

    setState(() {
      this.nbReportedComments = "${data[0]['nbReportedComments']}";
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: double.infinity,
      color: Colors.white,
      child: Column(
        children: [
          Center(
            child: Container(
              width: 1000,
              height: 100,

              //child: Text(votes[index]["title"])
              child: Card(
                elevation: 10,
                color: Colors.grey,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Container(
                      child: Text(
                        title,
                        style: TitleTableStyle,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
          Row(
            children: [
              Card(
                child: Column(
                  children: [
                    Container(
                      height: 50,
                      width: 200,
                      child: Center(
                          child: Text(
                        nbReportedPosts,
                        style: TextStyle(fontSize: 32),
                      )),
                    ),
                    Container(
                      height: 150,
                      width: 200,
                      child: Center(child: Text("Post signalé")),
                    ),
                  ],
                ),
              ),
              Card(
                child: Column(
                  children: [
                    Container(
                      height: 50,
                      width: 200,
                      child: Center(
                          child: Text(
                            nbReportedComments,
                            style: TextStyle(fontSize: 32),
                          )),
                    ),
                    Container(
                      height: 150,
                      width: 200,
                      child: Center(child: Text("Commentaire signalé")),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
