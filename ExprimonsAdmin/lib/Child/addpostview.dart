import 'package:exprimons_nous/objects/post.dart';
import 'package:exprimons_nous/objects/TextStyle.dart';
import 'package:flutter/material.dart';

import '../objects/Colors.dart';

class AddPostView extends StatefulWidget {
  const AddPostView({Key? key}) : super(key: key);

  @override
  State<AddPostView> createState() => _AddPostViewState();
}

class _AddPostViewState extends State<AddPostView> {
  late TextEditingController title;
  late TextEditingController body;
  bool _validateTitle = true;
  bool _validateBody = true;

  bool important = false;

  @override
  void initState() {
    super.initState();
    title = TextEditingController();
    body = TextEditingController();
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
                        "Ajout de Post",
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
                            labelText: 'Entrer le titre du post ici',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                            errorText: _validateTitle
                                ? 'Cette valeur ne peut etre vide'
                                : null,
                          ),
                          onChanged: (text) {
                            setState(() {
                              if (text == "") {
                                _validateTitle = true;
                              } else {
                                _validateTitle = false;
                              }
                            });
                          },
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          minLines: 1,
                          maxLines: 5,
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
                            labelText: 'Entrer le descriptif du post ici',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                            errorText: _validateBody
                                ? 'Cette valeur ne peut etre vide'
                                : null,
                          ),
                          onChanged: (text) {
                            setState(() {
                              if (text == "") {
                                _validateBody = true;
                              } else {
                                _validateBody = false;
                              }
                            });
                          },
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
                                  if (_validateBody == true ||
                                      _validateTitle == true) {
                                    setState(() {});
                                  } else {
                                    addPost(title.text, body.text);
                                    Navigator.pop(context);
                                  }
                                },
                                child: Text(
                                  "Créer le Post",
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
          )
        ],
      ),
    );
  }
}
