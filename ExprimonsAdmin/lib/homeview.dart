import 'package:exprimons_nous/Colors.dart';
import 'package:exprimons_nous/dashboardview.dart';
import 'package:exprimons_nous/postview.dart';
import 'package:exprimons_nous/userview.dart';
import 'package:exprimons_nous/voteview.dart';
import 'package:flutter/material.dart';
import 'package:side_navigation/side_navigation.dart';

class HomeView extends StatefulWidget {
  const HomeView({Key? key}) : super(key: key);

  @override
  State<HomeView> createState() => _HomeViewState();
}

class _HomeViewState extends State<HomeView> {
  /// Views to display
  List<Widget> views = const [
    Center(
      child: DashboardView(),
    ),
    Center(
      child: PostView(),
    ),
    Center(
      child: VoteView(),
    ),
    Center(
      child: UserView(),
    ),
  ];

  /// The currently selected index of the bar
  int selectedIndex = 0;

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () async {
        return false;
      },
      child: Scaffold(
        /// You can use an AppBar if you want to
        //appBar: AppBar(
        //  title: const Text('App'),
        //),

        // The row is needed to display the current view
        body: Row(
          children: [
            /// Pretty similar to the BottomNavigationBar!
            SideNavigationBar(
              selectedIndex: selectedIndex,
              header: SideNavigationBarHeader(
                title: Text("Exprimons "),
                subtitle: Text("Admin"),
                image: Image(
                  image: AssetImage('assets/images/logo.png'),
                  width: 50,
                  height: 50,
                ),
              ),
              items: const [
                SideNavigationBarItem(
                  icon: Icons.dashboard,
                  label: 'Dashboard',
                ),
                SideNavigationBarItem(
                  icon: Icons.article,
                  label: 'Post',
                ),
                SideNavigationBarItem(
                  icon: Icons.how_to_vote,
                  label: 'Vote',
                ),
                SideNavigationBarItem(
                  icon: Icons.person,
                  label: 'User',
                ),
              ],
              onTap: (index) {
                setState(() {
                  selectedIndex = index;
                });
              },
              theme: SideNavigationBarTheme(
                backgroundColor: veryDarkRedColor,
                dividerTheme: SideNavigationBarDividerTheme.standard(),
                itemTheme: SideNavigationBarItemTheme(
                  unselectedItemColor: lightGray,
                  selectedItemColor: Colors.white,
                ),
                togglerTheme: SideNavigationBarTogglerTheme(
                  expandIconColor: Colors.blue,
                  shrinkIconColor: Colors.green,
                ),
              ),
            ),

            /// Make it take the rest of the available width
            Expanded(
              child: views.elementAt(selectedIndex),
            )
          ],
        ),
      ),
    );
  }
}
