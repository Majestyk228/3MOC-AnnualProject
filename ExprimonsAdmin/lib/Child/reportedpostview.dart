import 'dart:convert';

import 'package:exprimons_nous/loginview.dart';
import 'package:exprimons_nous/objects/TextStyle.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../objects/Colors.dart';
import 'dart:html' as html;
import '../component/postlistline.dart';
import '../objects/post.dart';
import 'Details/detailspostview.dart';

class ReportedPostView extends StatefulWidget {
  const ReportedPostView({Key? key}) : super(key: key);

  @override
  State<ReportedPostView> createState() => _ReportedPostViewState();
}

class _ReportedPostViewState extends State<ReportedPostView> {
  var posts = [];

  @override
  void initState() {
    refreshPosts();
    super.initState();
  }

  Future refreshPosts() async {
    //endpoint
    Uri uri = Uri.parse(
        "https://www.titan-photography.com/post/reportedPosts/${html.window.localStorage["idAdmin"]}");
    //methode get du package HTTP
    final response = await http.get(
      uri,
      headers: {
        "Access-Control-Allow-Origin": "*", // Required for CORS support to work
        "Access-Control-Allow-Headers":
            "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS",
        "token":html.window.localStorage["token"]!
      },
    );

    if (response.statusCode >= 200 && response.statusCode <= 299) {
      //parsing du JSON de la réponse
      var data = json.decode(response.body);

      this.posts = [];
      setState(() {
        for (var i = 0; i < data.length; i++) {
          Post unPost = Post(
            idPost: data[i]['idPost'],
            title: data[i]['title'],
            body: data[i]['body'],
            date: data[i]['date'],
            time: data[i]['time'],
            likes: data[i]['likes'],
            dislikes: data[i]['dislikes'],
            idCommunity: data[i]['idCommunity'],
            idUser: data[i]['idUser'],
            idAdmin: data[i]['idAdmin'],
            reported: data[i]['reported'],
          );

          posts.add(unPost);
        }
      });
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

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: double.infinity,
      color: Colors.white,
      child: Column(
        children: [
          Row(
            children: [
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Container(
                  width: 75,
                  height: 75,
                  child: Card(
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(25)),
                    color: DarkRedColor,
                    elevation: 2,
                    child: TextButton(
                      onPressed: () {
                        Navigator.pop(context);
                      },
                      child: Center(
                        child: Text(
                          style: RedButtonStyle,
                          "<",
                        ),
                      ),
                    ),
                  ),
                ),
              ),
            ],
          ),
          SizedBox(
            height: 100,
          ),
          Center(
            child: Container(
              width: 1000,
              height: 100,

              //child: Text(votes[index]["title"])
              child: Card(
                elevation: 2,
                color: Colors.grey,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Container(
                      child: Text(
                        "Post signalé",
                        style: TitleTableStyle,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
          Container(
            width: double.infinity,
            height: 100,

            //child: Text(votes[index]["title"])
            child: Card(
              elevation: 2,
              color: veryDarkRedColor,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  Container(
                    width: 150,
                    child: Text(
                      "Titre",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 32,
                      ),
                    ),
                  ),
                  Container(
                    width: 150,
                    child: Text(
                      "Date",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 32,
                      ),
                    ),
                  ),
                  Container(
                    width: 150,
                    child: Text(
                      "Like",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 32,
                      ),
                    ),
                  ),
                  Container(
                    width: 150,
                    child: Text(
                      "Dislike",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 32,
                      ),
                    ),
                  ),
                  Container(
                    width: 150,
                    child: Text(
                      "Reported",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 32,
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
          Expanded(
            child: SingleChildScrollView(
              scrollDirection: Axis.vertical,
              child: Wrap(
                children: [
                  ListView.builder(
                      scrollDirection: Axis.vertical,
                      shrinkWrap: true,
                      addRepaintBoundaries: false,
                      itemCount: posts.length,
                      itemBuilder: (context, index) {
                        return MouseRegion(
                            cursor: SystemMouseCursors.click,
                            child: GestureDetector(
                                onTap: () async {
                                  //go to  post details
                                  final value = await Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) => DetailsPostView(
                                            post: posts[index])),
                                  );
                                  refreshPosts();
                                },
                                child: PostListLine(
                                  post: posts[index],
                                  index: index,
                                )));
                      }),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
