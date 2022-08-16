//
//  MainVoteView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 12/05/2022.
//

import SwiftUI

struct MainVoteView: View {
    @Binding var  user:User
    var titleVote = [
        Vote(title: "Test Vote", bestChoice: "1", Description: "c'est un vote qui permet de voter"),
        Vote(title: "Test", bestChoice: "2", Description: "c'est un vote qui permet de revoter"),
        Vote(title: "Test", bestChoice: "3", Description: "c'est un vote qui permet de revoter"),
        Vote(title: "Test", bestChoice: "2", Description: "c'est un vote qui permet de revoter"),
        Vote(title: "Test", bestChoice: "4", Description: "c'est un vote qui permet de revoter")
        
    ]
    @State private var showingSheet = false
    var body: some View {
        Color.normalColor
            .ignoresSafeArea()
            .edgesIgnoringSafeArea(.all)// Ignore just for the color
            .overlay(
                
            
                VStack(spacing:100){
                    Text("Vote")
                        .font(.system(size: 36))
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                    VStack(spacing:30){
                        Text("Votes en cours")
                            .font(.system(size: 36))
                            .foregroundColor(Color.white)
                            .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                            .background(Color.darkColor)
                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                        
                        ScrollView(.horizontal){
                            HStack(spacing:20){
                                ForEach(titleVote) {votetest in
                                    VStack{
                                        Text(votetest.title)
                                            .font(.system(size: 36))
                                            .foregroundColor(Color.white)
                                            .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                                            .background(Color.darkColor)
                                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                                        
                                        VStack{
                                            Text(votetest.bestChoice)
                                                .font(.system(size: 36))
                                                .foregroundColor(Color.white)
                                                .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                                                .background(Color.darkColor)
                                                .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                                            
                                            Text(votetest.Description)
                                                .font(.system(size: 24))
                                            
                                        }
                                        .frame(width: 280.0, height: 150.0)
                                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                                        .cornerRadius(25)
                                        
                                        
                                        
                                        
                                        
                                    }
                                    .frame(width: 300.0, height: 380.0)
                                    .background(Color.lightColor)
                                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                                    
                                }
                            }
                        }
                        .frame(height: nil)
                        
                    }
                    .frame(width: 700, height: 530)
                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                    .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                    
                    Button(action: {
                        showingSheet=true
                    }) {
                        Text("Voir l'historique")
                            .font(.system(size:36))
                            .foregroundColor(Color.black)
                    }
                    .frame(width: 400, height: 60)
                    .background(Color.ligthColor2)
                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                    .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                    .sheet(isPresented: $showingSheet) {
                        SheetVoteView()
                                
                                
                        
                    }
                }
            
                    
                
            )
   
    }
}

struct MainVoteView_Previews: PreviewProvider {
    @State static var userTest = User(userId: 3, userMail: "torresdacosta@myges.fr", userPassword: "Torres", communityId: 2, communityTitle: "ESGI")
    
    static var previews: some View {
        MainVoteView(user:$userTest)
    }
}
