import 'package:exprimons_nous/Child/Details/detailsvoteview.dart';
import 'package:exprimons_nous/Child/addvoteview.dart';
import 'package:exprimons_nous/Globals.dart';
import 'package:exprimons_nous/TextStyle.dart';
import 'package:exprimons_nous/component/voteslistline.dart';
import 'package:exprimons_nous/loginview.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:exprimons_nous/Colors.dart';
import 'package:flutter/material.dart';

import 'objects/votes.dart';

class VoteView extends StatefulWidget {
  const VoteView({Key? key}) : super(key: key);

  @override
  State<VoteView> createState() => _VoteViewState();
}

class _VoteViewState extends State<VoteView> {
  var votes = [];

  @override
  void initState() {
    refreshVotes();
    super.initState();
  }

  Future refreshVotes() async {
    //endpoint
    Uri uri = Uri.parse(
        "https://www.titan-photography.com/vote/voteList/${currentAdmin.idCommunity}");
    //methode get du package HTTP
    final response = await http.get(
      uri,
      headers: {
        "Access-Control-Allow-Origin": "*", // Required for CORS support to work
        "Access-Control-Allow-Headers":
            "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS"
      },
    );

    //parsing du JSON de la réponse
    var data = json.decode(response.body);

    this.votes = [];
    setState(() {
      for (var i = 0; i < data.length; i++) {
        Vote unVote = Vote(
          idVote: data[i]['idVote'],
          title: data[i]['title'],
          body: data[i]['body'],
          nbChoice: data[i]['nbChoices'],
          important: data[i]['important'],
          idUser: data[i]['idUser'],
          idAdmin: data[i]['idAdmin'],
          voteBegins: data[i]['voteBegins'],
          voteEnds: data[i]['voteEnds'],
          idCommunity: data[i]['idCommunity'],
        );
        votes.add(unVote);
      }
    });
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
              Card(
                shape:  RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(25)),
                elevation: 10,
                color: DarkRedColor,
                child: TextButton(
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const AddVoteView()),
                      );
                    },
                    child: Container(
                      width: 200,
                      height: 75,
                      child: Center(
                        child: Text("Add Vote",style: RedButtonStyle),
                      ),
                    )),
              ),
            ],
          ),
          SizedBox(
            height: 100,
          ),
          Center(
            child: Container(
              width: 1000,
              height: 100,

              //child: Text(votes[index]["title"])
              child: Card(
                elevation: 2,
                color: Colors.grey,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Container(
                      child: Text(
                        "Vote de la communauté",
                        style: TitleTableStyle,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
          Container(
            width: double.infinity,
            height: 100,

            //child: Text(votes[index]["title"])
            child: Card(
              elevation: 2,
              color: veryDarkRedColor,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  Container(
                    width: 150,
                    child: Text(
                      "Titre",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 160,
                    child: Text(
                      "Nb choix",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 200,
                    child: Text(
                      "Début du vote",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 160,
                    child: Text(
                      "Fin du vote",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 150,
                    child: Text(
                      "Important",
                      style: HeaderTableStyle,
                    ),
                  ),
                ],
              ),
            ),
          ),
          Expanded(
            child: SingleChildScrollView(
              scrollDirection: Axis.vertical,
              child: Wrap(
                children: [
                  ListView.builder(
                      scrollDirection: Axis.vertical,
                      shrinkWrap: true,
                      addRepaintBoundaries: false,
                      itemCount: votes.length,
                      itemBuilder: (context, index) {
                        return MouseRegion(
                            cursor: SystemMouseCursors.click,
                            child: GestureDetector(
                                onTap: () async {
                                  final value = await Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) => DetailsVoteView(
                                              vote: votes[index],
                                            )),
                                  );
                                },
                                child: VotesListLine(
                                  votes: votes[index],
                                  index: index,
                                )));
                      }),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}