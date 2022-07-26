import 'package:exprimons_nous/Colors.dart';
import 'package:exprimons_nous/objects/votes.dart';
import 'package:flutter/material.dart';

class VotesListLine extends StatefulWidget {
  const VotesListLine({Key? key, required this.votes, required this.index}) : super(key: key);

  final Vote votes;
  final int index;

  @override
  State<VotesListLine> createState() => _VotesListLineState();
}

class _VotesListLineState extends State<VotesListLine> {
  @override
  late Color color;
  void initState() {
    if(widget.index % 2 == 0){
      color=Colors.white;
    }
    else{
      color=veryLightRedColor;
    }
    super.initState();


  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 300,
      height: 100,


      //child: Text(votes[index]["title"])
      child: Card(
        elevation: 2,
        color: color,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            Container(
              width: 150,
              child: Text(widget.votes.title ?? "Failed to load",style: TextStyle(fontSize: 16),),
            ),
            Container(
              width: 160,
              child: Text("${widget.votes.nbChoice}",style: TextStyle(fontSize: 16), ),
            ),
            Container(
              width: 200,
              child: Text(widget.votes.voteBegins ?? "Failed to load",style: TextStyle(fontSize: 16),),
            ),
            Container(
              width: 160,
              child: Text(widget.votes.voteEnds ?? "Failed to load",style: TextStyle(fontSize: 16),),
            ),
            Container(
              width: 150,
              child: Text("${widget.votes.important}",style: TextStyle(fontSize: 16), ),
            ),
          ],
        ),
      ),
    );
  }
}
