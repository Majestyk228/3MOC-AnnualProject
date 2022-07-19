import 'package:exprimons_nous/objects/post.dart';

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
      color: ultraLightRedColor,
      child: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
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
