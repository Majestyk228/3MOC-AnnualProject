import 'package:exprimons_nous/Colors.dart';
import 'package:flutter/material.dart';

class DashboardView extends StatelessWidget {
  const DashboardView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: double.infinity,
      color: ultraLightRedColor,
      child: Column(
        children: [
          Row(
            children: [
              Card(
                child: Column(
                  children: [
                    Container(
                      height: 50,
                      width: 200,
                      child: Center(child: Text("5",style: TextStyle(fontSize: 32),)),
                    ),
                    Container(
                      height: 150,
                      width: 200,
                      child: Center(child: Text("Reported Post")),
                    ),
                  ],
                ),
              ),
              Card(
                child: Column(
                  children: [
                    Container(
                      height: 100,
                      width: 200,
                      child: Center(child: Text("5")),
                    ),
                    Container(
                      height: 100,
                      width: 200,
                      child: Center(child: Text("Reported Comment")),
                    ),
                  ],
                ),
              ),
            ],
          ),
          Row(
            children: [
              Card(
                child: Column(
                  children: [
                    Container(
                      height: 50,
                      width: 200,
                      child: Center(child: Text("5")),
                    ),
                    Container(
                      height: 150,
                      width: 200,
                      child: Center(child: Text("Reported Post")),
                    ),
                  ],
                ),
              ),
              Card(
                child: Column(
                  children: [
                    Container(
                      height: 50,
                      width: 200,
                      child: Center(child: Text("5")),
                    ),
                    Container(
                      height: 150,
                      width: 200,
                      child: Center(child: Text("Reported Post")),
                    ),
                  ],
                ),
              ),
              Card(
                child: Column(
                  children: [
                    Container(
                      height: 50,
                      width: 200,
                      child: Center(child: Text("5")),
                    ),
                    Container(
                      height: 150,
                      width: 200,
                      child: Center(child: Text("Reported Post")),
                    ),
                  ],
                ),
              ),
            ],
          )
        ],
      ),
    );
  }
}
