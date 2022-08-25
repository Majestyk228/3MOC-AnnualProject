//
//  ConnectedView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 25/08/2022.
//

import SwiftUI

struct ConnectedView: View {
    
    var body: some View {
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

struct ConnectedView_Previews: PreviewProvider {
    
    static var previews: some View {
        ConnectedView()
    }
}
