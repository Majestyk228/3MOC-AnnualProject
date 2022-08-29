//
//  DetailsPostView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 28/08/2022.
//

import SwiftUI

struct DetailsPostView: View {
    @Binding var isConnected: Bool
     var post:Post
    @State var nbComment:Int=0
    
    var body: some View {
        Color.normalColor
            .ignoresSafeArea()
            .overlay(
                VStack(spacing:100){
                    
                    Text(post.title ?? "Loading")
                        .font(.system(size: 36))
                    
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                     
                    
                    
                        VStack{
                            HStack(){
                                Text("Corps du post :")
                                    .font(.system(size: 24))
                                Text(post.body ?? "Loading").font(.system(size: 24))
                            }.frame(maxWidth:.infinity,alignment: .leading).padding(.all,10)
                            HStack(){
                                Text("Date :")
                                    .font(.system(size: 24))
                                Text(post.date ?? "Loading").font(.system(size: 24))
                            }.frame(maxWidth:.infinity,alignment: .leading).padding(.all,10)
                            HStack(){
                                Text("Like :")
                                    .font(.system(size: 24))
                                Text("\(post.likes ?? 0)").font(.system(size: 24))
                            }.frame(maxWidth:.infinity,alignment: .leading).padding(.all,10)
                            HStack(){
                                Text("Dislike :")
                                    .font(.system(size: 24))
                                Text("\(post.dislikes ?? 0)").font(.system(size: 24))
                            }.frame(maxWidth:.infinity,alignment: .leading).padding(.all,10)
                            
                            Button(action: {
                                
                            }){
                                Text("\(nbComment) Commentaires")
                            }.frame(width: 400, height: 60)
                                .background(Color.ligthColor2)
                                .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                                .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                             
                        }
                        .frame(width: 500, height: 500)
                        
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                        
                        
                        
                    

                }
                
            ).onAppear(perform: {
                
            })
    }
}

struct DetailsPostView_Previews: PreviewProvider {
    @State static var post:Post = Post(idPost: 1, title: "Titre du post", body: "Corps du post", date: "2022-02-13", time: "00-00-00", likes: 2, dislikes: 4, idCommunity: 2, idUser: 2, idAdmin: 2, reported: 2)
    @State static var isConnected:Bool=true
    static var previews: some View {
        DetailsPostView(isConnected:$isConnected , post: post)
    }
}
