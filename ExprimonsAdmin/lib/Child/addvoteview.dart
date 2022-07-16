import 'package:exprimons_nous/Colors.dart';
import 'package:exprimons_nous/objects/votes.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class AddVoteView extends StatefulWidget {
  const AddVoteView({Key? key}) : super(key: key);

  @override
  State<AddVoteView> createState() => _AddVoteViewState();
}

class _AddVoteViewState extends State<AddVoteView> {
  late TextEditingController title;
  late TextEditingController body;
  late TextEditingController nbChoice;
  bool important = false;

  @override
  void initState() {
    super.initState();
    title = TextEditingController();
    body = TextEditingController();
    nbChoice=TextEditingController();
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
                          hintText: 'Entrer le titre du votre ici'),
                    ),
                    TextField(
                      controller: body,
                      autocorrect: true,
                      decoration: InputDecoration(
                          hintText: 'Entrer le descriptif du votre ici'),
                    ),
                    TextField(
                      controller: nbChoice,
                      autocorrect: true,
                      keyboardType: TextInputType.number,
                      inputFormatters: <TextInputFormatter>[
                        // for below version 2 use this
                        FilteringTextInputFormatter.allow(RegExp(r'[0-9]')),
                        // for version 2 and greater youcan also use this
                        FilteringTextInputFormatter.digitsOnly

                      ],

                      decoration: InputDecoration(
                          hintText: 'Entrer le nombre de choix ici'),
                    ),
                    Row(
                      children: [
                        Text("Si c'est un vote important cocher la case"),
                        Checkbox(
                          value: important,
                          onChanged: (bool? value) {
                            setState(() {
                              important = value!;
                            });
                          },
                        ),
                      ],
                    ),
                    TextButton(
                        onPressed: () {
                          addVotes(title.text, body.text, nbChoice.text, important);
                          Navigator.pop(context);
                        },
                        child: Text("Créer le vote")),
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
