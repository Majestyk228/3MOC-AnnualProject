//
//  MainUsersView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 13/05/2022.
//

import SwiftUI

struct MainUsersView: View {
    @Binding var  user:User
    var body: some View {
        Color.normalColor
            .ignoresSafeArea() // Ignore just for the color
            .overlay(
                VStack{
                    Text("Hello, World!")
                    Button(action: {
                        
                    }) {
                        /*@START_MENU_TOKEN@*/Text("Button")/*@END_MENU_TOKEN@*/
                    }
                }
                
                
            )
    }
}

struct MainUsersView_Previews: PreviewProvider {
    @State static var userTest = User(userId: 3, userMail: "torresdacosta@myges.fr", userPassword: "Torres", communityId: 2, communityTitle: "ESGI")
    static var previews: some View {
        MainUsersView(user: $userTest)
    }
}
