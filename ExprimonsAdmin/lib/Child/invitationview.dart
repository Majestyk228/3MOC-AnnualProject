import 'dart:convert';

import 'package:exprimons_nous/objects/invitation.dart';
import 'package:flutter/material.dart';

import '../Colors.dart';
import 'package:http/http.dart' as http;
import '../Globals.dart';

class InvitationView extends StatefulWidget {
  const InvitationView({Key? key}) : super(key: key);

  @override
  State<InvitationView> createState() => _InvitationViewState();
}

class _InvitationViewState extends State<InvitationView> {
  var invitation = [];

  @override
  void initState() {
    refreshInvitation();
    super.initState();
  }

  Future refreshInvitation() async {
    //endpoint
    Uri uri = Uri.parse("https://www.titan-photography.com/invite/all");
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

    this.invitation = [];
    setState(() {
      for (var i = 0; i < data.length; i++) {
        Invitation uneInvitation = Invitation(
          code: data[i]['code'],
          idCommunity: data[i]['idCommunity'],
          creationDate: data[i]['creationDate'],
          endDate: data[i]['endDate'],
        );

        invitation.add(uneInvitation);
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
                    onPressed: () async {
                      await addInvitation();
                      refreshInvitation();
                    },
                    child: Container(
                      width: 200,
                      height: 75,
                      child: Center(
                        child: Text("Add User"),
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
                  itemCount: invitation.length,
                  itemBuilder: (context, index) {
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
                              child: Text("${invitation[index].code}"),
                            ),
                            Container(
                              width: 150,
                              child: Text(invitation[index].endDate),
                            ),
                          ],
                        ),
                      ),
                    );
                  }),
            ),
          )
        ],
      ),
    );
  }
}
