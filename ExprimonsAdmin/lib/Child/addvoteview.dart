import 'package:exprimons_nous/Colors.dart';
import 'package:exprimons_nous/component/dialogaddoptionvote.dart';
import 'package:exprimons_nous/objects/optionvote.dart';
import 'package:exprimons_nous/objects/votes.dart';
import 'package:exprimons_nous/TextStyle.dart';
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
  bool _validateTitle = true;
  bool _validateBody = true;
  bool _validateNbChoice = true;

  String errorMessageNbChoice = "Cette valeur ne peut etre vide";

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
                        "Ajout de Vote",
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
                            labelText: 'Entrer le titre du vote ici',
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
                            labelText: 'Entrer le descriptif du vote ici',
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
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
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
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: "Entrer le Nombre d'options de vote ici",
                            hintText: "3",
                            floatingLabelStyle: TextStyle(color: Colors.red),
                            errorText:
                                _validateNbChoice ? errorMessageNbChoice : null,
                          ),
                          onChanged: (text) {
                            setState(() {
                              if (text == "") {
                                _validateNbChoice = true;
                                errorMessageNbChoice =
                                    'Cette valeur ne peut etre vide';
                              } else if (int.parse(text) >= 5 ||
                                  int.parse(text) <= 1) {
                                _validateNbChoice = true;
                                errorMessageNbChoice =
                                    "Veuillez rentrer un chiffre entre 2 et 4";
                              } else {
                                _validateNbChoice = false;
                              }
                            });
                          },
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Row(
                          children: [
                            Text(
                              "Si c'est un vote important cocher la case",
                              style: InputStyle,
                            ),
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
                                onPressed: () async {
                                  if (_validateTitle == true ||
                                      _validateBody == true ||
                                      _validateNbChoice == true) {
                                    setState(() {});
                                  } else {
                                    optionVotes = List<OptionVotes>.generate(
                                        int.parse(nbChoice.text),
                                        (int index) => OptionVotes());
                                    showDialog(
                                        context: context,
                                        builder: (BuildContext context) {
                                          return DialogAddOptionVote(nbChoice: int.parse(nbChoice.text),title: title.text,body: body.text,important: important,) ;
                                        });
                                  }
                                },
                                child: Text(
                                  "Créer le vote",
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



