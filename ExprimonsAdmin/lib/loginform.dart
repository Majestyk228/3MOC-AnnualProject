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
  //String gender = 'Choisisez';

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
          Padding(
            padding: EdgeInsets.all(8.0),
            child: TextButton(
                onPressed: () async {

                  //print(gender);



                  await logAdmin(email.text, password.text);

                  if (currentAdmin.idCommunity == null) {
                    showDialog(
                      context: context,
                      builder: (BuildContext context) => AlertDialog(
                        title: const Text('Alerte'),
                        content: const Text(
                            'votre email ou votre mot de passe est erroné'),
                        actions: <Widget>[
                          TextButton(
                            onPressed: () async {
                              Navigator.pop(context, 'ok');
                            },
                            child: const Text('Ok'),
                          ),
                        ],
                      ),
                    );
                  } else {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => HomeView(),
                      ),
                    );
                  }


                },
                child: Text("LOGIN")),
          ),
         /* DropdownButton(
            value: gender,
            icon: const Icon(Icons.arrow_downward),
            elevation: 16,
            style: const TextStyle(color: Colors.deepPurple),
            underline: Container(
              height: 2,
              color: Colors.deepPurpleAccent,
            ),
            onChanged: (String? newValue) {
              setState(() {
                gender = newValue!;
              });
            },
            items: <String>['Choisisez','Homme', 'Femme', 'Autres']
                .map<DropdownMenuItem<String>>((String value) {
              return DropdownMenuItem<String>(
                value: value,
                child: Text(value),
              );
            }).toList(),
          ),*/

        ],
      ),
    );
  }
}







class MyStatefulWidget extends StatefulWidget {
  const MyStatefulWidget({Key? key, this.restorationId}) : super(key: key);

  final String? restorationId;

  @override
  State<MyStatefulWidget> createState() => _MyStatefulWidgetState();
}

/// RestorationProperty objects can be used because of RestorationMixin.
class _MyStatefulWidgetState extends State<MyStatefulWidget>
    with RestorationMixin {
  // In this example, the restoration ID for the mixin is passed in through
  // the [StatefulWidget]'s constructor.
  @override
  String? get restorationId => widget.restorationId;

  final RestorableDateTime _selectedDate =
  RestorableDateTime(DateTime(2021, 7, 25));
  late final RestorableRouteFuture<DateTime?> _restorableDatePickerRouteFuture =
  RestorableRouteFuture<DateTime?>(
    onComplete: _selectDate,
    onPresent: (NavigatorState navigator, Object? arguments) {
      return navigator.restorablePush(
        _datePickerRoute,
        arguments: _selectedDate.value.millisecondsSinceEpoch,
      );
    },
  );

  static Route<DateTime> _datePickerRoute(
      BuildContext context,
      Object? arguments,
      ) {
    return DialogRoute<DateTime>(
      context: context,
      builder: (BuildContext context) {
        return DatePickerDialog(
          restorationId: 'date_picker_dialog',
          initialEntryMode: DatePickerEntryMode.calendarOnly,
          initialDate: DateTime.fromMillisecondsSinceEpoch(arguments! as int),
          firstDate: DateTime(2021),
          lastDate: DateTime(2022),
        );
      },
    );
  }

  @override
  void restoreState(RestorationBucket? oldBucket, bool initialRestore) {
    registerForRestoration(_selectedDate, 'selected_date');
    registerForRestoration(
        _restorableDatePickerRouteFuture, 'date_picker_route_future');
  }

  void _selectDate(DateTime? newSelectedDate) {
    if (newSelectedDate != null) {
      setState(() {
        _selectedDate.value = newSelectedDate;
        ScaffoldMessenger.of(context).showSnackBar(SnackBar(
          content: Text(
              'Selected: ${_selectedDate.value.day}/${_selectedDate.value.month}/${_selectedDate.value.year}'),
        ));
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: OutlinedButton(
          onPressed: () {
            _restorableDatePickerRouteFuture.present();
          },
          child: const Text('Open Date Picker'),
        ),
      ),
    );
  }
}


