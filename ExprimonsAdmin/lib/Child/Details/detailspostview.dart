import 'package:flutter/material.dart';

import '../../Colors.dart';
import '../../objects/post.dart';

class DetailsPostView extends StatefulWidget {
  const DetailsPostView({Key? key, required this.post}) : super(key: key);
  final Post post;

  @override
  State<DetailsPostView> createState() => _DetailsPostViewState();
}

class _DetailsPostViewState extends State<DetailsPostView> {
  late TextEditingController title;
  late TextEditingController body;
  late bool isAdmin = false;
  bool important = false;

  @override
  void initState() {
    if (widget.post.idAdmin != null) {
      isAdmin = true;
    }

    super.initState();

    title = TextEditingController();
    body = TextEditingController();
    title.text = "${widget.post.title}";
    body.text = "${widget.post.body}";
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
                      child: Column(
                        children: [
                          Text(
                            "Detail du post",
                            style: TextStyle(fontWeight: FontWeight.bold),
                          ),
                          Text((() {
                            if (isAdmin) {
                              return " Modification autorisée";
                            }

                            return "Modification non autorisée";
                          })()),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
            ),
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
                      enabled: isAdmin,
                      controller: title,
                      autocorrect: true,
                      decoration: InputDecoration(
                          hintText: 'Entrer le titre du Post ici'),
                    ),
                    TextField(
                      enabled: isAdmin,
                      controller: body,
                      autocorrect: true,
                      decoration: InputDecoration(
                          hintText: 'Entrer le descriptif du Post ici'),
                    ),
                    TextButton(
                        onPressed: () async {
                          if (isAdmin) {
                            if (widget.post.idPost != null) {
                              await updatePost(
                                  widget.post.idPost!, title.text, body.text);
                            }

                            Navigator.pop(context);
                          } else {
                            showDialog(
                              context: context,
                              builder: (BuildContext context) => AlertDialog(
                                title: const Text('Alerte'),
                                content: const Text(
                                    'Vous ne pouvez pas mettre a jour ce post car c\'est le post d\'un utilisateur'),
                                actions: <Widget>[
                                  TextButton(
                                    onPressed: () async {
                                      Navigator.pop(context, 'ok');
                                    },
                                    child: const Text('Ok'),
                                  ),
                                ],
                              ),
                            );
                          }
                        },
                        child: Text("Mettre a jour le post")),
                    TextButton(
                        onPressed: () {
                          showDialog<String>(
                              context: context,
                              builder: (BuildContext context) => AlertDialog(
                                    title: Text(
                                        'Supression du post ${widget.post.title}'),
                                    content: const Text(
                                        'Voullez vous vraiment supprimer le Post ?'),
                                    actions: <Widget>[
                                      TextButton(
                                        onPressed: () => Navigator.pop(context),
                                        child: const Text('Non'),
                                      ),
                                      TextButton(
                                        onPressed: () async {
                                          await deletePost(widget.post.idPost!);
                                          Navigator.pop(context, 'OK');
                                          Navigator.pop(context);
                                        },
                                        child: const Text('Oui'),
                                      ),
                                    ],
                                  ));
                        },
                        child: Text("Supprimer le post")),
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
