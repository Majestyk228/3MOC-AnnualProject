import 'package:exprimons_nous/objects/TextStyle.dart';
import 'package:flutter/material.dart';

import '../../objects/Colors.dart';
import '../../objects/user.dart';

class DetailsUserView extends StatefulWidget {
  const DetailsUserView({Key? key, required this.user}) : super(key: key);
  final User user;

  @override
  State<DetailsUserView> createState() => _DetailsUserViewState();
}

class _DetailsUserViewState extends State<DetailsUserView> {
  late TextEditingController firstName;
  late TextEditingController lastName;
  late TextEditingController birthDate;
  late TextEditingController gender;
  late TextEditingController areaCode;
  late TextEditingController email;

  @override
  void initState() {
    super.initState();
    firstName = TextEditingController();
    lastName = TextEditingController();
    birthDate = TextEditingController();
    gender = TextEditingController();
    areaCode = TextEditingController();
    email = TextEditingController();

    firstName.text = widget.user.firstName ?? "Failed to load";
    lastName.text = widget.user.lastName ?? "Failed to load";
    birthDate.text = widget.user.birthDate ?? "Failed to load";
    gender.text = widget.user.gender ?? "Failed to load";
    areaCode.text = widget.user.areaCode ?? "Failed to load";
    email.text = widget.user.email ?? "Failed to load";
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
            ],
          ),
          Center(
            child: Container(
              width: 1000,
              height: 100,

              //child: Text(votes[index]["title"])
              child: Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(25)),
                elevation: 2,
                color: DarkRedColor,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Container(
                      child: Text(
                        "Details de l'utilisateur",
                        style: TitleAddStyle,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
          SizedBox(
            height: 100,
          ),
          Center(
            child: Container(
              width: 1000,
              child: Card(
                shadowColor: DarkRedColor,
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(25)),
                elevation: 25,
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Column(
                    children: [
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          enabled: false,
                          controller: firstName,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: ' Prénom de l\'utilisateur',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          enabled: false,
                          controller: lastName,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Nom de l\'utilisateur',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          enabled: false,
                          controller: birthDate,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Date de naissance de l\'utilisateur',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          enabled: false,
                          controller: gender,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Genre de l\'utilisateur',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          enabled: false,
                          controller: areaCode,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Code postale de l\'utilisateur',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
                          enabled: false,
                          controller: email,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Email de l\'utilisateur',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                          ),
                        ),
                      ),
                      Container(
                        width: 400,
                        height: 75,
                        child: Padding(
                          padding: EdgeInsets.all(8.0),
                          child: Card(
                            shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(25)),
                            color: DarkRedColor,
                            elevation: 5,
                            child: TextButton(
                              onPressed: () {
                                showDialog<String>(
                                    context: context,
                                    builder: (BuildContext context) =>
                                        AlertDialog(
                                          title: Text(
                                              'Supression de l\'utilisateur ${widget.user.lastName} ${widget.user.firstName}'),
                                          content: const Text(
                                              'Voullez vous vraiment supprimer l\'utilisateur ?'),
                                          actions: <Widget>[
                                            TextButton(
                                              onPressed: () =>
                                                  Navigator.pop(context),
                                              child: const Text('Non'),
                                            ),
                                            TextButton(
                                              onPressed: () async {
                                                await deleteUser(
                                                    widget.user.idUser!,context);
                                                Navigator.pop(context, 'OK');
                                                Navigator.pop(context);
                                              },
                                              child: const Text('Oui'),
                                            ),
                                          ],
                                        ));
                              },
    /*
                              onPressed: () async {
                                await deleteUser(widget.user.idUser!);
                                Navigator.pop(context);
                              },*/
                              child: Text(
                                "Supprimer l'utilisateur",
                                style: RedButtonStyle,
                              ),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}
