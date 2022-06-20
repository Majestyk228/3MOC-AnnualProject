import 'package:flutter/material.dart';

class Login extends StatelessWidget {
  const Login({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Expanded(
          child: Container(
            color: Colors.red,
            height: 100,            //test
            width: 100,             //test
          ),
        ),
        Expanded(
          child: Container(
            color: Colors.green,
            height: 100,            //test
            width: double.infinity, //test
          ),
        ),
      ],
    );
  }
}
