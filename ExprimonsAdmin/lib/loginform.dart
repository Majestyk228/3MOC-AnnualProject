import 'package:flutter/material.dart';

class LoginForm extends StatelessWidget {
  const LoginForm({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Card(
      color: Colors.white,
      elevation: 3,
      child: Column(
        children:  [
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
            child: TextButton(onPressed: () {}, child: Text("LOGIN")),
          )

        ],
      ),
    );
  }
}
