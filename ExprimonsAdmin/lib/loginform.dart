import 'package:exprimons_nous/Globals.dart';
import 'package:exprimons_nous/homeview.dart';
import 'package:exprimons_nous/objects/admin.dart';
import 'package:flutter/material.dart';

class LoginForm extends StatefulWidget {
  const LoginForm({Key? key}) : super(key: key);

  @override
  State<LoginForm> createState() => _LoginFormState();
}

class _LoginFormState extends State<LoginForm> {
  late TextEditingController email;
  late TextEditingController password;

  @override
  void initState() {
    super.initState();
    email = TextEditingController();
    password = TextEditingController();
  }

  @override
  Widget build(BuildContext context) {
    return Card(
      color: Colors.white,
      elevation: 3,
      child: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Text("Connexion ADMIN"),
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam"),
          ),
          Padding(
            padding: EdgeInsets.all(8.0),
            child: TextField(
              controller: email,
              obscureText: false,
              decoration: InputDecoration(
                  focusedBorder: OutlineInputBorder(
                      borderSide:
                          BorderSide(color: Color(0xFFFF616F), width: 2)),
                  border: OutlineInputBorder(
                    borderSide:
                        const BorderSide(color: Color(0xFFFFCBD0), width: 3.0),
                  ),
                  labelText: 'Email',
                  floatingLabelStyle: TextStyle(color: Colors.red)),
            ),
          ),
          Padding(
            padding: EdgeInsets.all(8.0),
            child: TextField(
              controller: password,
              obscureText: true,
              decoration: InputDecoration(
                focusedBorder: OutlineInputBorder(
                    borderSide: BorderSide(color: Color(0xFFFF616F), width: 2)),
                border: OutlineInputBorder(
                  borderSide:
                      const BorderSide(color: Color(0xFFFFEFFB), width: 3.0),
                ),
                labelText: 'Password',
              ),
            ),
          ),
          Align(
            alignment: Alignment.centerRight,
            child: Text(
              "Recovery password",
              style: TextStyle(color: Colors.red),
            ),
          ),
          Padding(
            padding: EdgeInsets.all(8.0),
            child: TextButton(
                onPressed: () async {
                  await logAdmin(email.text, password.text);


                  if(currentAdmin!=null){
                    Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => HomeView(),
                    ),
                  );
                  }
                  else{
                    print("Can't Connect");
                  }

                },
                child: Text("LOGIN")),
          )
        ],
      ),
    );
  }
}
