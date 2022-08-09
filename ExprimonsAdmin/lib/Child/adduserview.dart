import 'package:exprimons_nous/TextStyle.dart';
import 'package:exprimons_nous/objects/user.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

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
  late TextEditingController birthDate;

  bool _validatefirstName = true;
  bool _validatelastName = true;

  bool _validateareaCode = true;
  bool _validateEmail = true;
  bool _validatePassword = true;

  String errorMessagePassword = "Cette valeur ne peut etre vide";
  String errorMessageEmail = "Cette valeur ne peut etre vide";
  String errorMessageAreaCode = "Cette valeur ne peut etre vide";

  DateTime selectedDate = DateTime.now();

  _selectDate(BuildContext context) async {
    final DateTime? selected = await showDatePicker(
      initialEntryMode: DatePickerEntryMode.calendarOnly,
      context: context,
      locale: const Locale("fr", "FR"),
      initialDate: selectedDate,
      firstDate: DateTime(1900),
      lastDate: DateTime.now(),
    );
    if (selected != null && selected != selectedDate)
      setState(() {
        selectedDate = selected;
        birthDate.text =
            "${selectedDate.day.toString().padLeft(2, '0')}/${selectedDate.month.toString().padLeft(2, '0')}/${selectedDate.year.toString()}";
      });
  }

  @override
  void initState() {
    super.initState();
    firstName = TextEditingController();
    lastName = TextEditingController();
    birthDate = TextEditingController();
    birthDate.text = "00/00/0000";
    areaCode = TextEditingController();
    email = TextEditingController();
    password = TextEditingController();
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
                          style: InputStyle,
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
                        child: Row(
                          children: [
                            Container(
                              width: 150,
                              child: TextField(
                                enabled: false,
                                style: InputStyle,
                                controller: birthDate,
                                obscureText: false,
                                decoration: InputDecoration(
                                  focusedBorder: OutlineInputBorder(
                                      borderSide: BorderSide(
                                          color: DarkRedColor, width: 2)),
                                  border: OutlineInputBorder(
                                    borderSide: const BorderSide(
                                        color: Color(0xFFFFCBD0), width: 3.0),
                                  ),
                                ),
                              ),
                            ),
                            Card(
                              shape: RoundedRectangleBorder(
                                  borderRadius: BorderRadius.circular(25)),
                              color: DarkRedColor,
                              elevation: 5,
                              child: TextButton(
                                onPressed: () {
                                  _selectDate(context);
                                },
                                child: Text(
                                  "Choisisez une date",
                                  style: InputButtonDateStyle,
                                ),
                              ),
                            )
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
                          style: InputStyle,
                          controller: areaCode,
                          autocorrect: true,
                          keyboardType: TextInputType.number,
                          inputFormatters: <TextInputFormatter>[
                            // for below version 2 use this
                            FilteringTextInputFormatter.allow(RegExp(r'[0-9]')),
                            // for version 2 and greater youcan also use this
                            FilteringTextInputFormatter.digitsOnly
                          ],
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
                                ? errorMessageAreaCode
                                : null,
                          ),
                          onChanged: (text) {
                            setState(() {
                              if (text == "") {
                                _validateareaCode = true;
                                errorMessageAreaCode = "Cette valeur ne peut etre vide";
                              } else if(text.length!=5){
                                _validateareaCode=true;
                                errorMessageAreaCode = "Code Postale non valide";

                              }
                              else {
                                _validateareaCode = false;
                              }
                            });
                          },
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: TextField(
                          style: InputStyle,
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
                                ? errorMessageEmail
                                : null,
                          ),
                          onChanged: (text) {
                            setState(() {
                              if (text == "") {
                                _validateEmail = true;
                                errorMessageEmail="Cette valeur ne peut etre vide";
                              } else if (!text.contains('@') ||
                                  !text.contains('.')) {
                                _validateEmail = true;
                                errorMessageEmail="Email non valide";
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
                          style: InputStyle,
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
                            errorText:
                                _validatePassword ? errorMessagePassword : null,
                          ),
                          onChanged: (text) {
                            setState(() {
                              if (text == "") {
                                _validatePassword = true;
                                errorMessagePassword =
                                    "Cette valeur ne peut etre vide";
                              } else if (text.length < 8) {
                                _validatePassword = true;
                                errorMessagePassword =
                                    "Le mot de passe n'est pas assez long";
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
                                  if (_validatefirstName == true ||
                                      _validatelastName == true ||
                                      _validateareaCode == true ||
                                      _validateEmail == true ||
                                      _validatePassword == true ||
                                      birthDate.text == "00/00/0000") {
                                    setState(() {});
                                  } else {
                                    addUser(
                                        firstName.text,
                                        lastName.text,
                                        "${selectedDate.year.toString()}-${selectedDate.month.toString().padLeft(2, '0')}-${selectedDate.day.toString().padLeft(2, '0')}",
                                        gender,
                                        areaCode.text,
                                        email.text,
                                        password.text);
                                    Navigator.pop(context);
                                  }
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
