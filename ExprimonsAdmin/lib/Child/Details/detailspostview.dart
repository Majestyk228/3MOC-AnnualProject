import 'dart:convert';

import 'package:exprimons_nous/Child/commentviewpost.dart';
import 'package:exprimons_nous/objects/TextStyle.dart';
import 'package:flutter/material.dart';
import 'dart:html' as html;
import '../../objects/Colors.dart';
import '../../objects/post.dart';
import 'package:http/http.dart' as http;

class DetailsPostView extends StatefulWidget {
  const DetailsPostView({Key? key, required this.post}) : super(key: key);
  final Post post;

  @override
  State<DetailsPostView> createState() => _DetailsPostViewState();
}

class _DetailsPostViewState extends State<DetailsPostView> {
  late TextEditingController title;
  late TextEditingController body;
  late TextEditingController date;
  late TextEditingController time;
  late TextEditingController likes;
  late TextEditingController dislikes;
  late TextEditingController reported;
  int nbComment =0;

  late bool isAdmin = false;
  bool important = false;

  Future refreshNbComment() async {
    //endpoint
    Uri uri = Uri.parse(
        "https://www.titan-photography.com/comment/count/${widget.post.idPost}");
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
  void initState() {
    refreshNbComment();
    if (widget.post.idAdmin != null) {
      isAdmin = true;
    }

    super.initState();

    title = TextEditingController();
    body = TextEditingController();
    date = TextEditingController();
    time = TextEditingController();
    likes = TextEditingController();
    dislikes = TextEditingController();
    reported = TextEditingController();

    title.text = "${widget.post.title}";
    body.text = "${widget.post.body}";
    date.text = "${widget.post.date}";
    time.text = "${widget.post.time}";
    likes.text = "${widget.post.likes}";
    dislikes.text = "${widget.post.dislikes}";
    reported.text = "${widget.post.reported}";
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
                      final value = await Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) =>  CommentViewPost(idPost: widget.post.idPost!,titlePost: widget.post.title!,)),
                      );
                    },
                    child: Container(
                      width: 300,
                      height: 75,
                      child: Center(
                        child: Text(
                          "${nbComment} Commentaires du post",
                          style: RedButtonStyle,
                        ),
                      ),
                    )),
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
                        "Details du Post",
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
                          style: InputStyle,
                          enabled: false,
                          controller: title,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Titre du post',
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
                            labelText: 'Descriptif du post',
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
                            labelText: 'Date du post',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          enabled: false,
                          controller: time,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Heure du post',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
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
                            labelText: 'Likes du post',
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
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          enabled: false,
                          controller: reported,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Nombre de signalement du post',
                            floatingLabelStyle: TextStyle(color: Colors.red),
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
                                              'Supression du post ${widget.post.title}'),
                                          content: const Text(
                                              'Voullez vous vraiment supprimer le Post ?'),
                                          actions: <Widget>[
                                            TextButton(
                                              onPressed: () =>
                                                  Navigator.pop(context),
                                              child: const Text('Non'),
                                            ),
                                            TextButton(
                                              onPressed: () async {
                                                await deletePost(
                                                    widget.post.idPost!,context);
                                                Navigator.pop(context, 'OK');
                                                Navigator.pop(context);
                                              },
                                              child: const Text('Oui'),
                                            ),
                                          ],
                                        ));
                              },
                              child: Text(
                                "Supprimer le post",
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
