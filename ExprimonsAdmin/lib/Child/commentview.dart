import 'dart:convert';

import 'package:exprimons_nous/Globals.dart';
import 'package:exprimons_nous/component/commentline.dart';
import 'package:exprimons_nous/objects/comment.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../Colors.dart';
import 'package:exprimons_nous/TextStyle.dart';

class CommentView extends StatefulWidget {
  const CommentView({Key? key, required this.idPost, required this.titlePost})
      : super(key: key);
  final int idPost;
  final String titlePost;

  @override
  State<CommentView> createState() => _CommentViewState();
}

class _CommentViewState extends State<CommentView> {
  var comments = [];

  @override
  void initState() {
    refreshComment();
    super.initState();
  }

  Future refreshComment() async {
    //endpoint
    Uri uri = Uri.parse(
        "https://www.titan-photography.com/comment/all/${widget.idPost}");
    //methode get du package HTTP
    final response = await http.get(
      uri,
      headers: {
        "Access-Control-Allow-Origin": "*", // Required for CORS support to work
        "Access-Control-Allow-Headers":
            "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS",
        "token": currentAdmin.token!
      },
    );

    //parsing du JSON de la réponse
    var data = json.decode(response.body);

    this.comments = [];
    setState(() {
      for (var i = 0; i < data.length; i++) {
        print(data[i]);
        Comment uneInvitation = Comment(
          idComment: data[i]['idComment'],
          body: data[i]['body'],
          likes: data[i]['likes'],
          dislikes: data[i]['dislikes'],
          reports: data[i]['reports'],
          anonymous: data[i]['anonymous'],
          idPost: data[i]['idPost'],
          idUser: data[i]['idUser'],
          date: data[i]['date'],
        );

        comments.add(uneInvitation);
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
              Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(25)),
                elevation: 2,
                color: DarkRedColor,
                child: TextButton(
                    onPressed: () async {
                      //redirect on reported comment view of the post
                    },
                    child: Container(
                      width: 200,
                      height: 75,
                      child: Center(
                        child: Text(
                          "0 Commentaire signalée",
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
              height: 200,

              //child: Text(votes[index]["title"])
              child: Card(
                elevation: 2,
                color: Colors.grey,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Container(
                      width: 950,
                      height: 200,
                      child: Center(
                        child: Text(
                          'Commentaire du post \n"${widget.titlePost}"',
                          textAlign: TextAlign.center,

                          style: TitleTableStyle,
                        ),
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
                      "Message",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 230,
                    child: Text(
                      "Date de création",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 200,
                    child: Text(
                      "Likes",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 200,
                    child: Text(
                      "Dislikes",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 200,
                    child: Text(
                      "Nombre de signalement",
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
                      itemCount: comments.length,
                      itemBuilder: (context, index) {
                        return MouseRegion(
                          cursor: SystemMouseCursors.click,
                          child: GestureDetector(
                            /*
                            onTap: () => showDialog<String>(
                              context: context,
                              builder: (BuildContext context) => AlertDialog(
                                title: Text(
                                    'Supression de l\'invitation ${invitation[index].code}'),
                                content: const Text(
                                    'Voullez vous vraiment supprimer cette invitation ?'),
                                actions: <Widget>[
                                  TextButton(
                                    onPressed: () => Navigator.pop(context),
                                    child: const Text('Non'),
                                  ),
                                  TextButton(
                                    onPressed: () async {
                                      Navigator.pop(context, 'OK');

                                      await deleteInvitation(
                                          invitation[index].code);
                                      refreshInvitation();
                                    },
                                    child: const Text('Oui'),
                                  ),
                                ],
                              ),
                            ),*/
                            child: CommentLine(
                              comment: comments[index],
                              index: index,
                            ),
                          ),
                        );
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
