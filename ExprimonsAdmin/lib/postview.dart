import 'package:exprimons_nous/Child/addpostview.dart';
import 'package:exprimons_nous/Child/reportedcommentview.dart';
import 'package:exprimons_nous/Child/reportedpostview.dart';
import 'package:exprimons_nous/objects/Colors.dart';
import 'package:exprimons_nous/objects/TextStyle.dart';
import 'package:exprimons_nous/component/postlistline.dart';
import 'package:exprimons_nous/objects/post.dart';
import 'package:flutter/material.dart';
import 'dart:convert';
import 'Child/Details/detailspostview.dart';
import 'dart:html' as html;
import 'package:http/http.dart' as http;

class PostView extends StatefulWidget {
  const PostView({Key? key}) : super(key: key);

  @override
  State<PostView> createState() => _PostViewState();
}

class _PostViewState extends State<PostView> {
  var posts = [];
  int nbComment =0;
  @override
  void initState() {
    refreshPosts();
    refreshNbReportedComment();
    super.initState();
  }

  Future refreshPosts() async {
    //endpoint
    Uri uri = Uri.parse(
        "https://www.titan-photography.com/post/all/${html.window.localStorage["idCommunity"]}");
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

  Future refreshNbReportedComment() async {
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

    //parsing du JSON de la réponse
    var data = json.decode(response.body);
  print(data);

    setState(() {
      if(data[0]["nbComment"]==null){
        nbComment=0;
      }
      else{
        nbComment=data[0]["nbComment"];
      }

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
          Row(
            children: [
              Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(25)),
                elevation: 10,
                color: DarkRedColor,
                child: TextButton(
                    onPressed: () async {
                      final value = await Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const AddPostView()),
                      );
                      refreshPosts();
                    },
                    child: Container(
                      width: 200,
                      height: 75,
                      child: Center(
                        child: Text(
                          "Ajout de Post",
                          style: RedButtonStyle,
                        ),
                      ),
                    )),
              ),
              Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(25)),
                elevation: 10,
                color: DarkRedColor,
                child: TextButton(
                    onPressed: () async {
                      final value = await Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const ReportedPostView()),
                      );
                      refreshPosts();
                    },
                    child: Container(
                      width: 200,
                      height: 75,
                      child: Center(
                        child: Text(
                          "Post signalé",
                          style: RedButtonStyle,
                        ),
                      ),
                    )),
              ),

              Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(25)),
                elevation: 10,
                color: DarkRedColor,
                child: TextButton(
                    onPressed: () async {
                      final value = await Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const ReportedCommentView()),
                      );
                      refreshPosts();
                    },
                    child: Container(
                      width: 300,
                      height: 75,
                      child: Center(
                        child: Text(
                          "${nbComment} Commentaire signalé",
                          style: RedButtonStyle,
                        ),
                      ),
                    )),
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
                        "Post de la communauté",
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
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 150,
                    child: Text(
                      "Date",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 150,
                    child: Text(
                      "Like",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 150,
                    child: Text(
                      "Dislike",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 150,
                    child: Text(
                      "Reported",
                      style: HeaderTableStyle,
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
