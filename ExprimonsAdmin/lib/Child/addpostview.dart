import 'package:exprimons_nous/objects/post.dart';
import 'package:exprimons_nous/TextStyle.dart';
import 'package:flutter/material.dart';

import '../Colors.dart';

class AddPostView extends StatefulWidget {
  const AddPostView({Key? key}) : super(key: key);

  @override
  State<AddPostView> createState() => _AddPostViewState();
}

class _AddPostViewState extends State<AddPostView> {
  late TextEditingController title;
  late TextEditingController body;

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
              width: 500,
              height: 300,
              child: Card(
                elevation: 10,
                child: Column(
                  children: [
                    TextField(
                      controller: title,
                      autocorrect: true,
                      decoration: InputDecoration(
                          hintText: 'Entrer le titre du Post ici'),
                    ),
                    TextField(
                      controller: body,
                      autocorrect: true,
                      decoration: InputDecoration(
                          hintText: 'Entrer le descriptif du Post ici'),
                    ),
                    TextButton(
                        onPressed: () {
                          addPost(title.text, body.text);
                          Navigator.pop(context);
                        },
                        child: Text("Créer le Post")),
                  ],
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}
