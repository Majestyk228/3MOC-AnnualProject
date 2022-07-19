import 'package:flutter/material.dart';

import '../../Colors.dart';
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
    firstName.text=widget.user.firstName??"Failed to load";
    lastName.text=widget.user.lastName??"Failed to load";
    birthDate.text=widget.user.birthDate??"Failed to load";
    gender.text=widget.user.gender??"Failed to load";
    areaCode.text=widget.user.areaCode??"Failed to load";
    email.text=widget.user.email??"Failed to load";
    password.text=widget.user.password??"Failed to load";
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
                        enabled: false,
                        controller: firstName,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer le prénom de l\'utilisateur ici'),
                      ),
                      TextField(
                        enabled: false,
                        controller: lastName,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer le nom de l\'utilisateur ici'),
                      ),
                      TextField(
                        enabled: false,
                        controller: birthDate,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer la date de naissance de l\'utilisateur ici'),
                      ),
                      TextField(
                        enabled: false,
                        controller: gender,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer le genre de l\'utilisateur ici'),
                      ),
                      TextField(
                        enabled: false,
                        controller: areaCode,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer le code postale de l\'utilisateur ici'),
                      ),
                      TextField(
                        enabled: false,
                        controller: email,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer l\'email de l\'utilisateur ici'),
                      ),
                      TextField(
                        enabled: false,
                        obscureText: true,
                        controller: password,
                        autocorrect: true,
                        decoration: InputDecoration(
                            hintText: 'Entrer le mot de passe de l\'utilisateur ici'),
                      ),
                      TextButton(
                          onPressed: () async {
                            await deleteUser(widget.user.idUser!);
                            Navigator.pop(context);
                          },
                          child: Text("Supprimer l'utilisateur")),
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
