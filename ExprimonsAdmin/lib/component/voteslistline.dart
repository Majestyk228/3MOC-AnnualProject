import 'package:exprimons_nous/objects/votes.dart';
import 'package:flutter/material.dart';

class VotesListLine extends StatefulWidget {
  const VotesListLine({
    Key? key,
    required this.votes,
  }) : super(key: key);

  final Vote votes;

  @override
  State<VotesListLine> createState() => _VotesListLineState();
}

class _VotesListLineState extends State<VotesListLine> {

  @override
  void initState() {

    super.initState();

  }

  @override
  Widget build(BuildContext context) {
    return MouseRegion(
      cursor: SystemMouseCursors.click,
      child: GestureDetector(
        onTap: (){
          //go to  vote details
        },
        child: Container(
          width: 300,
          height: 100,

          //child: Text(votes[index]["title"])
          child: Card(
            elevation: 2,
            color: Colors.white,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                Container(
                  width: 150,
                  child: Text(widget.votes.title??"Failed to load"),
                ),
                Container(
                  width: 150,
                  child: Text(widget.votes.voteEnds??"Failed to load"),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}