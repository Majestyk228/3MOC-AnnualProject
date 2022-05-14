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
    @State private var user = User(userId: -1,userMail: "", userPassword: "", communityId: -1, communityTitle: "")
    init() {
        UITabBar.appearance().backgroundColor = UIColor(Color.lightColor)
        
    }
    var body: some View {
        if(user.userId == -1){
            
            ConnexionView(user:self.$user)
            
        }
        else{
            
                TabView {
                    DashboardView(user: self.$user)
                        .tabItem {
                                Image(systemName: "percent")
                                .font(.system(size: 3000))
                                
                               
                        }
                    MainVoteView(user: self.$user)
                        .tabItem {
                                Image(systemName: "tray.and.arrow.down")
                                .font(.system(size: 3000))
                                
                               
                        }
                    MainUsersView(user: self.$user)
                        .tabItem {
                                Image(systemName: "person.fill")
                                .font(.system(size: 3000))
                                
                               
                        }
                    MainPostView(user: self.$user)
                        .tabItem {
                                Image(systemName: "list.bullet.rectangle.fill")
                                .font(.system(size: 3000))
                                
                               
                        }
                        
                }.accentColor(.black).font(.system(size: 300))
                
            
                
            
        }
        
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
