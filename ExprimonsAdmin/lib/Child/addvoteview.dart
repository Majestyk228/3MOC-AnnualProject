import 'package:exprimons_nous/Colors.dart';
import 'package:exprimons_nous/objects/optionvote.dart';
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
  late List<OptionVotes> optionVotes;

  @override
  void initState() {
    super.initState();
    title = TextEditingController();
    body = TextEditingController();
    nbChoice = TextEditingController();
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
                          hintText: 'Entrer le titre du vote ici'),
                    ),
                    TextField(
                      controller: body,
                      autocorrect: true,
                      decoration: InputDecoration(
                          hintText: 'Entrer le descriptif du vote ici'),
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
                        onPressed: () async {
                          if (nbChoice.text == "" ||
                              int.parse(nbChoice.text) >= 5 ||
                              int.parse(nbChoice.text) <= 1) {
                            showDialog(
                              context: context,
                              builder: (BuildContext context) => AlertDialog(
                                title: const Text('Alerte'),
                                content: const Text(
                                    'il faut mettre entre 2 et 4 nombre de choix pour un vote'),
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
                          } else {
                            optionVotes = List<OptionVotes>.filled(
                                int.parse(nbChoice.text), OptionVotes());
                            showDialog(
                                context: context,
                                builder: (BuildContext context) {
                                  return Dialog(
                                    child: Container(
                                      width: 700,
                                      height: 300,
                                      color: ultraLightRedColor,
                                      child: Column(
                                        children: [
                                          Container(
                                            width: 700,
                                            height: 200,
                                            child: Row(
                                              children: [
                                                Expanded(
                                                  child: ListView.builder(
                                                      scrollDirection:
                                                          Axis.horizontal,
                                                      shrinkWrap: true,
                                                      addRepaintBoundaries:
                                                          true,
                                                      itemCount: int.parse(
                                                          nbChoice.text),
                                                      itemBuilder:
                                                          (context, index) {
                                                        return Card(
                                                          elevation: 10,
                                                          child: Padding(
                                                            padding:
                                                                const EdgeInsets
                                                                    .all(8.0),
                                                            child: Column(
                                                              children: [
                                                                Container(
                                                                  width: 150,
                                                                  height: 100,
                                                                  child:
                                                                      TextField(
                                                                    onChanged:
                                                                        (text) {
                                                                      optionVotes[index]
                                                                              .label =
                                                                          text;
                                                                    },
                                                                    autocorrect:
                                                                        true,
                                                                    decoration: InputDecoration(
                                                                        hintText:
                                                                            'option ${index + 1} '),
                                                                  ),
                                                                ),
                                                              ],
                                                            ),
                                                          ),
                                                        );
                                                      }),
                                                ),
                                              ],
                                            ),
                                          ),
                                          TextButton(
                                              onPressed: () async {
                                                final idVote = await addVotes(
                                                    title.text,
                                                    body.text,
                                                    nbChoice.text,
                                                    important);
                                                for (int i = 0;
                                                    i < optionVotes.length;
                                                    i++) {
                                                  optionVotes[i].idVote =
                                                      idVote;
                                                }
                                                addOptionVotes(optionVotes);
                                                Navigator.pop(context);
                                                Navigator.pop(context);
                                              },
                                              child: Text(
                                                  "Créer les options du vote")),
                                        ],
                                      ),
                                    ),
                                  );
                                });
                          }
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
