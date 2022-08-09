import 'package:exprimons_nous/TextStyle.dart';
import 'package:exprimons_nous/objects/user.dart';
import 'package:flutter/material.dart';

import '../Colors.dart';

class AddUserView extends StatefulWidget {
  const AddUserView({Key? key}) : super(key: key);

  @override
  State<AddUserView> createState() => _AddUserViewState();
}

class _AddUserViewState extends State<AddUserView> {
  late TextEditingController firstName;
  late TextEditingController lastName;

  String gender = "Veuillez spécifier un genre";
  late TextEditingController areaCode;
  late TextEditingController email;
  late TextEditingController password;

  bool _validatefirstName = false;
  bool _validatelastName = false;
  bool _validateareaCode = false;
  bool _validateEmail = false;
  bool _validatePassword = false;

  DateTime selectedDate = DateTime.now();

  _selectDate(BuildContext context) async {
    final DateTime? selected = await showDatePicker(
      context: context,
      initialDate: selectedDate,
      firstDate: DateTime(2010),
      lastDate: DateTime(2025),
    );
    if (selected != null && selected != selectedDate)
      setState(() {
        selectedDate = selected;
      });
  }

  @override
  void initState() {
    super.initState();
    firstName = TextEditingController();
    lastName = TextEditingController();

    areaCode = TextEditingController();
    email = TextEditingController();
    password = TextEditingController();
    print(selectedDate);
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
                        "Ajout d'utilisateur",
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
                shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(25)),
                elevation: 25,
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Column(
                    children: [
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
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
                            labelText: 'Entrer le prénom de l\'utilisateur ici',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                            errorText: _validatefirstName
                                ? 'Cette valeur ne peut etre vide'
                                : null,
                          ),
                          onChanged: (text) {
                            setState(() {
                              if (text == "") {
                                _validatefirstName = true;
                              } else {
                                _validatefirstName = false;
                              }
                            });
                          },
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          controller: lastName,
                          obscureText: false,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText: 'Entrez le nom de l\'utilisateur ici',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                            errorText: _validatelastName
                                ? 'Cette valeur ne peut etre vide'
                                : null,
                          ),
                          onChanged: (text) {
                            setState(() {
                              if (text == "") {
                                _validatelastName = true;
                              } else {
                                _validatelastName = false;
                              }
                            });
                          },
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Column(
                          children: [
                            TextButton(
                              onPressed: () {
                                _selectDate(context);
                              },
                              child: Text("Choisisez une date"),
                            ),
                            Text(
                                "${selectedDate.day}/${selectedDate.month}/${selectedDate.year}")
                          ],
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: DropdownButton<String>(
                            value: gender,
                            icon: const Icon(Icons.arrow_downward),
                            elevation: 16,
                            underline: Container(
                              height: 2,
                              color: Colors.grey,
                            ),
                            onChanged: (String? newValue) {
                              setState(() {
                                gender = newValue!;
                              });
                            },
                            items: <String>[
                              'Veuillez spécifier un genre',
                              'Homme',
                              'Femme',
                              'Autre'
                            ].map<DropdownMenuItem<String>>((String value) {
                              return DropdownMenuItem<String>(
                                value: value,
                                child: Text(value),
                              );
                            }).toList(),
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
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
                            labelText:
                                'Entrer le code postale de l\'utilisateur ici',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                            errorText: _validateareaCode
                                ? 'Cette valeur ne peut etre vide'
                                : null,
                          ),
                          onChanged: (text) {
                            setState(() {
                              if (text == "") {
                                _validateareaCode = true;
                              } else {
                                _validateareaCode = false;
                              }
                            });
                          },
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
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
                            labelText: 'Entrer l\'email de l\'utilisateur ici',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                            errorText: _validateEmail
                                ? 'Cette valeur ne peut etre vide'
                                : null,
                          ),
                          onChanged: (text) {
                            setState(() {
                              if (text == "") {
                                _validateEmail = true;
                              } else {
                                _validateEmail = false;
                              }
                            });
                          },
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          obscureText: true,
                          controller: password,
                          autocorrect: true,
                          decoration: InputDecoration(
                            focusedBorder: OutlineInputBorder(
                                borderSide:
                                    BorderSide(color: DarkRedColor, width: 2)),
                            border: OutlineInputBorder(
                              borderSide: const BorderSide(
                                  color: Color(0xFFFFCBD0), width: 3.0),
                            ),
                            labelText:
                                'Entrer le mot de passe de l\'utilisateur ici',
                            floatingLabelStyle: TextStyle(color: Colors.red),
                            errorText: _validatePassword
                                ? 'Cette valeur ne peut etre vide'
                                : null,
                          ),
                          onChanged: (text) {
                            setState(() {
                              if (text == "") {
                                _validatePassword = true;
                              } else {
                                _validatePassword = false;
                              }
                            });
                          },
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
                                  addUser(
                                      firstName.text,
                                      lastName.text,
                                      "${selectedDate.year.toString()}-${selectedDate.month.toString().padLeft(2, '0')}-${selectedDate.day.toString().padLeft(2, '0')}",
                                      gender,
                                      areaCode.text,
                                      email.text,
                                      password.text);
                                  Navigator.pop(context);
                                },
                                child: Text(
                                  "Créer l'utilisateur",
                                  style: RedButtonStyle,
                                )),
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
