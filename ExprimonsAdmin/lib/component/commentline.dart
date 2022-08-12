import 'package:exprimons_nous/objects/comment.dart';
import 'package:flutter/material.dart';
import 'package:exprimons_nous/TextStyle.dart';

import '../Colors.dart';

class CommentLine extends StatefulWidget {
  const CommentLine({Key? key, required this.comment, required this.index}) : super(key: key);
final Comment comment;
final int index;
  @override
  State<CommentLine> createState() => _CommentLineState();
}

class _CommentLineState extends State<CommentLine> {
  @override
  late Color color;

  void initState() {
    if (widget.index % 2 == 0) {
      color = Colors.white;
    } else {
      color = veryLightRedColor;
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
              child: Text(
                "${widget.comment.body}",
                style: DataTableStyle,
              ),
            ),
            Container(
              width: 230,
              child: Text(
                "${widget.comment.date}",
                style: DataTableStyle,
              ),
            ),
            Container(
              width: 200,
              child: Text(
                "${widget.comment.likes}",
                style: DataTableStyle,
              ),
            ),
            Container(
              width: 200,
              child: Text(
                "${widget.comment.dislikes}",
                style: DataTableStyle,
              ),
            ),
            Container(
              width: 200,
              child: Text(
                "${widget.comment.reports}",
                style: DataTableStyle,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
