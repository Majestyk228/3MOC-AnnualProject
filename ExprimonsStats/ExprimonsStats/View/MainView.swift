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
@available(iOS 15.0, *)
struct MainView: View {
    @State private var connected : Bool
    
    
    init() {
        UITabBar.appearance().backgroundColor = UIColor(Color.lightColor)
        if(UserDefaults.standard.integer(forKey: "idAdmin")==0){
            connected=false
        }
        else{
            connected=true
        }
       /*
        if let bundleID = Bundle.main.bundleIdentifier {
            UserDefaults.standard.removePersistentDomain(forName: bundleID)
        }
         */
        print(UserDefaults.standard.string(forKey: "token") ?? "ahbon")
         
    }
    var body: some View {
        
        if( connected == false){
            
            ConnexionView(isConnected: $connected)
            
        }
        else{
            
                TabView {
                    DashboardView(isConnected: $connected)
                        .tabItem {
                                Image(systemName: "percent")
                                
                                
                               
                        }
                    NavigationView{
                        MainVoteView(isConnected: $connected)}.navigationViewStyle(StackNavigationViewStyle())
                        .tabItem {
                                Image(systemName: "tray.and.arrow.down")
                                
                                
                               
                        }
                    NavigationView{
                        MainUsersView(isConnected: $connected)}.navigationViewStyle(StackNavigationViewStyle())
                        .tabItem {
                                Image(systemName: "person.fill")
                                
                                
                               
                        }
                    NavigationView{
                        MainPostView(isConnected: $connected)
                    }.navigationViewStyle(StackNavigationViewStyle())
                        .tabItem {
                                Image(systemName: "list.bullet.rectangle.fill")
                                
                                
                               
                        }
                    
                        
                }.accentColor(.black)
                
                
                
            
                
            
        }
        
    }
}

@available(iOS 15.0, *)
struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
