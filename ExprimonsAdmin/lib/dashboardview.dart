import 'dart:convert';

import 'package:exprimons_nous/Child/reportedpostview.dart';
import 'package:exprimons_nous/objects/Colors.dart';
import 'package:exprimons_nous/objects/TextStyle.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'dart:html' as html;

import 'loginview.dart';

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
        "https://www.titan-photography.com/community/${html.window.localStorage["idCommunity"]}");
    //methode get du package HTTP
    final response = await http.get(
      uri,
      headers: {
        "Access-Control-Allow-Origin": "*", // Required for CORS support to work
        "Access-Control-Allow-Headers":
            "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS",
        "token": html.window.localStorage["token"]!
      },
    );

    if(response.statusCode>=200 && response.statusCode<=299){
      //parsing du JSON de la réponse
      var data = json.decode(response.body);

      setState(() {
        this.title = data[0]['label'];
      });
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

  Future refreshNbReportedPost() async {
    //endpoint
    Uri uri = Uri.parse(
        "https://www.titan-photography.com/post/nbReportedPosts/${html.window.localStorage["idCommunity"]}");
    //methode get du package HTTP
    final response = await http.get(
      uri,
      headers: {
        "Access-Control-Allow-Origin": "*", // Required for CORS support to work
        "Access-Control-Allow-Headers":
            "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS",
        "token": html.window.localStorage["token"]!
      },
    );

    if(response.statusCode>=200 && response.statusCode<=299){
      //parsing du JSON de la réponse
      var data = json.decode(response.body);

      setState(() {
        this.nbReportedPosts = "${data[0]['nbReportedPost']}";
      });
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

  Future refreshNbReportedComments() async {
    //endpoint
    Uri uri = Uri.parse(
        "https://www.titan-photography.com/comment/nbReportedCommentsAll/${html.window.localStorage["idCommunity"]}");
    //methode get du package HTTP
    final response = await http.get(
      uri,
      headers: {
        "Access-Control-Allow-Origin": "*", // Required for CORS support to work
        "Access-Control-Allow-Headers":
            "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS",
        "token": html.window.localStorage["token"]!
      },
    );

    if(response.statusCode>=200 && response.statusCode<=299){
      //parsing du JSON de la réponse
      var data = json.decode(response.body);

      setState(() {
        this.nbReportedComments = "${data[0]['nbReportedComments']}";
      });
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
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Row(
              children: [
                Card(
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(25)),
                  elevation: 10,
                  shadowColor: DarkRedColor,
                  child: Column(
                    children: [
                      Container(
                        height: 50,
                        width: 200,
                        child: Center(
                            child: Text(
                          nbReportedPosts,
                          style: TitleTableStyle,
                        )),
                      ),
                      Container(
                        height: 150,
                        width: 200,
                        child: Center(
                          child: Text(
                            "Post signalé",
                            style: DashboardLabelStyle,
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
                                final value = await Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) => const ReportedPostView()),
                                );

                              },
                              child: Text(
                                "Voir les Post signalés",
                                style: RedButtonStyle,
                              ),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
                Card(
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(25)),
                  elevation: 10,
                  shadowColor: DarkRedColor,
                  child: Column(
                    children: [
                      Container(
                        height: 50,
                        width: 200,
                        child: Center(
                            child: Text(
                          nbReportedComments,
                          style: TitleTableStyle,
                        )),
                      ),
                      Container(
                        height: 150,
                        width: 300,
                        child: Center(
                          child: Text(
                            "Commentaire signalé",
                            style: DashboardLabelStyle,
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
                              onPressed: () {
                                //TODO
                              },
                              child: Text(
                                "Voir les commentaires signalés",
                                style: RedButtonStyle,
                              ),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
