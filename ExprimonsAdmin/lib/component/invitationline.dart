import 'package:exprimons_nous/objects/TextStyle.dart';
import 'package:exprimons_nous/objects/invitation.dart';
import 'package:flutter/material.dart';

import '../objects/Colors.dart';

class InvitationLine extends StatefulWidget {
  const InvitationLine(
      {Key? key, required this.invitation, required this.index})
      : super(key: key);
  final Invitation invitation;
  final int index;

  @override
  State<InvitationLine> createState() => _InvitationLineState();
}

class _InvitationLineState extends State<InvitationLine> {
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
                "${widget.invitation.code}",
                style: DataTableStyle,
              ),
            ),
            Container(
              width: 230,
              child: Text(
                "${widget.invitation.creationDate}",
                style: DataTableStyle,
              ),
            ),
            Container(
              width: 200,
              child: Text(
                "${widget.invitation.endDate}",
                style: DataTableStyle,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
