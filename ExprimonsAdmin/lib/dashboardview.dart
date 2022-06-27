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
                child: Text("UWU"),
              ),
              Card(
                child: Text("UWU"),
              ),
              Card(
                child: Text("UWU"),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
