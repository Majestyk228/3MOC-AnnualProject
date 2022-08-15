import 'package:exprimons_nous/homeview.dart';
import 'package:exprimons_nous/loginview.dart';
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'dart:html' as html;
void main() {
  var home;
  print(html.window.localStorage["idAdmin"]);
  print(html.window.localStorage["idCommunity"]);
  print(html.window.localStorage["token"]);
  if (html.window.localStorage["idAdmin"] != null ||
      html.window.localStorage["idCommunity"] != null ||
      html.window.localStorage["token"] != null){
     home = HomeView();
  }
  else{
     home =Login();
  }
  print(home);
  runApp( MyApp(home: home));
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key, this.home}) : super(key: key);
final home;
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      localizationsDelegates: [
        GlobalMaterialLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
      ],
      supportedLocales: [const Locale('en'), const Locale('fr')],
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: home,
    );
  }
}
