//
//  DetailsUserView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 20/05/2022.
//

import SwiftUI
var listAllUser=[
    DetailsUser(userId: 1, userName: "Jacqueline", userPoints: 132, Post: 10, Commentaire: 203),
    DetailsUser(userId: 1, userName: "Jacqueline", userPoints: 132, Post: 10, Commentaire: 203),
    
    ]
struct DetailsUserView: View {
    @Binding var isConnected: Bool
    
    
    @Binding var detailUser:Int
    
    var user=listAllUser[1]
    
    
    
    
    var body: some View {
        
        
        
        Color.normalColor
            .ignoresSafeArea()
            .overlay(
                VStack(spacing:100){
                    Text(user.userName)
                        .font(.system(size: 36))
                    
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                    
                    HStack{
                        VStack{
                            Text("Points")
                                .font(.system(size: 24))
                            Text("\(user.userPoints)")
                                .font(.system(size: 36))
                                .frame(width: 150.0, height: 150.0)
                                .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                        }
                        .frame(width: 250.0, height: 250.0)
                        
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                        
                        
                        
                    }
                    
                    HStack(spacing:150){
                        VStack{
                            Text("Posts")
                                .font(.system(size: 24))
                            Text("\(user.Post)")
                                .font(.system(size: 36))
                                .frame(width: 150.0, height: 150.0)
                                .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                        }
                        .frame(width: 250.0, height: 250.0)
                        
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                        
                        VStack{
                            Text("Commentaires")
                                .font(.system(size: 24))
                            Text("\(user.Commentaire)")
                                .font(.system(size: 36))
                                .frame(width: 150.0, height: 150.0)
                                .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                        }
                        .frame(width: 250.0, height: 250.0)
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                    }
                    
                    
                    
                }
                
            )
    }
}

struct DetailsUserView_Previews: PreviewProvider {
    @State static var userdetailTest=1
    @State static var isConnected=true
    static var previews: some View {
        DetailsUserView(isConnected:$isConnected,detailUser: $userdetailTest)
    }
}
