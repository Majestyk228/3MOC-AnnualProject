//
//  MainUsersView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 13/05/2022.
//

import SwiftUI

struct MainUsersView: View {
    @Binding var isConnected: Bool
    var listBestUser=[
        BestUser( userName: "Michelle"),
        BestUser( userName: "Claude"),
        BestUser( userName: "Jacqueline")
    ]
    var listAllUser=[
        ListofUser(userName: "Michelle"),
        ListofUser(userName: "Claude"),
        ListofUser(userName: "Jacqueline"),
        ListofUser(userName: "Lucifer"),
        ListofUser(userName: "Francis"),
        ListofUser(userName: "Martin"),
        ListofUser(userName: "Antoine"),
        ListofUser(userName: "Kevin"),
        ListofUser(userName: "Lucas"),
        ]
    @State private var userdetail = 1
    
    var body: some View {
        Color.normalColor
            .ignoresSafeArea()
            .edgesIgnoringSafeArea(.all)// Ignore just for the color
            .overlay(
                
                
                VStack(spacing:50){
                    Text("Utilisateurs")
                        .font(.system(size: 48))
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                    VStack(spacing:30){
                        Text("Meilleurs Utilisateurs")
                            .font(.system(size: 36))
                            .foregroundColor(Color.white)
                            .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                            .background(Color.darkColor)
                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                        HStack{
                            NavigationLink(destination:DetailsUserView(isConnected:$isConnected,detailUser: $userdetail)) {
                                VStack{
                                    Image(systemName: "person.fill")
                                        .foregroundColor(Color.black)
                                        .font(.system(size: 100))
                                    
                                    Text(listBestUser[0].userName)
                                        .font(.system(size:36))
                                        .foregroundColor(Color.black)
                                }
                                
                            }
                            .frame(width: 200, height: 250)
                            .background(Color.lightColor)
                            .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                            NavigationLink(destination:DetailsUserView(isConnected:$isConnected,detailUser: $userdetail)) {
                                VStack{
                                    Image(systemName: "person.fill")
                                        .foregroundColor(Color.black)
                                        .font(.system(size: 100))
                                    
                                    Text(listBestUser[1].userName)
                                        .font(.system(size:36))
                                        .foregroundColor(Color.black)
                                }
                                
                            }
                            .frame(width: 200, height: 250)
                            .background(Color.lightColor)
                            .cornerRadius(50.0)
                            NavigationLink(destination:DetailsUserView(isConnected:$isConnected,detailUser: $userdetail)) {
                                VStack{
                                    Image(systemName: "person.fill")
                                        .foregroundColor(Color.black)
                                        .font(.system(size: 100))
                                    
                                    Text(listBestUser[2].userName)
                                        .font(.system(size:36))
                                        .foregroundColor(Color.black)
                                }
                                
                            }
                            .frame(width: 200, height: 250)
                            .background(Color.lightColor)
                            .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                            
                            
                            
                            
                        }
                        
                        
                 
                    }
                    .frame(width: 700, height: 400.0)
                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                    .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                    Text("Liste des Utilisateurs")
                        .font(.system(size: 48))
                    ScrollView{
                        VStack(spacing:10){
                            
                            
                            ForEach(listAllUser){user in
                                HStack{
                                    Image(systemName: "person.fill")
                                        .font(.system(size: 52))
                                    Spacer()
                                    Text(user.userName).font(.system(size: 36))
                                    Spacer()
                                    NavigationLink(destination:DetailsUserView(isConnected:$isConnected,detailUser: $userdetail)) {
                                    Image(systemName: "chevron.right")
                                        .font(.system(size: 52))
                                    }
                                }.padding(.horizontal, 50.0).frame(height: 100.0).background(Color.white).cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                            }
                        }
                        .padding(.horizontal, 25.0)
                    }
                
                
                }
                    
                
                
            )
    }
}

struct MainUsersView_Previews: PreviewProvider {
    @State static var isConnected=true
    static var previews: some View {
        MainUsersView(isConnected: $isConnected)
    }
}
