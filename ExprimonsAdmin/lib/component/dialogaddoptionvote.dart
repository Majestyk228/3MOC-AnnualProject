import 'package:exprimons_nous/Colors.dart';
import 'package:exprimons_nous/TextStyle.dart';
import 'package:flutter/material.dart';

import '../objects/optionvote.dart';
import '../objects/votes.dart';

class DialogAddOptionVote extends StatefulWidget {
  const DialogAddOptionVote(
      {Key? key,
      required this.nbChoice,
      required this.title,
      required this.body,
      required this.important})
      : super(key: key);
  final int nbChoice;
  final String title;
  final String body;
  final bool important;

  @override
  State<DialogAddOptionVote> createState() => _DialogAddOptionVoteState();
}

class _DialogAddOptionVoteState extends State<DialogAddOptionVote> {
  late List<OptionVotes> optionVotes;

  @override
  void initState() {
    super.initState();
    optionVotes = List<OptionVotes>.generate(
        widget.nbChoice, (int index) => OptionVotes());
  }

  @override
  Widget build(BuildContext context) {
    return Dialog(
      child: Container(
        width: 700,
        height: 300,
        color: Colors.white,
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Column(
            children: [
              Container(
                width: 1000,
                height: 200,
                child: Row(
                  children: [
                    Center(
                      child: Expanded(
                        child: ListView.builder(
                            scrollDirection: Axis.horizontal,
                            shrinkWrap: true,
                            addRepaintBoundaries: true,
                            itemCount: widget.nbChoice,
                            itemBuilder: (context, index) {
                              return Card(
                                shadowColor: DarkRedColor,
                                shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(10)),
                                elevation: 25,
                                child: Padding(
                                  padding: const EdgeInsets.all(8.0),
                                  child: Column(
                                    children: [
                                      Container(
                                        width: 150,
                                        height: 100,
                                        child: TextField(
                                          style: InputStyle,
                                          minLines: 1,
                                          maxLines: 5,
                                          onChanged: (text) {
                                            optionVotes[index].label = text;
                                          },
                                          autocorrect: true,
                                          decoration: InputDecoration(
                                            focusedBorder: OutlineInputBorder(
                                                borderSide: BorderSide(
                                                    color: DarkRedColor,
                                                    width: 2)),
                                            border: OutlineInputBorder(
                                              borderSide: const BorderSide(
                                                  color: Color(0xFFFFCBD0),
                                                  width: 3.0),
                                            ),
                                            labelText: 'option ${index + 1} ',
                                            floatingLabelStyle:
                                                TextStyle(color: Colors.red),
                                            /*
                                            errorText: _validateBody
                                                ? 'Cette valeur ne peut etre vide'
                                                : null,

                                             */
                                          ),
                                        ),
                                      ),
                                    ],
                                  ),
                                ),
                              );
                            }),
                      ),
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
                          final idVote = await addVotes(
                              widget.title,
                              widget.body,
                              "${widget.nbChoice}",
                              widget.important);

                          for (int i = 0; i < optionVotes.length; i++) {
                            print(optionVotes[i].label);
                            optionVotes[i].idVote = idVote;
                          }
                          addOptionVotes(optionVotes);
                          Navigator.pop(context);
                          Navigator.pop(context);
                        },
                        child: Text(
                          "Créer les options du vote",
                          style: RedButtonStyle,
                        )),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
