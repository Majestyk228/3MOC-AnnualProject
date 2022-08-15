import 'dart:html';
import 'dart:convert';
import 'package:exprimons_nous/objects/Colors.dart';
import 'package:exprimons_nous/objects/TextStyle.dart';
import 'package:exprimons_nous/objects/comment.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:html' as html;

class DetailsCommentView extends StatefulWidget {
  const DetailsCommentView({Key? key, required this.comments})
      : super(key: key);
  final Comments comments;

  @override
  State<DetailsCommentView> createState() => _DetailsCommentViewState();
}

class _DetailsCommentViewState extends State<DetailsCommentView> {
  late TextEditingController body;
  late TextEditingController likes;
  late TextEditingController dislikes;
  late TextEditingController reports;
  late TextEditingController date;
  late TextEditingController userName;
  late bool anonymous = true;
  late String Name = "";

  Future refreshUserName() async {
    //endpoint
    Uri uri = Uri.parse(
        "https://www.titan-photography.com/comment/count/${widget.comments.idUser}");
    //methode get du package HTTP
    final response = await http.post(
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

    setState(() {
      Name = data[0]["lastName"] + data[0]["firstName"];
    });
  }

  @override
  void initState() {
    if (widget.comments.anonymous == 0) {
      anonymous = true;
      refreshUserName();
    } else {
      anonymous = false;
    }

    super.initState();

    body = TextEditingController();
    likes = TextEditingController();
    dislikes = TextEditingController();
    reports = TextEditingController();
    date = TextEditingController();
    userName = TextEditingController();

    body.text = "${widget.comments.body}";
    likes.text = "${widget.comments.likes}";
    dislikes.text = "${widget.comments.dislikes}";
    reports.text = "${widget.comments.reports}";
    date.text = "${widget.comments.date}";
    userName.text = "${Name}";

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
          Center(
            child: Container(
              width: 1000,
              height: 100,

              //child: Text(votes[index]["title"])
              child: Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(25)),
                elevation: 2,
                color: DarkRedColor,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Container(
                      child: Text(
                        "Details du Commentaire",
                        style: TitleAddStyle,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
          SizedBox(
            height: 100,
          ),
          Center(
            child: Container(
              width: 1000,
              child: Card(
                shadowColor: DarkRedColor,
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(25)),
                elevation: 25,
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Column(
                    children: [
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          minLines: 1,
                          maxLines: 5,
                          style: InputStyle,
                          enabled: false,
                          controller: body,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Corps du commentaire',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          minLines: 1,
                          maxLines: 5,
                          style: InputStyle,
                          enabled: false,
                          controller: likes,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Likes du commentaire',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          enabled: false,
                          controller: dislikes,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Dislikes du commentaire',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          enabled: false,
                          controller: reports,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Nombre de signalement du commentaire',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          enabled: false,
                          controller: date,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Date du commentaire',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          enabled: false,
                          controller: dislikes,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Dislikes du post',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Visibility(
                        visible: anonymous,
                        child: Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: TextField(
                            style: InputStyle,
                            enabled: false,
                            controller: userName,
                            autocorrect: true,
                            decoration: InputDecoration(
                              focusedBorder: OutlineInputBorder(
                                  borderSide: BorderSide(
                                      color: DarkRedColor, width: 2)),
                              border: OutlineInputBorder(
                                borderSide: const BorderSide(
                                    color: Color(0xFFFFCBD0), width: 3.0),
                              ),
                              labelText: "Nom de l'auteur du commentaire",
                              floatingLabelStyle: TextStyle(color: Colors.red),
                            ),
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
                                showDialog<String>(
                                    context: context,
                                    builder: (BuildContext context) =>
                                        AlertDialog(
                                          title: Text(
                                            'Supression du commentaire ${widget.comments.body}',
                                            overflow: TextOverflow.ellipsis,
                                          ),
                                          content: const Text(
                                              'Voullez vous vraiment supprimer ce commentaire ?'),
                                          actions: <Widget>[
                                            TextButton(
                                              onPressed: () =>
                                                  Navigator.pop(context),
                                              child: const Text('Non'),
                                            ),
                                            TextButton(
                                              onPressed: () async {
                                                await deleteComment(
                                                    widget.comments.idComment!);
                                                Navigator.pop(context, 'OK');
                                                Navigator.pop(context);
                                              },
                                              child: const Text('Oui'),
                                            ),
                                          ],
                                        ));
                              },
                              child: Text(
                                "Supprimer le Commentaire",
                                style: RedButtonStyle,
                              ),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}
