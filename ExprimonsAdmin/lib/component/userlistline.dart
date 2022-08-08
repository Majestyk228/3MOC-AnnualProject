import 'package:exprimons_nous/TextStyle.dart';
import 'package:exprimons_nous/objects/user.dart';
import 'package:flutter/material.dart';

import '../Colors.dart';

class UserListLine extends StatefulWidget {
  const UserListLine({Key? key, required this.user, required this.index}) : super(key: key);
  final User user;
  final int index;
  @override
  State<UserListLine> createState() => _UserListLineState();
}

class _UserListLineState extends State<UserListLine> {

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
              width: 200,
              child: Text(widget.user.lastName ?? "Failed to load",style: DataTableStyle,),
            ),
            Container(
              width: 200,
              child: Text(widget.user.firstName ?? "Failed to load",style: DataTableStyle,),
            ),
            Container(
              width: 190,
              child: Text("${widget.user.areaCode}",style: DataTableStyle,),
            ),
            Container(
              width: 150,
              child: Text("${widget.user.points}",style: DataTableStyle,),
            )
          ],
        ),
      ),
    );
  }
}
