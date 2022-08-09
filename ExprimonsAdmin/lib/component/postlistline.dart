import 'package:exprimons_nous/Child/Details/detailspostview.dart';
import 'package:flutter/material.dart';

import '../Colors.dart';
import '../TextStyle.dart';
import '../objects/post.dart';

class PostListLine extends StatefulWidget {
  const PostListLine({Key? key, required this.post, required this.index})
      : super(key: key);
  final Post post;
  final int index;

  @override
  State<PostListLine> createState() => _PostListLineState();
}

class _PostListLineState extends State<PostListLine> {
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
                widget.post.title ?? "Failed to load",
                style: DataTableStyle,
              ),
            ),
            Container(
              width: 150,
              child: Text(
                widget.post.date ?? "Failed to load",
                style: DataTableStyle,
              ),
            ),
            Container(
              width: 150,
              child: Text(
                "${widget.post.likes}",
                style: DataTableStyle,
              ),
            ),
            Container(
              width: 150,
              child: Text(
                "${widget.post.dislikes}",
                style: DataTableStyle,
              ),
            ),
            Container(
              width: 150,
              child: Text(
                "${widget.post.reported}",
                style: DataTableStyle,
              ),
            )
          ],
        ),
      ),
    );
  }
}
