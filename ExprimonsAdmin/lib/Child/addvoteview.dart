import 'package:exprimons_nous/Colors.dart';
import 'package:flutter/material.dart';

class AddVoteView extends StatefulWidget {
  const AddVoteView({Key? key}) : super(key: key);

  @override
  State<AddVoteView> createState() => _AddVoteViewState();
}

class _AddVoteViewState extends State<AddVoteView> {
  late TextEditingController title;
  late TextEditingController body;
  late TextEditingController nbChoice;
  late TextEditingController important;



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
              height: 300,
              child: Card(
                elevation: 10,
                child: Column(
                  children: [
                    TextField(
                      controller: title,
                      autocorrect: true,
                      decoration: InputDecoration(hintText: 'Enter Class Here'),
                    )
                  ],
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}
