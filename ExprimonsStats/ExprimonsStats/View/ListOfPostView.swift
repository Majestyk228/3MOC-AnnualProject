//
//  ListOfPostView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 30/08/2022.
//

import SwiftUI

struct ListOfPostView: View {
    @State var alert:Bool=false
    @Binding var isConnected: Bool
    var posts:[Post]
    var body: some View{
        Color.normalColor
            .ignoresSafeArea()
            .overlay(
                VStack(spacing:100){
                    
                    Text("Historique de tout les posts")
                        .font(.system(size: 36))
                    
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                     
                    
                    
                    ScrollView{
                        VStack(spacing:10){
                            
                            
                            ForEach(posts,id: \.id){post in
                                NavigationLink(destination:DetailsPostView(isConnected: $isConnected, post: post)){
                                HStack{
                                    Image(systemName: "person.fill")
                                        .font(.system(size: 52))
                                    Spacer()
                                    Text(post.title ?? "Loading").font(.system(size: 36))
                                    Spacer()
                                    
                                }.padding(.horizontal, 50.0).frame(height: 100.0).background(Color.white).cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                            }
                            }
                             
                        }
                        .padding(.horizontal, 25.0)
                    }
                        
                        
                        
                    

                }
                
            ).onAppear(perform: {
                
            })
    }
    
}

struct ListOfPostView_Previews: PreviewProvider {
    @State static var posts:[Post] = [Post(idPost: 1, title: "Title", body: "Body", date: "2022-02-02", time: "00-00-00", likes: 2, dislikes: 2, idCommunity: 2, idUser: 2, idAdmin: 2, reported: 2)]
    @State static var isConnected:Bool = true
    static var previews: some View {
        ListOfPostView(isConnected: $isConnected, posts: posts)
    }
}
