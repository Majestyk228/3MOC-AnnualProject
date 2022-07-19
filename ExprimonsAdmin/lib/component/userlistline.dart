import 'package:exprimons_nous/objects/user.dart';
import 'package:flutter/material.dart';

class UserListLine extends StatefulWidget {
  const UserListLine({Key? key, required this.user}) : super(key: key);
  final User user;

  @override
  State<UserListLine> createState() => _UserListLineState();
}

class _UserListLineState extends State<UserListLine> {
  @override
  Widget build(BuildContext context) {
    return Container(
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
              child: Text(widget.user.lastName ?? "Failed to load"),
            ),
            Container(
              width: 150,
              child: Text(widget.user.firstName ?? "Failed to load"),
            ),
            Container(
              width: 150,
              child: Text("${widget.user.points}"),
            )
          ],
        ),
      ),
    );
  }
}
