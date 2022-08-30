//
//  ListOfVoteView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 30/08/2022.
//

import SwiftUI

struct ListOfVoteView: View {
    @State var alert:Bool=false
    @Binding var isConnected: Bool
    var votes:[Vote]
    var body: some View{
        Color.normalColor
            .ignoresSafeArea()
            .overlay(
                VStack(spacing:100){
                    
                    Text("Historique des votes")
                        .font(.system(size: 36))
                    
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                     
                    
                    
                    ScrollView{
                        VStack(spacing:10){
                            
                            
                            ForEach(votes,id: \.id){vote in
                                NavigationLink(destination:DetailsVoteView(isConnected: $isConnected, vote: vote)){
                                HStack{
                                    Image(systemName: "tray.and.arrow.down").font(.system(size: 25))
                                    Spacer()
                                    Text("Titre: \(vote.title ?? "Loading")").font(.system(size: 36))
                                    Spacer()
                                    if(vote.important == 0){
                                        Text("Vote non important")
                                            .font(.system(size: 22))
                                            .foregroundColor(Color.white)
                                            .padding(EdgeInsets(top: 10, leading: 10, bottom: 10, trailing: 10) )
                                            .background(Color.darkColor)
                                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                                    }else{
                                        Text("Vote important")
                                            .font(.system(size: 22))
                                            .foregroundColor(Color.white)
                                            .padding(EdgeInsets(top: 10, leading: 10, bottom: 10, trailing: 10) )
                                            .background(Color.darkColor)
                                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                                    }
                                    
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

struct ListOfVoteView_Previews: PreviewProvider {
    @State static var votes:[Vote]=[Vote(idVote: 1, title: "Title", body: "Body", nbChoice: 2, important: 2, idAdmin: 2, voteBegins: "2022-02-02", voteEnds: "2022-02-09", idCommunity: 2)]
    @State static var isConnected:Bool = true
    static var previews: some View {
        ListOfVoteView(isConnected: $isConnected, votes: votes)
    }
}
