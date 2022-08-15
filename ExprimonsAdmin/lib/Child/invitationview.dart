import 'dart:convert';

import 'package:exprimons_nous/TextStyle.dart';
import 'package:exprimons_nous/component/invitationline.dart';
import 'package:exprimons_nous/objects/invitation.dart';
import 'package:flutter/material.dart';

import '../Colors.dart';
import 'package:http/http.dart' as http;
import 'dart:html' as html;

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
    Uri uri = Uri.parse(
        "https://www.titan-photography.com/invite/allByCommunity/${html.window.localStorage["idAdmin"]}");
    //methode get du package HTTP
    final response = await http.get(
      uri,
      headers: {
        "Access-Control-Allow-Origin": "*", // Required for CORS support to work
        "Access-Control-Allow-Headers":
            "Origin,Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,locale",
        "Access-Control-Allow-Methods": "POST, OPTIONS",
        "token":html.window.localStorage["token"]!
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
      color: Colors.white,
      child: Column(
        children: [
          Row(
            children: [
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Container(
                  width: 75,
                  height: 75,
                  child: Card(
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(25)),
                    color: DarkRedColor,
                    elevation: 2,
                    child: TextButton(
                      onPressed: () {
                        Navigator.pop(context);
                      },
                      child: Center(
                        child: Text(
                          style: RedButtonStyle,
                          "<",
                        ),
                      ),
                    ),
                  ),
                ),
              ),
              Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(25)),
                elevation: 2,
                color: DarkRedColor,
                child: TextButton(
                    onPressed: () async {
                      await addInvitation();
                      refreshInvitation();
                    },
                    child: Container(
                      width: 300,
                      height: 75,
                      child: Center(
                        child: Text(
                          "Ajouter une invitation",
                          style: RedButtonStyle,
                        ),
                      ),
                    )),
              ),
            ],
          ),
          SizedBox(
            height: 100,
          ),
          Center(
            child: Container(
              width: 1000,
              height: 100,

              //child: Text(votes[index]["title"])
              child: Card(
                elevation: 2,
                color: Colors.grey,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Container(
                      child: Text(
                        "Invitation de la communauté",
                        style: TitleTableStyle,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
          Container(
            width: double.infinity,
            height: 100,

            //child: Text(votes[index]["title"])
            child: Card(
              elevation: 2,
              color: veryDarkRedColor,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: [
                  Container(
                    width: 150,
                    child: Text(
                      "Code",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 230,
                    child: Text(
                      "Date de création",
                      style: HeaderTableStyle,
                    ),
                  ),
                  Container(
                    width: 200,
                    child: Text(
                      "Date de fin",
                      style: HeaderTableStyle,
                    ),
                  ),
                ],
              ),
            ),
          ),
          Expanded(
            child: SingleChildScrollView(
              scrollDirection: Axis.vertical,
              child: Wrap(
                children: [
                  ListView.builder(
                      scrollDirection: Axis.vertical,
                      shrinkWrap: true,
                      addRepaintBoundaries: false,
                      itemCount: invitation.length,
                      itemBuilder: (context, index) {
                        return MouseRegion(
                          cursor: SystemMouseCursors.click,
                          child: GestureDetector(
                            onTap: () => showDialog<String>(
                              context: context,
                              builder: (BuildContext context) => AlertDialog(
                                title: Text(
                                    'Supression de l\'invitation ${invitation[index].code}'),
                                content: const Text(
                                    'Voullez vous vraiment supprimer cette invitation ?'),
                                actions: <Widget>[
                                  TextButton(
                                    onPressed: () => Navigator.pop(context),
                                    child: const Text('Non'),
                                  ),
                                  TextButton(
                                    onPressed: () async {
                                      Navigator.pop(context, 'OK');

                                      await deleteInvitation(
                                          invitation[index].code);
                                      refreshInvitation();
                                    },
                                    child: const Text('Oui'),
                                  ),
                                ],
                              ),
                            ),
                            child: InvitationLine(
                                invitation: invitation[index], index: index),
                          ),
                        );
                      }),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
