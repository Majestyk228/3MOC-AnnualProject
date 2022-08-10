import 'dart:convert';

import 'package:exprimons_nous/TextStyle.dart';
import 'package:exprimons_nous/objects/votes.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import '../../Colors.dart';
import '../../objects/optionvote.dart';

class DetailsVoteView extends StatefulWidget {
  const DetailsVoteView({Key? key, required this.vote}) : super(key: key);
  final Vote vote;

  @override
  State<DetailsVoteView> createState() => _DetailsVoteViewState();
}

class _DetailsVoteViewState extends State<DetailsVoteView> {
  late TextEditingController title;
  late TextEditingController body;
  late TextEditingController nbChoice;

  bool important = false;

  //late List<OptionVotes> optionVotes;

  @override
  void initState() {
    /*refreshOptionVotes();*/
    super.initState();
    title = TextEditingController();
    body = TextEditingController();
    nbChoice = TextEditingController();
    title.text=widget.vote.title??"Failed to load";
    body.text=widget.vote.body?? "Failed to load";
    if(widget.vote.nbChoice==null){
      nbChoice.text="Failed to load";
    }
    else{
      nbChoice.text="${widget.vote.nbChoice!}";
    }
    if (widget.vote.important==0){
      important=false;
    }
    else{
      important=true;
    }


  }

  /*Future refreshOptionVotes() async {
    //endpoint
    Uri uri = Uri.parse("https://www.titan-photography.com/vote/voteinfo");
    //methode get du package HTTP
    print(widget.vote.idVote);
    var body = jsonEncode({
      "idVote": 53
      //"idVote": widget.vote.idVote
    });
    print(body);
    final response = await http.post(uri,
        headers: {
          "Access-Control-Allow-Origin": "*",
          // Required for CORS support to work
          "Access-Control-Allow-Headers":
              "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
          "Access-Control-Allow-Methods": "POST, OPTIONS"
        },
        body: body);

    //parsing du JSON de la réponse
    var data = json.decode(response.body);
  print(data);
    this.optionVotes = [];
    setState(() {
      for (var i = 0; i < data[0]['voteOptions'].length; i++) {
        OptionVotes uneOptionVote = OptionVotes(
          label: data[0]['voteOptions']['label'],
          idVote: widget.vote.idVote
        );

        optionVotes.add(uneOptionVote);
      }
    });
  }*/

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
                        "Detail d'un Vote",
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

              child: Card(
                elevation: 10,
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Column(
                    children: [
                      TextField(
                        enabled: false,
                        controller: title,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer le titre du vote ici'),
                      ),
                      TextField(
                        enabled: false,
                        controller: body,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer le descriptif du vote ici'),
                      ),
                      TextField(
                        enabled: false,
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
                          Text("si coché le vote est important"),
                          Checkbox(
                            value: important,
                            onChanged: null,
                          ),
                        ],
                      ),
                     /* TextButton(
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
                                          ],
                                        ),
                                      ),
                                    );
                                  });
                            }
                          },
                          child: Text("Créer le vote")),*/
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
