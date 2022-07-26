import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../Colors.dart';
import '../Globals.dart';
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
        "https://www.titan-photography.com/post/reportedPosts/${currentAdmin.idCommunity}");
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
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Container(
                  width: 50,
                  height: 50,
                  child: Card(
                    color: Colors.white,
                    elevation: 2,
                    child: TextButton(
                      onPressed: () {
                        Navigator.pop(context);
                      },
                      child: Center(
                        child: Text(
                          style: TextStyle(fontSize: 30),
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
          Expanded(
            child: SingleChildScrollView(
              scrollDirection: Axis.vertical,
              child: Wrap(
                children: [
                  Center(
                    child: Container(
                      width: 300,
                      height: 100,

                      //child: Text(votes[index]["title"])
                      child: Card(
                        elevation: 2,
                        color: Colors.white,
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Container(
                              child: Text(
                                "Post signalé",
                                style: TextStyle(fontWeight: FontWeight.bold),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                  ),
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
                                child: PostListLine(post: posts[index])));
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