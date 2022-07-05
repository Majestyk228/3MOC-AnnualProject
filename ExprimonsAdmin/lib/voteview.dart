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
  var votes=[];
  @override
  void initState() {
    refreshCocktails();
    super.initState();
  }
  Future refreshCocktails() async {
    //endpoint
    Uri uri = Uri.parse("https://www.titan-photography.com/vote/voteList/1");

    //methode get du package HTTP
    final response = await http.get(uri);

    //parsing du JSON de la réponse
    var data = json.decode(response.body);

    this.votes = [];
    setState(() {
      for (var i = 0; i < data.length; i++) {
        Vote unCocktail = Vote(
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
        votes.add(unCocktail);

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
          ListView.builder(
              shrinkWrap: true,
              addRepaintBoundaries: false,
              itemCount: 10,
              itemBuilder: (context, index) {
                return Container(
                 child: Text(index.toString())
                );
              })
        ],
      ),
    );
  }
}
