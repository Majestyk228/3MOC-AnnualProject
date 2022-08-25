//
//  MainPostView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 13/05/2022.
//

import SwiftUI

struct MainPostView: View {
    @Binding var isConnected: Bool
    var currentPostList=[
        currentPost(titlePost: "Le marché", like: 12, nbComment: 2),
        currentPost(titlePost: "30/03", like: 32, nbComment: 10),
        currentPost(titlePost: "Malbouffe", like: 123, nbComment: 54),
        currentPost(titlePost: "Sport", like: 15, nbComment: 15),
        currentPost(titlePost: "Chauffard", like: 321, nbComment: 10),
        currentPost(titlePost: "Élection", like: 49, nbComment: 6),
        currentPost(titlePost: "Compétition", like: 301, nbComment: 1024),
    ]
    @State private var showingSheet = false
    var body: some View {
        Color.normalColor
            .ignoresSafeArea()
            .edgesIgnoringSafeArea(.all)// Ignore just for the color
            .overlay(
                
            
                VStack(spacing:100){
                    Text("Post")
                        .font(.system(size: 48))
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                    VStack(spacing:30){
                        Text("Post en cours")
                            .font(.system(size: 36))
                            .foregroundColor(Color.white)
                            .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                            .background(Color.darkColor)
                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                        
                        ScrollView(.horizontal){
                            HStack(spacing:20){
                                ForEach(currentPostList) {curPost in
                                    VStack{
                                        Text(curPost.titlePost)
                                            .font(.system(size: 36))
                                            .foregroundColor(Color.white)
                                            .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                                            .background(Color.darkColor)
                                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                                        
                                        HStack{
                                            VStack{
                                            Text("\(curPost.like)")
                                                .font(.system(size: 36))
                                                
                                            Image(systemName: "hand.thumbsup.circle")
                                                    .font(.system(size: 50))
                                            }
                                            .foregroundColor(Color.black)
                                            .frame(width: 110.0, height: 140.0)
                                            .background(Color.lightColor)
                                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                                            Spacer()
                                            VStack{
                                            Text("\(curPost.nbComment)")
                                                .font(.system(size: 36))
                                                
                                            Image(systemName: "note.text.badge.plus")
                                                    .font(.system(size: 50))
                                            }
                                            .foregroundColor(Color.black)
                                            .frame(width: 110.0, height: 140.0)
                                            .background(Color.lightColor)
                                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                                            
                                            
                                        }
                                        .padding(.horizontal, 15.0)
                                        .frame(width: 280.0, height: 150.0)
                                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                                        .cornerRadius(25)
                                        
                                        Button(action: {
                                            
                                        }) {
                                            Text("Details")
                                                .font(.system(size:36))
                                                .foregroundColor(Color.black)
                                        }
                                        .frame(width: 200.0, height: 60)
                                        .background(Color.ligthColor2)
                                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                                        
                                        
                                        
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
                        SheetPostView()
                                
                                
                        
                    }
                }
            
                    
                
            )
    }
}

struct MainPostView_Previews: PreviewProvider {
    @State static var isConnected=true
    static var previews: some View {
        MainPostView(isConnected: $isConnected)
    }
}
