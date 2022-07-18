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
  late TextEditingController birthDate;
  late TextEditingController gender;
  late TextEditingController areaCode;
  late TextEditingController email;
  late TextEditingController password;


  @override
  void initState() {
    super.initState();
    firstName = TextEditingController();
    lastName = TextEditingController();
    birthDate = TextEditingController();
    gender = TextEditingController();
    areaCode = TextEditingController();
    email = TextEditingController();
    password = TextEditingController();
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
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Container(
                  width: 50,
                  height: 50,
                  child: Card(
                    color: Colors.white,
                    elevation: 2,
                    child: TextButton(
                      onPressed: () {
                        Navigator.pop(context);
                      },
                      child: Center(
                        child: Text(
                          style: TextStyle(fontSize: 30),
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
              width: 500,

              child: Card(
                elevation: 10,
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Column(
                    children: [
                      TextField(
                        controller: firstName,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer le prénom de l\'utilisateur ici'),
                      ),
                      TextField(
                        controller: lastName,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer le nom de l\'utilisateur ici'),
                      ),
                      TextField(
                        controller: birthDate,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer la date de naissance de l\'utilisateur ici'),
                      ),
                      TextField(
                        controller: gender,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer le genre de l\'utilisateur ici'),
                      ),
                      TextField(
                        controller: areaCode,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer le code postale de l\'utilisateur ici'),
                      ),
                      TextField(
                        controller: email,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer l\'email de l\'utilisateur ici'),
                      ),
                      TextField(
                        obscureText: true,
                        controller: password,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer le mot de passe de l\'utilisateur ici'),
                      ),
                      TextButton(
                          onPressed: () {
                            addUser(firstName.text, lastName.text,birthDate.text,gender.text,areaCode.text,email.text,password.text);
                            Navigator.pop(context);
                          },
                          child: Text("Créer l'utilisateur")),
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
