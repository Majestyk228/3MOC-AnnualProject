import 'package:exprimons_nous/loginform.dart';
import 'package:flutter/material.dart';

class Login extends StatelessWidget {
  const Login({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Expanded(
          child: Container(
            color: const Color(0xFFFF616F),
            height: double.infinity, //test
            width: double.infinity,
            child: const FractionallySizedBox(
              widthFactor: 0.7,
              heightFactor: 0.6,
              child: Image(

                image: AssetImage('assets/images/logo.png'),
              ),
            ), //test
          ),
        ),
        Expanded(
          child: Container(
            color: Colors.white,
            height: double.infinity, //test
            width: double.infinity,
            child: FractionallySizedBox(
              widthFactor: 0.7,
              heightFactor: 0.5,
              child: Center(child: LoginForm()),
            ), //test
          ),
        ),

      ],
    );
  }
}
