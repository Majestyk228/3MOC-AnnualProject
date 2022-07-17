import 'package:exprimons_nous/Child/addvoteview.dart';
import 'package:exprimons_nous/Globals.dart';
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
    Uri uri = Uri.parse("https://www.titan-photography.com/vote/voteList/${currentAdmin.idCommunity}");
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
          nbChoice: data[i]['nbChoice'],
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
      color: ultraLightRedColor,
      child: Column(
        children: [
          Row(
            children: [
              Card(
                elevation: 2,
                color: Colors.white,
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
                        child: Text("Add Vote"),
                      ),
                    )),
              ),
            ],
          ),
          SizedBox(
            height: 100,
          ),
          Expanded(
            child: SingleChildScrollView(
              scrollDirection: Axis.vertical,
              child: ListView.builder(
                  scrollDirection: Axis.vertical,
                  shrinkWrap: true,
                  addRepaintBoundaries: false,
                  itemCount: votes.length,
                  itemBuilder: (context, index) {
                    return VotesListLine(votes: votes[index]);
                  }),
            ),
          )
        ],
      ),
    );
  }
}


