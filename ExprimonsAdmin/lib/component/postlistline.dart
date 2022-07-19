import 'package:exprimons_nous/Child/Details/detailspostview.dart';
import 'package:flutter/material.dart';

import '../objects/post.dart';

class PostListLine extends StatefulWidget {
  const PostListLine({Key? key,required this.post}) : super(key: key);
  final Post post;
  @override
  State<PostListLine> createState() => _PostListLineState();
}

class _PostListLineState extends State<PostListLine> {
  @override
  Widget build(BuildContext context) {
    return MouseRegion(
      cursor: SystemMouseCursors.click,
      child: GestureDetector(
        onTap: () {
          //go to  post details
          Navigator.push(
            context,
            MaterialPageRoute(
                builder: (context) => DetailsPostView(post: widget.post)),
          );
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
                  child: Text(widget.post.title ?? "Failed to load"),
                ),
                Container(
                  width: 150,
                  child: Text(widget.post.date ?? "Failed to load"),
                ),
                Container(
                  width: 150,
                  child: Text("${widget.post.reported}"),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
