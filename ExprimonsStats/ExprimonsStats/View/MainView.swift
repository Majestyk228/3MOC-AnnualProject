//
//  MainView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 26/04/2022.
//

import SwiftUI

extension Color{
    static let normalColor=Color(red: 102/255, green: 158/255, blue: 55/255)
    static let darkColor=Color(red: 54/255, green: 111/255, blue: 0/255)
    static let lightColor=Color(red: 151/255, green: 207/255, blue: 101/255)
    static let ligthColor2=Color(red: 113/255, green: 204/255, blue: 27/255)
}
struct MainView: View {
    
    
    
    init() {
        UITabBar.appearance().backgroundColor = UIColor(Color.lightColor)
        /*
        if let bundleID = Bundle.main.bundleIdentifier {
            UserDefaults.standard.removePersistentDomain(forName: bundleID)
        }
         */
    }
    var body: some View {
        if(UserDefaults.standard.integer(forKey: "idAdmin") == 0){
            
            ConnexionView()
            
        }
        else{
            
                TabView {
                    DashboardView()
                        .tabItem {
                                Image(systemName: "percent")
                                
                                
                               
                        }
                    MainVoteView()
                        .tabItem {
                                Image(systemName: "tray.and.arrow.down")
                                
                                
                               
                        }
                    NavigationView{
                        MainUsersView()}.navigationViewStyle(StackNavigationViewStyle())
                        .tabItem {
                                Image(systemName: "person.fill")
                                
                                
                               
                        }
                    
                    MainPostView()
                        .tabItem {
                                Image(systemName: "list.bullet.rectangle.fill")
                                
                                
                               
                        }
                    
                        
                }.accentColor(.black)
                
                
                
            
                
            
        }
        
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
