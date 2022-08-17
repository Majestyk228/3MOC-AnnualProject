//
//  DashBoard.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 17/04/2022.
//

import SwiftUI

struct DashboardView: View {
    
    @StateObject var dashboardStats = DashboardStats()
    
    
    var body: some View {
        
        
        Color.normalColor
            .ignoresSafeArea()
            .overlay(
                VStack(spacing:150){
                    Text("Community Title")
                        .font(.system(size: 36))
                    
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                    
                    HStack(spacing: 150){
                        VStack{
                            Text("Utilisateurs")
                                .font(.system(size: 24))
                            Text("320")
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
                            Text("Points Totals")
                                .font(.system(size: 24))
                            Text("12394")
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
                    .frame(width: /*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/, height: /*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                    HStack(spacing:150){
                        VStack{
                            Text("Posts")
                                .font(.system(size: 24))
                            Text("52")
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
                            Text("Votes")
                                .font(.system(size: 24))
                            Text("7")
                                .font(.system(size: 36))
                                .frame(width: 150.0, height: 150.0)
                                .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                        }
                        .frame(width: 250.0, height: 250.0)
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                        .onAppear{
                            dashboardStats.fetch()
                        }
                    }
                    
                    
                    
                }
                    
                
            )
    
        
        
        
        
    }
}

struct DashBoard_Previews: PreviewProvider {
    
    
    static var previews: some View {
        DashboardView()
    }
}
